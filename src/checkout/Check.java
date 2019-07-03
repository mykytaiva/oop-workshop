package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private List<Offer> usedOffers = new ArrayList<>();
    private int points = 0;
    private int totalCost = 0;

    public int getTotalCost() {
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
        totalCost += product.price;
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, (a, b) -> a + b);
    }

    void addOffer(Offer offer) {
        usedOffers.add(offer);
    }

    public int getUsedOffers() {
        return usedOffers.size();
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void getDiscountCost(int price) {
        this.totalCost -= price;
    }

}
