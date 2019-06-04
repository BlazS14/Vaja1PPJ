package meinClasses.Database;

import meinClasses.Racun;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RacunDao extends DaoCrud<Racun>{
    public Racun getById(short uuid);
    public List<Racun> getAll();
    public boolean insert(Racun m);
    public boolean update(Racun m);
    public boolean delete(Racun m);
    public Racun extractFromResultSet(ResultSet rs) throws SQLException;
}
