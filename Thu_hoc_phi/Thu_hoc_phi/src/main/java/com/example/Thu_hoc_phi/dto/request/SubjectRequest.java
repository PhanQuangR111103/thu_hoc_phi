package com.example.Thu_hoc_phi.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {
    Long id;
    String name;
    int tinchi;
    double fee;
    LocalDate startTime;
    LocalDate endTime;
}
