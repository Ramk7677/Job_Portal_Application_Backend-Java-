package com.job.portal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.portal.Entity.JobCategories;
@Repository
public interface JobCategoriesRepository extends JpaRepository<JobCategories, Long>{

	Optional<JobCategories> getJobByCategoryName(long categoryName);

}
