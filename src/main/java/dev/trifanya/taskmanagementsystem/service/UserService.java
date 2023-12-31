package dev.trifanya.taskmanagementsystem.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import dev.trifanya.taskmanagementsystem.model.User;
import dev.trifanya.taskmanagementsystem.repository.UserRepository;
import dev.trifanya.taskmanagementsystem.exception.NotFoundException;

@Data
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Пользователь с указанным email не найден."));
    }

    public User getUser(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с указанным id не найден."));
    }

    public User createNewUser(User userToSave) {
        return userRepository.save(userToSave
                .setPassword(encoder.encode(userToSave.getPassword())));
    }

    public User updateUserInfo(int userToUpdateId, User updatedUser) {
        return userRepository.save(updatedUser
                .setId(userToUpdateId)
                .setPassword(encoder.encode(updatedUser.getPassword())));
    }

    public void deleteUser(int userToDeleteId) {
        userRepository.deleteById(userToDeleteId);
    }
}
