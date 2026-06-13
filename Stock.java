import java.util.Random;

public class Stock {
    private String ticker;
    private String companyName;
    private double currentPrice;

    public Stock(String ticker, String companyName, double startingPrice) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.currentPrice = startingPrice;
    }

    // Getters
    public String getTicker() { return ticker; }
    public String getCompanyName() { return companyName; }
    public double getCurrentPrice() { return currentPrice; }

    /**
     * Simulates market volatility by randomly shifting the price up or down.
     */
    public void updateMarketPrice() {
        Random random = new Random();
        // Generates a percent change between -5% and +5%
        double percentChange = (random.nextDouble() * 10 - 5) / 100;
        this.currentPrice += this.currentPrice * percentChange;
        
        // Prevent stock from hitting zero or negative values
        if (this.currentPrice < 1.0) {
            this.currentPrice = 1.0;
        }
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | $%.2f", ticker, companyName, currentPrice);
    }
}
