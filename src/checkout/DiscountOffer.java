package checkout;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    private DiscountRule discount;

    public DiscountOffer(LocalDate expiryDate, Condition condition, DiscountRule discount) {
        super(expiryDate, condition);
        this.discount = discount;
    }

    @Override
    public void apply(Check check) {
        check.getDiscountCost(discount.getDiscount(check));
    }

    @Override
    public boolean isValid(Check check) {
        return this.condition.checkCondition(check);
    }
}
