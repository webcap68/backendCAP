
package com.portfolio.cap.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1 , max = 50, message = "longitud insuficiente")
    private String nombre;
    
    @NotNull
    @Size(min = 1 , max = 50, message = "longitud insuficiente")
    private String apellido;
    
     @NotNull
     private String descripcion;
    
  
    private String email;
    
    private String url_foto;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String descripcion, String email, String url_foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.email = email;
        this.url_foto = url_foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
    
    
}

