/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.cap.Repository;


import com.portfolio.cap.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer>{
    public Optional<Proyecto> findByNombre (String nombre);
    public boolean existsByNombre (String nombre) ;
   //agregado para descripcion 
    public Optional<Proyecto> findByDescripcion (String descripcion);
    public boolean existsByDescripcion (String descripcion) ;
    
}
