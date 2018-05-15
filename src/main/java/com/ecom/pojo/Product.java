package com.ecom.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


@Entity
@Table(name="product_table")
public class Product {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productID", unique=true, nullable = false)
	int productID;

	@Column(name = "productName")
	String productName;

	@Column(name = "productPrice")
	double productPrice;


	@Transient
	private CommonsMultipartFile photo;   //for DataBinder to bind <input type="file".../>
										  //will not be mapped for Hibernate as we store the file in the FileSystem
										  //file will be placed into this field by DataBinder
										  //file is in the memory. needs to be transferred to the FileSystem using java.io.file
	@Column(name = "filename")
	private String filename;   
	
	@ManyToOne
//	@JoinColumn (name = "categoryId")
	private Category category;
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(String productName, double productPrice) {
		this.productName = productName;
		this.productPrice = productPrice;
	}

	
	
	public Product() {
	
	}

	public int getProductID() {
		return productID;
	}

	
	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}



	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
