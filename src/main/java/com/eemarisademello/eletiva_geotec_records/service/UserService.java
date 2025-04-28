package com.eemarisademello.eletiva_geotec_records.service;

import com.eemarisademello.eletiva_geotec_client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final String userApiURL = "http://localhost:8080";

    public UserDTO getUserByEmail(String email) {
        try{
            WebClient webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            Mono<UserDTO> user = webClient
                    .get()
                    .uri("/user/{email}/email", email)
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }

    public UserDTO getUserById(Long userId) {
        try{
            WebClient webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            Mono<UserDTO> user = webClient
                    .get()
                    .uri("/user/{userId}", userId)
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }
}
