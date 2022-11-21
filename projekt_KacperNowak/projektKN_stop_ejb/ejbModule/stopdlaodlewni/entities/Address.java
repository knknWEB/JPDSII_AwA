package stopdlaodlewni.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressID;

	private String city;

	private int nofFlat;

	private int nofHouse;

	private String postcode;

	private String street;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UsersLogin")
	private User user;

	public Address() {
	}

	public int getAddressID() {
		return this.addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}