package dailysales;

public class RepairSales extends SalesItem {
    private int hoursWorked;

    public RepairSales(String itemName, double pricePerHour, int hoursWorked) {
        super(itemName, pricePerHour);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateTotal() {
        return price * hoursWorked;
    }
}