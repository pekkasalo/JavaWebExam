package data;

import model.Category;
import model.Product;
import util.SnackException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frederic
 */
public class SnackDA
{
   
    
    private static final String URL = "jdbc:mysql://localhost/snackplace";
    private static final String UID = "snack";
    private static final String PWD = "place";
    private static SnackDA instance;
    
    private Connection connection;
    
    public static SnackDA getInstance()
    {
        if (instance == null)
        {
            instance = new SnackDA();
        }
        
        return instance;
    }
    
    private SnackDA()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, UID, PWD);
        }
        catch (ClassNotFoundException ex)
        {
            throw new SnackException("Unable to register database driver.", ex);
        }
        catch (SQLException ex)
        {
            throw new SnackException("Unable to open database connection.", ex);
        }
    }
    
    public static final String SQL_SELECT_CATEGORIES     = "select * from category";
    public static final String SQL_SELECT_CATEGORY_BY_ID = "select * from category where id = ?";
    public static final String SQL_SELECT_PRODUCTS_BY_CATEGORY_ID = "select * from product where category_id = ?";
    public static final String SQL_SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
    public static final String SQL_INSERT_LOG_ENTRY = "insert into logentry(message) values(?)";
    
    public List<Category> getCategories()
    {
       try {
           List<Category> categories = new ArrayList<>();
           String sql = SQL_SELECT_CATEGORIES;
           PreparedStatement prep = this.connection.prepareStatement(sql);
           
          ResultSet rs = prep.executeQuery();
          
          while (rs.next() == true )
          {
              Category category = new Category(rs.getInt("id"), rs.getString("name"));
              categories.add(category);
          }
          
          prep.close();
          rs.close();
          return categories;
           
       }
       catch (SQLException ex)
       {
           throw new SnackException("cannot retrieve categories", ex );
       }
    }
    
    public Category getCategory(int id)
    {
        try {
            
            String sql = SQL_SELECT_CATEGORY_BY_ID;
            
            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            
            if (rs.next())
            {
                Category category = new Category(rs.getInt("id"), rs.getString("name") );
            
            rs.close();
            prep.close();
            return category;
            }
            else {
                rs.close();
                prep.close();
                throw new SnackException("Could not find the Category");
            }
        }
        catch (SQLException ex)
        {
            throw new SnackException("Cannot get category from database",ex);
        }
    }
    
    public List<Product> getProducts(int categoryId)
    {
        try {
          List<Product> toppings = new ArrayList<>();
          
          String sql = SQL_SELECT_PRODUCTS_BY_CATEGORY_ID;
          PreparedStatement prep = this.connection.prepareStatement(sql);
          prep.setInt(1,categoryId);
          
          ResultSet rs = prep.executeQuery();
          
          while (rs.next())
          {
              Product topping = new Product(rs.getInt("id"), rs.getString("name"),rs.getDouble("price"));
              toppings.add(topping);
          }
          return toppings;
      
      }
      catch (SQLException ex)
      {
          throw new SnackException("cannot get products for pizza", ex);
      }
    }
    
      public List<Product> getProducts2()
    {
        try {
          List<Product> toppings = new ArrayList<>();
          
          String sql = "select * from product";
          PreparedStatement prep = this.connection.prepareStatement(sql);
         
          
          ResultSet rs = prep.executeQuery();
          
          while (rs.next())
          {
              Product topping = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
              toppings.add(topping);
          }
          return toppings;
      
      }
      catch (SQLException ex)
      {
          throw new SnackException("cannot get products for pizza", ex);
      }
    }
    
    
    public Product getProduct(int productId)
    {
        try {
            
            String sql = SQL_SELECT_PRODUCT_BY_ID;
            
            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.setInt(1, productId);
            ResultSet rs = prep.executeQuery();
            
            if (rs.next())
            {
               Product topping = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            
            rs.close();
            prep.close();
            return topping;
            }
            else {
                rs.close();
                prep.close();
                throw new SnackException("Could not find the product");
            }
        }
        catch (SQLException ex)
        {
            throw new SnackException("Cannot get product from database",ex);
        }
    }
    
    public void addLogEntry(String message)
    {
        
             try {
       String sql ="insert into logentry(message) values(?)";
       
       PreparedStatement prep = this.connection.prepareStatement(sql);
       
       prep.setString(1, message);
       
       prep.executeUpdate();
       prep.close();
       
       }
       catch (SQLException ex)
       {
           throw new SnackException("Unable to add new log", ex );
       }
    }
    
}
