package art.vol.b1.reservation;

import org.springframework.stereotype.Service;

import art.vol.b1.common.PlusDePlaceException;
import art.vol.b1.common.VolIntrouvableException;
import art.vol.b1.vol.VolRepository;
import  org.springframework.transaction.annotation.Transactional;

@Service 
public class ReservationService {
    private final VolRepository volRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(VolRepository volRepository, ReservationRepository reservationRepository) {
        this.volRepository = volRepository;
        this.reservationRepository = reservationRepository;
    }
    @Transactional 
    public ReservationResponse creerReservation(Long volId, ReservationRequest reservationRequest) {
        var vol = volRepository.findById(volId)
                .orElseThrow(() -> new VolIntrouvableException(volId));

        if (vol.getPlacesDisponibles() <= 0) {
            throw new PlusDePlaceException(volId);
        }

        var reservation = new Reservation();
        reservation.setNomPassager(reservationRequest.nomPassager());
        reservation.setVol(vol);

        vol.setPlacesDisponibles(vol.getPlacesDisponibles() - 1);
        
        return ReservationResponse.from(reservationRepository.save(reservation));
    }


}
