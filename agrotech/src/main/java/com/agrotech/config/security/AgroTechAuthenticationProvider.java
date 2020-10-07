package com.agrotech.config.security;

import com.agrotech.model.User;
import com.agrotech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class AgroTechAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public org.springframework.security.core.Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if("".equals(username) || "".equals(password)) {
            throw new UsernameNotFoundException("Invalid credentials!");
        }

        User user = userService.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username invalid!");
        }

        Boolean passwordEqual = passwordEncoder.matches(password, user.getPassword());

        if(!passwordEqual) {
            throw new UsernameNotFoundException("Password invalid!");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
