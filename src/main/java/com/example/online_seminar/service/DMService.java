package com.example.online_seminar.service;

import com.example.online_seminar.entity.user.DirectMessage;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.DirectMessageRepository;
import com.example.online_seminar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DMService {

    public final DirectMessageRepository directMessageRepository;
    public final UserRepository userRepository;
    public final EntityManager entityManager;

    @Transactional
    public List<DirectMessage> saveAndFindDM(DirectMessage directMessage, String userId) {
        ;
        return FindAllByAddressUserIdOrCreateUserId(userId);
    }

    public List<DirectMessage> FindAllByAddressUserIdOrCreateUserId(String userId) {
        return directMessageRepository.findAllByAddressUserIdOrCreateUserId(userId, userId);
    }

    public List<DirectMessage> FindDirectMessage(String addressUserId, String userId) {
        return directMessageRepository.findDirectMessage(addressUserId, userId);
    }

    public User FindByUserId(String addressUserId) {
        return userRepository.findByUserId(addressUserId);
    }

    public List<User> FindAllByUserNameLike(String keyword) {
        return userRepository.findAllByUserNameLike("%" + keyword + "%");
    }

    public void saveAndFlushDM(DirectMessage directMessage) {
        directMessageRepository.save(directMessage);
    }
}
