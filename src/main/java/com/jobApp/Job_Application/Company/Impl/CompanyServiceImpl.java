package com.jobApp.Job_Application.Company.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobApp.Job_Application.Company.Company;
import com.jobApp.Job_Application.Company.CompanyRepository;
import com.jobApp.Job_Application.Company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	CompanyRepository companyrepository;
	
	public CompanyServiceImpl(CompanyRepository companyrepository) {
		this.companyrepository = companyrepository;
	}

	@Override
	public List<Company> getCompanies() {
		return companyrepository.findAll();
	}

	@Override
	public Company addCompany(Company newCompany) {
		return companyrepository.save(newCompany);
	}

	@Override
	public Company getCompanyById(Long id) {
		Optional<Company> company =companyrepository.findById(id);
		if (company.isPresent()) {
			Company c = company.get();
			return c;
		}
		return null;
	}

	@Override
	public boolean updateCompany(Company newCompany,Long id) {
		Optional<Company> companyOptional= companyrepository.findById(id);
		if (companyOptional.isPresent()) {
			Company toUpdateCompany = companyOptional.get();
			toUpdateCompany.setName(newCompany.getName());
			toUpdateCompany.setDescription(newCompany.getDescription());
			toUpdateCompany.setJobs(newCompany.getJobs());
			companyrepository.save(toUpdateCompany);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCompany(Long id) {
		if (companyrepository.existsById(id)) {
			companyrepository.deleteById(id);
			return true;
		}
		return false;
	}

}
