package com.codependent.s4h5.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codependent.s4h5.dao.IFooDepartmentDAO;
import com.codependent.s4h5.dao.IFooEmployeeDAO;
import com.codependent.s4h5.dto.FooDepartment;
import com.codependent.s4h5.dto.FooEmployee;
import com.codependent.s4h5.entity.FooDepartmentEntity;
import com.codependent.s4h5.entity.FooEmployeeEntity;
import com.codependent.s4h5.mapper.ObjectMapper;
import com.codependent.s4h5.service.IFooUserService;

@Service
@Transactional
public class FooUserServiceImpl implements IFooUserService {

	@Autowired
	private IFooEmployeeDAO fooEmployeeDAO;
	
	@Autowired
	private IFooDepartmentDAO fooDepartmentDAO;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public FooEmployee createEmployee(FooEmployee employee) {
		FooEmployeeEntity emp = fooEmployeeDAO.save(objectMapper.convert(employee, FooEmployeeEntity.class));
		return objectMapper.convert(emp, FooEmployee.class);
	}
	
	@Override
	public FooEmployee updateEmployee(FooEmployee employee) {
		FooEmployeeEntity empBBDD = fooEmployeeDAO.findOne(employee.getId());
		empBBDD.setName(employee.getName());
		FooEmployeeEntity emp = fooEmployeeDAO.save(empBBDD);
		return objectMapper.convert(emp, FooEmployee.class);
	}

	@Override
	public FooDepartment createDepartment(FooDepartment department) {
		FooDepartmentEntity dep = fooDepartmentDAO.save(objectMapper.convert(department, FooDepartmentEntity.class));
		if(department.getEmployeesView()!=null){
			for (Integer idEmpl : department.getEmployeesView()) {
				FooEmployeeEntity employee = fooEmployeeDAO.findOne(idEmpl);
				employee.setDepartment(dep);
				fooEmployeeDAO.save(employee);
			}
		}
		return objectMapper.convert(dep, FooDepartment.class);
	}

	@Transactional(readOnly=true)
	@Override
	public FooEmployee getEmployee(int id){
		FooEmployeeEntity emp = fooEmployeeDAO.findOne(id);
		return objectMapper.convert(emp, FooEmployee.class);
	}

	@Transactional(readOnly=true)
	@Override
	public List<FooEmployee> buscarEmployees(String nombre) {
		List<FooEmployeeEntity> emp = fooEmployeeDAO.findByName(nombre);
		return objectMapper.convert(emp, FooEmployee.class);
	}
		

	@Transactional(readOnly=true)
	@Override
	public List<FooEmployee> getEmployees(){
		return objectMapper.convert(fooEmployeeDAO.findAll(), FooEmployee.class);
	}
	
	@Transactional(readOnly=true)
	@Override
	public FooDepartment getDepartment(int id) {
		FooDepartmentEntity dep = fooDepartmentDAO.findOne(id);
		if(dep!=null){
			dep.setEmployees(null);
		}
		return objectMapper.convert(dep, FooDepartment.class);
	}

	@Transactional(readOnly=true)
	@Override
	public FooDepartment getDepartment(String name) {
		FooDepartmentEntity dep = fooDepartmentDAO.findByName(name);
		if(dep!=null){
			dep.setEmployees(null);
		}
		return objectMapper.convert(dep, FooDepartment.class);
	}

	@Override
	public void eliminarEmployee(int id) {
		fooEmployeeDAO.delete(id);
	}

	@Override
	public FooDepartment updateDepartment(FooDepartment department) {
		FooDepartmentEntity depBBDD = fooDepartmentDAO.findByIdWithEmployees(department.getId());
		depBBDD.setName(department.getName());
		
		for (Iterator<FooEmployeeEntity> iterator = depBBDD.getEmployees().iterator(); iterator.hasNext();) {
			boolean borrar = true;
			FooEmployeeEntity empBBDD = iterator.next();
			if(department.getEmployeesView()!=null){
				for (Integer ev : department.getEmployeesView()) {
					if(empBBDD.getId().equals(ev)){
						borrar = false;
					}
				}
			}
			if(borrar){
				empBBDD.setDepartment(null);
				iterator.remove();
			}
		}
		
		Set<Integer> employeesNuevos = new HashSet<Integer>();
		if(department.getEmployeesView()!=null){
			for (Integer ev : department.getEmployeesView()) {
				boolean nuevo = true;
				for (FooEmployeeEntity empBBDD : depBBDD.getEmployees()) {
					if(empBBDD.getId().equals(ev)){
						nuevo = false;
					}
				}
				if(nuevo){
					employeesNuevos.add(ev);
				}
			}
		}
		
		for (Integer id : employeesNuevos) {
			FooEmployeeEntity empBBDD = fooEmployeeDAO.findOne(id);
			empBBDD.setDepartment(depBBDD);
			depBBDD.getEmployees().add(empBBDD);
		}
		
		depBBDD = fooDepartmentDAO.save(depBBDD);
		depBBDD.setEmployees(null);
		return objectMapper.convert(depBBDD, FooDepartment.class);
	}

	@Transactional(readOnly=true)
	@Override
	public List<FooDepartment> getDepartments() {
		List<FooDepartmentEntity> departments = fooDepartmentDAO.findAll();
		for (FooDepartmentEntity fooDepartmentEntity : departments) {
			fooDepartmentEntity.setEmployees(null);
		}
		return objectMapper.convert(departments, FooDepartment.class);
	}

	@Override
	public void eliminarDepartment(int id) {
		FooDepartmentEntity depBBDD = fooDepartmentDAO.findByIdWithEmployees(id);
		for (FooEmployeeEntity empBBDD : depBBDD.getEmployees()) {
			empBBDD.setDepartment(null);
		}
		fooDepartmentDAO.delete(id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<FooEmployee> buscarEmployeesDepartment(Integer idDepartment) {
		List<FooEmployeeEntity> emps = fooEmployeeDAO.findByDepartmentId(idDepartment);
		for (FooEmployeeEntity fooEmployeeEntity : emps) {
			fooEmployeeEntity.setDepartment(null);
		}
		return objectMapper.convert(emps, FooEmployee.class);
	}

	@Transactional(readOnly=true)
	@Override
	public List<FooEmployee> buscarEmployeesSinDepartment() {
		return buscarEmployeesDepartment(null);
	}

}
