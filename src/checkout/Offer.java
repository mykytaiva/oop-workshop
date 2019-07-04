package checkout;

import java.time.LocalDate;

public abstract class Offer {

    private final LocalDate expiryDate;
    protected Condition condition;

    public Offer (LocalDate expiryDate, Condition condition) {
        this.expiryDate = expiryDate;
        this.condition = condition;
    }

    public void useSuitableCheck(Check check){
        if (this.isExpired() && isValid(check)) {
            apply(check);
            check.addOffer(this);
        }
    }

    public abstract void apply(Check check);
    public abstract boolean isValid(Check check);

    boolean isExpired() {
        return LocalDate.now().isBefore(this.expiryDate);
    }
}
