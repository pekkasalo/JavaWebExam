<%-- 
    Document   : home
    Created on : 20-Jan-2017, 14:05:12
    Author     : peksu
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="data.SnackDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <li><a href="Checkout">To checkout</a></li>
                  <%--  <li><a href="http://www.howest.be">Howest</a></li> --%>
                </ul>
            </nav>
            <main>
                <h1>Place your order</h1>
                
                      <c:forEach var="category" items="<%= SnackDA.getInstance().getCategories()%>">
                        <div class="templatemo_pizza_box">
                           
                            <div class="textbox">
                                <a 
                                    
                                     href="selectCategory?category=${category.id}">  ${category.name}  </a></div>
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
               
            </main>

            <footer>
                Images taken from <a href="http://thesnackplace.com/">thesnackplace.com</a> and <a href="http://www.hetfrietkot.be">hetfrietkot.be</a> | Exam Java Web Technologie 2016-2017 | First Session | Good Luck 
            </footer>
        </div>
    </body>
</html>
