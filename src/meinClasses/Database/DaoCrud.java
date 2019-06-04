package meinClasses.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DaoCrud<T> {
    public <T> T getById(short uuid);
    public <T> List<T> getAll();
    public <T> boolean insert(T m);
    public <T> boolean update(T m);
    public <T> boolean delete(T m);
    public <T> T extractFromResultSet(ResultSet rs) throws SQLException;
}
