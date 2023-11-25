package com.kulturman.mdd.services;

import com.kulturman.mdd.dtos.requests.RegisterRequest;
import com.kulturman.mdd.dtos.responses.auth.me.GetUserProfile;
import com.kulturman.mdd.entities.User;
import com.kulturman.mdd.exceptions.BadRequestException;
import com.kulturman.mdd.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        var user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmailOrUsername(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No user with email or username %s", email));
        }

        return user.get();
    }

    public Optional<User> findByEmailOrUsername(String email) {
        return userRepository.findByEmailOrUsername(email);
    }

    public GetUserProfile getUserProfile() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new GetUserProfile(
            userRepository.getUserProfile(user.getId()).orElseThrow(() -> new BadRequestException("User not found"))
        );
    }
}
