package com.abit.Abit.repo;

import com.abit.Abit.entety.Abit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbitRepo extends JpaRepository<Abit, Long> {


}
