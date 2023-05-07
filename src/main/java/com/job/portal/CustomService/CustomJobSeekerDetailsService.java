package com.job.portal.CustomService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.JobSeeker;
import com.job.portal.Entity.Recruiters;
import com.job.portal.Repository.JobSeekerRepository;
import com.job.portal.Repository.RecruitersRepository;


@Service
public class CustomJobSeekerDetailsService implements UserDetailsService {

    private JobSeekerRepository jobSeekerRepository;

    public CustomJobSeekerDetailsService(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	JobSeeker jobSeeker = jobSeekerRepository.findByEmail(email)
               .orElseThrow(() ->
                       new UsernameNotFoundException("User not found with email:" + email));
        return new org.springframework.security.core.userdetails.User(jobSeeker.getEmail(),
        		jobSeeker.getPassword(), mapRolesToAuthorities(jobSeeker.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<com.job.portal.Entity.Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}