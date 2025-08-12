import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String description;
    private double price;
    private String category;

    public MenuItem(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }


    @Override
    public String toString() {
        return "Name: " + name +
                "\nCategory: " + category +
                "\nPrice: " + String.format("%.2f EGP", price) +
                "\nDescription: " + description;
    }

}
