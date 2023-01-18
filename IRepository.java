import java.sql.SQLException;
import java.util.Collection;

public interface IRepository<T extends Student> {

    public T get(int ID) throws SQLException;
    public Collection<T> getAll() throws SQLException;
    public T add(T entity) throws SQLException;
    public T update(T entity) throws SQLException;
    public String delete(T entity) throws SQLException;

}
