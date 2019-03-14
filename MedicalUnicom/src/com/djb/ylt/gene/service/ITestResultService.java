package com.djb.ylt.gene.service;

import java.util.List;

import com.djb.ylt.gene.entity.TestResultEntity;

public interface ITestResultService {

	public void addTestResult(TestResultEntity testResult);

	public void deleteTestResult(TestResultEntity testResult);

	public void deleteTestResultBatch(List<TestResultEntity> list);

	public void updateTestResult(TestResultEntity testResult);

	public TestResultEntity getObject(TestResultEntity testResult);

	public List<TestResultEntity> getTestResultList();

	public List<TestResultEntity> getTestResultList(TestResultEntity testResult);
}
