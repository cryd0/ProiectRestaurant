package date;
public class ProdusDAOFactory {
	static ProdusDAOFactory produsDAOFactory = null;
	private ProdusDAOFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static ProdusDAOFactory getInstance() {
		if (produsDAOFactory == null) {
			produsDAOFactory = new ProdusDAOFactory();
		}
		return produsDAOFactory;
	}
	public ProdusDAO getProdusDAO() {
		String implementation = "JDBC";
		//String implementation = "Hibernate";
		ProdusDAO result = null;
		if (implementation.equals("JDBC")) {
			result = new ProdusJDBCDAO();
		}
		if (implementation.equals("Hibernate")) {
			result = new ProdusManager();
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

