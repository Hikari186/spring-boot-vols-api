package art.vol.b1.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import art.vol.b1.vol.Vol;
import jakarta.persistence.FetchType;

@Entity 
public class Reservation {

    @Id @GeneratedValue
    private Long id;
    private String nomPassager;
    @ManyToOne(fetch = FetchType.LAZY) 
    private Vol vol;

    protected Reservation() {
    }
    
    public Reservation(String nomPassager, Vol vol) {
        this.nomPassager = nomPassager;
        this.vol = vol;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNomPassager(String nomPassager) {
        this.nomPassager = nomPassager;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
    public Long getId() {
        return id;
    }
    public String getNomPassager() {
        return nomPassager;
    }
    public Vol getVol() {
        return vol;
    }
}