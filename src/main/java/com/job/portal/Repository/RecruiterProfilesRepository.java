package com.job.portal.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.portal.Entity.RecruiterProfiles;

@Repository
public interface RecruiterProfilesRepository extends JpaRepository<RecruiterProfiles, Long> {

	Optional<RecruiterProfiles> findByrecruiterprofileId(long recruiterprofileId);



}
