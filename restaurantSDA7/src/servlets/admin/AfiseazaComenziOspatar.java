package servlets.admin;

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

import date.CategorieManager;
import date.ComandItemManager;
import date.Comanda;
import date.ComandaManager;
import date.CommandItem;
import date.Produs;
import date.ProdusDAO;
import date.ProdusManager;
import ui.admin.ComandaUI;
import ui.admin.LinieTabelComandaOspatar;

/**
 * Servlet implementation class AfiseazaComenziOspatar
 */
@WebServlet("/AfiseazaComenziOspatar")
public class AfiseazaComenziOspatar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfiseazaComenziOspatar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Pun mana pe numeOspatar din sesiune
		String ospatar = (String) request.getSession().getAttribute("numeOspatar");
		// Iau comenzile deschise din DB
		if (ospatar != null) {
			// ospatar = "eu";
			ComandaManager comandaManager = new ComandaManager();
			comandaManager.setup();
			
			
			List comenzi = comandaManager.readOpenComandsForOspatar(ospatar);
			comandaManager.exit();
			List listaComenziUI = new ArrayList();// Sac de comenzi; Reprezentarea comenzilor

			List produse = (List) request.getServletContext().getAttribute("produse");
			List categorii = (List) request.getServletContext().getAttribute("categorii");
			
			if (produse == null) {
				// citesc produsele din DB si le pun pe aplicatie
				ProdusDAO manager = new ProdusManager();
				manager.setup();
				produse = manager.readAllProducts();
				manager.exit();
				request.getServletContext().setAttribute("produse", produse);
				
				CategorieManager managerCategorie = new CategorieManager();
				managerCategorie.setup();
				categorii = managerCategorie.readAllCategories();
				managerCategorie.exit();
				request.getServletContext().setAttribute("categorii", categorii);
			}
			System.out.println("Am pus mana pe produse! Size:" + produse.size());

			// Pregatesc comenzi si produse per comanda sac mare/sac mic
			Iterator it = comenzi.iterator();
			while (it.hasNext()) {
				Comanda comanda = (Comanda) it.next();
				ComandaUI cui = new ComandaUI();// cui este bloc comanda de pe pagina
				cui.setNrMasa(comanda.getNrMasa());
				cui.setIdComanda(comanda.getIdComanda());
				cui.setPretTotal(comanda.getPretTotalComanda());
				cui.setNumeOspatar(comanda.getNumeOspatar());
				cui.setDataCreare(comanda.getDataCreare());
				cui.setDataLivrare(comanda.getDataLivrare());
				List liniiTabelComandaOspatar = new ArrayList();

				ComandItemManager cim = new ComandItemManager();
				cim.setup();
				List comandItems = cim.readComandItemsForComand(comanda.getIdComanda());

				cim.exit();

				Iterator itComandItems = comandItems.iterator();

				while (itComandItems.hasNext()) {

					CommandItem ci = (CommandItem) itComandItems.next();
					LinieTabelComandaOspatar ltc = new LinieTabelComandaOspatar();
					ltc.setCantitate(ci.getNrProduse());
					ltc.setDataLivrare(ci.getDataLivrareItem());
					Iterator itProduse = produse.iterator();

					while (itProduse.hasNext()) {

						Produs produs = (Produs) itProduse.next();
						if (produs.getIdProdus().intValue() == ci.getIdProdus().intValue()) {

							ltc.setNumeProdus(produs.getNumeProdus());
							ltc.setDescriereProdus(produs.getDescriereProdus());
							ltc.setPretUnitar(produs.getPretUnitar());
							ltc.setPretTotal(ci.getPretTotal());
							ltc.setIdComandItem(ci.getIdItem());
							liniiTabelComandaOspatar.add(ltc);
							break;

						}
					}

					
				}
					// String continut = comanda.getContinutComanda();
					/*
					 * String[] tokens = continut.split(";");
					 * 
					 * List liniiTabelComandaOspatar = new ArrayList(); for(int i=0; i<
					 * tokens.length; i++) { String[] el = tokens[i].split(":"); String id = el[0];
					 * String nrBucati = el[1];
					 * 
					 * LinieTabelComandaOspatar ltc = new LinieTabelComandaOspatar();//linie produs
					 * comanda ltc.setCantitate(new Integer(nrBucati)); Iterator itProduse =
					 * produse.iterator();
					 * 
					 * // Impachetat liniile intr-o colectie; while(itProduse.hasNext()){ Produs
					 * produs = (Produs)itProduse.next();
					 * 
					 * if (produs.getIdProdus().intValue()==Integer.parseInt(id)) {
					 * ltc.setIdProdus(new Integer(Integer.parseInt(id)));
					 * ltc.setNumeProdus(produs.getNumeProdus());
					 * ltc.setDescriereProdus(produs.getDescriereProdus());
					 * ltc.setPretUnitar(produs.getPretUnitar());
					 * ltc.setPretTotal(ltc.getCantitate()*ltc.getPretUnitar()); //
					 * ltc.setDataLivrare(dataLivrare); liniiTabelComandaOspatar.add(ltc); break; }
					 * }
					 */
					// }
					cui.setLiniiTabelComandaOspatar(liniiTabelComandaOspatar);
					listaComenziUI.add(cui);
				}
				request.getSession().setAttribute("listaComenziUI", listaComenziUI);
		
		}

		// Redirectez catre comenziospatar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("admin/comenziospatar.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
