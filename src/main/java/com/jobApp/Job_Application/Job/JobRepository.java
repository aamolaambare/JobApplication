package com.jobApp.Job_Application.Job;

import org.springframework.data.jpa.repository.JpaRepository;

//You can create custom repository by extending 
// CrudRepository Interface or JpaRepository Interface
// CrudRepository : It Provides basic CRUD operations
// JpaRepository<Entity, Primary-Key>  : It Contains CRUD Operation and Also Some special methods

// We are using JpaRepository to convert Entity into table row
// We are using Repository here to avoid  to write boiler plate code that make DB operation 
// JpaRepository<ENTITY_TYPE, PRIMARY_KEY>

//At the runtime JPA provides the implementation of this repository 

public interface JobRepository extends JpaRepository<Job, Long>{
	
}
