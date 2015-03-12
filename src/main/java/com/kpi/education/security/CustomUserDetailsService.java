package com.kpi.education.security;

import com.kpi.education.businesslogic.user.ManagerUser;
import com.kpi.education.businesslogic.user.SimpleUser;
import com.kpi.education.dao.ManagerUserDAO;
import com.kpi.education.dao.SimpleUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class CustomUserDetailsService implements UserDetailsService {

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
        SimpleUser simpleUser = simpleUserDAO.getByEmail(email);
       
        if (simpleUser != null) {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new CustomUserDetails(auths, 
                    simpleUser.getId(),
                    simpleUser.getEmail(), 
                    simpleUser.getPassword(), 
                    simpleUser.getFirstName(), 
                    simpleUser.getLastName());
        } else {
            ManagerUser managerUser = managerUserDAO.getByEmail(email);
            if(managerUser != null) {
                auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                return new CustomUserDetails(auths, 
                        managerUser.getId(), 
                        managerUser.getEmail(), 
                        managerUser.getPassword(),
                        managerUser.getFirstName(),
                        managerUser.getLastName());
            }
        }
        return null;
    }
}