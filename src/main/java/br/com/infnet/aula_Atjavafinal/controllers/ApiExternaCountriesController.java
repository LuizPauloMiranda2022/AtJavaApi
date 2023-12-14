package br.com.infnet.aula_Atjavafinal.controllers;

import br.com.infnet.aula_Atjavafinal.model.ApiExternaCountries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/apiexternacountriescapital")
public class ApiExternaCountriesController {
    private static final String EXTERNAL_API_URL = "https://restcountries.com/v3.1/all";

    Logger logger = LoggerFactory.getLogger(ApiExternaCountriesController.class);

    @GetMapping
    public ResponseEntity<?> getCountriesAndCapitals() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(EXTERNAL_API_URL, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                ApiExternaCountries[] countriesAndCapitals = objectMapper.readValue(responseBody, ApiExternaCountries[].class);

                if (countriesAndCapitals != null) {
                    for (ApiExternaCountries item : countriesAndCapitals) {
                        logger.info("Country: {}, Capital: {}", item.getName(), item.getCapital());
                    }
                } else {
                    logger.warn("No data received from the external API.");
                }
            } else {
                logger.error("Failed to fetch data from the external API. Status code: {}", responseEntity.getStatusCodeValue());
            }

            return responseEntity;
        } catch (HttpClientErrorException ex) {
            logger.error("Error while making a request to the external API: {}", ex.getMessage());
            return ResponseEntity.status(ex.getStatusCode()).build();
        } catch (Exception e) {
            logger.error("Error while processing the response from the external API: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

