package com.kpi.education.rest.config.security;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.rest.dao.ManagerUserDAO;
import com.kpi.education.rest.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

//@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private SimpleUserDAO simpleUserDAO;
    private ManagerUserDAO managerUserDAO;
    
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        SimpleUser simpleUser = simpleUserDAO.retrieveByLogin(name);
        if (simpleUser != null) {
            if (simpleUser.getPassword().equals(password)) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
                return auth;
            }
        } else {
            ManagerUser managerUser = managerUserDAO.retrieveByLogin(name);
            if(managerUser != null) {
                if (managerUser.getPassword().equals(password)) {
                    List<GrantedAuthority> grantedAuths = new ArrayList<>();
                    grantedAuths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                    Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
                    return auth;
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }

    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }
}
