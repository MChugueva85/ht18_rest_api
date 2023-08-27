package in.reqres.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class SearchUserResponseModel {

    UserData data;
    Support support;

    @lombok.Data
    public class UserData {
        Integer id;
        String email, avatar;
//        @JsonProperty("first_name")
        String first_name;
//        @JsonProperty("last_name")
        String last_name;
    }

    @lombok.Data
    public class Support {
        String url, text;
    }
}
