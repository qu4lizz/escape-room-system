package qu4lizz.escape_room.data;

import qu4lizz.escape_room.model.economics.Payment;

import java.util.List;

public interface GeneralDataAccess {
    List<Payment> getPayments();
}
