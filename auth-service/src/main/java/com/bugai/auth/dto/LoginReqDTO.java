package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginReqDTO {
    private String email;
    private String password;
}
