package repository.impl;

import Base.repository.impl.BaseRepositoryImpl;
import model.ShoppingCart;
import model.User;
import repository.ShoppingCartRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartRepositoryImpl extends BaseRepositoryImpl<Integer, ShoppingCart> implements ShoppingCartRepository {
    @Override
    public String getTableName() {
        return "shopping_cart";
    }

    @Override
    public String getColumnsName() {
        return "(user_id,product,price)";
    }

    @Override
    public String getUpdateQueryParams() {
        return "user_id=? , product=? , price=?";
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "?,?,?";
    }

    @Override
    public ShoppingCart mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new ShoppingCart(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getInt(4));
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, ShoppingCart entity) throws SQLException {
        preparedStatement.setInt(1, entity.getUser_id());
        preparedStatement.setString(2, entity.getProductName());
        preparedStatement.setInt(3, entity.getPrice());
    }
}
