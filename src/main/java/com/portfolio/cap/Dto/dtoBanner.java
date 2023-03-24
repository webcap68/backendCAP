/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Dto;


public class dtoBanner {
   
    
    private String nombreB;
    
    private String url_fotoB;

    public dtoBanner() {
    }

    public dtoBanner(String nombreB, String url_fotoB) {
        this.nombreB = nombreB;
        this.url_fotoB = url_fotoB;
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
