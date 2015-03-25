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
public class ShirtForm {

    private String id;
    private String cost;
    private String size;
    private String description;

    public ShirtForm(String id, String cost, String size, String description) {
        this.id = id;
        this.cost = cost;
        this.size = size;
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "id = " + id + ", cost = " + cost + ", size = " + size + ", desc = " + description;
    }
    
    public Shirt toShirt(){
        return new Shirt(Integer.parseInt(id), Integer.parseInt(cost), size, description);
    }

}
