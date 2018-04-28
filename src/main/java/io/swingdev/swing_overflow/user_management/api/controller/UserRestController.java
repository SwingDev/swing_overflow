package io.swingdev.swing_overflow.user_management.api.controller;

import io.swingdev.swing_overflow.user_management.api.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @GetMapping("/me")
    public UserDTO me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new UserDTO(auth.getName());
    }
}
