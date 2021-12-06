package com.example.online_seminar.repository;

import com.example.online_seminar.entity.tag.Tag;
import com.example.online_seminar.entity.tag.TagRequest;
import com.example.online_seminar.entity.user.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRequestRepository extends JpaRepository<TagRequest,Long> {

    // タグで検索するメソッド
    @Query( name = "Request.findByTagNQVariable", nativeQuery = true)
    List<Request> findNyTagNq(Tag tagName);

}
