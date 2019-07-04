/*package checkout;

import java.time.LocalDate;

public class TrademarkOffer extends Offer {
    final Trademark trademark;
    final int requiredAmount;
    final int points;

    public TrademarkOffer(Trademark trademark, int requiredAmount, int points, LocalDate expiryDate) {
        super(expiryDate);
        this.trademark = trademark;
        this.requiredAmount = requiredAmount;
        this.points = points;
    }

    @Override
    public boolean isValid(Check check) {
        if (check.getSubCost(p -> p.trademark == this.trademark) >= requiredAmount) {
            return true;
        }
        return false;
    }

    @Override
    public void apply(Check check) {
        check.addPoints(this.points);
    }
}
*/