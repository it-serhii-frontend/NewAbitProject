package com.abit.Abit.service;

import com.abit.Abit.entety.Abit;
import com.abit.Abit.repo.AbitRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AbitService {


    @Autowired
    private AbitRepo abitRepo;


    public Abit findById(Long id) {
        return abitRepo.findById(id).orElse(null);
    }

    public Iterable<Abit> findAll() {
        return abitRepo.findAll();
    }

    public Abit save(Abit abit) {
        return abitRepo.save(abit);
    }

    public void update(Long id, Abit abit) {
        Abit abitDb = abitRepo.findById(id).get();
        System.out.println(abitDb.toString());
        abitDb.setId(abit.getId());
        abitDb.setName(abit.getName());
        abitDb.setSurname(abit.getSurname());
        abitDb.setAge(abit.getAge());
        abitDb.setEmail(abit.getEmail());
        abitRepo.save(abitDb);
    }


    public void deleteById(Long id) {

        abitRepo.deleteById(id);
    }
}