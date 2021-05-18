package com.anne.savvy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anne.savvy.model.Ideation;

@Repository
public interface IdeationRepository extends JpaRepository<Ideation, Long>{

}
