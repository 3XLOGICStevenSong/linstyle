/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.gene.action;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.ylt.common.util.CommonUtil;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.gene.dto.GeneProductsDTO;
import com.djb.ylt.gene.dto.TestResultMasterDTO;
import com.djb.ylt.gene.dtoclient.DetectAnalysisClientDTO;
import com.djb.ylt.gene.dtoclient.DetectResultClientDTO;
import com.djb.ylt.gene.dtoclient.DiseaseAnalysisClientDTO;
import com.djb.ylt.gene.dtoclient.GeneProductsClientDTO;
import com.djb.ylt.gene.dtoclient.GeneProductsListClientDTO;
import com.djb.ylt.gene.dtoclient.ResultListClientDTO;
import com.djb.ylt.gene.dtoclient.TestResultClientDTO;
import com.djb.ylt.gene.dtoclient.TestResultMasterClientDTO;
import com.djb.ylt.gene.entity.DetectAnalysisEntity;
import com.djb.ylt.gene.entity.GeneProductsEntity;
import com.djb.ylt.gene.entity.TestResultEntity;
import com.djb.ylt.gene.entity.TestResultMasterEntity;
import com.djb.ylt.gene.service.IDetectAnalysisService;
import com.djb.ylt.gene.service.IGeneProductsService;
import com.djb.ylt.gene.service.ITestResultMasterService;


/**
 * <p>
 * <strong>类名: </strong>
 * </p>
 * PatientAction <br/>
 * <p>
 * <strong>功能说明: </strong>
 * </p>
 * 这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p>
 * <strong>创建日期: </strong><br/>
 * </p>
 * 2016年7月29日下午1:19:01 <br/>
 * 
 * @author 林行
 * @version 1.0
 * @since JDK 1.8
 */

@Controller("/DetectResult")
public class DetectResultAction extends BaseAction {

	@Autowired
	@Qualifier("iTestResultMasterService")
	private ITestResultMasterService iTestResultMasterService;

	@Autowired
	@Qualifier("iDetectAnalysisService")
	private IDetectAnalysisService iDetectAnalysisService;
	
	public DetectResultAction() {
		super();
	}

	/**
	 * 获取基因检测结果
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * 
	 * @exception Exception
	 *                if business logic throws an exception
	 */
	public ActionForward doGetDetectResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		TestResultMasterDTO paramDTO = (TestResultMasterDTO) form;

		// 参数Entity
		TestResultMasterEntity paramEntity=new TestResultMasterEntity();

		// ClientResultDTO
		DetectResultClientDTO resultClientDTO = new DetectResultClientDTO();
		List<DetectAnalysisClientDTO> resultAnalysisList=null;
		
		List<TestResultClientDTO> testResultList=null;
		// 参数赋值
		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setRmId(paramDTO.getRmId());
		//test
		//paramEntity.setPatientId(1);
		// DB查询
		try {
			TestResultMasterEntity resultEntity = new TestResultMasterEntity();
			resultEntity=iTestResultMasterService.getObject(paramEntity);		 
			if(resultEntity!= null ){
				
				resultClientDTO.setEvaluate(resultEntity.getEvaluate());
				resultClientDTO.setDetectedName(resultEntity.getDetectedName());
				resultClientDTO.setDetectNum(resultEntity.getDetectNum());
				//resultClientDTO.setRmId(resultEntity.getRmId());
				//获取检查结果
					if(resultEntity.getTestResultEntitys()!=null && resultEntity.getTestResultEntitys().size()>0){
						testResultList=new ArrayList<TestResultClientDTO>();
						for(TestResultEntity param:resultEntity.getTestResultEntitys()){
							TestResultClientDTO testResultClientDTO=new TestResultClientDTO();
							CommonUtil.reflectClass(param, testResultClientDTO);
							testResultList.add(testResultClientDTO);
					}
						resultClientDTO.setDetectResultList(testResultList);
						}
					
					//结果分析
					DetectAnalysisEntity detectAnalysisEntity=new DetectAnalysisEntity();
					detectAnalysisEntity.setRmId(paramDTO.getRmId());
					List<DetectAnalysisEntity> resultAnalysis=null;
					resultAnalysis=iDetectAnalysisService.getDetectAnalysisList(detectAnalysisEntity);
					if(resultAnalysis!=null&& resultAnalysis.size()>0){
						resultAnalysisList=new ArrayList<DetectAnalysisClientDTO>();
						for(DetectAnalysisEntity param:resultAnalysis){
							DetectAnalysisClientDTO  analysisClientDTO=new DetectAnalysisClientDTO();
							analysisClientDTO.setDiseaseName(param.getDiseaseName());
							if(param.getDiseaseAnalysis()!=null){
								List<DiseaseAnalysisClientDTO> diseaseAnalysis = null;
								String[] gene=StringUtils.split(param.getDiseaseAnalysis(), ",");
								if(gene!=null&& gene.length>0){
									diseaseAnalysis=new ArrayList<DiseaseAnalysisClientDTO>();
									for(String str:gene){
										String[] diseaseDetail=StringUtils.split(str, ":");
										if(diseaseDetail.length==3){
											DiseaseAnalysisClientDTO detailinfo=new DiseaseAnalysisClientDTO();
											for(int i=0;i<=diseaseDetail.length;i++){
												
												detailinfo.setGeneName(diseaseDetail[0]);
												detailinfo.setGeneType(diseaseDetail[1]);
												detailinfo.setRiskAdded(diseaseDetail[2]);
												
												}
											diseaseAnalysis.add(detailinfo);
										}
									}
								}
								analysisClientDTO.setDiseaseAnalysis(diseaseAnalysis);
							}
							resultAnalysisList.add(analysisClientDTO);
						}
						resultClientDTO.setDetectAnalysisList(resultAnalysisList);
					}
	
			}
			
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8300");
		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}
	
	
	/**
	 * 获取基因检测列表
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * 
	 * @exception Exception
	 *                if business logic throws an exception
	 */
	public ActionForward doGetDetectResultList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		TestResultMasterDTO paramDTO = (TestResultMasterDTO) form;

		// 参数Entity
		TestResultMasterEntity paramEntity=new TestResultMasterEntity();

		// ClientResultDTO
		ResultListClientDTO resultClientDTO = new ResultListClientDTO();
		List<TestResultMasterClientDTO> resultTestList=null;
		
		
		// 参数赋值
		// CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setUserCode(paramDTO.getUserCode());
		//test
		//paramEntity.setPatientId(1);
		// DB查询
		try {
			List<TestResultMasterEntity> resultEntity=null;
			resultEntity=iTestResultMasterService.getTestResultMasterList(paramEntity);		 
			if(resultEntity!= null && resultEntity.size()>0){
				resultTestList=new ArrayList<TestResultMasterClientDTO>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
						for(TestResultMasterEntity param:resultEntity){
							TestResultMasterClientDTO testResultClientDTO=new TestResultMasterClientDTO();
							if(param.getDetectTime()!=null){
							testResultClientDTO.setDetectTime(sdf.format(param.getDetectTime()));
							}
							testResultClientDTO.setRmId(param.getRmId());
							testResultClientDTO.setProductsName(param.getProductsName());
							testResultClientDTO.setDetectedName(param.getDetectedName());
							resultTestList.add(testResultClientDTO);
					}
						resultClientDTO.setDetectResultList(resultTestList);
						}
					
					
			
			
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8700");
		}
		// return 处理
		if (!paramDTO.isErrFlg()) {
			resultClientDTO.setReturnCode("0");
			//
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}
}
