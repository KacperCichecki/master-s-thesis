package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.errors.UserAlreadyExistException;
import cichecki.kacper.jsonflattener.dto.UserDto;
import cichecki.kacper.jsonflattener.persistence.model.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}
