package com.kpi.education.rest.config.security;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.rest.dao.ManagerUserDAO;
import com.kpi.education.rest.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class CustomUserDetailsServise implements UserDetailsService {

    private SimpleUserDAO simpleUserDAO;
    private ManagerUserDAO managerUserDAO;

    @Autowired
    public void setSimpleUserDAO(SimpleUserDAO simpleUserDAO) {
        this.simpleUserDAO = simpleUserDAO;
    }

    @Autowired
    public void setManagerUserDAO(ManagerUserDAO managerUserDAO) {
        this.managerUserDAO = managerUserDAO;
    }
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        

        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();

        System.out.println("Finding for name: " + name);
        SimpleUser simpleUser = simpleUserDAO.retrieveByLogin(name);
        if (simpleUser != null) {
            System.out.println("login: " + simpleUser.getLogin() + "  | password : " + simpleUser.getPassword());
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new CustomUserDetails(auths, simpleUser.getLogin(), simpleUser.getPassword());
        } else {
            ManagerUser managerUser = managerUserDAO.retrieveByLogin(name);
            if(managerUser != null) {
                System.out.println("login: " + managerUser.getLogin() + "  | password : " + managerUser.getPassword());

                auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                return new CustomUserDetails(auths, managerUser.getLogin(), managerUser.getPassword());
            }
        }
        return null;
    }
}