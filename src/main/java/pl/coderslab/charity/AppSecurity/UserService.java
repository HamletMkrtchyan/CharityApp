package pl.coderslab.charity.AppSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.coderslab.charity.dto.UserRegistrationDto;
import pl.coderslab.charity.entity.User;
public interface UserService extends UserDetailsService {
   User save(UserRegistrationDto userRegistrationDto);
}
