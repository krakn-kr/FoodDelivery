package hara.userservice.controller;

import hara.userservice.dto.UserResponse;
import hara.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;
    @GetMapping("/{id}")
    public UserResponse getUserData(@PathVariable Long id){
      return userService.getUserData(id);
    }
}
