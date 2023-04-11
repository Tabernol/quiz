package repo;

import exeptions.DataBaseException;

import java.util.List;

public interface BaseRepo <T>{

    int create(T t) throws DataBaseException;

    int delete(Long id) throws DataBaseException;

   // List<T> getAllById(Long id);
}
