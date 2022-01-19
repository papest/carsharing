package carsharing.menu;

import carsharing.Car;
import carsharing.Operation;
import carsharing.Table;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class MenuList<T extends Table> {

    ArrayList<T> menuList;
    Menu menu = null;

    public MenuList(ArrayList<T> objectList,  ArrayList<MenuItem> menuItems, String menuName) {

        this.menuList = objectList;
        if (menuItems != null && !menuItems.isEmpty()) {
            this.menu = new Menu(menuItems, menuName);
        }
    }


    public String getAllText() {
        int[] index = {1};
        return menuList.stream().map(t -> String.format("%s. %s", index[0]++, t.getName()))
                .collect(Collectors.joining("\n")) + "\n"
                + (menu != null ? menu.getAllText() : "");
    }

    public Object getObject(int number) {

       if (number - 1 < menuList.size() && number > 0) {
           return menuList.get(number - 1);
       }
       return menu.getOperationByItemNumber(number);

    }
}
