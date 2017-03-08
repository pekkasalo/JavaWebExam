<%-- 
    Document   : selectProduct
    Created on : 20-Jan-2017, 14:33:51
    Author     : peksu
--%>
<%@page import="model.Category"%>
<%@page import="data.SnackDA"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>SnackPlace | Place your order</title>
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">
    </head>
     <body>
        <div class="pseudoBody">
            <header></header>
            <nav>
                <ul>
                    <li><a href="cancel">Cancel</a></li>
                    <li><a href="Checkout">To checkout</a></li>
                  <%--  <li><a href="http://www.howest.be">Howest</a></li> --%>
                </ul>
            </nav>
            <main>
                <% Category category = (Category) session.getAttribute("category");
                    int i = category.getId();
                    %>
                <h1>Place your order</h1>
                
                      <c:forEach var="product" items="<%= SnackDA.getInstance().getProducts(i)%>">
                        <div class="templatemo_pizza_box">
                           
                            <div class="textbox">
                                <a href="selectProduct?product=${product.id}">  ${product.name}  </a></div>
                        </div>
                    </c:forEach>
                    
                <hr>
  <h2>  Your orders so far:  </h2>
                
                  <p class="text">
                      
                      <c:forEach var="product" items="${order.products}">
                          
                      <div class="textbox">
                          
                          <p>${product.name} ${product.price}€ </p>
                      </div>
                          
                      </c:forEach>
                      
                    You have ${fn:length(order.products)} items in your cart, and the total price is ${order.price}€
                      
                     
                    </p> 
                    
                    
                <%--    
                    <hr>
                    <hr>
                    
                    
                      <c:forEach var="product" items="<%= SnackDA.getInstance().getProducts(i)%>">
                        <div class="templatemo_pizza_box">
                           
                            <div class="textbox">
                                <a href="selectProduct?product=${product.id}">  ${product.name}  </a></div>
                        </div>
                    </c:forEach>
                --%>
                    
              
            </main>

            <footer>
                Images taken from <a href="http://thesnackplace.com/">thesnackplace.com</a> and <a href="http://www.hetfrietkot.be">hetfrietkot.be</a> | Exam Java Web Technologie 2016-2017 | First Session | Good Luck 
            </footer>
        </div>
    </body>
</html>
