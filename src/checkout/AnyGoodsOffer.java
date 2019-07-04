package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expiryDate) {
        super(expiryDate);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public boolean isValid(Check check){
        if (this.totalCost <= check.getTotalCost()) {
            return true;
        }
        return false;
    }

    @Override
    public void apply(Check check) {
        check.addPoints(this.points);
        check.addOffer(this);
    }
}
