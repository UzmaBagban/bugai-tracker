package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class RegisterReqDTO {
    private String email;
    private String password;

}
