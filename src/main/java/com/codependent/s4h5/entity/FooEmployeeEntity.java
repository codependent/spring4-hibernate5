package com.codependent.s4h5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TFOEMPLE")
public class FooEmployeeEntity extends BaseEntity<Integer> {

	private static final long serialVersionUID = 2583160744316336577L;

	@Id
	@Column(name="FOEMPLE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	
	@Column(name="FOEMPLE_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="FOEMPLE_FODEPAR_ID")
	private FooDepartmentEntity department;

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}
	
	public String getName() {
		return name;
	}

	public FooDepartmentEntity getDepartment() {
		return department;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(FooDepartmentEntity department) {
		this.department = department;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "FooEmpleadoEntity ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "nombre = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}

}
