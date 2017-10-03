package io.tchepannou.academy.user.service;

import io.tchepannou.academy.user.dao.PersonDao;
import io.tchepannou.academy.user.dao.RoleDao;
import io.tchepannou.academy.user.domain.Person;
import io.tchepannou.academy.user.domain.Role;
import io.tchepannou.academy.user.dto.person.PersonDto;
import io.tchepannou.academy.user.dto.person.PersonMapper;
import io.tchepannou.academy.user.dto.person.PersonResponse;
import io.tchepannou.academy.user.exception.BusinessError;
import io.tchepannou.academy.user.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonMapper personMapper;

    public PersonResponse findByRole(Integer roleId){
        final Role role = roleDao.findOne(roleId);
        if (role == null){
            throw new NotFoundException(BusinessError.ROLE_NOT_FOUND);
        }

        final Person person = personDao.findOne(role.getPersonId());
        final PersonDto dto = personMapper.toPersonDto(person);

        final PersonResponse response = new PersonResponse();
        response.setPerson(dto);
        return response;
    }
}
