package io.tchepannou.academy.user.dto.person;

import io.tchepannou.academy.user.dto.BaseResponse;

public class PersonResponse extends BaseResponse{
    private PersonDto person;

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(final PersonDto person) {
        this.person = person;
    }
}
