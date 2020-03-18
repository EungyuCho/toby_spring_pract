package com.toby.pract1.updatabledb;

import sqlService.ConcurrentHashMapSqlRegistry;
import sqlService.issuetracker.UpdatableSqlRegistry;

public class ConcurrentHashMapRegistryTest extends AbstractUpdatableSqlRegistryTest{

	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		return new ConcurrentHashMapSqlRegistry();
	}
	
}
