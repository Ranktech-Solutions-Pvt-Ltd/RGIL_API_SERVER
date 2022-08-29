package com.rank.rgil.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rank.rgil.entities.ModuleMst;

/**
 *
 * @author Arunabha
 */
public interface ModuleMstService extends JpaRepository<ModuleMst, Long> {
	
    List<ModuleMst> findByName(String moduleName);//chayan
    
}
