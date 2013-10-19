/**
 * $Id: CmArticleController.java,v 1.0 2013/02/06 23:34:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmConfigManager;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.bm.service.BmParamManager;
import org.ganjp.jpw.cm.model.CmArticle;
import org.ganjp.jpw.cm.model.CmAudio;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.service.CmArticleManager;
import org.ganjp.jpw.cm.service.CmAudioManager;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoManager;
import org.ganjp.jpw.cm.service.CmVideoManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>VtwoController jQuery Mobile Version</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/vtwo")
public class VtwoController extends BaseController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String toHome(HttpServletRequest request) {
		request.setAttribute("photoMaps", cmPhotoManager.getPhotoMaps(CmPhoto.PHOTO_TAG_WEBSITE_THEME, ServletUtil.getLanguage(request), Const.ROLE_NO_NEED));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("menuId", request.getParameter("menuId"));
		List pMenuMapAndMenuMapss = Config.getPmenuMapAndMenuMapss(BmParam.PARAM_CD_HOME_LINK_SET, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		request.setAttribute("nextLinkCategoryMap", pMenuMapAndMenuMapss.get(pMenuMapAndMenuMapss.size()-1));
		pMenuMapAndMenuMapss.remove(pMenuMapAndMenuMapss.size()-1);
		request.setAttribute("pMenuMapAndMenuMapss", pMenuMapAndMenuMapss);
		
		super.accessProject(request, Const.PROJECT_WEBSITE_NAVIGATE);
        return "common/vtwo/home";
	}

	@RequestMapping(value = "/knowledge", method = RequestMethod.POST)
	public String toKnowledgePost(HttpServletRequest request) {
		return this.toKnowledge(request);
	}
	
	@RequestMapping(value = "/knowledge", method = RequestMethod.GET)
	public String toKnowledge(HttpServletRequest request) {
		int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?30:Integer.parseInt(request.getParameter("pageSize"));
		String tag = request.getParameter("tag");
		List<Map<String,String>> articleMaps = cmArticleManager.getArticleMaps(tag, ServletUtil.getLanguage(request), request.getParameter("startDate"), 
				request.getParameter("endDate"), pageNo, pageSize, super.getLoginUserRoleIds(request));
		String articleCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_ARTICLE_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		List<BmParam> tagBmParams = bmParamManager.getBmParams(BmParam.PARAM_CD_ARTICLE_TAG, ServletUtil.getLanguage(request));
		
		Map<String,String> map = articleMaps.get(0);
		int totalPages = Integer.parseInt(map.get("totalPages"));
		int totalCount = Integer.parseInt(map.get("totalCount"));
		int nextPageNo = pageNo;
		int showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		articleMaps.remove(0);
		
		request.setAttribute("nextPageNo", pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("showCount", showCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("tag", tag);
		request.setAttribute("articleCategoryCheckboxHtml", articleCategoryCheckboxHtml);
		request.setAttribute("articleMaps", articleMaps);
		request.setAttribute("tagBmParams", tagBmParams);
		request.setAttribute("edit", request.getParameter("edit"));
		super.accessProject(request, Const.PROJECT_KNOWLEDGE);
        return "common/vtwo/knowledge/index";
	}

	@RequestMapping(value={"/knowledgeDetail"}, method={RequestMethod.POST})
	public String toKowledgeDetailPost(HttpServletRequest request) {
		return this.toKowledgeDetail(request);
	}
	@RequestMapping(value={"/knowledgeDetail"}, method={RequestMethod.GET})
	public String toKowledgeDetail(HttpServletRequest request) {
	    String articleId = request.getParameter("articleId");
	    CmArticle cmArticle = this.cmArticleManager.getCmArticleById(articleId);
	    request.setAttribute("cmArticle", cmArticle);

	    request.setAttribute("edit", request.getParameter("edit"));
	    request.setAttribute("pageNo", request.getParameter("pageNo"));
	    request.setAttribute("pageSize", request.getParameter("pageSize"));
	    request.setAttribute("tag", request.getParameter("tag"));
	    request.setAttribute("cmPhotos", cmPhotoManager.getCmPhotosByArticleId(articleId));
	    
	    return "common/vtwo/knowledge/knowledgeDetail";
	}
	
	@RequestMapping(value={"/photo"}, method=RequestMethod.POST)
	public String toPhotoPost(HttpServletRequest request) {
	    return toPhoto(request);
	}

	
	@RequestMapping(value = "/photo", method = RequestMethod.GET)
	public String toPhoto(HttpServletRequest request) {
		int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?30:Integer.parseInt(request.getParameter("pageSize"));
		String tag = request.getParameter("tag");
		String language = ServletUtil.getLanguage(request);
		List<Map<String,String>> photoMaps = cmPhotoManager.getPhotoMaps(tag, ServletUtil.getLanguage(request), request.getParameter("startDate"), 
				request.getParameter("endDate"), pageNo, pageSize, super.getLoginUserRoleIds(request));
		String photoCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_PHOTO_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		List<BmParam> tagBmParams = bmParamManager.getBmParams(BmParam.PARAM_CD_PHOTO_TAG, ServletUtil.getLanguage(request));
		
		Map<String,String> map = photoMaps.get(0);
		int totalPages = Integer.parseInt(map.get("totalPages"));
		int totalCount = Integer.parseInt(map.get("totalCount"));
		int nextPageNo = pageNo;
		int showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		photoMaps.remove(0);
		
		StringBuffer photoUrls = new StringBuffer("");
		for (Map<String,String> photoMap : photoMaps) {
			if ("".equals(photoUrls)) {
				photoUrls.append(photoMap.get("url"));
			} else {
				photoUrls.append(",").append(super.getBasePath(request)+photoMap.get("url"));
			}
		}
		
		request.setAttribute("nextPageNo", pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("showCount", showCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("tag", tag);
		request.setAttribute("photoCategoryCheckboxHtml", photoCategoryCheckboxHtml);
		request.setAttribute("photoMaps", photoMaps);
		request.setAttribute("tagBmParams", tagBmParams);
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("photoUrls", photoUrls);
		
		super.accessProject(request, Const.PROJECT_PHOTO);
        return "common/vtwo/photo/index";
	}

	@RequestMapping(value={"/audio"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String toAudioPost(HttpServletRequest request) {
	    return toAudio(request);
	}

	@RequestMapping(value = "/audio", method = RequestMethod.GET)
	public String toAudio(HttpServletRequest request) {
		int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?30:Integer.parseInt(request.getParameter("pageSize"));
		String tag = request.getParameter("tag");
		String language = ServletUtil.getLanguage(request);
		List<Map<String,String>> audioMaps = cmAudioManager.getAudioMaps(tag, ServletUtil.getLanguage(request), request.getParameter("startDate"), 
				request.getParameter("endDate"), pageNo, pageSize, super.getLoginUserRoleIds(request));
		String audioCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_AUDIO_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		List<BmParam> tagBmParams = bmParamManager.getBmParams(BmParam.PARAM_CD_AUDIO_TAG, ServletUtil.getLanguage(request));
		
		Map<String,String> map = audioMaps.get(0);
		int totalPages = Integer.parseInt(map.get("totalPages"));
		int totalCount = Integer.parseInt(map.get("totalCount"));
		int nextPageNo = pageNo;
		int showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		audioMaps.remove(0);
		
		BmConfig bmConfig = bmConfigManager.getBmConfig(BmConfig.CONFIG_CD_DEFAULT_AUDIO, ServletUtil.getLanguage(request));
		List<CmAudio> cmAudios = cmAudioManager.getCmAudiosByField("url", bmConfig.getConfigValue());
		if (cmAudios!=null && !cmAudios.isEmpty()) {
			CmAudio cmAudio = cmAudios.get(0);
			cmAudio.setDescription(StringUtil.convertForHtml(cmAudio.getDescription()));
			cmAudio.setRemark(StringUtil.convertForHtml(cmAudio.getRemark()));
			request.setAttribute("defaultCmAudio", cmAudio);
		}
		
		request.setAttribute("nextPageNo", pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("showCount", showCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("tag", tag);
		request.setAttribute("audioCategoryCheckboxHtml", audioCategoryCheckboxHtml);
		request.setAttribute("audioMaps", audioMaps);
		request.setAttribute("tagBmParams", tagBmParams);
		request.setAttribute("edit", request.getParameter("edit"));
		
		super.accessProject(request, Const.PROJECT_AUDIO);
        return "common/vtwo/audio/index";
	}

	@RequestMapping(value={"/video"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String toVideoPost(HttpServletRequest request) {
	    return toVideo(request);
	}

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String toVideo(HttpServletRequest request) {
		int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?30:Integer.parseInt(request.getParameter("pageSize"));
		String tag = request.getParameter("tag");
		String language = ServletUtil.getLanguage(request);
		List<Map<String,String>> videoMaps = cmVideoManager.getVideoMaps(tag, ServletUtil.getLanguage(request), request.getParameter("startDate"), 
				request.getParameter("endDate"), pageNo, pageSize, super.getLoginUserRoleIds(request));
		String videoCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_VIDEO_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		List<BmParam> tagBmParams = bmParamManager.getBmParams(BmParam.PARAM_CD_VIDEO_TAG, ServletUtil.getLanguage(request));
		
		Map<String,String> map = videoMaps.get(0);
		int totalPages = Integer.parseInt(map.get("totalPages"));
		int totalCount = Integer.parseInt(map.get("totalCount"));
		int nextPageNo = pageNo;
		int showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		videoMaps.remove(0);
		
		request.setAttribute("nextPageNo", pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("showCount", showCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("tag", tag);
		request.setAttribute("videoCategoryCheckboxHtml", videoCategoryCheckboxHtml);
		request.setAttribute("videoMaps", videoMaps);
		request.setAttribute("tagBmParams", tagBmParams);
		request.setAttribute("edit", request.getParameter("edit"));
		
		super.accessProject(request, Const.PROJECT_VIDEO);
        return "common/vtwo/video/index";
	}
	
	@RequestMapping(value = "/ecommerce", method = RequestMethod.GET)
	public String toEcommerce(HttpServletRequest request) {
		super.accessProject(request, Const.PROJECT_ECOMMERCE);
        return "common/vtwo/ecommerce/index";
	}
	
	@RequestMapping(value={"/download"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String toDownloadPost(HttpServletRequest request) {
	    return toDownload(request);
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String toDownload(HttpServletRequest request) {
		int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?30:Integer.parseInt(request.getParameter("pageSize"));
		String tag = request.getParameter("tag");
		String language = ServletUtil.getLanguage(request);
		List<Map<String,String>> fileMaps = cmFileManager.getFileMaps(tag, ServletUtil.getLanguage(request), request.getParameter("startDate"), 
				request.getParameter("endDate"), pageNo, pageSize, super.getLoginUserRoleIds(request));
		String fileCategoryCheckboxHtml = cmCategoryManager.getCategoryCheckboxHtml(CmCategory.CATEGORY_CD_VIDEO_CATEGORY, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request));
		List<BmParam> tagBmParams = bmParamManager.getBmParams(BmParam.PARAM_CD_FILE_TAG, ServletUtil.getLanguage(request));
		
		Map<String,String> map = fileMaps.get(0);
		int totalPages = Integer.parseInt(map.get("totalPages"));
		int totalCount = Integer.parseInt(map.get("totalCount"));
		int nextPageNo = pageNo;
		int showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		fileMaps.remove(0);
		
		request.setAttribute("nextPageNo", pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("showCount", showCount);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("tag", tag);
		request.setAttribute("fileCategoryCheckboxHtml", fileCategoryCheckboxHtml);
		request.setAttribute("fileMaps", fileMaps);
		request.setAttribute("tagBmParams", tagBmParams);
		request.setAttribute("edit", request.getParameter("edit"));
	
		super.accessProject(request, Const.PROJECT_DOWNLOAD);
        return "common/vtwo/download/index";
	}
	
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String toDemo(HttpServletRequest request) {
		super.accessProject(request, Const.PROJECT_DEMO);
        return "common/vtwo/demo/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/vtwo/home";
	}

	@RequestMapping(value = "/sessionTimeout", method = RequestMethod.GET)
	public String sessionTimeout(HttpServletRequest request) {
        return "common/vtwo/sessionTimeout";
	}

	@RequestMapping(value={"/SCEditor"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String toSCEditor(HttpServletRequest request) {
	    return "common/vtwo/demo/form/SCEditor/SCEditor";
	}

	@RequestMapping(value={"/masonry"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String toMasonry(HttpServletRequest request) {
	    return "common/vtwo/demo/layout/masonry/masonry";
	}
	
	@RequestMapping(value={"/magnificPopup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String toMagnificPopup(HttpServletRequest request) {
	    return "common/vtwo/demo/image/magnificPopup/index";
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