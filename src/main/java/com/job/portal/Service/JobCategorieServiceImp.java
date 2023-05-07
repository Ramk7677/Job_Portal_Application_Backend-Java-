package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.JobCategories;
import com.job.portal.Repository.JobCategoriesRepository;

@Service
public class JobCategorieServiceImp implements JobCategorieService{

	
	@Autowired
	private JobCategoriesRepository jobCategoriesRepository;
	
	@Override
	public JobCategories saveJobCategories(JobCategories jobCategories) {
		return this.jobCategoriesRepository.save(jobCategories);
	}

	@Override
	public Optional<JobCategories> getJobCategoriesById(long jobcategoriesId) {
		return this.jobCategoriesRepository.findById(jobcategoriesId);
	}

	@Override
	public void updateJobCategoriesById(JobCategories jobCategories, long jobcategoriesId) {
		Optional<JobCategories> jobc=this.jobCategoriesRepository.findById(jobcategoriesId);
		
		if(jobc.isPresent()) {
			JobCategories jc=jobc.get();
			jc.setCategoryName(jobCategories.getCategoryName());
			jc.setCreatedAt(jobCategories.getCreatedAt());
			jc.setUpdatedAt(jobCategories.getUpdatedAt());
			
			this.jobCategoriesRepository.save(jc);
		}
	
	}

	@Override
	public void daleteJobCategoriesBuId(long jobcategoriesId) {

		this.jobCategoriesRepository.deleteById(jobcategoriesId);
	}

	@Override
	public List<JobCategories> getAllJobCategories() {
		return this.jobCategoriesRepository.findAll();
	}

}
