package checkout;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor, String expiryDate) {
        super(expiryDate);
        this.category = category;
        this.factor = factor;
    }

    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));

        if (points != 0) {
            check.addOffer(this);
        }
    }
}
