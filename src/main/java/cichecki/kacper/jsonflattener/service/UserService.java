package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.errors.UserAlreadyExistException;
import cichecki.kacper.jsonflattener.errors.UserRegistrationException;
import cichecki.kacper.jsonflattener.persistence.dao.RoleRepository;
import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.dto.UserDto;
import cichecki.kacper.jsonflattener.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyEmailService myEmailService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, MyEmailService myEmailService) {
        this.myEmailService = myEmailService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @Override
    public User registerNewUserAccount(final UserDto accountDto) {

        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }

        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        User registeredUser;
        registeredUser = userRepository.save(user);

        myEmailService.sendSimpleMessage(accountDto.getEmail(), "welcome email", "Thanks for registration in our service");

        return registeredUser;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
