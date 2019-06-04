package meinClasses.Database;

import meinClasses.Izdelek;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IzdelekDao extends DaoCrud<Izdelek>{
    public Izdelek getById(short uuid);
    public List<Izdelek> getAll();
    public boolean insert(Izdelek m);
    public boolean update(Izdelek m);
    public boolean delete(Izdelek m);
    public Izdelek extractFromResultSet(ResultSet rs) throws SQLException;
}
