/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Controller;

import com.portfolio.cap.Dto.dtoEducacion;
import com.portfolio.cap.Entity.Educacion;
import com.portfolio.cap.Security.Controller.Mensaje;
import com.portfolio.cap.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")

@CrossOrigin (origins ={ "http://localhost:4200","https://institutoleonxiii.edu.ar"})
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion>  getById(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
            
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje ("no existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Dato de educación eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre debe ser obligatorio"), HttpStatus.BAD_REQUEST);
        }
                         
        if (sEducacion.existsByNombreE(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        //validaciones para descripcion
        if(StringUtils.isBlank(dtoeducacion.getDescripcionE())){
            return new ResponseEntity(new Mensaje("El campo Descripción debe ser obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if (sEducacion.existsByDescripcionE(dtoeducacion.getDescripcionE())){
            return new ResponseEntity(new Mensaje("La descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        //
        Educacion educacion = new Educacion (dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
        
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje ("Dato de educación creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion ){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje ("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El campo no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //validaciones para descripcion
         if(sEducacion.existsByDescripcionE(dtoeducacion.getDescripcionE()) && sEducacion.getByDescripcionE(dtoeducacion.getDescripcionE()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("La  descripcion ya existe"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(dtoeducacion.getDescripcionE())){
            return new ResponseEntity(new Mensaje("El campo descripcion no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //
         
         
         Educacion educacion = sEducacion.getOne(id).get();
         educacion.setNombreE(dtoeducacion.getNombreE());
         educacion.setDescripcionE(dtoeducacion.getDescripcionE());
         
         sEducacion.save(educacion);
         return new ResponseEntity(new Mensaje("Dato de educación actualizado"), HttpStatus.OK);
    }
}