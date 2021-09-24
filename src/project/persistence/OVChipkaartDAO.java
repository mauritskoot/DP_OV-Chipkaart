package project.persistence;

import project.domein.OVChipkaart;
import project.domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ovchipkaart) throws SQLException;
    public boolean update(OVChipkaart ovchipkaart) throws SQLException;
    public boolean delete(OVChipkaart ovchipkaart) throws SQLException;
    public List<OVChipkaart> findAll() throws SQLException;
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
}
