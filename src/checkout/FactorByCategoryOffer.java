package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor, LocalDate expiryDate) {
        super(expiryDate);
        this.category = category;
        this.factor = factor;
    }

    @Override
    public boolean isValid(Check check) {
        int points = check.getSubCost(p -> p.category == this.category);
        check.addPoints(points * (this.factor - 1));

        if (points != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void apply(Check check) {
        check.addOffer(this);
    }
}
