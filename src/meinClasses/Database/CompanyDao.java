package meinClasses.Database;

import meinClasses.Podjetje;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CompanyDao extends DaoCrud<Podjetje>{
    public Podjetje getById(short uuid);
    public List<Podjetje> getAll();
    public boolean insert(Podjetje m);
    public boolean update(Podjetje m);
    public boolean delete(Podjetje m);
    public Podjetje extractFromResultSet(ResultSet rs) throws SQLException;
}
