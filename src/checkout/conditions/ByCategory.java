package checkout.conditions;

import checkout.Category;
import checkout.Check;

public class ByCategory implements Condition {

    private Category category;

    public ByCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean checkCondition(Check check) {
        return check.getSubCost(p -> p.getProductCategory() == this.category) != 0;
    }
}
