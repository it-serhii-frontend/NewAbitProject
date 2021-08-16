package com.abit.Abit.repo;

import com.abit.Abit.entety.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileDB, String> {

}

