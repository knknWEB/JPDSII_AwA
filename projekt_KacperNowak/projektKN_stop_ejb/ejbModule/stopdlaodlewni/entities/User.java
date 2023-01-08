package stopdlaodlewni.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	private String city;

	private String login;

	private String mail;

	private String name;

	private int nofFlat;

	private int nofHouse;

	private String password;

	private String postcode;

	private String role;

	private String street;

	private String surname;

	//bi-directional many-to-one association to Shop
	@OneToMany(mappedBy="user")
	private List<Shop> shops;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNofFlat() {
		return this.nofFlat;
	}

	public void setNofFlat(int nofFlat) {
		this.nofFlat = nofFlat;
	}

	public int getNofHouse() {
		return this.nofHouse;
	}

	public void setNofHouse(int nofHouse) {
		this.nofHouse = nofHouse;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Shop> getShops() {
		return this.shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public Shop addShop(Shop shop) {
		getShops().add(shop);
		shop.setUser(this);

		return shop;
	}

	public Shop removeShop(Shop shop) {
		getShops().remove(shop);
		shop.setUser(null);

		return shop;
	}

}