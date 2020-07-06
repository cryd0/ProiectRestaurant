package date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import date.Categorie;
import date.Produs;
import util.HibernateUtil;
public class ProdusManager implements ProdusDAO {
	
    public ProdusManager() {
        // TODO Auto-generated constructor stub
    }
protected SessionFactory sessionFactory;
    
    @Override
	public void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
    
    @Override
	public Produs getProdusById(Integer idProdus) {
        
        Session session = sessionFactory.openSession();
        

        Produs produs = session.get(Produs.class, idProdus);
     
     
     
     
        session.close();
        return produs;
    }
 
    @Override
	public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }
 
    @Override
	public Object createProdus(Produs produs) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        Object id  = session.save(produs);
     
        session.getTransaction().commit();
        session.close();
        return id;
    }
    
    
    @Override
	public List readAllProducts() {
    	List produse = null;
    	//SessionFactory factory = HibernateUtil.getSessionfactory();
    	Session session = sessionFactory.openSession();
    	
    	try {
    		
    		Query query = session.createQuery("from Produs");
    		produse = query.list();
    		//request.setAttribute("produse", produse);
    		//System.out.println("Dimensiune = " + produse.size());
    		//System.out.println("nume produs 1" +((Produs) produse.get(2)).getNumeProdus());
    		
    	}finally {
    		session.close();
    	}
    	return produse;
    }
    
    @Override
	public void update(Produs p) {
     	
     	  Session session = sessionFactory.openSession();
     	  session.beginTransaction();
     	  
     	  session.update(p);
     	  
     	  session.getTransaction().commit();
     	  session.close();
     	
     }
    
    public static void main(String[]args) {
    	ProdusDAO manager = new ProdusManager();
    	manager.setup();
    	manager.readAllProducts();
    	manager.exit();
    }
    
    
 
}
