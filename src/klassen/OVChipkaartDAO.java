package klassen;

import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(Adres adres);
    public boolean update(Adres adres);
    public boolean delete(Adres adres);
    public List<OVChipkaart> findByReiziger();
    public List<OVChipkaart> findAll();
}
