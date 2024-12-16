package com.example.Thu_hoc_phi.mapper;

import com.example.Thu_hoc_phi.dto.request.UserCreationRequest;
import com.example.Thu_hoc_phi.dto.request.UserUpdateRequest;
import com.example.Thu_hoc_phi.dto.response.SubjectResponse;
import com.example.Thu_hoc_phi.dto.response.UserResponse;
import com.example.Thu_hoc_phi.entity.Subject;
import com.example.Thu_hoc_phi.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.dob( request.getDob() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dob( user.getDob() );
        Set<String> set = user.getRoles();
        if ( set != null ) {
            userResponse.roles( new LinkedHashSet<String>( set ) );
        }
        userResponse.subjects( subjectSetToSubjectResponseList( user.getSubjects() ) );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setPassword( request.getPassword() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setDob( request.getDob() );
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

    protected List<SubjectResponse> subjectSetToSubjectResponseList(Set<Subject> set) {
        if ( set == null ) {
            return null;
        }

        List<SubjectResponse> list = new ArrayList<SubjectResponse>( set.size() );
        for ( Subject subject : set ) {
            list.add( toSubjectResponse( subject ) );
        }

        return list;
    }
}
