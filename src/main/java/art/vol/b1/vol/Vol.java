package art.vol.b1.vol;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDate;


@Entity 
public class Vol {
    
    @Id @GeneratedValue
    private Long id;
    private String numero;
    private String origine;
    private String destination;
    private LocalDate dateDepart;
    private int placesDisponibles;

    protected Vol() {
    }
    
    public Vol(String numero, String origine, String destination, LocalDate dateDepart, int placesDisponibles) {
        this.numero = numero;
        this.origine = origine;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.placesDisponibles = placesDisponibles;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getOrigine() {
        return origine;
    }
    public void setOrigine(String origine) {
        this.origine = origine;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }
    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }
}
