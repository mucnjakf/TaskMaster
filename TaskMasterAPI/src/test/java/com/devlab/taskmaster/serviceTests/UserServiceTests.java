package com.devlab.taskmaster.serviceTests;

import com.devlab.taskmaster.data.ProjectMockData;
import com.devlab.taskmaster.data.UserMockData;
import com.devlab.taskmaster.dto.project.ProjectDTO;
import com.devlab.taskmaster.dto.user.AuthenticationDTO;
import com.devlab.taskmaster.dto.user.UserDTO;
import com.devlab.taskmaster.repository.UserRepository;
import com.devlab.taskmaster.service.JwtService;
import com.devlab.taskmaster.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService sut;

    @Test
    public void login_should_return_jwt() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(UserMockData.getUser()));
        when(jwtService.generateJwt(any())).thenReturn("jwt");

        AuthenticationDTO authenticationDTO = sut.login(UserMockData.getLoginDTO());

        assertNotNull(authenticationDTO);
        assertEquals("jwt", authenticationDTO.getToken());
        verify(authenticationManager).authenticate(any());
        verify(userRepository).findByEmail(any());
        verify(jwtService).generateJwt(any());
    }

    @Test
    public void getUsers_should_return_users() {
        when(userRepository.findAll()).thenReturn(UserMockData.getUsers());

        List<UserDTO> users = sut.getUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void getUser_should_return_project() {
        when(userRepository.findById(UserMockData.getUser().getId())).thenReturn(Optional.of(UserMockData.getUser()));

        UserDTO user = sut.getUser(UserMockData.getUser().getId());

        assertNotNull(user);
        assertEquals(UserMockData.getUser().getId(), user.getId());
        verify(userRepository).findById(UserMockData.getUser().getId());
    }

    @Test
    public void createUser_should_create_and_return_user() {
        when(passwordEncoder.encode(any())).thenReturn("encoded-password");

        UserDTO user = sut.createUser(UserMockData.getCreateUserDTO());

        assertNotNull(user);
        assertEquals(UserMockData.getCreateUserDTO().getEmail(), user.getEmail());
        verify(passwordEncoder).encode(any());
        verify(userRepository).save(any());
    }

    @Test
    public void updateUser_should_update() {
        when(userRepository.findById(any())).thenReturn(Optional.of(UserMockData.getUser()));

        sut.updateUser(any(), UserMockData.getUpdateUserDTO());

        verify(userRepository).findById(any());
        verify(userRepository).save(any());
    }

    @Test
    public void deleteUser_should_delete() {
        when(userRepository.findById(any())).thenReturn(Optional.of(UserMockData.getUser()));

        sut.deleteUser(any());

        verify(userRepository).findById(any());
        verify(userRepository).delete(any());
    }

    @Test
    public void getUserEntity_should_return_entity() {
        when(userRepository.findById(UserMockData.getUser().getId())).thenReturn(Optional.of(UserMockData.getUser()));

        sut.getUserEntity(UserMockData.getUser().getId());

        verify(userRepository).findById(UserMockData.getUser().getId());
    }
}
