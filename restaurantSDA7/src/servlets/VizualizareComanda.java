package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import date.Comanda;
import date.ComandaManager;
import date.Produs;
import ui.LinieTabelComanda;
import ui.LinieTabelCos;

/**
 * Servlet implementation class VizualizareComanda
 */
@WebServlet("/VizualizareComanda")
public class VizualizareComanda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VizualizareComanda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// TODO Luat idComanda de pe sesiune;
		String idComanda = ""+ request.getSession().getAttribute("idComanda");
	
		// TODO Citit obiect Comanda din DB pe baza idComanda;
		ComandaManager manager = new ComandaManager();
        manager.setup();
        Comanda comanda =  manager.readComanda(new Integer(idComanda));
        manager.exit();
       
		// TODO Obtinut produsele comenzii(CommandItems) si creat din ele LiniTabelComanda;
        String continut = comanda.getContinutComanda();
        String[] tokens = continut.split(";");
        List produse = (List) request.getServletContext().getAttribute("produse");
        List liniiTabelComanda = new ArrayList();
        for(int i=0; i<tokens.length; i++) {
        	String[] el = tokens[i].split(":");
        	String id = el[0];
        	String nrBucati = el[1];
        	
        	LinieTabelComanda ltc = new LinieTabelComanda();
        	ltc.setCantitate(new Integer(nrBucati));
        	Iterator itProduse = produse.iterator();
        	
        	// TODO Impachetat liniile intr-o colectie;
        	while(itProduse.hasNext()){
                Produs produs = (Produs)itProduse.next();
                
                if (produs.getIdProdus().intValue()==Integer.parseInt(id)) {
                    ltc.setIdProdus(new Integer(Integer.parseInt(id)));
                    ltc.setNumeProdus(produs.getNumeProdus());
                    ltc.setDescriereProdus(produs.getDescriereProdus());
                    ltc.setPretUnitar(produs.getPretUnitar());
                    ltc.setPretTotal(ltc.getCantitate()*ltc.getPretUnitar());
                    liniiTabelComanda.add(ltc);
                    break;
                }                
            }
        	
        }
        
        Double totalComanda = comanda.getPretTotalComanda();// TODO de actiualizat totalComanda cand  implementam edit pentru comanda
		// Redirectat catre afisarecomanda.jsp
        request.getSession().setAttribute("liniiTabelComanda",liniiTabelComanda);
        request.getSession().setAttribute("totalComanda",totalComanda);
        RequestDispatcher rd = request.getRequestDispatcher("/vizualizarecomanda.jsp");
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
