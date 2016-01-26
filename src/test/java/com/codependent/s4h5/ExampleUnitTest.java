package com.codependent.s4h5;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.dozer.DozerBeanMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.codependent.s4h5.dao.IFooEmployeeDAO;
import com.codependent.s4h5.dto.FooEmployee;
import com.codependent.s4h5.entity.FooEmployeeEntity;
import com.codependent.s4h5.mapper.ObjectMapper;
import com.codependent.s4h5.service.IFooUserService;
import com.codependent.s4h5.service.impl.FooUserServiceImpl;

@Test
public class ExampleUnitTest {

	@InjectMocks
	IFooUserService fooUserService;

	@Mock
	IFooEmployeeDAO fooEmployeeDAO;

	@BeforeMethod
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public void prepareService() {

		fooUserService = new FooUserServiceImpl();

		ObjectMapper objectMapper = new ObjectMapper();
		ReflectionTestUtils.setField(objectMapper, "mapper", new DozerBeanMapper());

		ReflectionTestUtils.setField(fooUserService, "objectMapper", objectMapper);
	}

	public void testExample() {

		FooEmployeeEntity mockResponse = new FooEmployeeEntity();
		mockResponse.setId(1);
		mockResponse.setName("MI NOMBRE");

		when(fooEmployeeDAO.findOne(1)).thenReturn(mockResponse);

		FooEmployee response = null;
			response = fooUserService.getEmployee(1);

		assertNotNull(response);
		assertEquals(response.getId(), (Integer) 1);
		assertEquals(response.getName(), "MI NOMBRE");

		verify(fooEmployeeDAO).findOne(1);
		verify(fooEmployeeDAO, times(1)).findOne(anyInt());
	}
}
