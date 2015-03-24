/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ilja
 */
public class Shirt {
    private int id;
    private int cost;
    private String size;
    private String description;

    public Shirt(int id, int cost, String size, String description) {
        this.id = id;
        this.cost = cost;
        this.size = size;
        this.description = description;
    }
    
    

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "id = " + id + ", cost = "+cost+", size = "+size+", desc = "+ description; 
    }
    
    
    
    
}
