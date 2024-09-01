package ru.darek.diplompro.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darek.diplompro.dtos.UserDtoMapper;
import ru.darek.diplompro.dtos.UserInfoDto;
import ru.darek.diplompro.entities.User;
import ru.darek.diplompro.errors.ErrorDto;
import ru.darek.diplompro.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Учетки пользователей", description = "Методы работы с учетками пользователей")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    private final UsersService usersService;
    private final UserDtoMapper userDtoMapper;

    @Operation(summary = "Получение информации об одном пользователе")
    @GetMapping("/{id}")
    public UserInfoDto getUserInfo(
            @PathVariable @Parameter(description = "Идентификатор пользователя") Long id,
            @RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId
    ) {
        logger.info("getUserInfo id = {}    requesterId = {}",id,requesterId);
        User user = usersService.getUserInfoById(id,requesterId);
        return userDtoMapper.userToDto(user);
    }

    @Operation(summary = "Получение информации о всех клиентах")
    @GetMapping("/clients")
    public List<UserInfoDto> getAllUsers(@RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId) {
        logger.info("getClientInfo    requesterId = {}",requesterId);
       // List<User> users = usersService.getAllUsers();
        return userDtoMapper.allUsersToDto(usersService.getAllUsers(requesterId));
    }

    @Operation(summary = "Получение информации о всех админах")
    @GetMapping("/admins")
    public List<UserInfoDto> getAllAdmins(@RequestHeader Long requesterId) {
        logger.info("getClientInfo   requesterId = {}",requesterId);
        return userDtoMapper.allUsersToDto(usersService.getAllAdmins(requesterId));
    }

    @Operation(summary = "Создание нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "422", description = "Не удалось создать пользователя из-за ошибки заполнения полей",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))}),
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto createUserInfo(
            @RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId,
            @RequestBody @Parameter(description = "json с данными нового пользователя") UserInfoDto userInfoDto
    ){
        logger.info("0-createUserInfo requesterId = {}    userInfoDto = {}",requesterId,userInfoDto);
        return userDtoMapper.userToDto(usersService.createUser(userInfoDto,requesterId));
    }

    @Operation(summary = "Модификация учетки пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "422", description = "Не удалось обновить пользователя из-за ошибки заполнения полей",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))}),
    })
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserInfoDto updateUserInfo(
            @RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId,
            @RequestBody @Parameter(description = "json с новыми данными пользователя") UserInfoDto userInfoDto
    ){
        logger.info("0-updateUserInfo requesterId = {}    userInfoDto = {}",requesterId,userInfoDto);
        return userDtoMapper.userToDto(usersService.updateUserById(userInfoDto,requesterId));
    }

    @Operation(summary = "Удаление учетки пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален",
                    content = {@Content(mediaType = "text/html", schema = @Schema(implementation = ErrorDto.class))}),  // TODO проверить html
    })
    @DeleteMapping("/delete")
    public String deleteUser(
            @RequestHeader @Parameter(description = "Идентификатор удаляемого пользователя") Long userId,
            @RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId
    ){
        usersService.deleteUser(userId,requesterId);
        return "Пользователь с id = " + userId + " был успешно удален.";
    }

    @Operation(summary = "Проверка доступности сервиса")
    @GetMapping("/test")
    public String getClientInfo(@RequestHeader @Parameter(description = "Идентификатор request-системы") Long requesterId) {
        return "ID - запросителя = " + requesterId;
    }

}
