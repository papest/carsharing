package carsharing;

import carsharing.menu.Menu;
import carsharing.menu.MenuList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHandler {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static Operation askOperation(Menu menu) {
        write("\n" + menu.getAllText() + "\n");
        int itemNumber;
        while (true) {
            try {
                itemNumber = Integer.parseInt(readString());
                return menu.getOperationByItemNumber(itemNumber);
            } catch (NumberFormatException e) {
            }

        }
    }

    public static String readString() {
        while (true) {
            try {
                return bis.readLine();
            } catch (IOException e) {

            }
        }

    }

    public static void write(String allText) {
        System.out.print(allText);
    }

//    public static Operation askOperationCarList(Menu carsharing.menu) {
//        write("\n" + carsharing.menu.getAllText() + "\n");
//        int itemNumber;
//        while (true) {
//            try {
//                itemNumber = Integer.parseInt(readString());
//                return carsharing.menu.getOperationByItemNumber(itemNumber);
//            } catch (NumberFormatException e) {
//            }
//
//        }
//    }

     public static<T extends Table> Object askObjectOfMenuList(MenuList<T> menuList) {
         write(menuList.getAllText() + "\n");
         int number;
         while (true) {
             try {
                 number = Integer.parseInt(readString());
                 return menuList.getObject(number);
             } catch (NumberFormatException e) {
             }

         }
     }


}

