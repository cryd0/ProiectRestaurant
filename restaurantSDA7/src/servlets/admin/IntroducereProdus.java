package servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import date.CategorieManager;
import date.Produs;
import date.ProdusDAO;
import date.ProdusManager;
import date.Categorie;

/**
 * Servlet implementation class IntroducereProdus
 */
@WebServlet("/admin/IntroducereProdus")
public class IntroducereProdus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntroducereProdus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//TODO luat atributele produsului din pagina (request)
		
		String numeProdus = request.getParameter("numeProdus");
		String descriereProdus = request.getParameter("descriereProdus");
		Double pretUnitar = Double.valueOf(request.getParameter("pretUnitar"));
		Integer stoc = Integer.parseInt(request.getParameter("nivelExistent"));
		Integer nivelAlerta = Integer.parseInt(request.getParameter("nivelAlerta"));
		Integer idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
		System.out.println("idCategorie:" + idCategorie);
		
		CategorieManager catManager = new CategorieManager();
		catManager.setup();
		Categorie categorie = catManager.readCategorie (idCategorie);
		catManager.exit();
		//TODO creat obiect produs
		
		Produs produs = new Produs();
		produs.setNumeProdus(numeProdus);
		produs.setDescriereProdus(descriereProdus);
		produs.setPretUnitar(pretUnitar);
		produs.setStoc(stoc);
		produs.setNivelAlerta(nivelAlerta);
		produs.setCategorie(categorie);
		
		//TODO salvat obiect produs in DB cu ajutorul lui ProdusManager
		
		ProdusDAO produsManager = new ProdusManager();
		produsManager.setup();
		produsManager.createProdus(produs);
		produsManager.exit();
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/AdaugaProdusNou.jsp");
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
