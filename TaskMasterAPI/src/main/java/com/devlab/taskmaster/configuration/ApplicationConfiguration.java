package com.devlab.taskmaster.configuration;

import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.enumeration.Role;
import com.devlab.taskmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener
    public void seedDatabase(ContextRefreshedEvent event) {
        seedAdminUser();
    }

    private void seedAdminUser() {
        String sql = "SELECT email FROM _user WHERE email = 'sysadmin@taskmaster.com' LIMIT 1";

        List<User> users = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (users.isEmpty()) {
            User user = User
                    .builder()
                    .email("sysadmin@taskmaster.com")
                    .password(getPasswordEncoder().encode("sysAdmin123."))
                    .firstName("Sys")
                    .lastName("Admin")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);
        }
    }
}
