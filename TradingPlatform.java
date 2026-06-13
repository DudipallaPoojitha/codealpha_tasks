import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TradingPlatform {
    private static Map<String, Stock> marketRegistry = new HashMap<>();
    private static Map<String, PortfolioItem> userPortfolio = new HashMap<>();
    private static double buyingPower = 10000.00; // Starting virtual cash balance

    public static void main(String[] args) {
        initializeMarket();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Welcome to the Alpha Traders Simulator ===");
        
        while (true) {
            // Simulate changing market conditions on every menu loop
            simulateMarketTick();
            
            System.out.println("\n--- TRADING DASHBOARD ---");
            System.out.printf("Available Virtual Cash: $%.2f\n", buyingPower);
            System.out.println("1. View Live Market Quotes");
            System.out.println("2. Buy Shares");
            System.out.println("3. Sell Shares");
            System.out.println("4. View My Portfolio Performance");
            System.out.println("5. Exit Market");
            System.out.print("Select an operation: ");
            
            int action = scanner.nextInt();
            scanner.nextLine(); // Clear scanner buffer
            
            if (action == 1) {
                displayMarketData();
            } else if (action == 2) {
                executeBuy(scanner);
            } else if (action == 3) {
                executeSell(scanner);
            } else if (action == 4) {
                displayPortfolioReport();
            } else if (action == 5) {
                System.out.println("Closing trade desk. Thank you for investing with us!");
                break;
            } else {
                System.out.println("Invalid selection. Please choose options 1 through 5.");
            }
        }
        scanner.close();
    }

    private static void initializeMarket() {
        marketRegistry.put("AAPL", new Stock("AAPL", "Apple Inc.", 175.50));
        marketRegistry.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 150.25));
        marketRegistry.put("TSLA", new Stock("TSLA", "Tesla Motors", 185.10));
        marketRegistry.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 178.00));
        marketRegistry.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 420.75));
    }

    private static void simulateMarketTick() {
        for (Stock stock : marketRegistry.values()) {
            stock.updateMarketPrice();
        }
    }

    private static void displayMarketData() {
        System.out.println("\n=========================================");
        System.out.println("TICKER | COMPANY NAME         | PRICE    ");
        System.out.println("=========================================");
        for (Stock stock : marketRegistry.values()) {
            System.out.println(stock);
        }
        System.out.println("=========================================");
    }

    private static void executeBuy(Scanner scanner) {
        displayMarketData();
        System.out.print("\nEnter the stock ticker you want to buy: ");
        String ticker = scanner.nextLine().toUpperCase();
        
        if (!marketRegistry.containsKey(ticker)) {
            System.out.println("Error: Ticker symbol not found on this exchange.");
            return;
        }
        
        Stock targetStock = marketRegistry.get(ticker);
        System.out.print("Enter quantity of shares to purchase: ");
        int sharesToBuy = scanner.nextInt();
        
        double transactionCost = targetStock.getCurrentPrice() * sharesToBuy;
        if (transactionCost > buyingPower) {
            System.out.printf("Transaction denied: Insufficient funds. Required: $%.2f\n", transactionCost);
        } else {
            buyingPower -= transactionCost;
            userPortfolio.putIfAbsent(ticker, new PortfolioItem(targetStock, 0));
            userPortfolio.get(ticker).addShares(sharesToBuy);
            System.out.printf("Success! Purchased %d shares of %s.\n", sharesToBuy, ticker);
        }
    }

    private static void executeSell(Scanner scanner) {
        if (userPortfolio.isEmpty()) {
            System.out.println("\nYou do not own any shares to sell yet.");
            return;
        }
        
        displayPortfolioReport();
        System.out.print("\nEnter the ticker you want to sell: ");
        String ticker = scanner.nextLine().toUpperCase();
        
        if (!userPortfolio.containsKey(ticker)) {
            System.out.println("Error: You do not own any positions in this asset.");
            return;
        }
        
        PortfolioItem holding = userPortfolio.get(ticker);
        System.out.printf("You own %d shares. How many would you like to sell?: ", holding.getQuantity());
        int sharesToSell = scanner.nextInt();
        
        if (sharesToSell > holding.getQuantity() || sharesToSell <= 0) {
            System.out.println("Error: Invalid share count requested.");
        } else {
            double revenue = holding.getStock().getCurrentPrice() * sharesToSell;
            buyingPower += revenue;
            holding.removeShares(sharesToSell);
            
            // Clean up portfolio map if position is totally liquidated
            if (holding.getQuantity() == 0) {
                userPortfolio.remove(ticker);
            }
            
            System.out.printf("Success! Sold %d shares for $%.2f.\n", sharesToSell, revenue);
        }
    }

    private static void displayPortfolioReport() {
        System.out.println("\n==============================================");
        System.out.println("              CURRENT PORTFOLIO               ");
        System.out.println("==============================================");
        if (userPortfolio.isEmpty()) {
            System.out.println(" No active positions held.");
        } else {
            double aggregatePortfolioValue = 0;
            for (PortfolioItem item : userPortfolio.values()) {
                System.out.printf("%s: %d Shares | Current Position Value: $%.2f\n", 
                        item.getStock().getTicker(), item.getQuantity(), item.getTotalValue());
                aggregatePortfolioValue += item.getTotalValue();
            }
            System.out.println("----------------------------------------------");
            System.out.printf("Total Equity Assets : $%.2f\n", aggregatePortfolioValue);
            System.out.printf("Total Net Worth     : $%.2f\n", (aggregatePortfolioValue + buyingPower));
        }
        System.out.println("==============================================");
    }
}