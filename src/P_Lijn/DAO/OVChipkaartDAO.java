package P_Lijn.DAO;

import P_Lijn.klassen.OVChipkaart;
import P_Lijn.klassen.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ovchipkaart);
    public boolean update(OVChipkaart ovchipkaart);
    public boolean delete(OVChipkaart ovchipkaart);
    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public List<OVChipkaart> findAll();
}
