package checkout;

public class ByCategory implements Condition {

    private Category category;

    public ByCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean checkCondition(Check check) {
        return check.getSubCost(p -> p.category == this.category) != 0;
    }
}
