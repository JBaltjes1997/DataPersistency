package P_Lijn.DAO;

import P_Lijn.klassen.Adres;
import P_Lijn.klassen.Reiziger;

import java.util.List;

public interface AdresDAO{
    public boolean save(Adres adres);
    public boolean update(Adres adres);
    public boolean delete(Adres adres);
    public Adres findByReiziger(Reiziger reiziger);
    public List<Adres> findAll();
}
