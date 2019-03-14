package com.djb.art.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djb.art.model.TestResource;
import com.djb.art.service.TestResourceService;
import com.djb.art.repository.TestResourceRepository;;

@Service
public class TestResourceServiceImpl implements TestResourceService {

	@Autowired
	private TestResourceRepository testResourceRepository;
	
	
	@Override
	public Integer createTestResource(TestResource resource) {
		return testResourceRepository.insertTestResource(resource);
	}

	@Override
	public Integer deleteTestResource(Integer id) {
		return testResourceRepository.deleteTestResourceById(id);
	}

	@Override
	public TestResource getTestResourceById(Integer id) {
		return testResourceRepository.selectTestResourceById(id);
	}

	@Override
	public List<TestResource> getTestResources(String partName, Integer start, Integer limit) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("partName", partName);
		conditions.put("start", start);
		conditions.put("limit", limit);
		return testResourceRepository.selectTestResources(conditions);
	}

	@Override
	public Integer updateTestResource(TestResource resource) {
		return testResourceRepository.updateTestResource(resource);
	}
	
}
