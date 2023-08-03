package pl.coderslab.charity.AppSecurity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.UserRegistrationDto;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new CustomUserDetails(user);
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getEmail(), passwordEncoder.encode(userRegistrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }


}
