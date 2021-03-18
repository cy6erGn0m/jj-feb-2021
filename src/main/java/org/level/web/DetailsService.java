package org.level.web;

import org.levelp.model.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;

    public DetailsService(UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN", "USER")
                    .build();
        }

        org.levelp.model.User found = usersRepository.findByLogin(username);
        if (found == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return User.withUsername(username)
                .password(found.getPassword())
                .roles("USER")
                .build();
    }
}
