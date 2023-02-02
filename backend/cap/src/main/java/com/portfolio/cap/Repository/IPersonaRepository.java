
package com.portfolio.cap.Repository;

import com.portfolio.cap.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona ,Long> {
    
}
