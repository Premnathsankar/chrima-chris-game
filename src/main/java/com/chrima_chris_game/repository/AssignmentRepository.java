package com.chrima_chris_game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrima_chris_game.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

	Optional<Assignment> findByGiverPhone(String giverPhone);

    Optional<Assignment> findByReceiverPhone(String receiverPhone);
    
    
    

}