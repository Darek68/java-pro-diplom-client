package ru.darek.diplompro.dtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.entities.User;
import ru.darek.diplompro.services.UserStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDtoMapper {
    public UserInfoDto userToDto(User user){
        return UserInfoDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userStatus(String.valueOf(user.getUserStatus()))
                .admin(user.isAdmin())
                .comment(user.getComment())
                .build();
    }
    public List<UserInfoDto> allUsersToDto(List<User> users){
        return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
    }
    public User dtoToUser(UserInfoDto userInfoDto){
        return User.builder()
              //  .id(userInfoDto.getId())
                .firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName())
                .userStatus(UserStatus.valueOf(userInfoDto.getUserStatus()))
                .admin(userInfoDto.isAdmin())
                .comment(userInfoDto.getComment())
                .build();
    }
}
