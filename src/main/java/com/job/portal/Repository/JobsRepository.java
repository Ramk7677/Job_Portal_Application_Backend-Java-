package com.job.portal.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.portal.Entity.Jobs;

@Repository

public interface JobsRepository extends JpaRepository<Jobs, Long>{

	Optional<Jobs> getJobByJobTitle(String jobTitle);

	Optional<Jobs> getJobStatusByisActive(boolean isActive);


	
}
