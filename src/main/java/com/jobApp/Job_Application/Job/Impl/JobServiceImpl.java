package com.jobApp.Job_Application.Job.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobApp.Job_Application.Job.Job;
import com.jobApp.Job_Application.Job.JobRepository;
import com.jobApp.Job_Application.Job.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	private JobRepository jobRepository;	

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
    }

	@Override
	public List<Job> getJobs() {
		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job newJob) {
		jobRepository.save(newJob);
	}
	
	@Override
	public Job getJobById(Long id) {
		Optional<Job> jobb = jobRepository.findById(id);
		if (jobb.isPresent()) {
			return jobb.get();
		}
		return null;
	}
	
	@Override
	public boolean deleteJob(Long jobId) {
		try {
	     	jobRepository.deleteById(jobId);
	     	return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean updateJob(Long jobId,Job updateJob) {
			Optional<Job> jobOptional = jobRepository.findById(jobId);
			if(jobOptional.isPresent()) {
				Job jobToUpdate = jobOptional.get();
				jobToUpdate.setTitle(updateJob.getTitle());
				jobToUpdate.setDescription(updateJob.getDescription());
				jobToUpdate.setMinSalary(updateJob.getMinSalary());
				jobToUpdate.setMaxSalary(updateJob.getMaxSalary());
				jobToUpdate.setLocation(updateJob.getLocation());
				jobRepository.save(jobToUpdate);
				return true;
			}
			return false;
	}
}
