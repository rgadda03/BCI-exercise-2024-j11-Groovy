package org.example.service

import org.example.dto.LoginResponseDTO
import org.example.dto.RegisterRequestDTO
import org.example.dto.RegisterResponseDTO
import org.example.exception.SuperErrorException

interface UserService {

    RegisterResponseDTO signUp (RegisterRequestDTO request) throws SuperErrorException;

    LoginResponseDTO login (String token) throws SuperErrorException;

}
