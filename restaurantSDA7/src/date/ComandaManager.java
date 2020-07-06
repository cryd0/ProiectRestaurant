package date;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ComandaManager {
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
 
    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }
 
    
 
    public void createComanda() {
        // code to save a book
        Comanda comanda = new Comanda();
        comanda.setIdComanda(1);
        comanda.setNrMasa(3);
        comanda.setNumeOspatar("Viorica Dancila");
        comanda.setDataCreare(new java.util.Date());
        comanda.setPretTotalComanda(200.20d);
        
        comanda.setDataIncasare(new java.util.Date());
        comanda.setContinutComanda("125:3;126:2");
        
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(comanda);
     
        session.getTransaction().commit();
        session.close();
    }
    
    public Object createComanda(Comanda comanda) {
       
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        Object id  = session.save(comanda);
     
        session.getTransaction().commit();
        session.close();
        return id;
    }
    
    
 
     public Comanda readComanda(Integer idComanda) {
        
        Session session = sessionFactory.openSession();
        
        Comanda comanda = session.get(Comanda.class, idComanda);
      
        session.close();
        
        return comanda;
    }
     
     public List readComandsForOspatar(String numeOspatar) {
         
        Session session = sessionFactory.openSession();
 		List comenzi = null;
 
 		try {
     		
     		Query query = session.createQuery("from Comanda where numeOspatar='"+numeOspatar+"'");
     		comenzi = query.list();
   //  		request.setAttribute("produse", produse);
     		//System.out.println("Dimensiune = " + produse.size());
     		//System.out.println("nume produs 1" +((Produs) produse.get(2)).getNumeProdus());
     		System.out.println("Numar comenzi pentru ospatar " + numeOspatar + ":" + comenzi.size());
     		
     	}finally {
     		session.close();
     	}
     	return comenzi;
     }
     
     public List readOpenComandsForOspatar(String numeOspatar) {
         
         Session session = sessionFactory.openSession();
  		List comenzi = null;
  
  		try {
      		
      		Query query = session.createQuery("from Comanda where numeOspatar='"+numeOspatar+"' and dataIncasare= null");
      		comenzi = query.list();
    //  		request.setAttribute("produse", produse);
      		//System.out.println("Dimensiune = " + produse.size());
      		//System.out.println("nume produs 1" +((Produs) produse.get(2)).getNumeProdus());
      		System.out.println("Numar comenzi active pentru ospatar " + numeOspatar + ":" + comenzi.size());
      		
      	}finally {
      		session.close();
      	}
      	return comenzi;
      }
      
    
//    protected void updateComanda() {
//        // code to modify a book
//        Comanda comanda = new Comanda();
//        comanda.setIdProdus(125);
//        comanda.setIdCategorie(1);
//        comanda.setNumeProdus("PRODUS DE Produs nou introdus");
//        comanda.setDescriereProdus("PRODUS DE DESCRIERE Produs nou introdus");
//        comanda.setPretUnitar(25.00);
//        comanda.setStoc(200);
//        comanda.setNivelAlerta(20);
//     
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//     
//        session.update(comanda);
//     
//        session.getTransaction().commit();
//        session.close();
//    }
 
//    protected void deleteProdus(Integer idComanda) {
//        // code to remove a book
//        Produs produs = new Produs();
//        produs.setIdProdus(idProdus);
//     
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//     
//        session.delete(produs);
//     
//        session.getTransaction().commit();
//        session.close();
//    }
 
     public void update(Comanda com) {
     	
   	  Session session = sessionFactory.openSession();
   	  session.beginTransaction();
   	  
   	  session.update(com);
   	  
   	  session.getTransaction().commit();
   	  session.close();
   	
   }
     
     
    public static void main(String[] args) {
        // code to run the program
        ComandaManager manager = new ComandaManager();
        manager.setup();
        
//        manager.createProdus();
      // List comenzi =  manager.readComandsForOspatar("Adrian");
      
//        manager.deleteProdus(135);
//        manager.updateProdus();
//        manager.readComanda(1);
     manager.createComanda();
        manager.exit();
    }
}
