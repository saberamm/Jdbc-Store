package Base.repository.impl;

import Base.model.BaseEntity;
import Base.repository.BaseRepository;
import config.DBConfig;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl<ID extends Serializable, TYPE extends BaseEntity<ID>>
        implements BaseRepository<ID, TYPE> {


    @Override
    public void save(TYPE entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() +" "+getColumnsName()+ " VALUES (" + getCountOfQuestionMarkForParams() + ")";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {

            fillParamForStatement(statement, entity);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(ID id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ? ";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        String sql = " SELECT * FROM " + getTableName();
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<TYPE> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        String sql="SELECT * FROM "+getTableName()+ " WHERE id = ? ;";
        try (PreparedStatement statement=new DBConfig().getConnection().prepareStatement(sql)){
            statement.setInt(1, (Integer) id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next())
                return mapResultSetToEntity(resultSet);
        }
        return null;
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET " + getUpdateQueryParams() + " WHERE id = ?";
        try (PreparedStatement statement = new DBConfig().getConnection().prepareStatement(sql)) {
            fillParamForStatement(statement, entity);
            statement.executeUpdate();
        }
    }


    public abstract String getTableName();

    public abstract String getColumnsName();

    public abstract String getUpdateQueryParams();

    public abstract String getCountOfQuestionMarkForParams();

    public abstract TYPE mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public abstract void fillParamForStatement(PreparedStatement preparedStatement, TYPE entity) throws SQLException;





}
