import java.math.BigDecimal;

public class Main {


  public static void main(String[] args) {
    ProductManagement management = new ProductManagement();

    System.out.println("Part 1. Type = BOOK and price > 250");
    management.getByTypeAndPrice(ProductType.BOOK, BigDecimal.valueOf(250))
        .forEach(System.out::println);

    System.out.println("\nPart 2. Apply all discounts and show list");
    management.applyDiscounts()
        .forEach(System.out::println);

    System.out.println("\nPart 3. Type = BOOK and lowest price");
    System.out.println(management.getByTypeAndLowestPrice(ProductType.BOOK));

    System.out.println("\nPart 4. Last three added products");
    management.getLastThreeProducts()
        .forEach(System.out::println);

    System.out.println("\nPart 5. Overall price for BOOKs and added this year");
    System.out.println(management.calcOverallPriceBy(ProductType.BOOK, BigDecimal.valueOf(800)));

    System.out.println("\nPart 6. Group by type and show all products");
    management.groupByType().forEach((type, products) -> {
      System.out.println(type);
      products.forEach(System.out::println);
    });
  }
}
