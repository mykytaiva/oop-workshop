package checkout.offers.discount;

import checkout.Category;
import checkout.Check;

public class DiscountByPercents implements DiscountStrategy {
    private int percent;
    private Category category;

    public DiscountByPercents(int percent, Category category) {
        this.percent = percent;
        this.category = category;
    }

    @Override
    public int getDiscount(Check check) {
        int subCost = check.getSubCost(p -> p.getProductCategory() == this.category);
        return (subCost * percent) / 100;
    }
}
