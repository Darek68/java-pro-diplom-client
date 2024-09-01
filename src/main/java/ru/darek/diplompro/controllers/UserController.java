package ru.darek.diplompro.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darek.diplompro.dtos.UserDtoMapper;
import ru.darek.diplompro.dtos.UserInfoDto;
import ru.darek.diplompro.entities.User;
import ru.darek.diplompro.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    private final UsersService usersService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/{id}")
    public UserInfoDto getUserInfo(@PathVariable Long id, @RequestHeader Long requesterId) {
        logger.info("getUserInfo id = {}    requesterId = {}",id,requesterId);
        User user = usersService.getUserInfoById(id,requesterId);
        return userDtoMapper.userToDto(user);
    }
    @GetMapping("/clients")
    public List<UserInfoDto> getAllUsers(@RequestHeader Long requesterId) {
        logger.info("getClientInfo    requesterId = {}",requesterId);
       // List<User> users = usersService.getAllUsers();
        return userDtoMapper.allUsersToDto(usersService.getAllUsers(requesterId));
    }
    @GetMapping("/admins")
    public List<UserInfoDto> getAllAdmins(@RequestHeader Long requesterId) {
        logger.info("getClientInfo   requesterId = {}",requesterId);
        return userDtoMapper.allUsersToDto(usersService.getAllAdmins(requesterId));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto createUserInfo(@RequestHeader Long requesterId, @RequestBody UserInfoDto userInfoDto){
        logger.info("0-createUserInfo requesterId = {}    userInfoDto = {}",requesterId,userInfoDto);
        return userDtoMapper.userToDto(usersService.createUser(userInfoDto,requesterId));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserInfoDto updateUserInfo(@RequestHeader Long requesterId, @RequestBody UserInfoDto userInfoDto){
        logger.info("0-updateUserInfo requesterId = {}    userInfoDto = {}",requesterId,userInfoDto);
        return userDtoMapper.userToDto(usersService.updateUserById(userInfoDto,requesterId));
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestHeader Long userId,@RequestHeader Long requesterId){
        usersService.deleteUser(userId,requesterId);
        return "Пользователь с id = " + userId + " был успешно удален.";
    }

    @GetMapping("/test")
    public String getClientInfo(@RequestHeader Long requesterId) {
        return "ID - запросителя = " + requesterId;
    }

}
