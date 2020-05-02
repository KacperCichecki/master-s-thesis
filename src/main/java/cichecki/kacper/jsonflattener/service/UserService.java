package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.errors.UserAlreadyExistException;
import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.dto.UserDto;
import cichecki.kacper.jsonflattener.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        return userRepository.save(user);
    }


    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
