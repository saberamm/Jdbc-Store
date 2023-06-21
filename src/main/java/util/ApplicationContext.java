package util;

import repository.ShoppingCartRepository;
import repository.UserRepository;
import repository.impl.ShoppingCartRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.ShoppingCartService;
import service.UserService;
import service.impl.ShoppingCartServiceImpl;
import service.impl.UserServiceImpl;

public class ApplicationContext {

    //user context

    private static UserRepository userRepository;

    private static UserService userService;


    static {
        userRepository = new UserRepositoryImpl();

        userService = new UserServiceImpl(userRepository);
    }

    public static UserService getUserService() {
        return userService;
    }


    //shopping cart context
    private static ShoppingCartRepository shoppingCartRepository;

    private static ShoppingCartService shoppingCartService;


    static {
        shoppingCartRepository = new ShoppingCartRepositoryImpl();

        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository);
    }

    public static ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }


}
