package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.economics.Reservation;

import java.util.List;

public interface ReservationDataAccess {
    boolean addReservation(Reservation reservation);
    boolean updateReservation(Reservation reservation);
    boolean deleteReservation(Reservation reservation);
    List<Reservation> getReservations();
}
