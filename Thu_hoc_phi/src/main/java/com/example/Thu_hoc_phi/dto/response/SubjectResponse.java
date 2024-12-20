package com.example.Thu_hoc_phi.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {
    Long id;
    String name;
    int tinchi;
    double fee;
    LocalDate startTime;
    LocalDate endTime;
}
