package ru.darek.diplompro.dtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.entities.User;

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
                .isAdmin(user.isAdmin())
                .comment(user.getComment())
                .build();
    }
    public List<UserInfoDto> allUsersToDto(List<User> users){
        return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
    }
}
