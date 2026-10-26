package art.vol.b1.vol;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record VolRequest(
    @NotBlank(message = "Le numéro du vol est obligatoire") String numero,
    @NotBlank(message = "L'origine du vol est obligatoire") @Pattern(regexp ="[A-Z]{3}", message = "code aéroport de 3 lettres majuscules attendu") String origine,
    @NotBlank(message = "La destination du vol est obligatoire") @Pattern(regexp ="[A-Z]{3}", message = "code aéroport de 3 lettres majuscules attendu") String destination,
    @NotNull(message = "La date de départ du vol est obligatoire") @FutureOrPresent(message = "La date de départ doit être une date future ou présente") LocalDate dateDepart,
    @Positive(message = "Le nombre de places disponibles doit être positif") int placesDisponibles
) {

}
