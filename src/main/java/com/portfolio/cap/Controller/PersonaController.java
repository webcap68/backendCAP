
package com.portfolio.cap.Controller;

import com.portfolio.cap.Dto.dtoPersona;
import com.portfolio.cap.Entity.Persona;
import com.portfolio.cap.Security.Controller.Mensaje;
import com.portfolio.cap.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins ={ "http://localhost:4200","https://frontendcap68.web.app"})
@RequestMapping("/personas" )
public class PersonaController {
   @Autowired ImpPersonaService personaService;
   
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona>  getById(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
            
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje ("no existe el ID"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }
    */
    
    /*@PostMapping("/create")
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
        
        personaService.save(educacion);
        return new ResponseEntity(new Mensaje ("Dato de educación creada"), HttpStatus.OK);
    }
    */
    @PutMapping("/update/{id}")
     
		
              
	
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona ){
         System.out.println("descripcion: " + dtopersona.getDescripcion());
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje ("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //validaciones para descripcion
        if(personaService.existsByDescripcion(dtopersona.getDescripcion()) && personaService.getByDescripcion(dtopersona.getDescripcion()).get().getId() !=id){
           return new ResponseEntity(new Mensaje("La  descripcion ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtopersona.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo descripcion no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //validaciones para apellido
          if(personaService.existsByApellido(dtopersona.getApellido()) && personaService.getByApellido(dtopersona.getApellido()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("El apellido ya existe"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(dtopersona.getApellido())){
            return new ResponseEntity(new Mensaje("El campo apellido no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
          //validaciones para email
          if(personaService.existsByEmail(dtopersona.getEmail()) && personaService.getByEmail(dtopersona.getEmail()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
        }
        
         if(StringUtils.isBlank(dtopersona.getEmail())){
            return new ResponseEntity(new Mensaje("El campo email no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
         
         //
        
         Persona persona = personaService.getOne(id).get();
         
         persona.setDescripcion(dtopersona.getDescripcion());
         persona.setNombre(dtopersona.getNombre());
         persona.setApellido(dtopersona.getApellido());
         persona.setEmail(dtopersona.getEmail());
         persona.setUrl_foto(dtopersona.getUrl_foto());
  
         personaService.save(persona);
         
         return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
  }
    
    /*
    @PreAuthorize("hasRole('USER')")
    @GetMapping ("personas/traer")
   public List<Persona> getPersona(){
       return ipersonaService.getPersona();
   }
   
   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping ("personas/crear")
  public String createPersona(@RequestBody Persona persona){
     ipersonaService.savePersona(persona);
    return "la persona fue creada OK";
   }
   
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping ("/personas/borrar/{id}")
  public String deletePersona (@PathVariable Long id){
     ipersonaService.deletePersona(id);
     return "la persona fue dado de baja";
       
  }
  URL:Puerto/personas/editar/4/nombre & apellido & img
  @PreAuthorize("hasRole('ADMIN')")
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
   

*/
    

