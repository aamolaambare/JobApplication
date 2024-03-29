package com.jobApp.Job_Application.Review.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobApp.Job_Application.Company.Company;
import com.jobApp.Job_Application.Company.CompanyRepository;
import com.jobApp.Job_Application.Company.CompanyService;
import com.jobApp.Job_Application.Review.Review;
import com.jobApp.Job_Application.Review.ReviewRepository;
import com.jobApp.Job_Application.Review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private CompanyService companyService;

	

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
		this.reviewRepository = reviewRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}
    
	// To Save review object
	//first we need to save company object into review object
	// then save the review object DB
	@Override
	public boolean addReview(Long CompanyId,Review review) {
		Company company =companyService.getCompanyById(CompanyId);
		if (company!=null) {
			// First Put Company Object in review
			review.setCompany(company);
			//Second , Stored Review object in DB
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review getReviewById(Long companyId,Long reviewId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
		//findFirst() method return the first filtered review object that contained in filtered stream
		// if filtered stream contain no review , Then it return null
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId,Review reviewForUpdate) {
		Review reviewTOUpdate =getReviewById(companyId, reviewId);
		if (reviewForUpdate !=null) {
			reviewTOUpdate.setTitle(reviewForUpdate.getTitle());
			reviewTOUpdate.setDescription(reviewForUpdate.getDescription());
			reviewTOUpdate.setRating(reviewForUpdate.getRating());
			reviewRepository.save(reviewTOUpdate);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReviewById(Long companyId, Long reviewId) {
		Review reviewTODelete =getReviewById(companyId, reviewId);
		if (reviewTODelete!=null) {
			reviewRepository.delete(reviewTODelete);
			return true;
		}
		return false;
	}
}
