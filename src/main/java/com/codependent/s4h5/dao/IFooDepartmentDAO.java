package com.codependent.s4h5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codependent.s4h5.entity.FooDepartmentEntity;

public interface IFooDepartmentDAO extends JpaRepository<FooDepartmentEntity, Integer>{

	FooDepartmentEntity findByName(String name);
	
	@Query("from FooDepartmentEntity dep left join fetch dep.employees where dep.id = :depId")
	FooDepartmentEntity findByIdWithEmployees(@Param("depId") Integer id);
	
}
