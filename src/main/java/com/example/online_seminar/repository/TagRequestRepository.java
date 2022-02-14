package com.example.online_seminar.repository;

import com.example.online_seminar.entity.tag.TagRequest;
import com.example.online_seminar.entity.tag.TagRequestID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TagRequestRepository extends JpaRepository<TagRequest, TagRequestID> {


    @Transactional
    List<TagRequest> deleteByRequestId(int requestId);

    // タグで検索するメソッド

    //QueryCreationException の原因になっている
//    @Query( name = "Request.findByTagNQVariable", nativeQuery = true)
//    List<Request> findNyTagNq(Tag tagName);

}
