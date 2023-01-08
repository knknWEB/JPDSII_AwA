package stopdlaodlewni.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;

	private double price;

	private String productName;

	private int quantityInPackage;

	private String type;

	//bi-directional many-to-one association to Shop
	@OneToMany(mappedBy="product")
	private List<Shop> shops;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantityInPackage() {
		return this.quantityInPackage;
	}

	public void setQuantityInPackage(int quantityInPackage) {
		this.quantityInPackage = quantityInPackage;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Shop> getShops() {
		return this.shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public Shop addShop(Shop shop) {
		getShops().add(shop);
		shop.setProduct(this);

		return shop;
	}

	public Shop removeShop(Shop shop) {
		getShops().remove(shop);
		shop.setProduct(null);

		return shop;
	}

}