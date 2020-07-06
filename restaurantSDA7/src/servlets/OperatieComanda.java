package servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import date.ComandItemManager;
import date.Comanda;
import date.ComandaManager;
import date.CommandItem;
import date.Produs;
import date.ProdusDAO;
import date.ProdusManager;
import ui.LinieTabelCos;
/**
 * Servlet implementation class Comanda
 */
@WebServlet("/OperatieComanda")
public class OperatieComanda extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatieComanda() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        Map cos = (HashMap)request.getSession().getAttribute("cos");
        Double grandTotal = new Double (""+ request.getSession().getAttribute("grandTotal"));
        String masa = (String) request.getSession().getAttribute("masa");
        String ospatar = (String) request.getSession().getAttribute("ospatar");
    
        
        ComandaManager manager = new ComandaManager();
        manager.setup();
        Comanda comanda = new Comanda();
        
        //comanda.setIdComanda(1);
        comanda.setNrMasa(new Integer(masa));
        comanda.setNumeOspatar(ospatar);
        comanda.setDataCreare(new java.util.Date());
        comanda.setPretTotalComanda(grandTotal);
        
        //comanda.setDataIncasare(new java.util.Date());
        
        String continutcomanda = "";
        Iterator it = cos.keySet().iterator();
        while(it.hasNext()) {
            String currentKey = it.next().toString();
            String valoare = cos.get(currentKey).toString();
            continutcomanda = continutcomanda+";" + currentKey+":"+valoare;
        }
        continutcomanda = continutcomanda.substring(1);
        comanda.setContinutComanda(continutcomanda);
        
        String idComanda =""+ manager.createComanda(comanda);
        manager.exit();
        Iterator it2 = cos.keySet().iterator();
        ComandItemManager cim = new ComandItemManager();
        cim.setup();
        
        while(it2.hasNext()) {
            String currentKey = it2.next().toString();
            List produse = (List) request.getServletContext().getAttribute("produse");
            Iterator itProduse = produse.iterator();
            Double pu = 0d;
            while (itProduse.hasNext()) {
                Produs produs = (Produs)itProduse.next();
                if((""+produs.getIdProdus()).equals(currentKey)) {
                    pu = produs.getPretUnitar(); break;
                }
            }
            String valoare = cos.get(currentKey).toString();
            CommandItem ci = new CommandItem();
            ci.setIdComanda(new Integer(idComanda));
            ci.setDataCreareItem(comanda.getDataCreare());
            //ci.setDataLivrareItem(null);
            ci.setIdProdus(new Integer(currentKey));
            ci.setNrProduse(new Integer(valoare));
            ci.setPretTotal(pu.doubleValue()*Integer.parseInt(valoare));
            cim.createComandItem(ci);
            //redus stoc cu catitatea comandata :)
            ProdusDAO pm = new ProdusManager();
            pm.setup();
            Produs produs = pm.getProdusById(ci.getIdProdus());
            produs.setStoc(produs.getStoc().intValue()-Integer.parseInt(valoare));
            pm.update(produs);
            pm.exit();
        }
        cim.exit();
        //String idComanda = "idComanda";
        request.getSession().setAttribute("idComanda", idComanda);
        cos.clear();
        request.getSession().setAttribute("cos", cos);
        request.getSession().setAttribute("grandTotal", new Double("0"));
        int numarObiecteCos = 0;
        
        request.getSession().setAttribute("numarObiecteCos", numarObiecteCos);
        
        ProdusDAO pm = new ProdusManager();
        pm.setup();
        List produse = pm.readAllProducts();
        pm.exit();
        request.getServletContext().setAttribute("produse", produse);
        
        response.getWriter().append("Comanda Creata!");
        RequestDispatcher rd = request.getRequestDispatcher("/meniu.jsp");
         rd.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}