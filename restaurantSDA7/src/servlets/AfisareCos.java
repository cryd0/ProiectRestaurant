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
import date.Produs;
import ui.LinieTabelCos;
/**
* Servlet implementation class AfisareCos
*/
@WebServlet("/AfisareCos")
public class AfisareCos extends HttpServlet {
    private static final long serialVersionUID = 1L;
   /**
    * @see HttpServlet#HttpServlet()
    */
   public AfisareCos() {
       super();
       // TODO Auto-generated constructor stub
   }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: aaa").append(request.getContextPath());
        List produse = (List)request.getServletContext().getAttribute("produse");
        Map cos = (HashMap)request.getSession().getAttribute("cos");
        List liniiTabelCos = new ArrayList();
        Iterator it = cos.keySet().iterator();
        Double grandTotal=0d;
        while(it.hasNext()){
                String currentKey = it.next().toString();
                String valoare = cos.get(currentKey).toString();
                int val = Integer.parseInt (valoare);
                Iterator itProduse = produse.iterator();
                while(itProduse.hasNext()){
                    Produs produs = (Produs)itProduse.next();
                    if (produs.getIdProdus().intValue()==Integer.parseInt(currentKey)) {
                        LinieTabelCos ltc = new LinieTabelCos();
                        ltc.setIdProdus(new Integer(Integer.parseInt(currentKey)));
                        ltc.setCantitate(val);
                        ltc.setNumeProdus(produs.getNumeProdus());
                        ltc.setDescriereProdus(produs.getDescriereProdus());
                        ltc.setPretUnitar(produs.getPretUnitar());
                        ltc.setPretTotal(ltc.getCantitate()*ltc.getPretUnitar());
                        liniiTabelCos.add(ltc);
                        grandTotal+=ltc.getPretTotal();
                        break;
                    }                
                }
            }
        request.getSession().setAttribute("liniiTabelCos",liniiTabelCos);
        request.getSession().setAttribute("grandTotal",grandTotal);
        
        RequestDispatcher rd = request.getRequestDispatcher("/afisarecos.jsp");
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
