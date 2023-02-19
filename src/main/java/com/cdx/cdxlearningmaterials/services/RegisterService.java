package com.cdx.cdxlearningmaterials.services;

import com.cdx.cdxlearningmaterials.model.User;
import com.cdx.cdxlearningmaterials.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    public ResponseEntity<?> execute (User request) {
        try {
            if (Boolean.TRUE.equals(userRepository.existsByUsername(request.getUsername()))) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            User user = userRepository.save(request);
            return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
