package carsharing;

public class Company implements Table{
    private int id;
    private String name;

    public Company(int id, String name)  {
        this.id = id;
        this.name = name;
    }
    @Override
    public int getId() {
        return id;
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
