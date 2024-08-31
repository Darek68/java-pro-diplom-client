package ru.darek.diplompro.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String clientStatus;
    private boolean isAdmin;
    private String comment;
}
