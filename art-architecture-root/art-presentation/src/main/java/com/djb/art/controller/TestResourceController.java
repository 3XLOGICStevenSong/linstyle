package com.djb.art.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djb.art.model.TestResource;
import com.djb.art.service.TestResourceService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class TestResourceController {
	
	private static Logger log =  LoggerFactory.getLogger(TestResourceController.class);
	
	@Autowired
	private TestResourceService testResourceService;

	
	@RequiresPermissions("testresource:create")
	@PostMapping("~resource/TestResource")
	public Integer createTestResource(@RequestBody TestResource resource) {
		log.debug("test");
		return testResourceService.createTestResource(resource);
	}
	
	@RequiresPermissions("testresource:update")
	@PutMapping("~resource/TestResource")
	public Integer updateTestResource(@RequestBody TestResource resource) {
		return testResourceService.updateTestResource(resource);
	}
	
	@RequiresPermissions("testresource:delete")
	@DeleteMapping("~resource/TestResource/{id}")
	public Integer removeTestResource(@PathVariable("id") Integer id) {
		return testResourceService.deleteTestResource(id);
	}
	
	@RequiresPermissions("testresource:view")
	@JsonView(TestResource.ListView.class)
	@GetMapping("~resource/TestResource")
	public List<TestResource> queryTestResources(
			@RequestParam(name="partName", required=false) String partName,
			@RequestParam(name="start", required=false) Integer start,
			@RequestParam(name="limit", required=false) Integer limit
	) {
		return testResourceService.getTestResources(partName, start, limit);
	}
	
	@RequiresPermissions("testresource:view")
	@GetMapping("~resource/TestResource/{id}")
	public TestResource getTestResource(@PathVariable("id") Integer id) {
		return testResourceService.getTestResourceById(id);
	}
}
