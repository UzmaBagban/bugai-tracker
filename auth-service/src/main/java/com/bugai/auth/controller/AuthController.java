package controller;

import dto.LoginReqDTO;
import dto.RegisterReqDTO;
import entity.Credentials;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Credentials register(@RequestBody RegisterReqDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public Credentials login(@RequestBody LoginReqDTO dto) {
        return authService.login(dto);
    }
}
