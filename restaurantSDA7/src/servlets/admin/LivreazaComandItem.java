package servlets.admin;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

/**
 * Servlet implementation class LivreazaComandItem
 */
@WebServlet("/LivreazaComandItem")
public class LivreazaComandItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LivreazaComandItem() {
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
		String comandItemId = request.getParameter("comandItemId");
		ComandItemManager cim = new ComandItemManager();
		cim.setup();

		CommandItem ci = cim.getComandItemsById(new Integer(comandItemId));

		ci.setDataLivrareItem(new Date());
		cim.update(ci);

		// TODO - de gasit command itemii comenzii respective
		List ciList = cim.readComandItemsForComand(ci.getIdComanda());

		cim.exit();
		Iterator it = ciList.iterator();

		boolean livrat = true;
		while (it.hasNext()) {

			CommandItem ciTemp = (CommandItem) it.next();
			if (ciTemp.getDataLivrareItem() == null) {
				livrat = false;
				break;
			}
		}
		// TODO2 - daca toti sunt livrati, se seteaza data livrarii pe comanda
		if (livrat) {
			ComandaManager cm = new ComandaManager();
			cm.setup();

			Comanda com = cm.readComanda(ci.getIdComanda());
			com.setDataLivrare(new Date());
			cm.update(com);
			cm.exit();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/AfiseazaComenziOspatar");
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
