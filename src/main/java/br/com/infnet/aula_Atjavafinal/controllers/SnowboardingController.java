package br.com.infnet.aula_Atjavafinal.controllers;

import br.com.infnet.aula_Atjavafinal.model.Snowboarding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snowboarding")
public class SnowboardingController {

    Logger logger = LoggerFactory.getLogger(SnowboardingController.class);
    List<Snowboarding> snowboardings = initsnowboardings();

    private List<Snowboarding> initsnowboardings() {
        Snowboarding flyngv = new Snowboarding(1L, "FlyngV", "Burton");
        Snowboarding cachamber = new Snowboarding(2L, "Cachamber", "Salomon");
        Snowboarding powederz = new Snowboarding(3L, "Powderz", "k2");
        ArrayList<Snowboarding> snowboardings = new ArrayList<>();
        snowboardings.add(flyngv);
        snowboardings.add(cachamber);
        snowboardings.add(powederz);
        return snowboardings;

    }
    @GetMapping
    public List<Snowboarding> getAll(){
        logger.info("Get All Snowboardings");
        return snowboardings;
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Snowboarding snowboarding) {
        logger.info("Create Snowboarding: {}", snowboarding);
        snowboardings.add(snowboarding);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Snowboarding updatedSnowboarding) {
        logger.info("Update Snowboarding with ID: {}", id);

        // Encontre o snowboarding com o ID fornecido na lista em memória
        Optional<Snowboarding> existingSnowboarding = snowboardings.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (existingSnowboarding.isPresent()) {
            // Atualize os campos do snowboarding existente com os valores do snowboarding atualizado
            Snowboarding snowboardingToUpdate = existingSnowboarding.get();
            snowboardingToUpdate.setName(updatedSnowboarding.getName());
            snowboardingToUpdate.setBrand(updatedSnowboarding.getBrand());
            return ResponseEntity.noContent().build();
        } else {
            // Se o snowboarding com o ID fornecido não foi encontrado, retorne um status HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Delete Snowboarding with ID: {}", id);

        // Encontre e remova o snowboarding com o ID fornecido da lista em memória
        boolean removed = snowboardings.removeIf(s -> s.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            // Se o snowboarding com o ID fornecido não foi encontrado, retorne um status HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
