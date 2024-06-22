package com.emirhanarici.human_resources_project.service;

import com.emirhanarici.human_resources_project.model.JobSeeker;
import com.emirhanarici.human_resources_project.repository.JobSeekerRepository;
import com.emirhanarici.human_resources_project.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final JobSeekerRepository jobSeekerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JobSeeker user = jobSeekerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
