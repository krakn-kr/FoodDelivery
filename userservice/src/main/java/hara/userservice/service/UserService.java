package hara.userservice.service;

import hara.userservice.dto.LoginRequest;
import hara.userservice.dto.RegisterRequest;
import hara.userservice.dto.UserResponse;
import hara.userservice.exception.ResourceNotFoundException;
import hara.userservice.model.User;
import hara.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface UserService {

    public UserResponse register(RegisterRequest request);

    public String login(LoginRequest request);

    public UserResponse getUserData(Long id);

    public UserResponse mapToUserResponse(User user);

}