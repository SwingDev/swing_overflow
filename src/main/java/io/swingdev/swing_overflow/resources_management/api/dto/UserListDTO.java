package io.swingdev.swing_overflow.resources_management.api.dto;

import io.swingdev.swing_overflow.user_management.api.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserListDTO {
    private List<UserDTO> members = new ArrayList<>();

    public List<UserDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserDTO> members) {
        this.members = members;
    }
}
