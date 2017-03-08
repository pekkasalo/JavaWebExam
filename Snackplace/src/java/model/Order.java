/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peksu
 */
public class Order {
        private List<Product> products = new ArrayList<>();

    public void add(Product p) {
        products.add(p);
    }
    
    public double getPrice(){
        double sum = 0;
        for(Product p : products) sum+= p.getPrice();
        return sum;
    }
    
    public List<Product> getProducts(){
        return new ArrayList<>(products);
    }
    
    
}
