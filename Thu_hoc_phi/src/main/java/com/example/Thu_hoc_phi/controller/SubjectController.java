package com.example.Thu_hoc_phi.controller;

import com.example.Thu_hoc_phi.dto.request.ApiResponse;
import com.example.Thu_hoc_phi.dto.request.SubjectRequest;
import com.example.Thu_hoc_phi.dto.request.SubjectUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SubjectController {
    SubjectService subjectService;

    @PostMapping
    ApiResponse<SubjectResponse> CreateSubject(@RequestBody @Valid SubjectRequest request) {
        ApiResponse<SubjectResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(subjectService.createSubject(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<SubjectResponse>> GetSubjects() {
        return ApiResponse.<List<SubjectResponse>>builder().code(1000)
                .result(subjectService.getSubjects())
                .build();
    }

    @PutMapping("/{subjectId}")
    ApiResponse<SubjectResponse> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectUpdateRequest request) {
        return ApiResponse.<SubjectResponse>builder().code(1000)
                .result(subjectService.updateSubject(subjectId, request))
                .build();
    }

    @DeleteMapping("/{subjectId}")
    ApiResponse<String> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ApiResponse.<String>builder().code(1000)
                .result("subject has been deleted")
                .build();
    }
}
