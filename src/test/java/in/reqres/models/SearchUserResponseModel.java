package in.reqres.models;

import lombok.*;

@Data
public class SearchUserResponseModel {

    UserData data;
    Support support;

    @lombok.Data
    public class UserData {
        Integer id;
        String email, first_name, last_name, avatar;
    }

    @lombok.Data
    public class Support {
        String url, text;
    }
}
