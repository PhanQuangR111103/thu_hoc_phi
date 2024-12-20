package com.example.Thu_hoc_phi.mapper;

import com.example.Thu_hoc_phi.dto.request.SubjectRequest;
import com.example.Thu_hoc_phi.dto.request.SubjectUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toSubject(SubjectRequest request);

    SubjectResponse toSubjectResponse(Subject subject);

    void updateSubject(@MappingTarget Subject subject, SubjectUpdateRequest request);
}
