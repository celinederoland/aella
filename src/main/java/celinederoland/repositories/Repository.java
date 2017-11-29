package celinederoland.repositories;

import java.util.List;

public interface Repository<T> {

    public T findOne(int id);

    public List<T> findAll();

    public T save(T object);

    public void delete(T object);
}
