public interface IRepository<T extends Student> {
    
    public T get(int ID);

    public boolean add(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);

}
