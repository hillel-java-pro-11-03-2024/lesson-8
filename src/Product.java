import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

  private UUID productId;
  private ProductType type;
  private BigDecimal price;
  private BigDecimal discount;
  private LocalDateTime added;

  Product() {}

  private Product(UUID productId, ProductType type, BigDecimal price, BigDecimal discount,
      LocalDateTime added) {
    this.productId = productId;
    this.type = type;
    this.price = price;
    this.discount = discount;
    this.added = added;
  }

  public Product withProductId(UUID productId) {
    return (this.productId == productId)
        ? this
        : new Product(productId, this.type, this.price, this.discount, this.added);
  }

  public Product withType(ProductType type) {
    return (this.type == type)
        ? this
        : new Product(this.productId, type, this.price, this.discount, this.added);
  }

  public Product withPrice(BigDecimal price) {
    return (this.price == price)
        ? this
        : new Product(this.productId, this.type, price, this.discount, this.added);
  }

  public Product withDiscount(BigDecimal discount) {
    return (this.discount == discount)
        ? this
        : new Product(this.productId, this.type, this.price, discount, this.added);
  }

  public Product withAdded(LocalDateTime added) {
    return (this.added == added)
        ? this
        : new Product(this.productId, this.type, this.price, this.discount, added);
  }

  public UUID getProductId() {
    return productId;
  }

  public ProductType getType() {
    return type;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public LocalDateTime getAdded() {
    return added;
  }

  @Override
  public String toString() {
    return "Product{" +
        "productId=" + productId +
        ", type=" + type +
        ", price=" + price +
        ", discount=" + discount +
        ", added=" + added +
        '}';
  }
}
