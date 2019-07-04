/*package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    private int points;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiryDate) {
        super(expiryDate);
        this.category = category;
        this.factor = factor;
    }

    @Override
    public boolean isValid(Check check) {
        points = check.getSubCost(p -> p.category == this.category);
        return points != 0;
    }

    @Override
    public void apply(Check check) {
        check.addOffer(this);
        check.addPoints(points * (this.factor - 1));
    }
}
*/