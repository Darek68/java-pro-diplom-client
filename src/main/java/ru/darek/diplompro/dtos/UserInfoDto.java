package ru.darek.diplompro.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userStatus;
    private boolean isAdmin;
    private String comment;
}
