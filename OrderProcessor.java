import java.util.ArrayList;

public class OrderProcessor {
    private ArrayList<Order> orders;

    public OrderProcessor() {
        this.orders = new ArrayList<>();
    }

    public Order placeOrder() {
        Order order = new Order(orders.size() + 1);
        orders.add(order);
        return order;
    }

    public void addItemToOrder(Order order, MenuItem item) {
        order.addItem(item);
    }

    public void markOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
                break;
            }
        }
    }

    public void listPendingOrders() {
        System.out.println("Pending Orders:");
        for (Order order : orders) {
            if (order.getStatus().equalsIgnoreCase("Pending")) {
                System.out.println(order);
            }
        }
    }

    public void listAllOrders() {
        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
