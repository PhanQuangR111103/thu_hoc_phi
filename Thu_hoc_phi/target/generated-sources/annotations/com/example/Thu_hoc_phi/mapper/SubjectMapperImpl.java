package com.example.Thu_hoc_phi.mapper;

import com.example.Thu_hoc_phi.dto.request.SubjectRequest;
import com.example.Thu_hoc_phi.dto.request.SubjectUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public Subject toSubject(SubjectRequest request) {
        if ( request == null ) {
            return null;
        }

        Subject.SubjectBuilder subject = Subject.builder();

        subject.id( request.getId() );
        subject.name( request.getName() );
        subject.tinchi( request.getTinchi() );
        subject.fee( request.getFee() );
        subject.startTime( request.getStartTime() );
        subject.endTime( request.getEndTime() );

        return subject.build();
    }

    @Override
    public SubjectResponse toSubjectResponse(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectResponse.SubjectResponseBuilder subjectResponse = SubjectResponse.builder();

        subjectResponse.id( subject.getId() );
        subjectResponse.name( subject.getName() );
        subjectResponse.tinchi( subject.getTinchi() );
        if ( subject.getFee() != null ) {
            subjectResponse.fee( subject.getFee() );
        }
        subjectResponse.startTime( subject.getStartTime() );
        subjectResponse.endTime( subject.getEndTime() );

        return subjectResponse.build();
    }

    @Override
    public void updateSubject(Subject subject, SubjectUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        subject.setName( request.getName() );
        subject.setTinchi( request.getTinchi() );
        subject.setFee( request.getFee() );
        subject.setStartTime( request.getStartTime() );
        subject.setEndTime( request.getEndTime() );
    }
}
