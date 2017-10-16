/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

public class Smoothie {
    
    private String nimi;
    private Integer id;

    
    public Smoothie(Integer id, String nimi) {
        this.nimi = nimi;
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public Integer getId() {
        return id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String toString() {
        return "id "+this.id+" nimi "+this.nimi;
    }
    
    
    
}
