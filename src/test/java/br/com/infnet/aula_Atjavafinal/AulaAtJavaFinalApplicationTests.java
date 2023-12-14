package br.com.infnet.aula_Atjavafinal;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import br.com.infnet.aula_Atjavafinal.controllers.SnowboardingController;
import br.com.infnet.aula_Atjavafinal.model.Snowboarding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AulaAtJavaFinalApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SnowboardingController snowboardingController;

    @Test
    public void testGetAllSnowboardings() throws Exception {
        mockMvc.perform(get("/snowboarding"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3)); // Verifique se há 3 snowboardings na resposta
    }
    @Test
    public void testCreateSnowboarding() throws Exception {
        Snowboarding newSnowboarding = new Snowboarding(4L, "NewBoard", "BrandNew");
        String requestBody = new ObjectMapper().writeValueAsString(newSnowboarding);

        mockMvc.perform(post("/snowboarding")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());

        // Verifique se o snowboarding foi adicionado à lista (Você pode fazer isso consultando o endpoint GET novamente)
    }
    @Test
    public void testUpdateSnowboarding() throws Exception {
        Snowboarding updatedSnowboarding = new Snowboarding(1L, "UpdatedBoard", "UpdatedBrand");
        String requestBody = new ObjectMapper().writeValueAsString(updatedSnowboarding);

        mockMvc.perform(put("/snowboarding/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNoContent());

        // Verifique se o snowboarding foi atualizado corretamente (Você pode fazer isso consultando o endpoint GET novamente)
    }
    @Test
    public void testDeleteSnowboarding() throws Exception {
        mockMvc.perform(delete("/snowboarding/{id}", 2L))
                .andExpect(status().isNoContent());

        // Verifique se o snowboarding foi removido corretamente (Você pode fazer isso consultando o endpoint GET novamente)
    }

    @Test
    public void testDeleteNonExistentSnowboarding() {
        // Tente excluir um snowboarding que não existe (deve lançar uma exceção 404)
        assertThrows(ResponseStatusException.class, () -> {
            snowboardingController.delete(100L); // 100L é um ID que não existe na lista
        });
    }

    }










