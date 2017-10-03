package io.tchepannou.academy.user.dto.person;

import io.tchepannou.academy.user.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonDto toPersonDto (final Person person){
        final PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setBiography(person.getBiography());
        dto.setEmail(person.getEmail());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setLanguage(person.getLanguage());
        dto.setPictureUrl(person.getPictureUrl());
        return dto;
    }
}
