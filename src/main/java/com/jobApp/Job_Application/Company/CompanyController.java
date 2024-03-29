package com.jobApp.Job_Application.Company;

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
@RequestMapping("/companyApplication")
public class CompanyController {
	
	CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/companies")
	public ResponseEntity<List<Company>> getCompanies(){
		return new ResponseEntity<List<Company>>(companyService.getCompanies(),HttpStatus.OK);
	}
	
	@PostMapping("/companies")
	public ResponseEntity<Company> addCompany(@RequestBody Company newCompany) {
		return new ResponseEntity<Company>(companyService.addCompany(newCompany),HttpStatus.CREATED);
	}
	
	@GetMapping("/companies/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
		Company c= companyService.getCompanyById(id);
		if (c!=null) {
			return new ResponseEntity<Company>(c,HttpStatus.OK);
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/companies/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id) {
		boolean isupdated=companyService.updateCompany(company, id);
		if (isupdated) {
			return new ResponseEntity<String>("Updated Successfully...!", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/companies/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean isDeleted=companyService.deleteCompany(id);
		if (isDeleted) {
			return new ResponseEntity<>("Deleted Successfully...!",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
