package pl.coderslab.charity.AppSecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

@Component
public class AppStartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;


    public AppStartupRunner(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;

    }

    @Override
    public void run(String... args) throws Exception {
        createAdminIfNotExist();
    }

    private void createAdminIfNotExist() {
        String adminEmail = "admin@vp.pl";
        if (!userRepository.existsByEmail(adminEmail)){
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword("admin123");
            userService.save(admin, "ROLE_ADMIN");

        }
    }
}
