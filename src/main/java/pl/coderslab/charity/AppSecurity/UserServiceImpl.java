package pl.coderslab.charity.AppSecurity;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new CustomUserDetails(user);
    }


    @Override
    public User save(User user, String role) {
       user = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), Arrays.asList(new Role(role)));

        return userRepository.save(user);


    }
}
