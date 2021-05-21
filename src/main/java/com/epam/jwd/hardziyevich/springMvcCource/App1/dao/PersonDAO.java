package com.epam.jwd.hardziyevich.springMvcCource.App1.dao;

import com.epam.jwd.hardziyevich.springMvcCource.App1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FOR_INDEX = "SELECT * FROM PERSON";
    public static final String INSERT_INTO_PERSON = "INSERT INTO person (name, age, email) Values(?, ?, ?);";
    public static final String UPDATE_PERSON_SET_NAME_AGE_EMAIL_WHERE_ID = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?";
    public static final String DELETE_FROM_PERSON_WHERE_ID = "DELETE from person WHERE id = ?";
    public static final String SELECT_FROM_PERSON_WHERE_ID = "SELECT * from person where id = ?";


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query(SQL_FOR_INDEX, new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query(SELECT_FROM_PERSON_WHERE_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }


    public void save(Person person) {
        jdbcTemplate.update(INSERT_INTO_PERSON, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(UPDATE_PERSON_SET_NAME_AGE_EMAIL_WHERE_ID, person.getName(),
                person.getAge(),
                person.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_FROM_PERSON_WHERE_ID, id);
    }
}
