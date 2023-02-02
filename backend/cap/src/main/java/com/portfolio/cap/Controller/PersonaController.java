
package com.portfolio.cap.Controller;

import com.portfolio.cap.Entity.Persona;
import com.portfolio.cap.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;//
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:4200")//
public class PersonaController {
   @Autowired IPersonaService ipersonaService;
   
   @GetMapping ("personas/traer")
   public List<Persona> getPersona(){
       return ipersonaService.getPersona();
   }
   
   @PostMapping ("personas/crear")
   public String createPersona(@RequestBody Persona persona){
       ipersonaService.savePersona(persona);
       return "la persona fue creada OK";
   }
   
   @DeleteMapping ("/personas/borrar/{id}")
   public String deletePersona (@PathVariable Long id){
       ipersonaService.deletePersona(id);
       return "la persona fue dado de baja";
       
   }
   //URL:Puerto/personas/editar/4/nombre & apellido & img
   @PutMapping ("/personas/editar/{id}")
   public Persona editPersona(@PathVariable Long id,
                                                @RequestParam("nombre") String nuevoNombre,
                                                @RequestParam("apellido") String nuevoApellido,
                                                @RequestParam("url_foto") String nuevoUrl_foto){
       Persona persona = ipersonaService.findPersona(id);
       persona.setNombre(nuevoNombre);
       persona.setApellido(nuevoApellido);
       persona.setUrl_foto(nuevoUrl_foto);
       ipersonaService.savePersona(persona);
       return persona;
   }
   
   @GetMapping ("/personas/traer/perfil")
   public Persona findPersona(){
       return ipersonaService.findPersona((long)2);
   }
   
}


