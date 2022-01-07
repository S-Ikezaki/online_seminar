package com.example.online_seminar.repository;

import com.example.online_seminar.entity.user.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectMessageRepository extends JpaRepository<DirectMessage,Long> {

    List<DirectMessage> findAllByAddressUserIdOrCreateUserId(String addressId, String createId);

//    List<DirectMessage> findAllByAddressUserIdOrCreateUserIdAndAddressUserIdOrCreateUserId(String addressId, String createId, String );

    @Query(name = "DirectMessage.findDirectMessageNq", nativeQuery = true)
    List<DirectMessage> findDirectMessage(String address,String create);
}
