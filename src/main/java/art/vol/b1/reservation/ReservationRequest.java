package art.vol.b1.reservation;

import jakarta.validation.constraints.NotBlank;

public record ReservationRequest(
    @NotBlank(message = "Le nom du passager est obligatoire") String nomPassager
) {

}
