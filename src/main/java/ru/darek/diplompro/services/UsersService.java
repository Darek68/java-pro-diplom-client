package ru.darek.diplompro.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.dtos.UserDtoMapper;
import ru.darek.diplompro.dtos.UserInfoDto;
import ru.darek.diplompro.entities.User;
import ru.darek.diplompro.errors.ResourceNotFoundException;
import ru.darek.diplompro.repository.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserDtoMapper userDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(UsersService.class.getName());
    private final UsersRepository usersRepository;

    public User getUserInfoById(Long id, Long requesterId) {
        logger.info("getUserInfoById id = {}  ",id);
        return usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь с id = " + id + " не найден"));
    }
    public List<User> getAllUsers(Long requesterId) {
        return usersRepository.findAllByAdmin(false);
    }

    public List<User> getAllAdmins(Long requesterId) {
        return usersRepository.findAllByAdmin(true);
    }

    public User updateUserById(UserInfoDto userInfoDto,Long requesterId) {
        logger.info("1 updateUserById userInfoDto = {}   ", userInfoDto);
        // TODO  добавить валидацию полей
        User user = getUserInfoById(userInfoDto.getId(),requesterId);
        user.setUserStatus(UserStatus.valueOf(userInfoDto.getUserStatus()));
        user.setComment(userInfoDto.getComment());
        logger.info("2 updateUserById userInfoDto = {}   ", userInfoDto);
        return usersRepository.save(user);
    }

    public User createUser(UserInfoDto userInfoDto,Long requesterId) {
        logger.info("1 createUser userInfoDto = {}   ", userInfoDto);
        // TODO  добавить валидацию полей
        User user = userDtoMapper.dtoToUser(userInfoDto);
        user.setUserStatus(UserStatus.CREATED);
        logger.info("2 createUser user = {}   ", user);
        return usersRepository.save(user);
    }

    public void deleteUser(Long id, Long requesterId){
        usersRepository.deleteById(id);
    }

}
