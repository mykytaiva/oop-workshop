package checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountOffer extends Offer {
    private final Category category;
    private List<Product> suitableProducts = new ArrayList<>();

    public DiscountOffer(Category category, LocalDate expiryDate) {
        super(expiryDate);
        this.category = category;
    }

    @Override
    public boolean isValid(Check check) {
        for (Product product : check.getProducts()) {
            if (product.category == this.category) {
                suitableProducts.add(product);
            }
        }

        if (!suitableProducts.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void apply(Check check){
        for (Product product: suitableProducts) {
            check.getDiscountCost((int)(product.price * 0.5));
        }
        suitableProducts.clear();
    }
}
