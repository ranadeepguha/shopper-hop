package com.ecom.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.ecom.exception.CategoryException;
import com.ecom.exception.UserException;
import com.ecom.pojo.Cart;
import com.ecom.pojo.Category;
import com.ecom.pojo.Order;
import com.ecom.pojo.OrderDetail;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;
import com.ecom.pojo.Wishlist;


public class CustomerDAO extends DAO{

	public CustomerDAO() {
	}

 	public List<Category> categoryList() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }
 
 	
 	public List<Product> productList() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Product");
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product", e);
        }
    }

 	
 	public List<Product> getProductsByKeyword(String keyword) throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Product where productName like :keyword or category.categoryName like :keyword");
			q.setString("keyword", "%"+keyword+"%");
            List<Product> results = q.list();
            commit();
            return results;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product", e);
        }
    }

 	
 	public List<Product> productListByCategory(Category category) throws CategoryException {
        try {
            begin();
            int categoryID=category.getCategoryId();
            Query q = getSession().createQuery("from Product where category= :category");
			//q.setInteger("categoryID", categoryID);
			q.setEntity("category", category);
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product", e);
        }
    }
			
 	
	public Category getCategory(String categoryName) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Category where categoryName= :categoryName");
			q.setString("categoryName", categoryName);
			
			Category category= (Category) q.uniqueResult();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return category;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get category " + categoryName, e);
		}
	}
	
	

	
	public void addToCart(Cart cart) throws UserException {
		try {
			begin();
				
			getSession().save(cart);
				
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}

	
	public List<Order> getOrders(User user) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Order where user= :user");
			q.setEntity("user", user);
			
			List<Order> order= q.list();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return order;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get orders for" + user.getFirstName(), e);
		}
		
		
	}

	
	public List<OrderDetail> getOrderDetail(Order order) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from OrderDetail where order= :order");
			q.setEntity("order", order);
			
			List<OrderDetail> orderDetails= q.list();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return orderDetails;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get order details for" + order.getOrderId(), e);
		}
		
		
	}
	
	public Order getOrderByOrderId(int orderId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Order where orderId= :orderId");
			q.setInteger("orderId", orderId);
			
			Order order= (Order) q.uniqueResult();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return order;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get order details for" + orderId, e);
		}
		
		
	}
	
	
	
	public void addToWishlist(Wishlist w) throws UserException {
		try {
			begin();
				
			getSession().save(w);
				
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get wishlistItems ", e);
		}
	}

	
	
	public Product getProduct(String productName) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Product where productName= :productName");
			q.setString("productName", productName);
			
			Product product= (Product) q.uniqueResult();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return product;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get Product " + productName, e);
		}
	}
	
	public Product getProductByID(int productID) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Product where productID= :productID");
			q.setInteger("productID", productID);
			
			Product product= (Product) q.uniqueResult();
			//System.out.println("Category received is "+category.getCategoryName());
			commit();
			return product;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get Product " + productID, e);
		}
	}
	
 	public List<Cart> cartListByUser(int userID) throws CategoryException {
        try {
            begin();
            System.out.println("User ID in the DAO is "+userID);
            Query q = getSession().createQuery("from Cart where userID= :userID");
			q.setInteger("userID", userID);
			//q.setEntity("category", category);
            List<Cart> list = q.list();
            commit();
            return list;
        } 
        
        catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product", e);
        }
    }

 	
 	public List<Wishlist> wishListByUser(int userID) throws CategoryException {
        try {
            begin();
            System.out.println("User ID in the DAO is "+userID);
            Query q = getSession().createQuery("from Wishlist where userID= :userID");
			q.setInteger("userID", userID);
			//q.setEntity("category", category);
            List<Wishlist> list = q.list();
            commit();
            return list;
        } 
        
        catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product", e);
        }
    }

 	
 
 	
	public void updateWishlist(int userID, Product product) throws UserException {
		try {
			begin();
			Query query = getSession().createQuery("update Wishlist set product=:product where userID=:userID");
			query.setInteger("userID", userID);
			query.setEntity("product", product);
			query.executeUpdate();

			System.out.println("In the update cart DAO");
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}

	 
	public void updateCart(int quantity, int userID, Product product) throws UserException {
		try {
			begin();
			Query query = getSession().createQuery("update Cart set quantity = :quantity where userID= :userID and product= :product");
			query.setInteger("quantity",quantity );
			query.setInteger("userID", userID);
			query.setEntity("product", product);
			query.executeUpdate();

			System.out.println("In the update cart DAO");
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}
	
	public void updateQuantity(Cart cart) throws UserException {
		try {
			begin();
			getSession().update(cart);
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}

	public void deleteItem(Cart cart) throws UserException {
		try {
			begin();
			getSession().delete(cart);
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}
	
	

	public void deleteItemFromWishlist(Wishlist wishlist) throws UserException {
		try {
			begin();
			getSession().delete(wishlist);
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get cartItems ", e);
		}
	}
	
	
	public void enterOrderDetail(OrderDetail orderDetail) throws UserException
	{
		try {
			begin();
			getSession().save(orderDetail);
			
			commit();
//			return cartItems;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not add Order Details! ", e);
		}
		
		
	}
	
	public int placeOrder(Order order) throws UserException
	{
		try {
			begin();
			
			getSession().save(order);
		
			commit();
			System.out.println("Order ID in DAO is "+order.getOrderId());

			return order.getOrderId();
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get place Order!! ", e);
		}
		
	}
	
}
