package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCos
 */
@WebServlet("/UpdateCos")
public class UpdateCos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Update Coss").append(request.getContextPath());
		
		String productId = request.getParameter("productId");
		String action = request.getParameter("action");
		HashMap cos = (HashMap)request.getSession().getAttribute("cos");

		if((action!= null)&&(action.equals("remove"))) {
			cos.remove(productId);
		}else {
			String cantitate = request.getParameter("cant"+request.getParameter("productId"));
			if(cos == null) {
				cos = new HashMap();
				cos.put(productId, cantitate);
			}else {
				if(cos.containsKey(productId)) {
					int q = Integer.parseInt((String) cos.get(productId));
					int newq =Integer.parseInt(cantitate)+q;
					cos.put(productId, newq);
				}else {
					cos.put(productId, cantitate);
				}
			}
		}
		
		request.getSession().setAttribute("cos", cos);
		Iterator it = cos.keySet().iterator();
		int numarObiecteCos = 0;
		while(it.hasNext()) {
			String currentKey = it.next().toString();
			String valoare =  cos.get(currentKey).toString();
			int val =Integer.parseInt(valoare);
			numarObiecteCos+= val;
		}
		request.getSession().setAttribute("numarObiecteCos", numarObiecteCos);
		RequestDispatcher rd;
		if((action!=null)&&(action.equals("remove"))) {
			rd = request.getRequestDispatcher("/AfisareCos");
		}else {
			rd = request.getRequestDispatcher("/meniu.jsp");
		}
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
