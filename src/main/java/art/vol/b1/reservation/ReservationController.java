package art.vol.b1.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController @RequestMapping("/api/vols/{volId}/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@PathVariable Long volId, @Valid  @RequestBody ReservationRequest reservationRequest) {
       ReservationResponse reservation = reservationService.creerReservation(volId, reservationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }
}
