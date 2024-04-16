import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductManagement {

  private final List<Product> catalog = new ArrayList<>();

  public void addProduct(Product product) {
    catalog.add(product);
  }

  public List<Product> getByTypeAndPrice(ProductType type, BigDecimal price) {
    return catalog.stream()
        .filter(p -> p.getType() == type && p.getPrice().compareTo(price) > 0)
        .toList();
  }

  public Product getByTypeAndLowestPrice(ProductType type) {
    return catalog.stream()
        .filter(p -> p.getType() == type && p.getPrice() != null)
        .min(Comparator.comparing(Product::getPrice))
        .orElseThrow(() -> new RuntimeException("No " + type + " products found"));
  }

  public List<Product> applyDiscounts() {
    return catalog.stream()
        .filter(product -> product.getType() == ProductType.BOOK)
        .filter(product -> product.getDiscount() != null && product.getDiscount().compareTo(BigDecimal.ZERO) > 0)
        .map(product -> product.withPrice(calcDiscount(product)))
        .toList();
  }

  private BigDecimal calcDiscount(Product product) {
    return product.getPrice().subtract(product.getDiscount().multiply(product.getPrice())
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
  }

  public List<Product> getLastThreeProducts() {
    return catalog.stream()
        .filter(p -> p.getAdded() != null)
        .sorted(Comparator.comparing(Product::getAdded).reversed())
        .limit(3)
        .toList();
  }

  public BigDecimal calcOverallPriceBy(ProductType type, BigDecimal maxPrice) {
    return catalog.stream()
        .filter(p -> p.getAdded() != null)
        .filter(p -> p.getType() == type && p.getPrice().compareTo(maxPrice) < 0)
        .filter(p -> p.getAdded().isAfter(LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0)))
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public Map<ProductType, List<Product>> groupByType() {
    return catalog.stream()
        .collect(Collectors.groupingBy(Product::getType));
  }

  ProductManagement() {
    addProduct(new Product()
            .withProductId(UUID.randomUUID())
            .withType(ProductType.BOOK)
            .withPrice(BigDecimal.valueOf(300))
            .withDiscount(BigDecimal.valueOf(15)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.BOOK)
        .withPrice(BigDecimal.valueOf(100)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.BOOK)
        .withPrice(BigDecimal.valueOf(600)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.BOOK)
        .withPrice(BigDecimal.valueOf(300))
        .withDiscount(BigDecimal.valueOf(0))
        .withAdded(LocalDateTime.now().minusHours(3)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.MOVIE)
        .withPrice(BigDecimal.valueOf(300))
        .withDiscount(BigDecimal.valueOf(10))
        .withAdded(LocalDateTime.now().minusHours(2)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.MUSIC)
        .withPrice(BigDecimal.valueOf(200))
        .withDiscount(BigDecimal.valueOf(15))
        .withAdded(LocalDateTime.now().minusHours(1)));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.BOOK)
        .withPrice(BigDecimal.valueOf(600))
        .withDiscount(BigDecimal.valueOf(10))
        .withAdded(LocalDateTime.now()));

    addProduct(new Product()
        .withProductId(UUID.randomUUID())
        .withType(ProductType.BOOK)
        .withPrice(BigDecimal.valueOf(350))
        .withDiscount(BigDecimal.valueOf(15))
        .withAdded(LocalDateTime.now().minusYears(1)));

  }

}
