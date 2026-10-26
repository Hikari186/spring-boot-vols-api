package art.vol.b1.vol;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import art.vol.b1.common.VolIntrouvableException;

@WebMvcTest(VolController.class)
class VolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VolService volService;

    @Test
    void createVol_valide_renvoie201AvecLocation() throws Exception {
        var vol = new VolResponse(1L, "AF1234", "NCE", "CDG", LocalDate.of(2030, 1, 1), 100);
        when(volService.creerVol(any())).thenReturn(vol);

        mockMvc.perform(post("/api/vols")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"numero":"AF1234","origine":"NCE","destination":"CDG","dateDepart":"2030-01-01","placesDisponibles":100}
                    """))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/vols/1"))
            .andExpect(jsonPath("$.numero").value("AF1234"));
    }

    @Test
    void createVol_invalide_renvoie400AvecErreurs() throws Exception {
        mockMvc.perform(post("/api/vols")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"numero":"","origine":"nice","destination":"CDG","dateDepart":"2020-01-01","placesDisponibles":0}
                    """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.numero").exists())
            .andExpect(jsonPath("$.errors.origine").exists())
            .andExpect(jsonPath("$.errors.dateDepart").exists())
            .andExpect(jsonPath("$.errors.placesDisponibles").exists());

        verify(volService, never()).creerVol(any());
    }

    @Test
    void getVolById_inexistant_renvoie404() throws Exception {
        when(volService.getVolById(999L)).thenThrow(new VolIntrouvableException(999L));

        mockMvc.perform(get("/api/vols/999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.detail").value("Vol introuvable avec l'id : 999"));
    }
}
