/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.cap.Repository;

import com.portfolio.cap.Entity.Banner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RBanner extends JpaRepository<Banner, Integer>{
    public Optional<Banner> findByNombreB (String nombreB);
    public boolean existsByNombreB (String nombreB) ;
   
    
}
