package ui;

import model.User;
import service.UserService;
import util.ApplicationContext;
import util.Constant;
import util.SecurityContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    private static final UserService userService = ApplicationContext.getUserService();

    public static void run() {

        while (true) {
            Printer.printMenu(Constant.FIRST_MENU, Constant.WELCOME);
            Printer.printMsg(Constant.CHOOSE_ITEM, false);
            switch (new Scanner(System.in).next().trim()) {
                case "1" -> loginMenu();
                case "2" -> signupMenu();
                case "3" -> System.exit(0);
                default -> {
                    Printer.printWarning(Constant.ITEM_NOT_FOUND);
                    run();
                }
            }
        }
    }

    private static void signupMenu() {
        Printer.printMsg(Constant.ENTER_SIGNUP_INFO, true);
        Printer.printMsg(Constant.ENTER_FIRST_NAME, false);
        String firstName = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_LAST_NAME, false);
        String lastName = scanner.next().trim();
        String username = validateUsername(scanner);
        Printer.printMsg(Constant.ENTER_PASSWORD, false);
        String password = scanner.next().trim();
        String natCode = validateNatCode(scanner);
        String phone = validatePhone(scanner);
        try {
            userService.save(new User(firstName, lastName, username, password, natCode, phone));
            Printer.printMsg(Constant.REGISTRATION_SUCCESS, true);
            run();
        } catch (SQLException e) {
            Printer.printWarning(e.getMessage());
        }


    }

    private static String validateUsername(Scanner scanner) {
        String username;
        while (true) {
            Printer.printMsg(Constant.ENTER_USERNAME, false);
            username = scanner.next().trim();
            try {
                if (!userService.isExistsUsername(username)) break;
            } catch (Throwable e) {
                Printer.printWarning(e.getMessage());
            }
        }
        return username;
    }

    private static String validatePhone(Scanner scanner) {
        String phone;
        while (true) {
            Printer.printMsg(Constant.ENTER_PHONE, false);
            phone = scanner.next().trim();
            try {
                if (!userService.isExistsPhone(phone)) break;
            } catch (Throwable e) {
                Printer.printWarning(e.getMessage());
            }
        }
        return phone;
    }

    private static String validateNatCode(Scanner scanner) {
        String natCode;
        while (true) {
            Printer.printMsg(Constant.ENTER_NAT_CODE, false);
            natCode = scanner.next().trim();
            try {
                if (!userService.isExistsNatCode(natCode)) break;
            } catch (Throwable e) {
                Printer.printWarning(e.getMessage());
            }
        }
        return natCode;
    }

    private static void loginMenu() {
        while (true) {

            Printer.printMsg(Constant.ENTER_CREDENTIAL_DATA, false);
            String[] credential = new Scanner(System.in).next().trim().split(",");
            if (credential[0].equals(Constant.BREAK)) break;
            try {
                User foundUser = userService.checkCredentialInfoForLogin(credential[0], credential[1]);
                setSecurityContext(foundUser);
                dashboardMenu();

            } catch (ArrayIndexOutOfBoundsException e) {
                Printer.printWarning(Constant.BAD_ENTRY_FORMAT);
            } catch (Throwable e) {
                Printer.printWarning(e.getClass().getSimpleName() + ": " + e.getMessage());
            }

        }

    }
    @SuppressWarnings("InfiniteLoopStatement")
    public static void dashboardMenu() {
        while (true) {
            Printer.printMenu(Constant.DASHBOARD_MENU, Constant.WELCOME_MESSAGE + SecurityContext.firstName);
            Printer.printMsg(Constant.CHOOSE_ITEM, false);
            switch (new Scanner(System.in).next().trim()) {
                case "1" -> CartMenu.buyingMenu();
                case "2" -> updateProfile();
                case "3" -> deleteAccount();
                case "4" -> {
                    clearSecurityContext();
                    run();
                }
                default -> Printer.printWarning(Constant.ITEM_NOT_FOUND);
            }
        }
    }


    private static void deleteAccount() {
        Printer.printMsg(Constant.ENTER_ID, false);
        int userId = scanner.nextInt();
        try {
            ApplicationContext.getUserService().delete(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateProfile() {
        Printer.printMsg(Constant.ENTER_FIRST_NAME, false);
        String firstName = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_LAST_NAME, false);
        String lastName = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_USERNAME, false);
        String username = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_PASSWORD, false);
        String password = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_NAT_CODE, false);
        String natCode = scanner.next().trim();
        Printer.printMsg(Constant.ENTER_NAT_CODE, false);
        String phone = scanner.next().trim();
        try {
            userService.update(new User(SecurityContext.id, firstName, lastName, username, password, natCode, phone));
            Printer.printMsg(Constant.UPDATE_SUCCESS, true);
            run();
        } catch (SQLException e) {
            Printer.printWarning(e.getMessage());
        }

    }

    private static void clearSecurityContext() {
        SecurityContext.id = -1;
        SecurityContext.firstName = null;
        SecurityContext.lastName = null;
        SecurityContext.username = null;
    }


    private static void setSecurityContext(User foundUser) {
        SecurityContext.id = foundUser.getId();
        SecurityContext.firstName = foundUser.getFirstName();
        SecurityContext.lastName = foundUser.getLastName();
        SecurityContext.username = foundUser.getUsername();
    }
}
