package dao;

public interface DaoFactory<E, T> {
    public E getCategoryDao();
    public T getFoodDao();
}
