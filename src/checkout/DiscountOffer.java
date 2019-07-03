package checkout;

public class DiscountOffer extends Offer {
    private final Category category;

    public DiscountOffer(Category category, String expiryDate) {
        super(expiryDate);
        this.category = category;
    }

    @Override
    public void apply(Check check){
        for (Product product : check.getProducts()) {
            int productPrice = product.price;
            if (product.category == this.category) {
                check.getDiscountCost((int)(productPrice * 0.5));
            }
        }
    }
}
