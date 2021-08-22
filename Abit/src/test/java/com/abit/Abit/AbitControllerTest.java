package com.abit.Abit;


import com.abit.Abit.entety.Abit;
import com.abit.Abit.repo.AbitRepo;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.jdbc.Sql;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbitControllerTest {

    @Autowired
    private TestRestTemplate testRestTempl;

    @Autowired
    private AbitRepo abitRepo;

    @After
    public void resetDb() {
        abitRepo.deleteAll();
    }



    @Test
    @Sql("/test.sql")
    public void getStudentByIdTest() {

        Abit abit = new Abit();

        abit.setId(10L);
        abit.setName("Jonny");
        abit.setSurname("Jonny");
        abit.setAge(33);
        abit.setEmail("rr@rr");
        abit.setPassword("4444");
        abit.setMark1(4);
        abit.setMark2(4);
        abit.setMark3(5);
        abit.setAverageMark("Passed");


        ResponseEntity<Abit> response = testRestTempl.getForEntity("/studentOne/10", Abit.class);

        Assert.assertEquals(java.util.Optional.of(10), response.getBody().getId());
        Assert.assertEquals("Jonny", response.getBody().getName());
        Assert.assertEquals("Johhy", response.getBody().getSurname());
        Assert.assertEquals(33, response.getBody().getAge());
        Assert.assertEquals("rr@rr", response.getBody().getEmail());
        Assert.assertEquals("4444", response.getBody().getPassword());
        Assert.assertEquals(4, response.getBody().getMark1());
        Assert.assertEquals(4, response.getBody().getMark2());
        Assert.assertEquals(5, response.getBody().getMark3());
        Assert.assertEquals("Passed", response.getBody().getAverageMark());


    }

    @Test
    public void saveStudents() {
        Abit abit = new Abit();
        abit.setName("Will");
        abit.setSurname("Will");
        abit.setEmail("will@w");
        abit.setAge(35);
        abit.setPassword("5555");

        HttpEntity<Abit> http = new HttpEntity<>(abit);
        ResponseEntity<Abit> response = testRestTempl.postForEntity("/student", http, Abit.class);

        Assert.assertNotNull(http.getBody().getId());
        Assert.assertEquals("Will", response.getBody().getName());
        Assert.assertEquals("Will", response.getBody().getSurname());
        Assert.assertEquals(35, response.getBody().getAge());
        Assert.assertEquals("will@w", response.getBody().getEmail());
        Assert.assertEquals("5555", response.getBody().getPassword());
    }
}
