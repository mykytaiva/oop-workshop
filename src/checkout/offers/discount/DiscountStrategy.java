package checkout.offers.discount;

import checkout.Check;

public interface DiscountStrategy {
    int getDiscount(Check check);
}
