package com.ecom.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ecom.exception.CategoryException;
import com.ecom.exception.UserException;
import com.ecom.pojo.Category;

import com.ecom.pojo.Product;


public class EmployeeDAO extends DAO{

	public EmployeeDAO() {
	}

	public Category createCategory(String title, String fileName) throws CategoryException {
        try {
            begin();
            Category cat = new Category(title, fileName);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
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
	 
	    public void updateCategory(Category category) throws CategoryException {
	        try {
	            begin();
	            getSession().update(category);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new CategoryException("Could not save the category", e);
	        }
	    }

	    public void deleteCategory(Category category) throws CategoryException {
	        try {
	            begin();
	            getSession().delete(category);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new CategoryException("Could not delete the category", e);
	        }
	    }

	    
		 public Product createProduct(String fileName, String productName,double productPrice, Category category) throws CategoryException {
		        try {
		            begin();
		            Product product=new Product();
		            product.setProductName(productName);
		            product.setProductPrice(productPrice);
		            product.setCategory(category);
		            product.setFilename(fileName);
		            getSession().save(product);
		            
		            commit();
		            
		            return product;
		        } catch (HibernateException e) {
		            rollback();
		            //throw new AdException("Could not create the category", e);
		            throw new CategoryException("Exception while creating product: " + e.getMessage());
		        }
		    }
		  
		 public void updateProduct(Product product) throws CategoryException {
		        try {
		            begin();
		            getSession().update(product);
		            commit();
		        } catch (HibernateException e) {
		            rollback();
		            throw new CategoryException("Could not save the product", e);
		        }
		    }

		 
		 
		    public void deleteProduct(Product product) throws CategoryException {
		        try {
		            begin();
		            getSession().delete(product);
		            commit();
		        } catch (HibernateException e) {
		            rollback();
		            throw new CategoryException("Could not delete the product", e);
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
	    

			public Product getProduct(int productID) throws UserException {
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

			
}
