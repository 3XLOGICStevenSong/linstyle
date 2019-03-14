/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.gene.action;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.djb.ylt.framework.action.BaseAction;
import com.djb.ylt.gene.dto.GeneProductsDTO;
import com.djb.ylt.gene.dtoclient.GeneProductsClientDTO;
import com.djb.ylt.gene.dtoclient.GeneProductsListClientDTO;
import com.djb.ylt.gene.entity.GeneProductsEntity;
import com.djb.ylt.gene.service.IGeneProductsService;


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

@Controller("/GeneProducts")
public class GeneProductsAction extends BaseAction {

	@Autowired
	@Qualifier("iGeneProductsService")
	private IGeneProductsService iGeneProductsService;

	public GeneProductsAction() {
		super();
	}

	/**
	 * 基因产品列表
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
	public ActionForward doGetGeneProductsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		GeneProductsDTO paramDTO = (GeneProductsDTO) form;

		// 参数Entity
		// GeneProductsEntity paramEntity=new GeneProductsEntity();

		// ClientResultDTO
		GeneProductsListClientDTO resultClientDTO = new GeneProductsListClientDTO();
		List<GeneProductsClientDTO> resultList=null;
		// 参数赋值
		// CommonUtil.reflectClass(paramDTO, paramEntity);

		// DB查询
		try {
			List<GeneProductsEntity> resultEntity=null;
			resultEntity=iGeneProductsService.getGeneProductsList();
			if(resultEntity!=null&& resultEntity.size()>0){
				resultList=new ArrayList<GeneProductsClientDTO>();
				for(GeneProductsEntity param:resultEntity){
					GeneProductsClientDTO clientDTO=new GeneProductsClientDTO();
					//CommonUtil.reflectClass(param, clientDTO);
					//if(param.getSimplePrice()!=null){
					//	clientDTO.setSimplePrice(param.getSimplePrice().toString());
					//}
					if(param.getStandardPrice()!=null){
						clientDTO.setStandardPrice(param.getStandardPrice().toString());
					}
					if(param.getPrice()!=null){
						clientDTO.setOriginalPrice(param.getPrice().toString());
					}
					clientDTO.setContent(param.getContent());
					clientDTO.setGpName(param.getGpName());
					clientDTO.setGpId(param.getGpId());
					clientDTO.setSignificance(param.getSignificance());
					resultList.add(clientDTO);
				}
				resultClientDTO.setGeneProductsList(resultList);
			}
			
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8100");
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
	 * 基因产品详细
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
	public ActionForward doGetProductsDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		GeneProductsDTO paramDTO = (GeneProductsDTO) form;

		// 参数Entity
		 GeneProductsEntity paramEntity=new GeneProductsEntity();

		// ClientResultDTO
		GeneProductsClientDTO resultClientDTO = new GeneProductsClientDTO();
	
		// 参数赋值
		//CommonUtil.reflectClass(paramDTO, paramEntity);
		paramEntity.setGpId(paramDTO.getGpId());
		// DB查询
		try {
			GeneProductsEntity resultEntity=new GeneProductsEntity();
			resultEntity=iGeneProductsService.getObject(paramEntity);
			if(resultEntity!=null){
				resultClientDTO.setContent(resultEntity.getContent());
				resultClientDTO.setGpName(resultEntity.getGpName());
				resultClientDTO.setSignificance(resultEntity.getSignificance());
				resultClientDTO.setFitPeople(resultEntity.getFitPeople());		
				if(resultEntity.getStandardPrice()!=null){
				resultClientDTO.setStandardPrice(resultEntity.getStandardPrice().toString());}
				}
			
		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-8600");
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
