package com.example.Thu_hoc_phi.mapper;

import com.example.Thu_hoc_phi.dto.request.UserCreationRequest;
import com.example.Thu_hoc_phi.dto.request.UserUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.dto.response.UserResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import com.example.Thu_hoc_phi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    default Set<SubjectResponse> mapSubjects(Set<Subject> subjects) {
        return subjects.stream().map(this::toSubjectResponse).collect(Collectors.toSet());
    }
    SubjectResponse toSubjectResponse(Subject subject);
}
