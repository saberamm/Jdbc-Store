package repository;

import Base.repository.BaseRepository;
import model.ShoppingCart;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartRepository extends BaseRepository<Integer, ShoppingCart> {
    public List<ShoppingCart> findAllByUserName(int id) throws SQLException;
}
