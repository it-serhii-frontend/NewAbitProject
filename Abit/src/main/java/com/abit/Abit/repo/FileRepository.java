package com.abit.Abit.repo;

import com.abit.Abit.entety.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
    public FileModel findByName(String name);
}
