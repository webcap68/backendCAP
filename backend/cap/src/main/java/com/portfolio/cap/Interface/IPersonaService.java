
package com.portfolio.cap.Interface;

import com.portfolio.cap.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //guardar un objeto del tipo persona
    public void savePersona(Persona persona);
    
    //eliminar un objeto(usuario) pero lo buscamos por ID
    public void deletePersona(Long Id);
    
    //buscar una persona por ID
    public Persona findPersona(Long Id);
    
}
