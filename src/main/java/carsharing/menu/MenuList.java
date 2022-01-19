package carsharing.menu;

import carsharing.Operation;
import carsharing.Table;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class MenuList<T extends Table> {

    ArrayList<T> menuList;
    Operation listOperation;
    Menu menu = null;
    int listPosition = 0;

    public MenuList(ArrayList<T> objectList, Operation listOperation, ArrayList<MenuItem> menuItems, String menuName) {

        this.menuList = objectList;
        this.listOperation = listOperation;
        if (menuItems != null && !menuItems.isEmpty()) {
            this.menu = new Menu(menuItems, menuName);
        }
    }


    public String getAllText() {
        return menuList.stream().map(T::toString).collect(Collectors.joining("\n")) + "\n"
                + (menu != null ? menu.getAllText() : "");
    }

    public Object getObject(int number) {
       T object = menuList.stream().filter(t -> t.getId() == number).findFirst().orElse(null);
       if (object != null) {
           return object;
       }
       return menu.getOperationByItemNumber(number);


    }
}
