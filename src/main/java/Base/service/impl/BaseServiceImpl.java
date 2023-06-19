package Base.service.impl;

import Base.model.BaseEntity;
import Base.repository.BaseRepository;
import Base.service.BaseService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class BaseServiceImpl<ID extends Serializable,TYPE extends BaseEntity<ID>,
        R extends BaseRepository<ID,TYPE>>
        implements BaseService<ID,TYPE> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(TYPE entity) throws SQLException {
        repository.save(entity);
    }

    @Override
    public void delete(ID id) throws SQLException {
        repository.delete(id);
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        return repository.findAll();
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        repository.update(entity);
    }
}