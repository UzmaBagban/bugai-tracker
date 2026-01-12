package service;

import entity.Credentials;
import org.springframework.stereotype.Service;
import repository.CredentialsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {
    //constructor injection not using autowired

    private final CredentialsRepository credentialsRepository;

    public AuthService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Register User
    public Credentials register(String email, String password) {

    }
}
