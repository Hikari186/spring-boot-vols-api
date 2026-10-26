package art.vol.b1.common;

public class VolIntrouvableException extends RuntimeException {
    
    public VolIntrouvableException(Long id) {
        super("Vol introuvable avec l'id : " + id);
    }

}
