package cichecki.kacper.jsonflattener.persistence.dao;

import cichecki.kacper.jsonflattener.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
