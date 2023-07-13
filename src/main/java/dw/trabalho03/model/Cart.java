package dw.trabalho03.model;

import java.util.Date;
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
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

// @SecondaryTable(name = "item_cart", pkJoinColumns =
// { @PrimaryKeyJoinColumn(name = "id") })
@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCart;

	@Column
	private Date date;

	@Column
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "id_user")
	@JsonIgnore
	private User user;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	// @ManyToMany
	// @JoinTable(name = "cart_products", joinColumns = @JoinColumn(name =
	// "id_cart"), inverseJoinColumns = @JoinColumn(name = "id_product"))
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	// @ManyToMany
	// @JoinTable(name = "items", joinColumns =
	// @JoinColumn(name = "id_cart", referencedColumnName = "id_cart", updatable =
	// false, nullable = false), inverseJoinColumns =
	// @JoinColumn(name = "id_product", referencedColumnName = "id_product",
	// updatable = false, nullable = false))
	// @JsonIgnore
	// @ManyToMany(mappedBy="items")
	// @OneToMany(mappedBy = "cart")

	// @OrderColumn(name = "id", columnDefinition = "ALTER TABLE item_cart ADD id
	// bigint AUTO_INCREMENT;")
	// @GeneratedValue
	// @ManyToMany
	// @JoinTable(name = "item_cart", joinColumns = { @JoinColumn(name = "id_cart")
	// }, inverseJoinColumns = {
	// @JoinColumn(name = "id_product") })

	@ManyToMany
	@JoinTable(name = "item_cart", joinColumns = { @JoinColumn(name = "id_cart") }, inverseJoinColumns = {
			@JoinColumn(name = "id_product") })
	private List<Product> products;

	// private List<ItemCart> itemCarts;

	public Cart() {
	}

	public Cart(Date date, User user, List<Product> Products) {
		this.date = date;
		this.user = user;
		this.products = Products;
		this.active = true;
	}

	public Cart(long id, Date date, User user, List<Product> Products) {
		this.idCart = id;
		this.date = date;
		this.user = user;
		this.products = Products;
		this.active = true;
	}

	public Cart(List<Product> Products) {
		this.products = Products;
		this.active = true;
	}

	public long getId() {
		return idCart;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
