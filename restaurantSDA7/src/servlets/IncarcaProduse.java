package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import date.ProdusDAO;
import date.ProdusDAOFactory;
import date.ProdusJDBCDAO;
import date.ProdusManager;

/**
 * Servlet implementation class IncarcaProduse
 */
@WebServlet("/IncarcaProduse")
public class IncarcaProduse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncarcaProduse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		
		List produse = null;
			ProdusDAO manager = ProdusDAOFactory.getInstance().getProdusDAO();
			
			manager.setup();
			
			produse = manager.readAllProducts();
			
			manager.exit();
			request.getServletContext().setAttribute("produse", produse);
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
