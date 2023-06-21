package service;

import Base.service.BaseService;
import model.ShoppingCart;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartService extends BaseService<Integer, ShoppingCart> {
    public List<ShoppingCart> findAllByUserName(int id) throws SQLException;
}
