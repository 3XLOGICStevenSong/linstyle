package com.djb.art.repository;

import java.util.List;
import java.util.Map;

import com.djb.art.cms.annotations.MybatisRepository;
import com.djb.art.model.TestResource;

@MybatisRepository
public interface TestResourceRepository {

	public Integer insertTestResource(TestResource resource);
	
	public Integer updateTestResource(TestResource resource);
	
	public Integer deleteTestResourceById(Integer id);
	
	public List<TestResource> selectTestResources(Map<String, Object> conditions);
	
	public TestResource selectTestResourceById(Integer id);
	
}
