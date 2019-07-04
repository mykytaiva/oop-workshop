package checkout;

import java.time.LocalDate;

public abstract class Offer {

    private final LocalDate expiryDate;

    public Offer (LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void useExpiredCheck(Check check){
        if (this.isExpired() && isValid(check)) {
            apply(check);
        }
    }

    public abstract void apply(Check check);
    public abstract boolean isValid(Check check);

    boolean isExpired() {
        return LocalDate.now().isBefore(this.expiryDate);
    }
}
