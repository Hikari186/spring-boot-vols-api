package art.vol.b1.reservation;

public record ReservationResponse(
    Long id,
    String nomPassager,
    Long volId
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
            reservation.getId(),
            reservation.getNomPassager(),
            reservation.getVol().getId()
        );
    }
}
