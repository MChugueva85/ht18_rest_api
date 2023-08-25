package models;

import lombok.Data;

@Data
public class LoginResponseModel {
    String email, password, id, createdAt;
}
