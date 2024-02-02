package com.algaworks.algalog.dtos;

import com.algaworks.algalog.enums.UserRole;

public record RegisterDto(String username, String email, String phone, String password, UserRole role) {
}
