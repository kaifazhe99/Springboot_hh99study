package com.sparta.springboot.repository;


import com.sparta.springboot.entity.Folder;
import com.sparta.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);

}