package cichecki.kacper.jsonflattener.persistence.dao;

import cichecki.kacper.jsonflattener.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    void delete(User user);

}
