package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;

public interface BaseService<T> {
    T get(Long id) throws DataBaseException;

    long create(T t) throws DataBaseException, ValidateException;

    int delete(Long id) throws DataBaseException;

    int update(T t) throws DataBaseException, ValidateException;
}
