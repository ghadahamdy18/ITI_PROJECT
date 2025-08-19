import java.util.List;

public interface IMenuProvider {
    void addMenuItem(MenuItem item);
    boolean editMenuItem(String name, String newDescription, double newPrice, String newCategory);
    boolean removeMenuItem(String name);
    void displayMenu();
    void saveMenuToFile(String filePath);
    void loadMenuFromFile(String filePath);
    List<MenuItem> getAllMenuItems(); // for access without breaking encapsulation
}
