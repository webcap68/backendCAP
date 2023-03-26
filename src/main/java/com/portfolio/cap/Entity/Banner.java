/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
     @NotNull
    private String nombreB;
    
    private String url_fotoB;

    public Banner() {
    }

    public Banner(String nombreB, String url_fotoB) {
        this.nombreB = nombreB;
        this.url_fotoB = url_fotoB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreB() {
        return nombreB;
    }

    public void setNombreB(String nombreB) {
        this.nombreB = nombreB;
    }

    public String getUrl_fotoB() {
        return url_fotoB;
    }

    public void setUrl_fotoB(String url_fotoB) {
        this.url_fotoB = url_fotoB;
    }

   
    
   
}
