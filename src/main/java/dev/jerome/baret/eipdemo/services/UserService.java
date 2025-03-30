package dev.jerome.baret.eipdemo.services;

import dev.jerome.baret.eipdemo.entities.InputData;
import dev.jerome.baret.eipdemo.mappers.UserMapper;
import dev.jerome.baret.eipdemo.repositories.InputDataRepository;
import org.openapitools.model.CreateUser;
import org.openapitools.model.User;
import org.openapitools.model.Users;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final InputDataRepository inputDataRepository;
    private final UserMapper userMapper;

    public UserService(InputDataRepository inputDataRepository, UserMapper userMapper) {
        this.inputDataRepository = inputDataRepository;
        this.userMapper = userMapper;
    }

    public Users getUsers() {
        Users users = new Users();
        users.setUsers(inputDataRepository.findAll().stream().map(userMapper::map).collect(Collectors.toList()));
        return users;
    }

    public User postUser(CreateUser createUser) {
        InputData user = new InputData();
        user.setLastName(createUser.getLastName());
        user.setFirstName(createUser.getFirstName());
        InputData savedUser = inputDataRepository.save(user);
        return userMapper.map(savedUser);
    }
}
