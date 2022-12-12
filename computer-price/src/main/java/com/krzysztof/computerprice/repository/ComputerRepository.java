package com.krzysztof.computerprice.repository;

import com.krzysztof.computerprice.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

    Optional<Computer> findByNameIgnoreCase(String name);

    List<Computer> findAllByNameIgnoreCaseContains(String part);

    List<Computer> findAllByPostingDate(LocalDate postingDate);


}
