package com.jobApp.Job_Application.Company;

import java.util.List;

public interface CompanyService {
	
	List<Company> getCompanies();
	Company addCompany(Company newCompany);
	Company getCompanyById(Long id);
	boolean updateCompany(Company company,Long id);
	boolean deleteCompany(Long id);
}
