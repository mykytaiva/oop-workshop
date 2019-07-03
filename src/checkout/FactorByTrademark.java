package checkout;

public class FactorByTrademark extends Offer {
    final Trademark trademark;
    final int requiredAmount;
    final int points;

    public FactorByTrademark(Trademark trademark, int requiredAmount, int points, String expiryDate) {
        super(expiryDate);
        this.trademark = trademark;
        this.requiredAmount = requiredAmount;
        this.points = points;
    }

    @Override
    public void apply(Check check) {
        if (check.getCostByTrademark(this.trademark) >= requiredAmount) {
            check.addPoints(this.points);
        }
    }
}
