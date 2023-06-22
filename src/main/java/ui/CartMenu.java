package ui;

import model.ElectronicDevices;
import model.Product;
import model.Shoes;
import model.ShoppingCart;
import util.ApplicationContext;
import util.Constant;
import util.SecurityContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CartMenu {
    static Scanner scanner = new Scanner(System.in);
    @SuppressWarnings("InfiniteLoopStatement")
    public static void buyingMenu() {
        while (true) {
            Printer.printMenu(Constant.CART_MENU, Constant.WELCOME_MESSAGE + SecurityContext.firstName);
            Printer.printMsg(Constant.CHOOSE_ITEM, false);
            switch (scanner.next().trim()) {
                case "1" -> shopping();
                case "2" -> updateCart();
                case "3" -> deleteCart();
                case "4" -> cartPrice();
                case "5" -> Menu.dashboardMenu();
                default -> Printer.printWarning(Constant.ITEM_NOT_FOUND);
            }
        }
    }

    private static void deleteCart() {
        try {
            List<ShoppingCart> products = ApplicationContext.getShoppingCartService().findAllByUserName(SecurityContext.id);
            for (ShoppingCart p : products) {
                System.out.println(p);
            }
            Printer.printMsg(Constant.ENTER_ID, false);
            int id = scanner.nextInt();
            ApplicationContext.getShoppingCartService().delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void shopping() {
        Product product = null;
        Printer.printMenu(Constant.PRODUCT_MENU, Constant.PRODUCT_CHOOSE);
        Printer.printMsg(Constant.CHOOSE_ITEM, false);
        switch (new Scanner(System.in).next().trim()) {
            case "1" -> product = ElectronicDevices.tv;
            case "2" -> product = ElectronicDevices.radio;
            case "3" -> product = Shoes.official;
            case "4" -> product = Shoes.sport;
            case "5" -> buyingMenu();
            default -> {
                Printer.printWarning(Constant.ITEM_NOT_FOUND);
                shopping();
            }
        }
        if (product != null) {
            ShoppingCart shoppingCart = new ShoppingCart(SecurityContext.id, product.toString(), product.getValue());
            try {
                ApplicationContext.getShoppingCartService().save(shoppingCart);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else shopping();
    }

    private static void updateCart() {
        try {
            List<ShoppingCart> products = ApplicationContext.getShoppingCartService().findAllByUserName(SecurityContext.id);
            for (ShoppingCart p : products) {
                System.out.println(p);
            }
            Printer.printMsg(Constant.ENTER_ID, false);
            int id = scanner.nextInt();
            Product product = null;
            Printer.printMenu(Constant.PRODUCT_MENU, Constant.PRODUCT_CHOOSE);
            Printer.printMsg(Constant.CHOOSE_ITEM, false);
            switch (scanner.next().trim()) {
                case "1" -> product = ElectronicDevices.tv;
                case "2" -> product = ElectronicDevices.radio;
                case "3" -> product = Shoes.official;
                case "4" -> product = Shoes.sport;
                case "5" -> updateCart();
                default -> {
                    Printer.printWarning(Constant.ITEM_NOT_FOUND);
                    shopping();
                }

            }
            if (product != null) {
                ShoppingCart shoppingCart = new ShoppingCart(id, SecurityContext.id, product.toString(), product.getValue());
                try {
                    ApplicationContext.getShoppingCartService().update(shoppingCart);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void cartPrice() {
        try {
            long sumPrice = 0;
            List<ShoppingCart> products = ApplicationContext.getShoppingCartService().findAllByUserName(SecurityContext.id);
            for (ShoppingCart p : products) {
                sumPrice += p.getPrice();
            }
            System.out.println("your shopping cart total price" + sumPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
