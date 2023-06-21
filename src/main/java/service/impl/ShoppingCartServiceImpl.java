package service.impl;

import Base.service.impl.BaseServiceImpl;
import model.ShoppingCart;
import repository.ShoppingCartRepository;
import service.ShoppingCartService;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCartServiceImpl
        extends BaseServiceImpl<Integer, ShoppingCart, ShoppingCartRepository>
        implements ShoppingCartService {


    public ShoppingCartServiceImpl(ShoppingCartRepository repository) {
        super(repository);
    }


    @Override
    public List<ShoppingCart> findAllByUserName(int id) throws SQLException {
        return repository.findAllByUserName(id);
    }
}
