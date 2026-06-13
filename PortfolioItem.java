public class PortfolioItem {
    private Stock stock;
    private int quantity;

    public PortfolioItem(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() { return stock; }
    public int getQuantity() { return quantity; }
    
    public void addShares(int count) { this.quantity += count; }
    public void removeShares(int count) { this.quantity -= count; }

    public double getTotalValue() {
        return stock.getCurrentPrice() * quantity;
    }
}