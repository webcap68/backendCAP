/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Carlos
 */
public class dtoProyecto {
    @NotBlank
    private String nombre;
    @NotBlank
    @Lob
    private String descripcion;
    private String url;
    private String foto;
    private int ano;

    public dtoProyecto() {
    }

    public dtoProyecto(String nombre, String descripcion, String url, String foto, int ano) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.foto = foto;
        this.ano = ano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

  
    
    
    
}
