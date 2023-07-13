package dw.trabalho03.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_product;

	@Column
	private String title;

	@Column
	private double price;

	@Column
	private String category;

	@Column
	private String description;

	@Column
	private String image;

	// @OneToMany(mappedBy = "product")
	// private List<ItemCart> itemCarts;

	// @JoinColumn(name = "id_cart")
	// @ManyToMany(mappedBy = "product")
	// private List<Cart> cart;
	// @JoinColumn(name = "id_item")
	// @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity
	// = Cart.class)
	// @JoinTable(name = "items", joinColumns = @JoinColumn(name = "id_product",
	// referencedColumnName = "id_product", updatable = false, nullable = false),
	// inverseJoinColumns = @JoinColumn(name = "id_cart", referencedColumnName =
	// "id_cart", updatable = false, nullable = false))
	// @ManyToMany(mappedBy = "product")
	// @JsonIgnore

	public Product() {
	}

	public Product(String title, double price, String category, String description, String image) {
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.image = image;
	}

	public Product(String title, double price, String category, String description, String image, long id_product) {
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.image = image;
		this.id_product = id_product;
	}

	public long getId() {
		return id_product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// public List<Cart> getCart() {
	// return cart;
	// }

	// public void setCart(List<Cart> cart) {
	// this.cart = cart;
	// }

}
