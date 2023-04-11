/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Controller;

import com.portfolio.cap.Dto.dtoProyecto;
import com.portfolio.cap.Entity.Proyecto;
import com.portfolio.cap.Security.Controller.Mensaje;
import com.portfolio.cap.Service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin (origins ={ "http://localhost:4200","https://frontendcap68.web.app"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto>  getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
            
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje ("no existe el ID"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("Dato de proyecto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto){
        if(StringUtils.isBlank(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre debe ser obligatorio"), HttpStatus.BAD_REQUEST);
        }
                         
        if (sProyecto.existsByNombre(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        //validaciones para descripcion
        if(StringUtils.isBlank(dtoproyecto.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo Descripción debe ser obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if (sProyecto.existsByDescripcion(dtoproyecto.getDescripcion())){
            return new ResponseEntity(new Mensaje("La descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        //
        Proyecto proyecto = new Proyecto (dtoproyecto.getNombre(), dtoproyecto.getDescripcion(), dtoproyecto.getUrl(), dtoproyecto.getFoto(), dtoproyecto.getAno() );
        
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje ("Dato de proyecto creado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto ){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje ("No existe el ID"), HttpStatus.NOT_FOUND);
        }
       // if(sProyecto.existsByNombreE(dtoproyecto.getNombreE()) && sProyecto.getByNombreE(dtoproyecto.getNombreE()).get().getId() !=id){
       //     return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
       // }
        
         if(StringUtils.isBlank(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //validaciones para descripcion
        // if(sProyecto.existsByDescripcionE(dtoproyecto.getDescripcionE()) && sProyecto.getByDescripcionE(dtoproyecto.getDescripcionE()).get().getId() !=id){
       //     return new ResponseEntity(new Mensaje("La  descripcion ya existe"), HttpStatus.BAD_REQUEST);
      //  }
        
         if(StringUtils.isBlank(dtoproyecto.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo descripcion no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //
         
         
         Proyecto proyecto = sProyecto.getOne(id).get();
         proyecto.setNombre(dtoproyecto.getNombre());
         proyecto.setDescripcion(dtoproyecto.getDescripcion());
         proyecto.setUrl(dtoproyecto.getUrl());
         proyecto.setFoto(dtoproyecto.getFoto());
         proyecto.setAno(dtoproyecto.getAno());
         
         sProyecto.save(proyecto);
         return new ResponseEntity(new Mensaje("Dato de proyecto actualizado"), HttpStatus.OK);
    }
}
