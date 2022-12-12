package com.krzysztof.computerprice.controller;

import com.krzysztof.computerprice.entity.Computer;
import com.krzysztof.computerprice.service.ComputerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PostMapping()
    public ResponseEntity<Computer> createComputer(@RequestBody Computer computer) {
        return ResponseEntity.ok(computerService.createComputer(computer));

    }

    @GetMapping
    public ResponseEntity<List<Computer>> getComputers() {
        return ResponseEntity.ok(computerService.getComputers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findComputerById(@PathVariable int id) {
        Optional<Computer> computer = computerService.findById(id);

        if (computer.isPresent()) {
            return ResponseEntity.ok(computer.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{part}")
    public ResponseEntity<?> findComputersByNamePart(@PathVariable String part) {
        List<Computer> computers = new ArrayList<>();
        computers = computerService.findAllByNamePart(part);

        if (computers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(computers);

    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> findComputersByDate(@PathVariable String date) {
        List<Computer> computers = new ArrayList<>();
        computers = computerService.findAllByDate(date);

        if (computers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(computers);

    }

    @GetMapping("/{dir}/{property}")
    public ResponseEntity<?> findWithSorting(@PathVariable String dir, @PathVariable String property) {
        List<Computer> computers = computerService.sortListByGivenProperty(dir, property);

        if (computers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(computers);
        }


    }


}
