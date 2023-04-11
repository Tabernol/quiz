package repo;

import exeptions.DataBaseException;

import java.util.List;

public interface BaseRepo<T> {

    long create(T t) throws DataBaseException;

    int delete(Long id) throws DataBaseException;

    int update(T t) throws DataBaseException;

    T get(Long id) throws DataBaseException;

    // List<T> getAllById(Long id);
}
