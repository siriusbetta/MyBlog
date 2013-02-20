package homework.alexey.blogcontroller;

import homework.alexey.datamodel.Registration;
import homework.alexey.datamodel.User;

import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;


//import homework.alexey.blogcontroller.;

@Component("registrationValidator")
public class RegistrationValidation {
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}
	
	public void validate(Object target, Errors errors, MongoOperations mongoOps){
		Registration registration = (Registration) target;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode)
		ValidationUtils.rejectIfEmpty(errors, "id", 
				"NotEmpty.registration.id", 
				"User name must not be empty");
		
		
		String id = registration.getId();
		User user = mongoOps.findOne("users", new Query(Criteria.where("id").is(id)), User.class);
		if(user != null){
			errors.rejectValue("id", "NotInData.registration.id",
					"chouse another name");
		}
		if(registration.getPassword().length() < 6){
			errors.rejectValue("password", "LessSix.registration.password",
					"The password must be 6 or more letters");
		}
		
		if(!(registration.getPassword().equals(registration.getConfirmPassword()))){
			errors.rejectValue("confirmPassword", "matchingpassword.registration.confirmPassword",
					"Password and Comformation not equal");
		}
	}
	

}
