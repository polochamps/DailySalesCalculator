package dailysales;

public class PhoneSales extends SalesItem {
    private int quantitySold;

    public PhoneSales(String itemName, double price, int quantitySold) {
        super(itemName, price);
        this.quantitySold = quantitySold;
    }

    @Override
    public double calculateTotal() {
        return price * quantitySold;
    }
}
