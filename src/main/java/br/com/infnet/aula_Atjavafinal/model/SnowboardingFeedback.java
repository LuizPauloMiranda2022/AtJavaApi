package br.com.infnet.aula_Atjavafinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SnowboardingFeedback {
    private String feedback;
    private int rating;
    private Object additionalData;
}
