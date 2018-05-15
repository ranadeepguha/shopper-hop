package com.ecom.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;
import java.text.DateFormat;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderID", unique=true, nullable = false)
	int orderId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	User user;
	
	@Column(name = "orderTotal")
	double orderTotal;

	@OneToMany
	List<OrderDetail> orderDetail;
	
	@Column(name = "itemTotal")
	double itemTotal;
	
	@Column(name= "orderDate")
	Date date;
	
	@Column(name = "shipping")
	double shipping;
	
	@Column(name = "taxes")
	double taxes;
	
	
	public double getItemTotal() {
		return itemTotal;
	}


	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public double getShipping() {
		return shipping;
	}

	
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}


	
		
}
