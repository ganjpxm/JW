/**
 * $Id: CmArticleController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.common.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.bm.model.BmParam;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmPhotoCategoryManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>VtwoController jQuery Mobile Version</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/vone")
public class VoneController extends BaseController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String toHome(HttpServletRequest request) {
		String categoryId = cmCategoryManager.getCategoryId(CmCategory.CATEGORY_CD_HOME_PHOTO_SCROLLED, ServletUtil.getLanguage(request));
		request.setAttribute("photoMaps", cmPhotoCategoryManager.getPhotoMaps(categoryId,super.getLoginUserRoleIds(request)));
		Map levelAndMenuMapss = Config.getLevelAndMenuMapss(BmParam.PARAM_CD_HOME_MAIN_MENU, 
				ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request), request.getParameter("menuId"));
		request.setAttribute("mainMenuMaps", levelAndMenuMapss.get("first"));
		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("pMenuMapAndMenuMapss",
				Config.getPmenuMapAndMenuMapss(BmParam.PARAM_CD_HOME_LINK_SET, ServletUtil.getLanguage(request), super.getLoginUserRoleIds(request)));
        return "common/vtwo/home";
	}

	@RequestMapping(value = "/console", method = RequestMethod.GET)
	public String toConsole(HttpServletRequest request) {
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
		request.setAttribute("serverTime", new Date());
		request.setAttribute("systemUserName", Const.SYSTEM_USER_NAME);
		request.setAttribute("systemUserHome", Const.SYSTEM_USER_HOME);
		request.setAttribute("systemUserDir", Const.SYSTEM_USER_DIR);
		request.setAttribute("javaVersion", Const.JAVA_VERSION);
		request.setAttribute("javaVendor", Const.JAVA_VENDOR);
		request.setAttribute("javaHome", Const.JAVA_HOME);
		request.setAttribute("javaIoTmpDir", Const.JAVA_IO_TMP_DIR);
		request.setAttribute("osName", Const.OS_NAME);
		request.setAttribute("osVersion", Const.OS_VERSION);
        return "cm/common/jqmStyle/console";
	}
	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadFile(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request) {
		String folder = request.getParameter("folder");
		String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", folder, file.getOriginalFilename());
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		if (file.getSize() > 1000000) {
			map.put("result", file.getOriginalFilename() + " size is more than 1M");
		}
		try {
			String saveUrl = "/resources/upload/"+folder+"/" + file.getOriginalFilename();
			map.put("saveUrl", saveUrl);
			map.put("showUrl", super.getBasePath(request) + saveUrl);
			if (new File(saveFullPath).exists()) {
				map.put("result", file.getOriginalFilename() + " is exist");
				return map;
			}
			FileUtil.copy(file.getInputStream(), new File(saveFullPath));
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", file.getOriginalFilename() + " upload fail");
			return map;
		}
		return map;
	}
	
	@RequestMapping(value="/deleteFile", method=RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteFile(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		try {
			String filePath = FileUtil.getPath(request.getRealPath(""), request.getParameter("fileUrl"));
			File file = new File(filePath);
			if (file.exists()) {
				FileUtil.delete(file);
			} else {
				map.put("result", "The file is not exist.");
			}
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	

	@Resource(name="cmPhotoCategoryManagerImpl")
	private CmPhotoCategoryManager cmPhotoCategoryManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
	
	@Resource(name="bmMenuManagerImpl")
	private BmMenuManager bmMenuManager;
}