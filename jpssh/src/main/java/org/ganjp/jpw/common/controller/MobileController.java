/**
 * $Id: CmArticleController.java,v 1.0 2013/02/06 23:34:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.service.CmArticleManager;
import org.ganjp.jpw.cm.service.CmAudioManager;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoManager;
import org.ganjp.jpw.cm.service.CmVideoManager;
import org.ganjp.jpw.core.model.Page;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>VtwoController jQuery Mobile Version</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/mobile")
public class MobileController extends BaseController {
	
	@RequestMapping(value="/getBmConfigsGet", method=RequestMethod.GET)
	public @ResponseBody List<BmConfig> getBmConfigsGet(HttpServletRequest request) {
		return this.getBmConfigs(request);
	}
	/**
	 * <p>Get bmConfigs</p>
	 * <pre>
	 * mobile/getBmConfigs?configCds=FileUrl&lastDateTime=01/01/2013 08:01:23
	 * mobile/getBmConfigs?configCds=FileUrl&lastTime=1379112384000
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBmConfigs", method=RequestMethod.POST)
	public @ResponseBody List<BmConfig> getBmConfigs(HttpServletRequest request) {
		long lastTime = StringUtil.isEmpty(request.getParameter("lastTime"))?0:Long.parseLong(request.getParameter("lastTime"));
		if (lastTime==0) lastTime = DateUtil.pareDdMmYyyyHhMmSs(request.getParameter("lastDateTime"));// dd/MM/yyyy HH:mm:ss
		String configCds = request.getParameter("configCds");
		List<BmConfig> bmConfigs = bmConfigManager.getBmConfigs(lastTime, configCds);
		return bmConfigs;
	}
	
	/**
	 * <p>Get CmArticles</p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCmArticles", method=RequestMethod.POST)
	public @ResponseBody List<CmArticle> getCmArticles(HttpServletRequest request) {
		String tag = request.getParameter("tag");
		String lang = request.getParameter("lang");
		String startDate = request.getParameter("startDate"); //dd/MM/yyyy hh:mm:ss
		String endDate = request.getParameter("endDate"); //dd/MM/yyyy hh:mm:ss
		int pageNo = request.getParameter("pageNo")==null?1:Integer.parseInt(request.getParameter("pageNo"));;
		int pageSize = request.getParameter("pageSize")==null?10000000:Integer.parseInt(request.getParameter("pageSize"));
		Page<CmArticle> cmArticlePage = cmArticleManager.getCmArticlePage(tag, lang, startDate, endDate, pageNo, pageSize, super.getLoginUserRoleIds(request));
		return cmArticlePage.getResult();
	}
	
	/**
	 * <p>Get CmPhotos</p>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCmPhotos", method=RequestMethod.POST)
	public @ResponseBody List<CmPhoto> getCmPhotos(HttpServletRequest request) {
		String tag = request.getParameter("tag");
		String lang = request.getParameter("lang");
		String startDate = request.getParameter("startDate"); //dd/MM/yyyy
		String endDate = request.getParameter("endDate"); //dd/MM/yyyy
		int pageNo = request.getParameter("pageNo")==null?1:Integer.parseInt(request.getParameter("pageNo"));;
		int pageSize = request.getParameter("pageSize")==null?10000000:Integer.parseInt(request.getParameter("pageSize"));
		Page<CmPhoto> cmPhotoPage = cmPhotoManager.getCmPhotoPage(tag, lang, startDate, endDate, pageNo, pageSize, super.getLoginUserRoleIds(request));
		return cmPhotoPage.getResult();
	}
	
	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
	
	@Resource(name="bmMenuManagerImpl")
	private BmMenuManager bmMenuManager;
	
	@Resource(name="cmArticleManagerImpl")
	private CmArticleManager cmArticleManager;
	
	@Resource(name="cmPhotoManagerImpl")
	private CmPhotoManager cmPhotoManager;
	
	@Resource(name="bmParamManagerImpl")
	private BmParamManager bmParamManager;

	@Resource(name="cmAudioManagerImpl")
	private CmAudioManager cmAudioManager;

	@Resource(name="cmVideoManagerImpl")
	private CmVideoManager cmVideoManager;

	@Resource(name="cmFileManagerImpl")
	private CmFileManager cmFileManager;

	@Resource(name="bmConfigManagerImpl")
	private BmConfigManager bmConfigManager;

}