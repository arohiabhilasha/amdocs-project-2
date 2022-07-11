package com.example.StudentCrud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.example.StudentCrud.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	/* JpaRepository
	 * specific extension of Repository. It contains the full API of CrudRepository
	 * and PagingAndSortingRepository. So it contains API for basic CRUD operations
	 * and also API for pagination and sorting.
	 */ 
}