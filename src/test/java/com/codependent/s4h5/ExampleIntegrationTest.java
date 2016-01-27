package com.codependent.s4h5;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.codependent.s4h5.dao.IFooEmployeeDAO;
import com.codependent.s4h5.dto.FooEmployee;
import com.codependent.s4h5.entity.FooEmployeeEntity;
import com.codependent.s4h5.service.IFooUserService;

@ContextConfiguration(loader=SpringockitoContextLoader.class, locations = { "/spring/test/test-context.xml" })
@Test
public class ExampleIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	IFooUserService fooUserService;

	@ReplaceWithMock
	@Autowired
	IFooEmployeeDAO fooEmployeeDAO;

	public void testExample() {

		FooEmployeeEntity mockResponse = new FooEmployeeEntity();
		mockResponse.setId(1);
		mockResponse.setName("John");

		when(fooEmployeeDAO.findOne(1)).thenReturn(mockResponse);

		FooEmployee response = null;
		response = fooUserService.getEmployee(1);

		assertNotNull(response);
		assertEquals(response.getId(), (Integer) 1);
		assertEquals(response.getName(), "John");

		verify(fooEmployeeDAO).findOne(1);

		verify(fooEmployeeDAO, times(1)).findOne(anyInt());
	}
}
