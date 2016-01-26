package com.codependent.s4h5.dto;

import java.io.Serializable;
import java.util.List;

public class FooDepartment implements Serializable {

	private static final long serialVersionUID = -6865229817429105954L;

	private Integer id ;
	
	private String name;
	
	private List<Integer> employeesView;

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}
	
	public String getName() {
		return name;
	}

	public List<Integer> getEmployeesView() {
		return employeesView;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmployeesView(List<Integer> employeesView) {
		this.employeesView = employeesView;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "FooDepartamento ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "nombre = " + this.name + TAB
	        + "empleadosView = " + this.employeesView + TAB
	        + " )";
	
	    return retValue;
	}

}
