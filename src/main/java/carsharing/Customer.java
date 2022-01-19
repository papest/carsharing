package carsharing;

public class Customer implements Table{
    int id;
    String name;

    @Override
    public int getId() {
        return id;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s. %s", id, name);
    }
}