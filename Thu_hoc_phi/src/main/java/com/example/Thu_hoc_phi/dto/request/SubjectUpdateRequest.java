package com.example.Thu_hoc_phi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectUpdateRequest {
    String name;
    int tinchi;
    double fee;
    LocalDate startTime;
    LocalDate endTime;
}
