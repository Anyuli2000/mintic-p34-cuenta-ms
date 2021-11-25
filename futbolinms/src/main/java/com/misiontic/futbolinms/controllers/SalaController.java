package com.misiontic.futbolinms.controllers;

import com.misiontic.futbolinms.exceptions.AccountNotFoundException;
import com.misiontic.futbolinms.models.Sala;
import com.misiontic.futbolinms.repositories.SalaRepository;
import org.springframework.web.bind.annotation.*;

@RestController

public class SalaController {
    private final SalaRepository salaRepository;

    public SalaController(SalaRepository salaRepository) {this.salaRepository = salaRepository;}

    @PostMapping ("/salas")
    Sala newSala(@RequestBody Sala sala){
        return salaRepository.save(sala);
    }


    @GetMapping ("/salas/{id}")
    Sala traerSala (@PathVariable String id){
        return salaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("No se encontro una cuenta con el username: " + id));
    }

    @PutMapping("/salas/{id}")
    Sala editarSala (@PathVariable String id, @RequestBody Sala salaEditar){
        Sala salas = salaRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("No se encontro la sala: " + id));
        
        salas.setMarcador1(salaEditar.getMarcador1());
        salas.setMarcador2(salaEditar.getMarcador2());
        Sala editarSala = salaRepository.save(salas);
        return salaRepository.save(editarSala);
    }
    
    @DeleteMapping ("/salas/{id}")
    void borrarSala (@PathVariable String id) {
            Sala salita = salaRepository.findById(id).orElse(null);
            if (salita == null)
                throw new AccountNotFoundException("No se encontro la cuenta: " + salita +" para poder realizar la apuesta");

            salaRepository.delete(salita);

            return ;
        }


    }

