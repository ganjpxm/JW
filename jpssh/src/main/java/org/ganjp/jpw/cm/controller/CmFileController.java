/**
 * $Id: CmFileController.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.cm.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.bm.model.BmMenu;
import org.ganjp.jpw.cm.model.CmCategory;
import org.ganjp.jpw.cm.model.CmFile;
import org.ganjp.jpw.cm.service.CmCategoryManager;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.controller.BaseController;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>CmFileController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CmFileController extends BaseController {
	@RequestMapping(value = "/cmFile", method = RequestMethod.GET)
	public String toCmFileJqmList(HttpServletRequest request) {
		List<CmFile> cmFiles = cmFileManager.getCmFiles();
		request.setAttribute("cmFiles", cmFiles);
		super.setLeftMenu(request, BmMenu.MENU_TYPE_MANAGEMENT_CONSOLE);
        return "cm/cmFile/cmFileJqmList";
	}
	
	@RequestMapping(value = "/cmFileJqmAdd", method = RequestMethod.GET)
	public String toCmFileJqmAdd(HttpServletRequest request) {
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_FILE_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
		return "cm/cmFile/cmFileJqmAdd";
	}
	
	@RequestMapping(value = "/cmFileJqmEdit/{fileId}", method = RequestMethod.GET)
	public String toCmFileJqmEdit(@PathVariable String fileId, HttpServletRequest request) {
		CmFile cmFile = cmFileManager.getCmFileById(fileId);
		request.setAttribute("cmFile",cmFile);
		request.setAttribute("roleMaps", super.getRoleMapsForJqmCheck(request));
		request.setAttribute("categoryMaps", cmCategoryManager.getCategorysForJqmCheck(CmCategory.CATEGORY_CD_FILE_CATEGORY, ServletUtil.getLanguage(request)));
		request.setAttribute("from", request.getParameter("from"));
		request.setAttribute("edit", request.getParameter("edit"));
		request.setAttribute("tag", request.getParameter("tag"));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNo", request.getParameter("pageNo"));
        return "cm/cmFile/cmFileJqmEdit";
	}
	
	@RequestMapping(value = "/saveCmFile", method = RequestMethod.POST)
	public String saveCmFile(HttpServletRequest request, RedirectAttributes ra) {
		CmFile cmFile = new CmFile();
		setValue(request, cmFile);
		cmFileManager.save(cmFile);
		ra.addAttribute("menuId", request.getParameter("menuId"));
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			return "redirect:/cmFile";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/updateCmFile", method = RequestMethod.POST)
	public String updateCmFile(HttpServletRequest request, RedirectAttributes ra) {
		String fileId = request.getParameter("fileId");
		CmFile cmFile = cmFileManager.getCmFileById(fileId);
		setValue(request, cmFile);
		cmFileManager.update(cmFile);
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			String categoryId = request.getParameter("categoryId");
			if (StringUtil.isNotEmpty(categoryId)) {
				ra.addAttribute("categoryId", categoryId);		
				return "redirect:/cmFileCategory";		
			} else {
				return "redirect:/cmFile";
			}
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("searchTag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}
	
	@RequestMapping(value = "/deleteCmFile/{fileId}", method = RequestMethod.GET)
	public String deleteCmFile(@PathVariable String fileId, HttpServletRequest request, RedirectAttributes ra) {
		cmFileManager.delete(fileId);
		String fileName = request.getParameter("fileName");
		if (StringUtil.isNotEmpty(fileName)) {
			FileUtil.delete(new File(FileUtil.getPath(request.getRealPath(""), "resources", "upload", "file", fileName)));
		}
		
		String from = request.getParameter("from");
		if (StringUtil.isEmpty(from)) {
			ra.addAttribute("menuId", request.getParameter("menuId"));
			return "redirect:/cmFile";
		} else {
			ra.addAttribute("edit", request.getParameter("edit"));
			ra.addAttribute("tag", request.getParameter("tag"));
			ra.addAttribute("pageNo", request.getParameter("pageNo"));
			ra.addAttribute("pageSize", request.getParameter("pageSize"));
			return "redirect:" + from;
		}
	}

	@RequestMapping(value = "/deleteCmFiles", method = RequestMethod.POST)
	public String deleteCmFiles(HttpServletRequest request, RedirectAttributes ra) {
		cmFileManager.batchDelete(request.getParameter("fileIds"));
		ra.addAttribute("menuId", request.getParameter("menuId"));
		return "redirect:/cmFile"; 
	}
	
	@Resource(name="cmFileManagerImpl")
	private CmFileManager cmFileManager;
	
	@Resource(name="cmCategoryManagerImpl")
	private CmCategoryManager cmCategoryManager;
}