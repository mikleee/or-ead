package uk.co.virtual1.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uk.co.virtual1.model.persist.User;
import uk.co.virtual1.service.UserService;

import java.util.ArrayList;

/**
 * @author Mikhail Tkachenko created on 27.05.2016
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not registered");
        } else {
            return convert(user);
        }
    }

    @Override
    public User getByName(String userName) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public User updatePassword(User user) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public User updateUserName(User user) {
        throw new RuntimeException("not implemented");
    }

    private User encodePassword(User user) {
        if (user.getPassword() != null) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
        }
        return user;
    }

    @Override
    public User retrieveCurrentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                String userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
                return getByName(userName);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
            return null;
        }
    }

    @Override
    public boolean isPasswordValid(User user) {
        throw new RuntimeException("not implemented");
    }


    private void updateSignedUser(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(convert(user), auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    private boolean isUserUnique(User user) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isUserNameUnique(User user) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public User registerUser(User user) {
        throw new RuntimeException("not implemented");
    }

    private org.springframework.security.core.userdetails.User convert(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<GrantedAuthority>()
        );
    }
}
