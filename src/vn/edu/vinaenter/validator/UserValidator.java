package vn.edu.vinaenter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.edu.vinaenter.model.bean.User;
import vn.edu.vinaenter.model.dao.UserDAO;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}
	
	public void validate(Object target, Errors errors, UserDAO userDAO) {
		User user = (User) target;
		
		if (user.getPassword().trim().equals("")) {
			errors.rejectValue("password", "messageInputPassword");
		} else if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "messageLengthField");
		}
		
		if (userDAO.hasUser(user.getUsername())) {
			errors.rejectValue("username", "existedUser");
		}
	}

	

}
