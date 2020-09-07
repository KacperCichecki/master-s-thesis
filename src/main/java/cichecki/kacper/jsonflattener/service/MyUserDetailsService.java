package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.persistence.model.Role;
import cichecki.kacper.jsonflattener.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        org.springframework.security.core.userdetails.User springUser =
         new org.springframework.security.core.userdetails.User
                (user.getEmail(),
                    user.getPassword(),
                    enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked,
                    getAuthorities(user.getRoles()));

        return springUser;
    }

    private List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.stream()
                .filter(Objects::nonNull)
                .map(Role::getName)
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        return authorities;
    }

}
