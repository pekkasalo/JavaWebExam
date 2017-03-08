/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.SnackDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Order;
import model.Product;

/**
 *
 * @author peksu
 */
@WebServlet(name = "Controller",urlPatterns ={ "/home", "/selectCategory", "/selectProduct","/cancel","/Checkout","/newOrder" })

public class Controller extends HttpServlet {


protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doIt(request, response);
        } catch (SQLException ex) {
          
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doIt(request, response);
        } catch (SQLException ex) {
            
        }
    }

    protected void doIt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        switch (request.getServletPath()) {
            case "/selectCategory":
                    doNewCategory(request, response);
                   
                    break;
            case "/selectProduct":
                doSelectProduct(request, response);
                break;
            case "/cancel":
                doCancel(request,response);
                break;
            case "/Checkout":
                doCheckout(request, response);
                break;
            case "/newOrder":
                doNewOrder(request, response);
            default:
                response.getWriter().println(request.getServletPath());
        }
    }
    private void doNewCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Category category = (Category) request.getSession().getAttribute("category");
        
        if (category != null)
        {
            request.getSession().setAttribute("Error", "there was category");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
            //request.getRequestDispatcher("/selectProduct.jsp").forward(request, response);
        }
        else 
        {
            String categoryAsString = request.getParameter("category");
            int newcategoryid = Integer.parseInt(categoryAsString);
            category = SnackDA.getInstance().getCategory(newcategoryid);
            request.getSession().setAttribute("category", category);
            request.getRequestDispatcher("/selectProduct.jsp").forward(request, response);
        }
        
    }
    
    private void doSelectProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
  
        String producidasString = request.getParameter("product");
        int productId = Integer.parseInt(producidasString);
       
        Product product = SnackDA.getInstance().getProduct(productId);
        
       
        Order order = (Order) request.getSession().getAttribute("order");
            if (order == null) {
                order = new Order();
                request.getSession().setAttribute("order", order);
            }
            order.add(product);
            request.getSession().removeAttribute("category");
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        
        }
    
private void doCancel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    request.getSession().removeAttribute("category");
    request.getRequestDispatcher("/home.jsp").forward(request, response);
    
}

     private void doCheckout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         
         request.getRequestDispatcher("/checkout.jsp").forward(request, response);
         
     }
    
     private void doNewOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         request.getSession().removeAttribute("order");
         request.getRequestDispatcher("/home.jsp").forward(request, response);
     }
     
     
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
