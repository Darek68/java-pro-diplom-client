package ru.darek.diplompro.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Описание пользователя")
public class UserInfoDto {
    @Schema(description = "Внутренний ID пользователя", required = true, example = "123")
    private Long id;
    @Schema(description = "Имя пользователя", required = true, example = "Михаил")
    private String firstName;
    @Schema(description = "Фамилия пользователя", required = true, example = "Горбачев")
    private String lastName;
    @Schema(description = "Статус пользователя", required = true, example = "BLOCKED")
    private String userStatus;
    @Schema(description = "Признак администратора", required = true, example = "true")
    private boolean admin;
    @Schema(description = "Комментарий об пользователе", required = true, example = "Менеджер склада")
    private String comment;
}
