package br.com.infnet.aula_Atjavafinal.controllers;

import br.com.infnet.aula_Atjavafinal.model.SnowboardingFeedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/feedback")

public class SnowboardingFeedbackController {
    Logger logger = LoggerFactory.getLogger(SnowboardingFeedbackController.class);
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SnowboardingFeedback snowboardingFeedback) {
        logger.info("Create Snowboarding Feedback: {}", snowboardingFeedback);

        // Simplesmente imprimir o feedback recebido no log
        logger.info("Received feedback: {}", snowboardingFeedback.getFeedback());
        logger.info("Received rating: {}", snowboardingFeedback.getRating());
        logger.info("Received additional data: {}", snowboardingFeedback.getAdditionalData());



        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
