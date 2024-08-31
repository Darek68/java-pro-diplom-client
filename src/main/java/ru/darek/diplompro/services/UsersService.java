package ru.darek.diplompro.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.dtos.UserInfoDto;
import ru.darek.diplompro.entities.User;
import ru.darek.diplompro.errors.ResourceNotFoundException;
import ru.darek.diplompro.repository.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersService.class.getName());
    private final UsersRepository usersRepository;

    public User getUserInfoById(Long id) {
        logger.info("getUserInfoById id = {}  ",id);
        return usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id = " + id + " не найден"));
    }
    public List<User> getAllUsers() {
        return usersRepository.findAllByAdmin(false);
    }

    public List<User> getAllAdmins() {
        return usersRepository.findAllByAdmin(true);
    }

    public User updateUserById(UserInfoDto userInfoDto) {
        logger.info("1 updateUserById userInfoDto = {}   ", userInfoDto);
        User user = getUserInfoById(userInfoDto.getId());
        user.setUserStatus(UserStatus.valueOf(userInfoDto.getUserStatus()));
        user.setComment(userInfoDto.getComment());
        logger.info("2 updateUserById userInfoDto = {}   ", userInfoDto);
        return usersRepository.save(user);
    }

}
