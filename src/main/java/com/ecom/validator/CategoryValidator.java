package com.ecom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecom.pojo.Product;
import com.ecom.pojo.User;
import com.ecom.pojo.Category;


public class CategoryValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Category.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		
		// check if user exists
		
		
	}
}
