package dao;

import java.util.List;

public interface Dao<T> {

    T get(Long id);

    void update(Long id);

    void delete(Long id);

    List<T> getAll();
}
