package com.example.online_seminar.service;

import com.example.online_seminar.entity.user.Certification;
import com.example.online_seminar.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CertificationRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId)
        throws UsernameNotFoundException{
        Certification certification = userRepository.findByUserId(userId);
        if(certification == null) {
            throw new UsernameNotFoundException(userId + "not found");
        }
        return createUserDetails(certification);
    }

    public User createUserDetails(Certification certification){
         Set<GrantedAuthority>grantedAuthorities = new HashSet<>();
//         grantedAuthorities.add(new SimpleGrantedAuthority(certification.getRole()));
        return new User(certification.getUserId(),certification.getPassword(),grantedAuthorities);
    }

}
