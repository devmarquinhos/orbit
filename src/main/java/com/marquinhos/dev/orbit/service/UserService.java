package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.User;
import com.marquinhos.dev.orbit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id "+ id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User userDetails){
        User user = findById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public void delete(Integer id){
        User user = findById(id);
        userRepository.delete(user);
    }
}
