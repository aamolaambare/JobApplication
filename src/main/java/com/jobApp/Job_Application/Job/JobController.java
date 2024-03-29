package com.jobApp.Job_Application.Job;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobApplication")
public class JobController {
	
//	GET    - /jobApplication/jobs     : Getting all jobs
//	GET    - /jobApplication/jobs/{id} : Getting specific job
//	POST   - /jobApplication/jobs      : Create new job
//	DELETE - /jobApplication/jobs/{id} : delete specific job
//	PUT    - /jobApplication/jobs/{id} : update existing job
	
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getJobs(){
		return ResponseEntity.ok(jobService.getJobs());
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<String> createJob(@RequestBody Job newjob) {
		jobService.createJob(newjob);
		return new ResponseEntity<String>("Job Created Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job getjob = jobService.getJobById(id);
		if(getjob!=null) {
			return new ResponseEntity<Job>(getjob,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		boolean deleted = jobService.deleteJob(id);
		if(deleted)
			return new ResponseEntity<String>("Deleted Successfully...!",HttpStatus.OK);
		return new ResponseEntity<String>("Not Found..!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/jobs/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job) {
		boolean updated = jobService.updateJob(id, job);
		if (updated) {
			return new ResponseEntity<String>("Updated Successfully...!",HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
