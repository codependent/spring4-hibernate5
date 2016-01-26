package com.codependent.s4h5.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseEntity<IdType extends Serializable> implements Serializable {
	
	public abstract IdType getId();

	public abstract void setId(IdType id);

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
	    
	    retValue = "BaseEntity ( "
	        + super.toString() + TAB
	        + "id = " + this.getId() + TAB
	        + " )";
	
	    return retValue;
	}
	
}