import java.util.ArrayList;

public class Order {
    private int orderId;
    private ArrayList<MenuItem> items;
    private String status;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double sum = 0.0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");
        for (MenuItem item : items) {
            sb.append("- ").append(item).append("\n");
        }
        sb.append("Total: ").append(calculateTotal()).append("\n");
        return sb.toString();
    }
}
