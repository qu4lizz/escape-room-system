package qu4lizz.escape_room.data;

public interface ReservationDataAccess {
    boolean addReservation(int reservationId);
    boolean updateReservation(int reservationId);
    boolean deleteReservation(int reservationId);
}
