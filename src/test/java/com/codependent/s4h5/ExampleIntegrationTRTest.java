package com.codependent.s4h5;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.testng.annotations.Test;

import com.codependent.s4h5.dto.FooEmployee;
import com.codependent.s4h5.service.IFooUserService;

@ContextConfiguration({ "/spring/test/test-context.xml" })
@Test
public class ExampleIntegrationTRTest extends AbstractTransactionalTestNGSpringContextTests {

	
	@Autowired
	IFooUserService fooUserService;

	private Integer createdFooWithRollback;

	private Integer createdFooWithoutRollback;

	@Autowired
	EntityManagerFactory entityManagerFactory;
	

	@Transactional
	public void testExampleInsertWithRollback() {
		
		FooEmployee response = null;
		response = fooUserService.getEmployee(1);

		
		FooEmployee newFoo = new FooEmployee();
		newFoo.setName("James");
		createdFooWithRollback = fooUserService.createEmployee(newFoo).getId();

		assertNotNull(createdFooWithRollback);
		EntityManagerHolder holder = (EntityManagerHolder)TransactionSynchronizationManager.getResource(entityManagerFactory);
		EntityManager em = holder.getEntityManager();
		em.flush();

		em.clear();

		response = null;
		response = fooUserService.getEmployee(createdFooWithRollback);

		assertNotNull(response);
		assertEquals(response.getId(), createdFooWithRollback);
		assertEquals(response.getName(), "James");

	}

	@Test(dependsOnMethods = "testExampleInsertWithRollback")
	@Rollback(value = false)
	public void testExampleInsertWithoutRollback() {

		FooEmployee newFoo = new FooEmployee();
		newFoo.setName("Charles");
		createdFooWithoutRollback = fooUserService.createEmployee(newFoo).getId();

		assertNotNull(createdFooWithoutRollback);

		FooEmployee response = null;
			response = fooUserService.getEmployee(createdFooWithoutRollback);

		assertNotNull(response);
		assertEquals(response.getId(), createdFooWithoutRollback);
		assertEquals(response.getName(), "Charles");

	}

	@Test(dependsOnMethods = "testExampleInsertWithRollback")
	@Transactional
	public void testExampleInsertWithRollbackChecking() {
		FooEmployee response = null;
		response = fooUserService.getEmployee(createdFooWithRollback);
		assertNull(response);
	}

	@Test(dependsOnMethods = "testExampleInsertWithoutRollback")
	@Transactional
	public void testExampleInsertWithoutRollbackChecking() {

		FooEmployee response = null;
		response = fooUserService.getEmployee(createdFooWithoutRollback);
		assertNotNull(response);
		assertEquals(response.getId(), createdFooWithoutRollback);
		assertEquals(response.getName(), "Charles");

	}

	@Test
	public void testExampleScript() {

		FooEmployee response = null;
		response = fooUserService.getEmployee(1);

		assertNotNull(response);
		assertEquals(response.getId(), (Integer) 1);
		assertEquals(response.getName(), "Louis");

	}
}
