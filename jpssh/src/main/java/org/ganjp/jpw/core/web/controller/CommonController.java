/**
 * $Id: CommonController.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.web.controller;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.am.model.AmUser;
import org.ganjp.jpw.am.service.AmUserManager;
import org.ganjp.jpw.bm.model.BmConfig;
import org.ganjp.jpw.bm.service.BmMenuManager;
import org.ganjp.jpw.cm.model.CmAudio;
import org.ganjp.jpw.cm.model.CmFile;
import org.ganjp.jpw.cm.model.CmPhoto;
import org.ganjp.jpw.cm.model.CmVideo;
import org.ganjp.jpw.cm.service.CmAudioManager;
import org.ganjp.jpw.cm.service.CmFileManager;
import org.ganjp.jpw.cm.service.CmPhotoManager;
import org.ganjp.jpw.cm.service.CmVideoManager;
import org.ganjp.jpw.core.Config;
import org.ganjp.jpw.core.Const;
import org.ganjp.jpw.core.util.DateUtil;
import org.ganjp.jpw.core.util.FileUtil;
import org.ganjp.jpw.core.util.RackspaceCloudFilesUtil;
import org.ganjp.jpw.core.util.StringUtil;
import org.ganjp.jpw.core.web.util.ServletUtil;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests from common view for the application.
 */
@Controller
public class CommonController extends BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		request.setAttribute("check", request.getParameter("check"));
		return "redirect:" + Config.getValue("homePage");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:" + Config.getValue("homePage");
	}
	
	@RequestMapping(value = "/changeJqmThemeLn/{jqmTheme}/{language}", method = RequestMethod.GET)
	public String changeThemeLn(@PathVariable String jqmTheme, @PathVariable String language, HttpServletRequest request) {
		request.setAttribute(Const.JQM_THEME, jqmTheme);
		request.setAttribute(Const.LANGUAGE, language);
		String url = request.getRequestURL().toString();
		return url;
	}
	
	@RequestMapping(value = "/systemInformation", method = RequestMethod.GET)
	public String getSystemInformation(Locale locale, Model model) {
		model.addAttribute("serverTime", DateUtil.getNowDateTime(locale));
        model.addAttribute("systemAmUserName", Const.SYSTEM_USER_NAME);
        model.addAttribute("systemAmUserHome", Const.SYSTEM_USER_HOME);
        model.addAttribute("systemAmUserDir", Const.SYSTEM_USER_DIR);
        model.addAttribute("javaVersion", Const.JAVA_VERSION);
        model.addAttribute("javaVendor", Const.JAVA_VENDOR);
        model.addAttribute("javaHome", Const.JAVA_HOME);
        model.addAttribute("javaIoTmpDir", Const.JAVA_IO_TMP_DIR);
        model.addAttribute("osName", Const.OS_NAME);
        model.addAttribute("osVersion", Const.OS_VERSION);
        return "common/systemInformation";
	}

	@RequestMapping(value="/uploadMobileFile", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadMobileFile(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request) {
		String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", "mobile", file.getOriginalFilename());
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		if (file.getSize() > 1000000) {
			map.put("result", file.getOriginalFilename() + " size is more than 1M");
		}
		try {
			String saveUrl = "/resources/upload/mobile/" + file.getOriginalFilename();
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

	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadFile(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request) {
		String folder = request.getParameter("folder");
		String fileName = "";
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		map.put("result", "success");
		map.put("imgId", request.getParameter("imgId"));
		try {
			fileName = DateUtil.getNowYyyyMmDmHhMmSs() + new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
			String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", folder, fileName);
			if (file.getSize() > 20000000) {
				map.put("result", fileName + " size is more than 20M");
			} else {
				String saveUrl = "/resources/upload/"+folder+"/" + fileName;
				map.put("saveUrl", saveUrl);
				map.put("showUrl", super.getBasePath(request) + saveUrl);
				if (new File(saveFullPath).exists()) {
					map.put("result", fileName + " is exist");
				} else {
					FileUtil.copy(file.getInputStream(), new File(saveFullPath));
				}
				
				String userId = request.getParameter("userId"); 
				if (StringUtil.isNotEmpty(userId)) {
					AmUser amUser = amUserManager.getAmUserById(userId);
					amUser.setPhotoUrl(saveUrl);
					amUserManager.update(amUser);
					AmUser loginUser = super.getLoginUser(request);
					loginUser.setPhotoUrl(saveUrl);
				}
				String save = request.getParameter("save"); 
				if ("CmPhoto".equals(save)) {
					CmPhoto cmPhoto = new CmPhoto();
					cmPhoto.setLang(ServletUtil.getLanguage(request));
					cmPhoto.setUrl(saveUrl);
					cmPhoto.setPhotoName(fileName);
					cmPhotoManager.save(cmPhoto);
				} else if ("CmAudio".equals(save)) {
					CmAudio cmAudio = new CmAudio();
					cmAudio.setLang(ServletUtil.getLanguage(request));
					cmAudio.setUrl(saveUrl);
					cmAudio.setAudioName(fileName);
					cmAudio.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
					cmAudio.setAudioFormat(file.getContentType().replace("audio/", ""));
					cmAudioManager.save(cmAudio);
				} else if ("CmVideo".equals(save)) {
					CmVideo cmVideo = new CmVideo();
					cmVideo.setLang(ServletUtil.getLanguage(request));
					cmVideo.setUrl(saveUrl);
					cmVideo.setVideoName(fileName);
					cmVideo.setVideoFormat(file.getContentType());
					cmVideo.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
					cmVideoManager.save(cmVideo);
				} else if ("CmFile".equals(save)) {
					CmFile cmFile = new CmFile();
					cmFile.setLang(ServletUtil.getLanguage(request));
					cmFile.setUrl(saveUrl);
					cmFile.setFileName(fileName);
					cmFile.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
					cmFileManager.save(cmFile);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", fileName + " upload fail");
			return map;
		}
		
		return map;
	}
	
	@RequestMapping(value="/capturePhoto", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> capturePhoto(HttpServletRequest request, RedirectAttributes ra) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "Success");
		String fileName = System.currentTimeMillis() + ".png";
		String imgData = request.getParameter("img");
		String folder = request.getParameter("folder");
		
		try { 
			String fullFileName = FileUtil.getPath(request.getRealPath(""), "resources", "upload", folder, fileName);
			String saveUrl = "/resources/upload/"+folder+"/" + fileName;
			String userId = request.getParameter("userId"); 
			if (StringUtil.isNotEmpty(userId)) {
				AmUser amUser = amUserManager.getAmUserById(userId);
				amUser.setPhotoUrl(saveUrl);
				amUserManager.update(amUser);
				AmUser loginUser = super.getLoginUser(request);
				loginUser.setPhotoUrl(saveUrl);
			}
			FileUtil.saveBase64Decoder(imgData, fullFileName);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", "Capture photo fail");
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

	@RequestMapping(value="/uploadImageToRackspaceCloudFiles", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadImageToRackspaceCloudFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		if (!file.getOriginalFilename().endsWith(".jpg") && !file.getOriginalFilename().endsWith(".png") ) {
			map.put("result", "The image must be jpg or png");
			return map;
		}
		map.put("result", "success");
		map.put("imgId", request.getParameter("imgId"));
		try {
			String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");//DateUtil.getNowYyyyMmDmHhMmSs()
			if (file.getSize() > 20 * 1000 * 1000) {
				map.put("result", fileName + " size is more than 20M");
			} else {
				RackspaceCloudFilesUtil.uploadFile(RackspaceCloudFilesUtil.DIRECTORY_IMAGE, file.getInputStream(), fileName);
				String save = request.getParameter("save"); 
				if ("CmPhoto".equals(save)) {
					CmPhoto cmPhoto = new CmPhoto();
					cmPhoto.setLang(ServletUtil.getLanguage(request));
					cmPhoto.setUrl(fileName);
					cmPhoto.setPhotoName(fileName);
					cmPhotoManager.save(cmPhoto);
				}
				map.put("saveUrl", fileName);
				map.put("showUrl", request.getServletContext().getAttribute(BmConfig.IMAGE_URL) + "/" + fileName);
			}
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	
	@RequestMapping(value="/uploadAudioToRackspaceCloudFiles", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadAudioToRackspaceCloudFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		if (!file.getOriginalFilename().endsWith(".mp3")) {
			map.put("result", "The audio must be mp3");
			return map;
		}
		map.put("result", "success");
		try {
			String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");//DateUtil.getNowYyyyMmDmHhMmSs()
			if (file.getSize() > 20000000) {
				map.put("result", fileName + " size is more than 20M");
			} else {
				RackspaceCloudFilesUtil.uploadFile(RackspaceCloudFilesUtil.DIRECTORY_AUDIO, file.getInputStream(), fileName);
				map.put("saveUrl", fileName);
				map.put("showUrl", request.getServletContext().getAttribute(BmConfig.AUDIO_URL) + "/" + fileName);
			}
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	
	@RequestMapping(value="/uploadVideoToRackspaceCloudFiles", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadVideoToRackspaceCloudFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		if (!file.getOriginalFilename().endsWith(".mp4")) {
			map.put("result", "The video must be mp4");
			return map;
		}
		map.put("result", "success");
		try {
			String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");//DateUtil.getNowYyyyMmDmHhMmSs()
			if (file.getSize() > 100000000) {
				map.put("result", fileName + " size is more than 200M");
			} else {
				final InputStream is = file.getInputStream();
				final String fn = fileName;
				taskExecutor.execute(new Runnable(){
		    		public void run(){
		    			try {
		    				RackspaceCloudFilesUtil.uploadFile(RackspaceCloudFilesUtil.DIRECTORY_VIDEO, is, fn);
		    			} catch (Exception e) {
		    				log.error(e.getMessage());
		    			}
		    		}
		    	});
				String save = request.getParameter("save");
				if ("CmVideo".equals(save)) {
					CmVideo cmVideo = new CmVideo();
					cmVideo.setLang(ServletUtil.getLanguage(request));
					cmVideo.setUrl(fileName);
					cmVideo.setVideoName(fileName);
					cmVideo.setVideoFormat(file.getContentType());
					cmVideo.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
					cmVideoManager.save(cmVideo);
				} 
				map.put("saveUrl", fileName);
				map.put("showUrl", request.getServletContext().getAttribute(BmConfig.VIDEO_URL) + "/" + fileName);
			}
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	
	@RequestMapping(value="/uploadFileToRackspaceCloudFiles", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadFileToRackspaceCloudFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		if (this.getLoginUser(request)==null) {
			map.put("result", "sessionOut");
			return map;
		}
		map.put("result", "success");
		try {
			final String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");//DateUtil.getNowYyyyMmDmHhMmSs()
			final InputStream is = file.getInputStream();
			taskExecutor.execute(new Runnable(){
	    		public void run(){
	    			try {
	    				RackspaceCloudFilesUtil.uploadFile(RackspaceCloudFilesUtil.DIRECTORY_FILE, is, fileName);
	    			} catch (Exception e) {
	    				log.error(e.getMessage());
	    			}
	    		}
	    	});
			String save = request.getParameter("save");
			if ("CmFile".equals(save)) {
				CmFile cmFile = new CmFile();
				cmFile.setLang(ServletUtil.getLanguage(request));
				cmFile.setUrl(fileName);
				cmFile.setFileName(fileName);
				cmFile.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
				cmFileManager.save(cmFile);
			}
			map.put("saveUrl", fileName);
			map.put("showUrl", request.getServletContext().getAttribute(BmConfig.FILE_URL) + "/" + fileName);
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
	@Resource(name="bmMenuManagerImpl")
	private BmMenuManager bmMenuManager;
	
	@Resource(name="amUserManagerImpl")
	private AmUserManager amUserManager;
	
	@Resource(name="cmPhotoManagerImpl")
	private CmPhotoManager cmPhotoManager;
	
	@Resource(name="cmAudioManagerImpl")
	private CmAudioManager cmAudioManager;
	
	@Resource(name="cmVideoManagerImpl")
	private CmVideoManager cmVideoManager;
	
	@Resource(name="cmFileManagerImpl")
	private CmFileManager cmFileManager;

	@Resource TaskExecutor taskExecutor;
}
