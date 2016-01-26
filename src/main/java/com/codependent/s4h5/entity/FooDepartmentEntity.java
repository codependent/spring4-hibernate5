package com.codependent.s4h5.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TFODEPAR")
public class FooDepartmentEntity extends BaseEntity<Integer> {

	private static final long serialVersionUID = 2583160744316336577L;

	@Id
	@Column(name="FODEPAR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ;
	
	@Column(name="FODEPAR_NAME")
	private String name;
	
	@OneToMany(mappedBy="department",cascade={CascadeType.MERGE,CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
	private List<FooEmployeeEntity> employees;

	public Integer getId() {
		return id ;
	}

	public void setId(Integer id) {
		this.id = id ;
	}
	
	public String getName() {
		return name;
	}

	public List<FooEmployeeEntity> getEmployees() {
		return employees;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmployees(List<FooEmployeeEntity> employees) {
		this.employees = employees;
	}

	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "FooDepartamentoEntity ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "nombre = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}

}
