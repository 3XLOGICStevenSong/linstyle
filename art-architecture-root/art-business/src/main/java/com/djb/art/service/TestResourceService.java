package com.djb.art.service;

import java.util.List;

import com.djb.art.model.TestResource;

public interface TestResourceService {
	
	public Integer createTestResource(TestResource resource);
	
	public Integer updateTestResource(TestResource resource);
	
	public Integer deleteTestResource(Integer id);
	
	public TestResource getTestResourceById(Integer id);
	
	public List<TestResource> getTestResources(String partName, Integer start, Integer limit);
	
}
