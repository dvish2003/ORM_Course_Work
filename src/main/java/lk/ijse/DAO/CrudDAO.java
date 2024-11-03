package lk.ijse.DAO;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T entity) throws Exception;
    public boolean update(T entity) throws Exception;
    public boolean delete(T entity) throws Exception;

}
