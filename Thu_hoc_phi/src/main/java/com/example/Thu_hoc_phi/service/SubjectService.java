package com.example.Thu_hoc_phi.service;

import com.example.Thu_hoc_phi.dto.request.SubjectRequest;
import com.example.Thu_hoc_phi.dto.request.SubjectUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import com.example.Thu_hoc_phi.mapper.SubjectMapper;
import com.example.Thu_hoc_phi.repository.SubjectRepository;
import com.example.Thu_hoc_phi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SubjectService {
    UserRepository userRepository;
    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public SubjectResponse createSubject(SubjectRequest request) {
        Subject subject = subjectMapper.toSubject(request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    public List<SubjectResponse> getSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::toSubjectResponse).toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public SubjectResponse updateSubject (Long subjectId , SubjectUpdateRequest request) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject Not Found"));
        subjectMapper.updateSubject(subject, request);
        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSubject (Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
