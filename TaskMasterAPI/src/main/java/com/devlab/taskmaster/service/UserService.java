package com.devlab.taskmaster.service;

import com.devlab.taskmaster.dto.user.*;
import com.devlab.taskmaster.entity.User;
import com.devlab.taskmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String jwt = jwtService.generateJwt(user);

        return AuthenticationDTO
                .builder()
                .token(jwt)
                .build();
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            userDTOs.add(mapUserToUserDTO(user));
        }

        return userDTOs;
    }

    public UserDTO getUser(Integer id) {
        User user = getUserEntity(id);

        return mapUserToUserDTO(user);
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User user = User
                .builder()
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .role(createUserDTO.getRole())
                .build();

        userRepository.save(user);

        return mapUserToUserDTO(user);
    }

    public void updateUser(Integer id, UpdateUserDTO updateUserDTO) {
        User user = getUserEntity(id);

        user.setFirstName(updateUserDTO.getFirstName());
        user.setLastName(updateUserDTO.getLastName());

        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = getUserEntity(id);

        userRepository.delete(user);
    }

    public User getUserEntity(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private UserDTO mapUserToUserDTO(User user) {
        List<UserProjectDTO> projects = new ArrayList<>();
        List<UserTaskDTO> tasks = new ArrayList<>();

        if (user.getProjects() != null) {
            projects = user
                    .getProjects()
                    .stream()
                    .map(x -> UserProjectDTO
                            .builder()
                            .id(x.getId())
                            .name(x.getName())
                            .description(x.getDescription())
                            .build())
                    .collect(Collectors.toList());
        }

        if (user.getTasks() != null) {
            tasks = user
                    .getTasks()
                    .stream()
                    .map(x -> UserTaskDTO
                            .builder()
                            .id(x.getId())
                            .name(x.getName())
                            .status(x.getStatus())
                            .priority(x.getPriority())
                            .build())
                    .collect(Collectors.toList());
        }

        return UserDTO
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .projects(projects)
                .tasks(tasks)
                .build();
    }
}
