package com.codependent.s4h5.dto;

import com.codependent.s4h5.entity.BaseEntity;

public class FooEmployee extends BaseEntity<Integer> {

	private static final long serialVersionUID = 7939719659856960648L;

	private Integer id ;
	
	private String name;

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "FooEmpleado ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "nombre = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}

}
