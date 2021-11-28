package model;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    //mentés, szerkesztés, getAll, ...
    List<T> getAll();
    T getById(Integer id);
    T getByString(String szoveg);
}
