package com.codependent.s4h5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codependent.s4h5.entity.FooEmployeeEntity;

public interface IFooEmployeeDAO extends JpaRepository<FooEmployeeEntity, Integer>{

	List<FooEmployeeEntity> findByName(String name);
	
	List<FooEmployeeEntity> findByDepartmentId(Integer id);
	
}
