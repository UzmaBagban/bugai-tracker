package service;

import dto.LoginReqDTO;
import dto.RegisterReqDTO;
import entity.Credentials;
import exception.EmailAlreadyExistsException;
import exception.InvalidCredentialsException;
import exception.InvalidCredentialsException1;
import org.springframework.stereotype.Service;
import repository.CredentialsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class AuthService {

    //constructor injection not using autowired
    private final CredentialsRepository credentialsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(CredentialsRepository credentialsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //Register User
    public Credentials register(RegisterReqDTO dto) {
        if(credentialsRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Credentials credentials = Credentials.builder()
                .email(dto.getEmail())
                .passwordHash(bCryptPasswordEncoder.encode(dto.getPassword()))
                .role(Credentials.Role.ROLE_USER)
                .active(true)
                .build();

        return credentialsRepository.save(credentials);
    }
    public Credentials login(LoginReqDTO dto) {
//exception thrown in 2 ways same
        Credentials credentials = credentialsRepository.findByEmail(dto.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!bCryptPasswordEncoder.matches(dto.getPassword(), credentials.getPasswordHash())) {
            throw new InvalidCredentialsException1("invalid password");
        }

        if (!credentials.isActive()) {
            throw new RuntimeException("Account is deactivated");
        }

        return credentials;
    }
}

