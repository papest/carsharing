package carsharing;

public class MenuItem {
    private Operation operation;
    private int number;

    public MenuItem(Operation operation, int number) {
        this.operation = operation;
        this.number = number;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getNumber() {
        return number;
    }
}
