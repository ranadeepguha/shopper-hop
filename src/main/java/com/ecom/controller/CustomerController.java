package com.ecom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.ecom.dao.CustomerDAO;
import com.ecom.dao.EmployeeDAO;
import com.ecom.dao.UserDAO;
import com.ecom.pojo.Cart;
import com.ecom.pojo.Category;
import com.ecom.pojo.Order;
import com.ecom.pojo.OrderDetail;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;
import com.ecom.pojo.Wishlist;

@Controller
public class CustomerController {

	@Autowired
	@Qualifier("customerDao")
	CustomerDAO customerDao;
	
	List<Cart> cartItems=new ArrayList<Cart>();
	List<Wishlist> wishlistItems=new ArrayList<Wishlist>();
	
	@RequestMapping(value="/customer/categoryselect*", method = RequestMethod.GET)
	public ModelAndView selectCategoryForm(HttpServletRequest request) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Category> categoryList;
		System.out.println("Inside the customer category controller");
		categoryList=customerDao.categoryList();
		mv.addObject("categoryList", categoryList);
		mv.setViewName("customer-select-category2");
		
		return mv;
	}
	
	@RequestMapping(value="customer/searchProducts*", method = RequestMethod.GET)
	public String searchProductPage(HttpServletRequest request, HttpSession session) throws Exception {	
				return "search-products";

	}	

	
	@RequestMapping(value="/customer/productSearch*", method = RequestMethod.POST)
	public ModelAndView searchProducts(HttpServletRequest request,HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		String keyword=request.getParameter("keyword");
		List<Product> searchResults=customerDao.getProductsByKeyword(keyword);
		//System.out.println("Search result count is "+searchResults.size());
		mv.addObject("resultSize", searchResults.size());
		mv.addObject("productList", searchResults);
		mv.setViewName("search-results");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/customer/productselect*", method = RequestMethod.POST)
	public ModelAndView selectProductForm(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Category> categoryList;
		System.out.println("Category selected is="+request.getParameter("category"));
		Category category=customerDao.getCategory(request.getParameter("category"));
		session.setAttribute("category", category);
		List<Product> productList;
		System.out.println("Inside the customer product controller");
		productList=customerDao.productListByCategory(category);
		System.out.println("Size of the product list is "+productList.size());
		mv.addObject("productList", productList);
		mv.setViewName("customer-select-product2");
		
		return mv;
	}
	
	@RequestMapping(value="/customer/productselect*", method = RequestMethod.GET)
	public ModelAndView selectProductFormGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		List<Category> categoryList;
		System.out.println("Category selected is="+request.getParameter("category"));
		
		Category category=(Category)(session.getAttribute("category"));
//		session.setAttribute("category", category);
		List<Product> productList;
		System.out.println("Inside the customer product controller");
		productList=customerDao.productListByCategory(category);
		System.out.println("Size of the product list is "+productList.size());
		mv.addObject("productList", productList);
		mv.setViewName("customer-select-product2");
		
		return mv;
	}
	
	
	@RequestMapping(value="/customer/viewprevious*", method = RequestMethod.GET)
	public ModelAndView getPreviousOrders(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		User u=(User)session.getAttribute("user");
		List<Order> orderList=customerDao.getOrders(u);
		mv.addObject("orderList", orderList);
		mv.setViewName("order-view");
		return mv;
	}

	@RequestMapping(value="/customer/wishlist*", method = RequestMethod.GET)
	public ModelAndView wishListGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		
		User u=(User) session.getAttribute("user");
		//List<Cart> addedCart=customerDao.cartListByUser(u.getPersonId());
		List<Wishlist> addedList=customerDao.wishListByUser(u.getPersonId());
		
		
		int listSize=addedList.size();
		mv.addObject("listSize", listSize);
		mv.addObject("addedList", addedList);
		mv.setViewName("wishlist-page");
		
		
		return mv;		
	
	}
	
	
	
	
	
	
	@RequestMapping(value="/customer/cart*", method = RequestMethod.GET)
	public ModelAndView addToCartGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		
		User u=(User) session.getAttribute("user");
		List<Cart> addedCart=customerDao.cartListByUser(u.getPersonId());
		
		double itemTotal=0;
		double shipping=7.5;
		double taxRate=5;
		
		for(Cart c: addedCart)
		{
			itemTotal+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
		session.setAttribute("addedCart", addedCart);
		session.setAttribute("cartSize", cartSize);
		double orderTotal=(1+(taxRate/100))*itemTotal+shipping;
		double taxes=itemTotal*(taxRate/100);
		session.setAttribute("itemTotal", itemTotal);
		session.setAttribute("shipping", shipping);
		session.setAttribute("taxes", taxes);
		session.setAttribute("orderTotal", orderTotal);
		
		
		
		mv.addObject("size", cartSize);
		mv.addObject("addedCart", addedCart);
		mv.addObject("itemTotal", itemTotal);
		mv.addObject("shipping", shipping);
		mv.addObject("taxes",taxes);
		mv.addObject("orderTotal",orderTotal);
	
		mv.setViewName("cart-page");
		
		
		return mv;
	
	}
	
	@RequestMapping(value="/customer/cart/update*", method = RequestMethod.GET)
	public ModelAndView cartUpdateGet(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		
		User u=(User) session.getAttribute("user");
		List<Cart> addedCart=customerDao.cartListByUser(u.getPersonId());
		
		double itemTotal=0;
		double shipping=7.5;
		double taxRate=5;
		
		for(Cart c: addedCart)
		{
			itemTotal+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
		session.setAttribute("addedCart", addedCart);
		session.setAttribute("cartSize", cartSize);
		double orderTotal=(1+(taxRate/100))*itemTotal+shipping;
		double taxes=itemTotal*(taxRate/100);
		session.setAttribute("itemTotal", itemTotal);
		session.setAttribute("shipping", shipping);
		session.setAttribute("taxes", taxes);
		session.setAttribute("orderTotal", orderTotal);
		
		
		
		mv.addObject("size", cartSize);
		mv.addObject("addedCart", addedCart);
		mv.addObject("itemTotal", itemTotal);
		mv.addObject("shipping", shipping);
		mv.addObject("taxes",taxes);
		mv.addObject("orderTotal",orderTotal);
	
		mv.setViewName("cart-page");
		
		
		return mv;
	
	}
	
	@RequestMapping(value="/customer/placeorder", method = RequestMethod.GET)
	public ModelAndView placeOrderGet(HttpServletRequest request) throws Exception {	
	
		ModelAndView mv=new ModelAndView();
		mv.addObject("errorMessage", "You cannot place an order without adding items");
		mv.setViewName("error");
		//mv.addObject("product", new Product());
		return mv;
	}
	
	
	
	@RequestMapping(value="/customer/cart*", method = RequestMethod.POST)
	public ModelAndView addToCart(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		String productName=request.getParameter("product");
		Product selectedProduct=customerDao.getProduct(productName);
		
		System.out.println("Product Name selected is "+productName);
		User u=(User) session.getAttribute("user");
		int userID=u.getPersonId();
		String action=request.getParameter("selection");
		
		if(action.equals("Add to Cart"))
			
		{
		List<Cart> previousItems=customerDao.cartListByUser(userID);
		
		System.out.println("Previous Items Size "+previousItems.size());
		
		//add new cart items
		Cart cart=new Cart();
		cart.setProduct(selectedProduct);
		cart.setQuantity(quantity);
		cart.setUserID(userID);
		
		List<Cart> addedCart=new ArrayList<Cart>();
		
		boolean sameItem=false;
		boolean exists=false;
		
		for(Cart c: previousItems)
		{
			if(c.getProduct().getProductID()==cart.getProduct().getProductID())
			{
				sameItem=true;
				int q=c.getQuantity()+cart.getQuantity();
				int uID=cart.getUserID();
				Product prod=cart.getProduct();
				customerDao.updateCart(q, uID, prod);
				c.setQuantity(c.getQuantity()+cart.getQuantity());
				break;
			}
		}
		
		if(sameItem==false)
		{
					for(Cart c: cartItems)
			{
				if(c.getProduct().getProductID()==cart.getProduct().getProductID())
				{
					exists=true;
					break;
				}
			}
			if(exists==false)
			{
				cartItems.add(cart);
				customerDao.addToCart(cart);
			}
		
		}
		
		
		addedCart=customerDao.cartListByUser(userID);
		
		double itemTotal=0;
		double shipping=7.5;
		double taxRate=5;
		
		for(Cart c: addedCart)
		{
			itemTotal+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
//		session.setAttribute("addedCart", addedCart);
//		session.setAttribute("cartSize", cartSize);
		double orderTotal=(1+(taxRate/100))*itemTotal+shipping;
		double taxes=itemTotal*(taxRate/100);
//		session.setAttribute("itemTotal", itemTotal);
//		session.setAttribute("shipping", shipping);
//		session.setAttribute("taxes", taxes);
//		session.setAttribute("orderTotal", orderTotal);
		
		
		
		mv.addObject("size", cartSize);
		mv.addObject("addedCart", addedCart);
		mv.addObject("itemTotal", itemTotal);
		mv.addObject("shipping", shipping);
		mv.addObject("taxes",taxes);
		mv.addObject("orderTotal",orderTotal);
	
		mv.setViewName("cart-page");
		
		
		return mv;
	}
		
		
		
		else //(action.equals("Add to Wishlist"))
		{
			List<Wishlist> previousItems=customerDao.wishListByUser(userID);
			
			System.out.println("Previous Items Size "+previousItems.size());
			
			//add new cart items
			Wishlist wishlist=new Wishlist ();
			wishlist.setProduct(selectedProduct);
			wishlist.setUserID(userID);
			
			List<Wishlist> addedList=new ArrayList<Wishlist>();
			
			boolean sameItem=false;
			boolean exists=false;
			addedList=customerDao.wishListByUser(userID);
			int listSize=addedList.size();
			
			for(Wishlist w: previousItems)
			{
				if(w.getProduct().getProductID()==wishlist.getProduct().getProductID())
				{

					mv.addObject("size", listSize);
					mv.addObject("addedList", addedList);
					mv.setViewName("wishlist-page");
					return mv;
					//break;
				}
			}
			
			if(sameItem==false)
			{
						for(Wishlist w: wishlistItems)
				{
					if(w.getProduct().getProductID()==wishlist.getProduct().getProductID())
					{
						exists=true;
						break;
					}
				}
				if(exists==false)
				{
					wishlistItems.add(wishlist);
					customerDao.addToWishlist(wishlist);
				}
			
			}
			
			
			addedList=customerDao.wishListByUser(userID);
			
		
			listSize=addedList.size();
			mv.addObject("size", listSize);
			mv.addObject("addedList", addedList);
			mv.setViewName("wishlist-page");
			
			
			return mv;		
			
		}

	
	
	
	
	}		
	
	@RequestMapping(value="/customer/cart/update*", method = RequestMethod.POST)
	public ModelAndView updateCart(HttpServletRequest request,HttpSession session, @ModelAttribute("cart") Cart cart) throws Exception {	
		ModelAndView mv=new ModelAndView();
		
		String action= request.getParameter("action");
		int productID=Integer.parseInt(request.getParameter("productID"));
		Product product=customerDao.getProductByID(productID);
		
		cart.setProduct(product);
		if(action.equals("Update Quantity"))
		{
			customerDao.updateQuantity(cart);
			
		}
	
		if(action.equals("Delete"))
		{
			customerDao.deleteItem(cart);
			
		}
	
		
		List<Cart> addedCart=customerDao.cartListByUser(cart.getUserID());
		double itemTotal=0;
		double shipping=7.5;
		double taxRate=5;
		
		for(Cart c: addedCart)
		{
			itemTotal+=c.getProduct().getProductPrice()*c.getQuantity();
						
		}
		
		int cartSize=addedCart.size();
		
		double orderTotal=(1+(taxRate/100))*itemTotal+shipping;
		double taxes=itemTotal*(taxRate/100);
				
		mv.addObject("size", cartSize);
		mv.addObject("addedCart", addedCart);
		mv.addObject("itemTotal", itemTotal);
		mv.addObject("shipping", shipping);
		mv.addObject("taxes",taxes);
		mv.addObject("orderTotal",orderTotal);
	

		mv.setViewName("cart-page");
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/customer/wishlist/update*", method = RequestMethod.POST)
	public ModelAndView updateWishlist(HttpServletRequest request,HttpSession session, @ModelAttribute("wishlist") Wishlist wishlist) throws Exception {	
		ModelAndView mv=new ModelAndView();
		
		String action= request.getParameter("action");
		int productID=Integer.parseInt(request.getParameter("productID"));
		Product selectedProduct=customerDao.getProductByID(productID);
		
		wishlist.setProduct(selectedProduct);
		
		if(action.equals("Delete"))
		{
			customerDao.deleteItemFromWishlist(wishlist);
			
			User u=(User) session.getAttribute("user");
			int userID=u.getPersonId();

			
			List<Wishlist> addedList=customerDao.wishListByUser(userID);
			
			int listSize=addedList.size();
			
					
			mv.addObject("listSize", listSize);
			mv.addObject("addedList", addedList);

			mv.setViewName("wishlist-page");
			
			return mv;
		

		}
	
		
		
		else 
		{
			User u=(User) session.getAttribute("user");
			int userID=u.getPersonId();
			
			List<Cart> previousItems=customerDao.cartListByUser(userID);
			System.out.println("After the wishlist page");
			System.out.println("Previous Items Size "+previousItems.size());
			
			//add new cart items
			Cart cart=new Cart();
			cart.setProduct(selectedProduct);
			cart.setQuantity(1);
			cart.setUserID(userID);
			
			List<Cart> addedCart=new ArrayList<Cart>();
			
			boolean sameItem=false;
			boolean exists=false;
			
			for(Cart c: previousItems)
			{
				if(c.getProduct().getProductID()==cart.getProduct().getProductID())
				{
					sameItem=true;
					int q=c.getQuantity()+cart.getQuantity();
					int uID=cart.getUserID();
					Product prod=cart.getProduct();
					customerDao.updateCart(q, uID, prod);
					c.setQuantity(c.getQuantity()+cart.getQuantity());
					break;
				}
			}
			
			if(sameItem==false)
			{
						for(Cart c: cartItems)
				{
					if(c.getProduct().getProductID()==cart.getProduct().getProductID())
					{
						exists=true;
						break;
					}
				}
						
				if(exists==false)
				{
					cartItems.add(cart);
					customerDao.addToCart(cart);
				}
			
			}
			
			
			addedCart=customerDao.cartListByUser(userID);
			
			double itemTotal=0;
			double shipping=7.5;
			double taxRate=5;
			
			for(Cart c: addedCart)
			{
				itemTotal+=c.getProduct().getProductPrice()*c.getQuantity();
							
			}
			
			int cartSize=addedCart.size();
			
			double orderTotal=(1+(taxRate/100))*itemTotal+shipping;
			double taxes=itemTotal*(taxRate/100);
					
			mv.addObject("size", cartSize);
			mv.addObject("addedCart", addedCart);
			mv.addObject("itemTotal", itemTotal);
			mv.addObject("shipping", shipping);
			mv.addObject("taxes",taxes);
			mv.addObject("orderTotal",orderTotal);
		
			mv.setViewName("cart-page");
			
			
			return mv;
		
			
			
			
			
		}
		
	
	
	
	
	}
	

	
	
	@RequestMapping(value="/customer/placeorder*", method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		User u=(User) session.getAttribute("user");
		int userID=u.getPersonId();
		double itemTotal=Double.parseDouble(request.getParameter("itemTotal"));
		double taxes=Double.parseDouble(request.getParameter("taxes"));
		double orderTotal=Double.parseDouble(request.getParameter("orderTotal"));
		double shipping=Double.parseDouble(request.getParameter("shipping"));
		
		List<Cart> addedCart=customerDao.cartListByUser(userID);
	
		Order order=new Order();
		order.setOrderTotal(orderTotal);
		order.setUser(u);
		order.setShipping(shipping);
		order.setTaxes(taxes);
		order.setItemTotal(itemTotal);
		// DateFormat df = new SimpleDateFormat("dd/MM/yy");
	     Date dateobj = new Date();
	     order.setDate(dateobj);
		
		int orderID=customerDao.placeOrder(order);
		order.setOrderId(orderID);
		System.out.println("Order ID in controller is "+order.getOrderId());
		
		for(Cart c: addedCart)
		{
			OrderDetail orderLine=new OrderDetail();
			orderLine.setOrder(order);
			orderLine.setProduct(c.getProduct());
			orderLine.setProductPrice(c.getProduct().getProductPrice());
			orderLine.setProductQty(c.getQuantity());
			customerDao.enterOrderDetail(orderLine);
		
			customerDao.deleteItem(c);
			cartItems=new ArrayList<Cart>();
		
		}
		
		
		mv.addObject("ordernumber",orderID);
		mv.setViewName("order-confirmation");
				
		return mv;
	}
	

	@RequestMapping(value="/customer/orderdetails*", method = RequestMethod.POST)
	public ModelAndView orderDetails(HttpServletRequest request, HttpSession session) throws Exception {	
		ModelAndView mv=new ModelAndView();
		User u=(User) session.getAttribute("user");
		int userID=u.getPersonId();
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		Order order=customerDao.getOrderByOrderId(orderId);
		
		mv.addObject("orderDetails",customerDao.getOrderDetail(order));
		mv.setViewName("order-details");
				
		return mv;
	}
	
}
