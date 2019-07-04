package checkout;

public class DiscountByPercents implements DiscountRule {
    @Override
    public int getDiscount(Check check) {
        return 0;
    }
}
