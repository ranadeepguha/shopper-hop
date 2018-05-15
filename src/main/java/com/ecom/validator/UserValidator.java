package com.ecom.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecom.dao.EmployeeDAO;
import com.ecom.dao.UserDAO;
import com.ecom.exception.UserException;
import com.ecom.pojo.Product;
import com.ecom.pojo.User;


public class UserValidator implements Validator {



	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.username", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email",	"Email Required");

		UserDAO userDao=new UserDAO();
		try {
			int emailCheck=userDao.getUserWithSameEmail(user.getEmail());
			if(emailCheck>=1)
			{
				System.out.println("Value of emailCheck="+emailCheck);
				errors.rejectValue("email", "error.invalid.email", "This Email ID has already been registered");
			}
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int userNameCheck=userDao.getUserWithSameUserName(user.getUsername());
			if(userNameCheck>=1)
			{
				System.out.println("Value of userName="+userNameCheck);
				errors.rejectValue("username", "error.invalid.username", "This username is taken. Please try another one!");
			}
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		// check if user exists
		
}
