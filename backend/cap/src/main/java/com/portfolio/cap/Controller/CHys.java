/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.cap.Controller;

import com.portfolio.cap.Dto.dtoHys;
import com.portfolio.cap.Entity.Hys;
import com.portfolio.cap.Security.Controller.Mensaje;
import com.portfolio.cap.Service.Shys;
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

@CrossOrigin (origins ={ "http://localhost:4200","https://institutoleonxiii.edu.ar"})
@RequestMapping("/skill")
public class CHys {
    @Autowired
    Shys shys;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Hys hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){
       //validacion por campo de nombre en blanco
        //el isBlank pertenece a la dependencia de apache commons agregada al pom
        if (StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        //validacion por si ya existe el nombre de skill
        if(shys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El skill ya existe"), HttpStatus.BAD_REQUEST );
        
        //guardamos el objeto de experiencia nueva en la BD
        Hys hys = new Hys(dtohys.getNombre(),dtohys.getPorcentaje());
        shys.save(hys);
        
        return new ResponseEntity(new Mensaje("Skill agregado a la base"), HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        // validacion de si existe el ID
        if (!shys.existsById(id))
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de skills
        if(shys.existsByNombre(dtohys.getNombre()) 
                && shys.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("El skill ya existe"), HttpStatus.BAD_REQUEST);
        //Validacion para chequear campo vacio
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        //chequeo de campo porcentaje vacio
         if(StringUtils.isBlank(String.valueOf(dtohys.getPorcentaje())))
            return new ResponseEntity(new Mensaje("El porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
        
        //Si pasa todas las validaciones se crea un objeto de skill nuevo
        Hys hys = shys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje(dtohys.getPorcentaje());
        
        shys.save(hys);
        return new ResponseEntity (new Mensaje("Skill actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") int id){
          // validacion de si existe el ID
        if (!shys.existsById(id))
            return new ResponseEntity(new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        shys.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }
    
}
