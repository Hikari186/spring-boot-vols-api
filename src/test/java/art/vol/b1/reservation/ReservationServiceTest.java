package art.vol.b1.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import art.vol.b1.common.PlusDePlaceException;
import art.vol.b1.common.VolIntrouvableException;
import art.vol.b1.vol.Vol;
import art.vol.b1.vol.VolRepository;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private VolRepository volRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void creerReservation_volInexistant_leveVolIntrouvable() {
        when(volRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                reservationService.creerReservation(99L, new ReservationRequest("Dupont")))
            .isInstanceOf(VolIntrouvableException.class);

        verify(reservationRepository, never()).save(any());
    }

    @Test
    void creerReservation_volComplet_levePlusDePlace() {
        var vol = new Vol("AF1234", "NCE", "CDG", LocalDate.now().plusDays(10), 0);
        when(volRepository.findById(1L)).thenReturn(Optional.of(vol));

        assertThatThrownBy(() ->
                reservationService.creerReservation(1L, new ReservationRequest("Dupont")))
            .isInstanceOf(PlusDePlaceException.class);

        verify(reservationRepository, never()).save(any());
    }

    @Test
    void creerReservation_placeDisponible_decrementeLesPlaces() {
        var vol = new Vol("AF1234", "NCE", "CDG", LocalDate.now().plusDays(10), 5);
        when(volRepository.findById(1L)).thenReturn(Optional.of(vol));
        when(reservationRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reservationService.creerReservation(1L, new ReservationRequest("Dupont"));

        assertThat(vol.getPlacesDisponibles()).isEqualTo(4);
        assertThat(response.nomPassager()).isEqualTo("Dupont");
    }
}
