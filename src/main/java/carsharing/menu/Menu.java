package carsharing.menu;


import carsharing.Main;
import carsharing.Operation;

import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Menu {

    ResourceBundle res;
 private ArrayList<MenuItem> menuList;
 private String name;

    public Menu(ArrayList<MenuItem> menuList, String name) throws MissingResourceException {
        if (menuList.stream().mapToInt(MenuItem::getNumber).distinct().count() < menuList.size()) {
            throw new IllegalArgumentException("Repeated numbers in the carsharing.menu");
        }
        this.menuList = menuList;
        this.name = name;

            res = ResourceBundle.getBundle("menu" + this.name);

    }

    public ArrayList<MenuItem> getMenuList() {
        return menuList;
    }

    public Operation getOperationByItemNumber(int itemNumber){

        return menuList.stream().filter(menuItem -> menuItem.getNumber() == itemNumber)
                    .findFirst().orElseThrow()
                    .getOperation();

   }


    public String getAllText(){
        return menuList.stream().map(MenuItem::getOperation)
                .map(operation -> res.getString(operation.name()))
                .collect(Collectors.joining("\n"));
    }

}

