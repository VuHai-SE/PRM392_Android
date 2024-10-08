package com.example.nermanapp.dto.Response.UserResponse;

import com.example.nermanapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String status;
    private User user;
}
