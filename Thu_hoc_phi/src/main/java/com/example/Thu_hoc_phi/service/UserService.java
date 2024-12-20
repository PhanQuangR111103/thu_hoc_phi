package com.example.Thu_hoc_phi.service;

import com.example.Thu_hoc_phi.dto.request.UserCreationRequest;
import com.example.Thu_hoc_phi.dto.request.UserUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.UserResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import com.example.Thu_hoc_phi.entity.User;
import com.example.Thu_hoc_phi.enums.Role;
import com.example.Thu_hoc_phi.exception.AppException;
import com.example.Thu_hoc_phi.exception.ErrorCode;
import com.example.Thu_hoc_phi.mapper.UserMapper;
import com.example.Thu_hoc_phi.repository.SubjectRepository;
import com.example.Thu_hoc_phi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    SubjectRepository subjectRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse addSubjectToUser(String userId, Long subjectId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_EXISTED));

        user.getSubjects().add(subject);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public Double getTotalFee(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

        Double totalCost = user.getSubjects()
                .stream()
                .mapToDouble(Subject::getFee)
                .sum();
        return totalCost;
    }
}

