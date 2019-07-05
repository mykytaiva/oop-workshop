package checkout.offers.discount;

import checkout.Check;
import checkout.conditions.Condition;
import checkout.offers.Offer;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    private DiscountStrategy discount;

    public DiscountOffer(LocalDate expiryDate, Condition condition, DiscountStrategy discount) {
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
