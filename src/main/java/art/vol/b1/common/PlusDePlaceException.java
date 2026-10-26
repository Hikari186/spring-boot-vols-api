package art.vol.b1.common;

public class PlusDePlaceException extends RuntimeException {
    public PlusDePlaceException(Long id) {
        super("Plus de place disponible pour le vol avec l'id : " + id);
    }

}
