package uk.co.virtual1.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uk.co.virtual1.model.persist.User;

/**
 * @author Mikhail Tkachenko created on 27.05.2016
 */
public interface UserService extends UserDetailsService {

    User getByName(String userName);

    boolean isUserNameUnique(User user);

    User updatePassword(User user);

    User updateUserName(User user);

    User retrieveCurrentUser();

    boolean isPasswordValid(User user);

    User registerUser(User user);

}
