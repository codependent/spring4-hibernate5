package com.codependent.s4h5.service;

import java.util.List;

import com.codependent.s4h5.dto.FooDepartment;
import com.codependent.s4h5.dto.FooEmployee;

public interface IFooUserService {

	FooEmployee createEmployee(FooEmployee employee);
	FooEmployee updateEmployee(FooEmployee employee);
	FooEmployee getEmployee(int id);
	List<FooEmployee> buscarEmployees(String nombre);
	List<FooEmployee> getEmployees();
	void eliminarEmployee(int id);
	List<FooEmployee> buscarEmployeesDepartment(Integer idDepartment);
	List<FooEmployee> buscarEmployeesSinDepartment();
	
	FooDepartment createDepartment(FooDepartment department);
	FooDepartment updateDepartment(FooDepartment department);
	FooDepartment getDepartment(int id);
	FooDepartment getDepartment(String name);
	List<FooDepartment> getDepartments();
	void eliminarDepartment(int id);
	
}
