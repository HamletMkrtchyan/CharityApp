package pl.coderslab.charity.appSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.coderslab.charity.entity.User;
public interface UserService extends UserDetailsService {
   User save(User user, String role);

}
