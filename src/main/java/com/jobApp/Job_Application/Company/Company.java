package com.jobApp.Job_Application.Company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobApp.Job_Application.Job.Job;
import com.jobApp.Job_Application.Review.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	//One Company have Many Jobs
	//Hence we add Annotation like @OneToMany
	@JsonIgnore  //ignore recursive json call
	@OneToMany(mappedBy="company")
	private List<Job> jobs;
	
	@OneToMany(mappedBy="company")
	private List<Review> reviews;
    
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// Default Constructor of JPA
	public Company() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Company(Long id, String name, String description, List<Job> jobs) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.jobs = jobs;
	}
	
}
