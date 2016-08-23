package uk.co.virtual1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.virtual1.config.Web;
import uk.co.virtual1.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mikhail Tkachenko created on 01.06.16 11:29
 */
@Component
public class CommonAttributesFilter implements Filter {
    private static Set<String> ignoreUrlsSuffixes = new HashSet<>();
    private static Set<String> ignoreUrlsPrefixes = new HashSet<String>() {{
        add("/public/directive-template");
        add("/directive-template");
    }};

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (ignoreUrlsSuffixes.isEmpty()) {
            for (String url : Web.STATIC_RESOURCES_LOCATIONS) {
                String suffix = url.split("\\.")[1];
                ignoreUrlsSuffixes.add("." + suffix);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isRequestApplicable(request)) {
            doFilterInternal(request);
        }
        chain.doFilter(request, response);
    }

    // TODO: 01.06.16 exclude ajax requests
    private boolean isRequestApplicable(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        if (isNotGet(request)) {
            return false;
        } else if (isIgnoredUri(request)) {
            return false;
        }

        return true;
    }

    private boolean isNotGet(HttpServletRequest request) {
        return !request.getMethod().equalsIgnoreCase("get");
    }

    private boolean isIgnoredUri(HttpServletRequest request) {
        String uri = request.getRequestURI();

        for (String suffix : ignoreUrlsSuffixes) {
            if (uri.endsWith(suffix)) {
                return true;
            }
        }

        for (String prefix : ignoreUrlsPrefixes) {
            if (uri.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private void doFilterInternal(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;

        request.setAttribute("$user", userService.retrieveCurrentUser());
        request.setAttribute("$uri", request.getRequestURI());
    }

    @Override
    public void destroy() {

    }

}
