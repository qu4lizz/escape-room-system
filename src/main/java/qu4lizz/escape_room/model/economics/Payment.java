package qu4lizz.escape_room.model.economics;

public class Payment {
    private float price;
    private float paid;
    private float change;

    public Payment(float price, float paid) {
        this.price = price;
        this.paid = paid;
        this.change = paid - price;
    }

    public float getPrice() {
        return price;
    }
}
