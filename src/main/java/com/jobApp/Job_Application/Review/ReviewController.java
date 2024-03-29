package com.jobApp.Job_Application.Review;

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
@RequestMapping("/companyApplication/{companyId}")
public class ReviewController {
	ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
		List<Review> reviews=reviewService.getAllReviews(companyId);
			return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review newReivew){
		boolean isReviewAdded=reviewService.addReview(companyId,newReivew);
		if (isReviewAdded) {
			return new ResponseEntity<String>("Review Added..!",HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
		Review review = reviewService.getReviewById(companyId,reviewId);
		if (review != null) {
			return new ResponseEntity<Review>(review,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review updatedReview){
		boolean updated =reviewService.updateReview(companyId, reviewId, updatedReview);
		if (updated) {
			return new ResponseEntity<String>("Review Updated..!",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
		boolean deleted=reviewService.deleteReviewById(companyId, reviewId);
		if (deleted) {
			return new ResponseEntity<>("Review Deleted..!",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
