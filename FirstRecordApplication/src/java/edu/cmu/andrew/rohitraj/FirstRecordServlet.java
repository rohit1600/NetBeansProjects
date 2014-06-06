/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.andrew.rohitraj;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rohit
 */
@WebServlet(name = "FirstRecordServlet", urlPatterns = {"/FirstRecordServlet"})
public class FirstRecordServlet extends HttpServlet {

    @PersistenceUnit EntityManagerFactory emf;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            
            String inputvalue=request.getParameter("customerID");
            Integer id= new Integer(inputvalue);
            Query queryCustomerByID = 
                    emf.createEntityManager().createNamedQuery("Customer.findByCustomerId");
                  queryCustomerByID.setParameter("customerId", new Integer(id));
                  Customer customer = (Customer) queryCustomerByID.getResultList().get(0);
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FirstRecordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Servlet FirstRecordServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2> From Database " + customer.getName() + "<h2>");
            out.println("<h2> from database " + customer.getCity() + "</h2>");
            out.println("<h2> from database " + customer.getDiscountCode() + "</h2>");
            out.println("<h2> from database " + customer.getDiscountCode().getRate() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
