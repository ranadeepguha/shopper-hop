package com.ecom.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail_table")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderLineID", unique=true, nullable = false)
	int orderLineId;
	
	@ManyToOne
	@JoinColumn(name="OrderID")
	Order order;

	
	@ManyToOne
	@JoinColumn(name="ProductID")
	Product product;
	
	@Column(name = "productPrice")
	double productPrice;
	
	@Column(name="productQuantity")
	double productQty;

	public int getOrderLineId() {
		return orderLineId;
	}

	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getProductQty() {
		return productQty;
	}

	public void setProductQty(double productQty) {
		this.productQty = productQty;
	}
	
	
}
