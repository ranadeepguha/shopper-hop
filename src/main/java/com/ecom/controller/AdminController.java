package com.ecom.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecom.dao.UserDAO;
import com.ecom.exception.UserException;
import com.ecom.pojo.User;
import com.ecom.validator.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/user/login*", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
	HttpSession session = (HttpSession) request.getSession();
		
	

    
	System.out.println("Inside the user controller");
	try {

			System.out.print("loginUser");
			
			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
				
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password is incorrect or does not exist");
				return "error";
			}
			
			
			else if(u.getRole().equals("Customer"))
			{			
			session.setAttribute("user", u);
			
			return "customer-home";
			}
			else if(u.getRole().equals("Employee"))
			{
				session.setAttribute("user", u);
				
				return "employee-home";
						
			}

			else if(u.getRole().equals("admin"))
			{
				session.setAttribute("user", u);
				
				return "admin-home";
						
			}
			
			
			
		}
		catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

		return null;
	}
	


	
	
	
	@RequestMapping(value = "/user/login*", method = RequestMethod.GET)
	public String UserHome(HttpServletRequest request) {
	HttpSession session = (HttpSession) request.getSession();
		
	

    
	System.out.println("Inside the user controller");
	System.out.print("loginUser");
	
	
	User u = (User) session.getAttribute("user");
	
		
	
	if(u == null){
		System.out.println("UserName/Password does not exist");
		session.setAttribute("errorMessage", "UserName/Password does not exist");
		return "error";
	}
	
	
	else if(u.getRole().equals("Customer"))
	{			
	session.setAttribute("user", u);
	
	return "customer-home";
	}
	else if(u.getRole().equals("Employee"))
	{
		session.setAttribute("user", u);
		
		return "employee-home";
				
	}

	else if(u.getRole().equals("admin"))
	{
		session.setAttribute("user", u);
		
		return "admin-home";
				
	}

		return null;
	}
	

	
	
	
	
	
	
	
	@RequestMapping(value = "/admin/delete/employee*", method = RequestMethod.GET)
	public ModelAndView sendUserList(HttpServletRequest request) throws UserException {
	HttpSession session = (HttpSession) request.getSession();
	ModelAndView mv=new ModelAndView();	
	mv.addObject("userList", userDao.getUsers());
	mv.setViewName("employee-delete");
	return mv;
		}

	@RequestMapping(value = "/admin/userdelete*", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request) throws UserException {
	HttpSession session = (HttpSession) request.getSession();
	ModelAndView mv=new ModelAndView();	
	int userId=Integer.parseInt(request.getParameter("userId"));
	User u=userDao.get(userId);
	userDao.delete(u);
	mv.addObject("userList", userDao.getUsers());
	mv.setViewName("employee-delete");
	return mv;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/user/register*", method = RequestMethod.GET)
	public ModelAndView register(Locale locale, Model model) {
			
		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
	
	HttpSession session = (HttpSession) request.getSession();

		userValidator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try 
		{

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			System.out.println("User role is "+user.getRole());
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error");
			}
			
			else if(u.getRole().equals("Customer"))
			{			
			session.setAttribute("user", u);
			
			return new ModelAndView("customer-home","user",u);
			}
			else if(u.getRole().equals("Employee"))
			{
		//		session.setAttribute("user", u);
				
				return new ModelAndView("employee-add-success","user",u);
						
			}
			request.getSession().setAttribute("user", u);
			
			
			return new ModelAndView("user-home", "user", u);

		} 
		catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	
	@RequestMapping(value = "/admin/add*", method = RequestMethod.GET)
	public ModelAndView addEmployee(Locale locale, Model model) {
			
		return new ModelAndView("register-employee", "user", new User());

	}
	
	
//	@RequestMapping(value = "/admin/delete/employee*", method = RequestMethod.GET)
//	public ModelAndView deleteEmployee(Locale locale, Model model) {
//			
//		return new ModelAndView("delete-employee", "user", new User());
//
//	}
	
	
	@RequestMapping(value = "/admin/delete/user*", method = RequestMethod.GET)
	public ModelAndView deleteUser(Locale locale, Model model) {
			
		return new ModelAndView("delete-user", "user", new User());

	}
	
	
}
