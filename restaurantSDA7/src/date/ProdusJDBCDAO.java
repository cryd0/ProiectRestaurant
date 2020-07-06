package date;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProdusJDBCDAO implements ProdusDAO {

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produs getProdusById(Integer idProdus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createProdus(Produs produs) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void update(Produs p) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public List readAllProducts() {
		Connection con = JDBCConnectionFactory.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM produse");
			List produse = new ArrayList();
			while (rs.next())
			{
				Produs produs = extractProdusFromResultSet(rs);
				produse.add(produs);
			}
			System.out.println("Size=" + produse.size());
			return produse;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private static Produs extractProdusFromResultSet (ResultSet rs) throws SQLException{
		
		Produs produs = new Produs();
		
		produs.setIdProdus(rs.getInt(1));
		System.out.println("idCategorie=" + rs.getInt(2));
		//TODO De gasit Categorie din DB cu id ul de 2
		
		produs.setCategorie(getCategorieByID(rs.getInt(2)));
		
		produs.setNumeProdus(rs.getString(3));
		produs.setDescriereProdus(rs.getString(4));
		produs.setPretUnitar(rs.getDouble(5));
		produs.setStoc(rs.getInt(6));
		produs.setNivelAlerta(rs.getInt(7));
		return produs;
	}
	
	private static Categorie getCategorieByID(int id) {
		Connection con = JDBCConnectionFactory.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categorie_produs WHERE id_categorie_produs =" + id);
			if (rs.next())
			{
				Categorie categorie = new Categorie();
				categorie.setIdCategorieProdus(rs.getInt(1));
				categorie.setNumeCategorieProdus(rs.getString(2));
				return categorie;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
