<%-- 
    Document   : checkout
    Created on : 20-Jan-2017, 16:01:17
    Author     : peksu
--%>

<%@page import="model.Product"%>
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
                   
                    <li><a href="newOrder">New Order</a></li>
                  <%--  <li><a href="http://www.howest.be">Howest</a></li> --%>
                </ul>
            </nav>
            <main>
                <h1>Thank you for your order</h1>
                
                <p>You have ordered following products: </p>
                
         
           
                    
               <table>
            
            <tr>
                <th>Product</th><th>Price</th>
                </tr>
              
                      <c:forEach var="product" items="${order.products}">
                          
                      <tr>
                        <td>${product.name}</td>
                        <td> ${product.price}€ </td>
                       
                      </tr>
                    
                      </c:forEach>
                        <tr>
                          <th>Total: </th>
                          <td>${order.price}€ </td>
                      </tr>
                    
          
            
           
        </table>

              
            </main>

            <footer>
                Images taken from <a href="http://thesnackplace.com/">thesnackplace.com</a> and <a href="http://www.hetfrietkot.be">hetfrietkot.be</a> | Exam Java Web Technologie 2016-2017 | First Session | Good Luck 
            </footer>
        </div>
    </body>
</html>