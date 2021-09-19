
package dao;

import Model.DatabaseConnector;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    public boolean update(int product_quantity, int product_id){
        Connection conn = DatabaseConnector.connect();
        String sql = "update products set product_quantity = ? where product_id = ?";  
                
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, product_quantity);
            pst.setInt(2, product_id);
            int rs = pst.executeUpdate();
            return rs > 0 ? true:false;
        
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
     
    return false;
    } 
    
     public List<Product> getall(){
        List<Product> products = new ArrayList<>();
        Connection conn = DatabaseConnector.connect();
        String sql = "select * from products";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            
             ResultSet rs = pst.executeQuery();
             
             
            while(rs.next()){
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("Product_name");
                int product_quantity = rs.getInt("Product_quantity");
                int  product_price  = rs.getInt("Product_price");
                
                Product product = new Product(product_id,product_name,product_quantity,product_price);
                products.add(product);
                
            
            
            
            }
        
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
     
    return products;
    } 
    
    
}
