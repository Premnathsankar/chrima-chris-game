package com.chrima_chris_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrima_chris_game.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    Assignment findByGiverPhone(String giverPhone);

    boolean existsByGiverPhone(String giverPhone);
    
    
    

}