package art.vol.b1.vol;

import java.time.LocalDate;

public record VolResponse( 
    
    Long id,
    String numero,
    String origine,
    String destination,
    LocalDate dateDepart,
    int placesDisponibles
) {
    public static VolResponse from(Vol vol) {
        return new VolResponse(
            vol.getId(),
            vol.getNumero(),
            vol.getOrigine(),
            vol.getDestination(),
            vol.getDateDepart(),
            vol.getPlacesDisponibles()
        );
    }

}
