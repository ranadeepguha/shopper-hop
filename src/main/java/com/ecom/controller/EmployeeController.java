package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ecom.dao.CustomerDAO;
import com.ecom.dao.EmployeeDAO;
import com.ecom.dao.UserDAO;
import com.ecom.exception.CategoryException;
import com.ecom.exception.UserException;
import com.ecom.pojo.Category;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;
import com.ecom.validator.UserValidator;

@Controller

public class EmployeeController {

	@Autowired
	@Qualifier("employeeDao")
	EmployeeDAO employeeDao;
	
	  @Autowired
	    ServletContext servletContext;
	  
	@RequestMapping(value="/employee/category/*", method = RequestMethod.GET)
	public ModelAndView initializeForm() throws Exception {	
		System.out.println("Inside the category controller");
		return new ModelAndView("category-add", "category", new Category());
	}


	
	@RequestMapping(value = "/category/add*", method = RequestMethod.POST)
	protected ModelAndView registerNewCategory(HttpServletRequest request,  @ModelAttribute("category") Category category, BindingResult result) throws Exception {
		
		try {
            if (category.getFilename().trim() != "" || category.getFilename() != null) {
                File directory;
                String check = File.separator; // Checking if system is linux
                                                // based or windows based by
                                                // checking seprator used.
                String path = null;
                if (check.equalsIgnoreCase("\\")) {
                    path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
                                                                                  // so we need to replace build in the path
                                                                                        }

                if (check.equalsIgnoreCase("/")) {
                    path = servletContext.getRealPath("").replace("build/", "");
                    path += "/"; // Adding trailing slash for Mac systems.
                }
                directory = new File(path + "\\" + category.getFilename());
                boolean temp = directory.exists();
                if (!temp) {
                    temp = directory.mkdir();
                }
                if (temp) {
                    // We need to transfer to a file
                    CommonsMultipartFile photoInMemory = category.getPhoto();
                    System.out.println("");
                    String fileName = photoInMemory.getOriginalFilename();
                    // could generate file names as well

                    File localFile = new File(directory.getPath(), fileName);

                    // move the file from memory to the file

                    photoInMemory.transferTo(localFile);
                    category.setFilename(localFile.getPath());
                    System.out.println("File is stored at" + localFile.getPath());
                    System.out.print("registerNewUser");
                    //User u = e.register(user);
                    Category c= employeeDao.createCategory(category.getCategoryName(),category.getFilename());
        			
        			//request.getSession().setAttribute("category", category);
        			
        			return new ModelAndView("category-success", "category", c);
                     
                } else {
                    System.out.println("Failed to create directory!");
                    return new ModelAndView("error", "errorMessage", "error while creating directory");
                }

            
            }

        } catch (IllegalStateException e) {
            System.out.println("*** IllegalStateException: " + e.getMessage());
            return new ModelAndView("error", "errorMessage", "error while creating directory");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("*** IOException: " + e.getMessage());
            return new ModelAndView("error", "errorMessage", "error while creating directory");
        }

	
    	return new ModelAndView("error", "errorMessage", "error while creating directory");

		
}


	
	
	

	
	@RequestMapping(value="/employee/product/add*", method = RequestMethod.GET)
	public ModelAndView initializeProductForm(HttpServletRequest request) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Category> categoryList;
		System.out.println("Inside the product controller");
		categoryList=employeeDao.categoryList();
		mv.addObject("categoryList", categoryList);
		mv.setViewName("product-add");
		mv.addObject("product", new Product());
		return mv;
	}

	

  	
	
	@RequestMapping(value = "/product/add*", method = RequestMethod.POST)
	protected ModelAndView registerNewProduct(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
		System.out.println("Product file name is "+request.getParameter("filename"));
		product.setFilename(request.getParameter("filename"));
		System.out.println("The category name sent is "+request.getParameter("category"));
		
    	try {
            if (product.getFilename().trim() != "" || product.getFilename() != null) {
                File directory;
                String check = File.separator; // Checking if system is linux
                                                // based or windows based by
                                                // checking seprator used.
                String path = null;
                if (check.equalsIgnoreCase("\\")) {
                    path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
                                                                                  // so we need to replace build in the path
                                                                                        }

                if (check.equalsIgnoreCase("/")) {
                    path = servletContext.getRealPath("").replace("build/", "");
                    path += "/"; // Adding trailing slash for Mac systems.
                }
                directory = new File(path + "\\" + product.getFilename());
                boolean temp = directory.exists();
                if (!temp) {
                    temp = directory.mkdir();
                }
                if (temp) {
                    // We need to transfer to a file
                    CommonsMultipartFile photoInMemory = product.getPhoto();

                    String fileName = photoInMemory.getOriginalFilename();
                    // could generate file names as well

                    File localFile = new File(directory.getPath(), fileName);

                    // move the file from memory to the file

                    photoInMemory.transferTo(localFile);
                    product.setFilename(localFile.getPath());
                    System.out.println("File is stored at" + localFile.getPath());
                    System.out.print("registerNewUser");
                    //User u = e.register(user);
                    Category category=employeeDao.getCategory(request.getParameter("category"));
                    Product p=employeeDao.createProduct(product.getFilename(), product.getProductName(), product.getProductPrice(), category);
                    return new ModelAndView("product-add-success", "product", p);
                
                } else {
                    System.out.println("Failed to create directory!");
                    return new ModelAndView("error", "errorMessage", "error while creating directory");
                }

            
            }

        } catch (IllegalStateException e) {
            System.out.println("*** IllegalStateException: " + e.getMessage());
            return new ModelAndView("error", "errorMessage", "error while creating directory");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("*** IOException: " + e.getMessage());
            return new ModelAndView("error", "errorMessage", "error while creating directory");
        }

	
    	return new ModelAndView("error", "errorMessage", "error while creating directory");
		
		
		
		
		
	}	

	
	
	@RequestMapping(value="/employee/product/list", method = RequestMethod.GET)
	public ModelAndView viewProduct(HttpServletRequest request) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Product> productList;
		System.out.println("Inside the product controller");
		productList=employeeDao.productList();
		mv.addObject("productList", productList);
		mv.setViewName("product-view");
		//mv.addObject("product", new Product());
		return mv;
	}
	
	
	
	@RequestMapping(value="employee/product/update*", method = RequestMethod.GET)
	public ModelAndView updateProductGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Category> categoryList;
		Product p= (Product)(session.getAttribute("productSelected"));
		//session.setAttribute("productSelected", p);
		categoryList=employeeDao.categoryList();
		mv.addObject("categoryList", categoryList);
		
		mv.setViewName("product-update");
		mv.addObject("product", p);
		
		return mv;

	}
	
	
	
	@RequestMapping(value = "/employee/product/update*", method = RequestMethod.POST)
	protected ModelAndView updateProduct(HttpServletRequest request, HttpSession session,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {

	//	userValidator.validate(user, result);
		System.out.println("Inside the product Update controller");
	

		try 
		{

			System.out.println("updateNewProduct");
			String action;
			action=request.getParameter("action");
			
			System.out.println(request.getParameter("productSelected"));
			//System.out.println("Category Type Selected is "+product.getCategory().getCategoryId());
			if(action.equals("Update Product"))
			{			
			ModelAndView mv=new ModelAndView();
			List<Category> categoryList;
			Product p= employeeDao.getProduct(Integer.parseInt(request.getParameter("productSelected")));
			//session.setAttribute("productSelected", p);
			categoryList=employeeDao.categoryList();
			mv.addObject("categoryList", categoryList);
			
			mv.setViewName("product-update");
			mv.addObject("product", p);
			
			return mv;
			}
			
			if(action.equals("Delete"))
			{			
			ModelAndView mv=new ModelAndView();
			
			Product p= employeeDao.getProduct(Integer.parseInt(request.getParameter("productSelected")));
			System.out.println("Product received is "+p.getProductName());
			employeeDao.deleteProduct(p);
			
			mv.setViewName("product-delete-success");
			
			return mv;
			}

			
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "Cannot Delete This Item. It is associated to a Cart or an Order!");
		}
	
		return null;
	}	
	
	@RequestMapping(value = "/product/update/success", method = RequestMethod.POST)
	protected ModelAndView updateSuccessCategory(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
			int productID=Integer.parseInt(request.getParameter("productID"));
			System.out.println("Product ID selected is "+productID);
			System.out.println("Inside the product Update Completion controller");

			try {
	            if (product.getFilename().trim() != "" || product.getFilename() != null) {
	                File directory;
	                String check = File.separator; // Checking if system is linux
	                                                // based or windows based by
	                                                // checking seprator used.
	                String path = null;
	                if (check.equalsIgnoreCase("\\")) {
	                    path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
	                                                                                  // so we need to replace build in the path
	                                                                                        }

	                if (check.equalsIgnoreCase("/")) {
	                    path = servletContext.getRealPath("").replace("build/", "");
	                    path += "/"; // Adding trailing slash for Mac systems.
	                }
	                directory = new File(path + "\\" + product.getFilename());
	                boolean temp = directory.exists();
	                if (!temp) {
	                    temp = directory.mkdir();
	                }
	                if (temp) {
	                    // We need to transfer to a file
	                    CommonsMultipartFile photoInMemory = product.getPhoto();

	                    String fileName = photoInMemory.getOriginalFilename();
	                    // could generate file names as well

	                    File localFile = new File(directory.getPath(), fileName);

	                    // move the file from memory to the file

	                    photoInMemory.transferTo(localFile);
	                    product.setFilename(localFile.getPath());
	                    System.out.println("File is stored at" + localFile.getPath());
	                    System.out.print("registerNewUser");
	                    //User u = e.register(user);
	                    Category category=employeeDao.getCategory(request.getParameter("category"));
	                    product.setCategory(category);

	    				employeeDao.updateProduct(product);
	    				
	    				ModelAndView mv=new ModelAndView();
	    				mv.setViewName("product-update-success");
	    				
	    				return mv;
	    				
	                } 
	                else {
	                    System.out.println("Failed to create directory!");
	                    return new ModelAndView("error", "errorMessage", "error while creating directory");
	                }

	            
	            }

	        } catch (IllegalStateException e) {
	            System.out.println("*** IllegalStateException: " + e.getMessage());
	            return new ModelAndView("error", "errorMessage", "error while creating directory");
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("*** IOException: " + e.getMessage());
	            return new ModelAndView("error", "errorMessage", "error while creating directory");
	        }

			
			
			
	    	return new ModelAndView("error", "errorMessage", "error while creating directory");

		
		
	}
	
}
