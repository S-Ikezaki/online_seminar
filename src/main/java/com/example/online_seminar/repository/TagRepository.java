package com.example.online_seminar.repository;

import com.example.online_seminar.entity.group.Group;
import com.example.online_seminar.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    //Tagの名前で検索するメソッド
    Optional<Tag> findByName(String name);

    //Groupで検索するメソッド
    List<Tag> findByGroup(Group group);
}
