/**
 * $Id: VmUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Velocity Util</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public class VmUtil {
	public static String VM_DIR = "resources/template/vm/ahk";
	
	public VmUtil(String templateFile, String outputFileName) {
		this.templateDir = VM_DIR;
		this.templateFile = templateFile;
		this.outputFileName = outputFileName;
		init();
	}

	public VmUtil(String templateDir, String templateFile, String outputFileName) {
		this.templateDir = templateDir;
		this.templateFile = templateFile;
		this.outputFileName = outputFileName;
		init();
	}
	
	public void init() {
		log.info("VM Path : " + this.templateDir + File.separator + this.templateFile);
		Properties properites = new Properties();
		properites.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.templateDir);
		try {
			this.init(properites);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		this.setTemplate(this.templateFile, ENCODE);
		velocityContext.put("INCUDE", "#include");
	}

	public void execute() {
		try {
			this.toFile(this.outputFileName);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	public String getOutputFileName(){
		return this.outputFileName;
	}

	/**
	 * 对
	 * @param properties
	 * @throws Exception
	 */
	public void init(String properties) throws Exception {
		if (properties != null && properties.trim().length() > 0) {
			Velocity.init(properties);
		} else {
			Velocity.init();
		}
		velocityContext = new VelocityContext();
	}

	/**
	 * 把properties对应的值初始化到Velocity
	 * @param properties
	 * @throws Exception
	 */
	public void init(Properties properties) throws Exception {
		Velocity.init(properties);
		velocityContext = new VelocityContext();
	}

	/**
	 * 把key和value放入velocityContext对象
	 * @param key
	 * @param value
	 */
	public void putVelocityContext(String key, Object value) {
		velocityContext.put(key, value);
	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile
	 *            模版文件
	 * @throws AppException
	 */
	public void setTemplate(String templateFile) throws RuntimeException {
		try {
			template = Velocity.getTemplate(templateFile);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new RuntimeException(" error : cannot find template " + templateFile);
		} catch (ParseErrorException pee) {
			throw new RuntimeException(" Syntax error in template " + templateFile + ":" + pee);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile 模版文件
	 * @throws RuntimeException
	 */
	public void setTemplate(String templateFile, String characterSet) throws RuntimeException {
		try {
			template = Velocity.getTemplate(templateFile, characterSet);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new RuntimeException(" error : cannot find template " + templateFile);
		} catch (ParseErrorException pee) {
			throw new RuntimeException(" Syntax error in template " + templateFile + ":" + pee);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

	/**
	 * 转换为文本文件
	 */
	public String toText() throws RuntimeException {
		StringWriter sw = new StringWriter();
		try {
			template.merge(velocityContext, sw);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		return sw.toString();
	}

	/**
	 * 
	 * @param fileName
	 */
	public void toFile(String fileName) throws RuntimeException {
	    OutputStreamWriter filewriter = null;
        try {
            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);

            File file = new File(fileName);
            if(!file.exists()){
            	String tmpFile = fileName.replace('/',File.separatorChar);
            	String dir = fileName.substring(0,tmpFile.lastIndexOf(File.separatorChar));
            	File dirFile = new File(dir);
            	if(!dirFile.exists()){
            		dirFile.mkdir();
            	}
            	file.createNewFile();
            }
            
            filewriter = new OutputStreamWriter(new FileOutputStream(fileName), ENCODE);
            filewriter.write(stringWriter.toString());
            filewriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        } finally {
            if (filewriter != null) {
                try {
                    filewriter.close();
                } catch (Exception e) {
                }
            }
        }
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	private String templateDir;
	private String templateFile;
	private String outputFileName;
	private VelocityContext velocityContext = null;
	private Template template = null;
	private static String ENCODE = "UTF-8";
	private static final Logger log = LoggerFactory.getLogger(VmUtil.class);
	
	public static void main(String[] args) {
		String tmpDirPath = System.getProperty("java.io.tmpdir");
		String outputFileFullPath = tmpDirPath + "coss" + File.separator + "application.coss";
		VmUtil vmUtil = new VmUtil("resources/template/vm", "Bean.vm", outputFileFullPath);
		vmUtil.putVelocityContext("BASE_PACKAGE", "Coss");
		vmUtil.execute();
		log.debug(outputFileFullPath);
	}
}
