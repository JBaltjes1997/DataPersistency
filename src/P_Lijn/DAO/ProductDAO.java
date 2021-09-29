package P_Lijn.DAO;

import P_Lijn.klassen.Product;

public interface ProductDAO {
    public boolean save(Product product);
    public boolean update(Product product);
    public boolean delete(Product product);
}
