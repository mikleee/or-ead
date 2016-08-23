package uk.co.virtual1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import uk.co.virtual1.model.json.JsonResponse;
import uk.co.virtual1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uk.co.virtual1.model.json.JsonResponse.error;

/**
 * @author Mikhail Tkachenko created on 12.05.2016
 */
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
                .antMatchers("/api/**", "/public/**").permitAll()
                .antMatchers("/**").authenticated();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("$username")
                .passwordParameter("$password")
                .loginProcessingUrl("/$login")
                .successHandler(new AuthenticationSuccessHandlerImpl())
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .permitAll();

        http.logout()
                .logoutSuccessUrl("/login")
                .logoutUrl("/logout");

        http.csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }


    private class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.getWriter().write(error());
        }
    }

    private class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
        private RequestCache requestCache = new HttpSessionRequestCache();

        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws ServletException, IOException {

            String redirect = getRedirect(request, response);
            clearAuthenticationAttributes(request);
            response.getWriter().write(JsonResponse.ok(redirect));
        }

        private String getRedirect(HttpServletRequest request, HttpServletResponse response) {
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            return savedRequest != null ? savedRequest.getRedirectUrl() : "/dashboard";
        }

    }

}




