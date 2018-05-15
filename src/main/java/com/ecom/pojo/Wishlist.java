package com.ecom.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="wishlist_table")
public class Wishlist {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "wishlistID", unique=true, nullable = false)
	int wishlistID;

	
	@Column(name="userID")
	int userID;
	
	@OneToOne
	Product product;

	
	public int getWishlistID() {
		return wishlistID;
	}

	public void setWishlistID(int wishlistID) {
		this.wishlistID = wishlistID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

		
	
}
