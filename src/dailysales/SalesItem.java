package dailysales;

abstract class SalesItem {
    protected String itemName;
    protected double price;

    public SalesItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public abstract double calculateTotal();
}