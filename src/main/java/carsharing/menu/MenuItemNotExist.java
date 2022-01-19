package carsharing.menu;

public class MenuItemNotExist extends RuntimeException{
    public MenuItemNotExist(int itemNumber) {
        super(String.format("Menu item %s does not exist", itemNumber));
    }
}
