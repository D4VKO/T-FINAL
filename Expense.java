public class Expense {
    private String date;
    private double amount;
    private String description;

    //izvade
    public Expense(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return date + " | " + amount + " EUR | " + description;
    }
}