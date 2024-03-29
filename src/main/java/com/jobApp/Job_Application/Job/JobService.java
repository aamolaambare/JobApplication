package com.jobApp.Job_Application.Job;

import java.util.List;

public interface JobService {
	List<Job> getJobs();
	
	void createJob(Job newJob);

	Job getJobById(Long jobId);
	
	boolean deleteJob(Long jobId);
	
	boolean updateJob(Long jobId,Job job);
}
