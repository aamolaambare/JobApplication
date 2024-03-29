package com.jobApp.Job_Application.Review;

import java.util.List;

public interface ReviewService {
    
	List<Review> getAllReviews(Long companyId);
	boolean addReview(Long CompanyId,Review review);
	Review getReviewById(Long companyId,Long reviewId);
	boolean updateReview(Long companyId,Long reviewId,Review reviewForupdate);
	boolean deleteReviewById(Long companyId,Long reviewId);
}
