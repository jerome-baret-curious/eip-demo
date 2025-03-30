package dev.jerome.baret.eipdemo.repositories;

import dev.jerome.baret.eipdemo.entities.InputData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDataRepository extends JpaRepository<InputData, Long> {

}