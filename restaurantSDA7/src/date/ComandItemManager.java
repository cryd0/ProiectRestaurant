package date;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ComandItemManager {

	public ComandItemManager() {
        // TODO Auto-generated constructor stub
    }
protected SessionFactory sessionFactory;
    
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
    
    public void createComandItem() {
        // code to save a book
        CommandItem ci = new CommandItem();
        ci.setIdComanda(1);
        ci.setDataCreareItem(new java.util.Date());
        ci.setIdProdus(3);
        ci.setPretTotal(39.59);
        ci.setDataLivrareItem(new java.util.Date());
        ci.setNrProduse(15);
        
        
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(ci);
     
        session.getTransaction().commit();
        session.close();
    }
    
    
    public Object createComandItem(CommandItem ci) {
  
             
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        Object id =   session.save(ci);
     
        session.getTransaction().commit();
        session.close();
        
        return id;
    }
    
    public void update(CommandItem ci) {
    	
    	  Session session = sessionFactory.openSession();
    	  session.beginTransaction();
    	  
    	  session.update(ci);
    	  
    	  session.getTransaction().commit();
    	  session.close();
    	
    }
    
    public List readComandItemsForComand(Integer idComanda) {
        
        Session session = sessionFactory.openSession();
 		List comandItems = null;
 
 		try {
     		
     		Query query = session.createQuery("from CommandItem where idComanda="+idComanda+"");
     		comandItems = query.list();
   //  		request.setAttribute("produse", produse);
     		//System.out.println("Dimensiune = " + produse.size());
     		//System.out.println("nume produs 1" +((Produs) produse.get(2)).getNumeProdus());
     		System.out.println("Numar itemi pentru comanda cu id = " + idComanda + ":" + comandItems.size());
     		
     	}finally {
     		session.close();
     	}
     	return comandItems;
     }
     
    
  public CommandItem getComandItemsById(Integer id) {
        
        Session session = sessionFactory.openSession();
        
        CommandItem ci = session.get(CommandItem.class, id);
 		
        return ci;
 		
  }
    
    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }
    
    public static void main(String[] args) {
    	ComandItemManager cim = new ComandItemManager();
    	cim.setup();
    	cim.readComandItemsForComand(1);
    	cim.exit();
    }
}
