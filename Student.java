import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String studentId;
    private String password;
    private int loyaltyPoints;

    public Student(String name, String studentId, String password) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.loyaltyPoints = 0; // Default when registering
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    // Increase points
    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    // Redeem points
    public void redeemLoyaltyPoints(int points) {
        if (points <= this.loyaltyPoints) {
            this.loyaltyPoints -= points;
        } else {
            System.out.println("Not enough points to redeem.");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + name + '\'' +
                ", Student ID='" + studentId + '\'' +
                ", Loyalty Points=" + loyaltyPoints +
                '}';
    }
}
