package br.com.infnet.aula_Atjavafinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class ApiExternaCountries {
    private String name;
    private String capital;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("capital")
    public String getCapital() {
        return capital;
    }
}
