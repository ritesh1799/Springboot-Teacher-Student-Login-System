package com.DRCProject.TeacherRegistration.Service;

import com.DRCProject.TeacherRegistration.model.User;
import com.DRCProject.TeacherRegistration.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

 //create a function to save the teacher's data for registration
 User save(UserRegistrationDto registrationDto);

}
