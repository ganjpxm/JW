/**
 * $Id: FileUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

/**
 * <p>File Utility for internal use.</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
    /**
     * <p>get a properties file from the classpath, a properties instance loaded with the properties from
     * the file. If no file can be found it returns an empty instance.</p>
     *
     * @param propertiesName eg:"validation.required"
     * @return Properties 
     * @throws Exception
     */
    public static Properties getPropertiesFromClassPath(final String propertiesClassPath) {
        Properties properties = new Properties();
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        InputStream inputStream = null;
        try {
    	    inputStream = classLoader.getResourceAsStream(propertiesClassPath);
            properties.load(inputStream);
        } catch(Exception ex) {
        	log.error("loadFromClassPath() has IOException : " + ex.getMessage());
        } finally {
            if (inputStream != null) {
            	try {
            		inputStream.close();
            	} catch(IOException ex) {
                	log.error("loadFromClassPath() finally inputStream close fail : " + ex.getMessage());
                } 
            }
        }
        log.debug("load classpath file: " + propertiesClassPath);
        return properties;
    }
    
    /**
     * <p>getResourceAsStream</p>
     * 
     * @param classPath
     * @return
     */
    public static InputStream getResourceAsStream(String classPath) {
		String stripped = classPath.startsWith("/") ? classPath.substring(1) : classPath;
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader!=null) {
			stream = classLoader.getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			stream = FileUtil.class.getResourceAsStream( classPath );
		}
		if ( stream == null ) {
			stream = FileUtil.class.getClassLoader().getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			throw new RuntimeException( classPath + " not found" );
		}
		return stream;
	}

	/**
	 * <p>extract zip file</p>
	 * 
	 * @param zipFilePath
	 * @param outPath
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void extract(String zipFilePath, String outPath) throws IOException, FileNotFoundException {
		FileInputStream in = new FileInputStream(zipFilePath);
		extract(in, outPath);
	}

	/**
	 * <p>extract zip file</p>
	 * 
	 * @param in
	 * @param outPath
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void extract(InputStream in, String outPath) throws IOException, FileNotFoundException {
		CheckedInputStream cis = new CheckedInputStream(in, new Adler32());
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(cis));
		ZipEntry ze = null;
		char separator = File.separatorChar;
		if (!outPath.endsWith(String.valueOf(separator))) {
			outPath = outPath + separator;
		}

		File dir = new File(outPath);
		if ((dir.exists() && !dir.isDirectory()) || !dir.exists())
			dir.mkdir();

		while ((ze = zis.getNextEntry()) != null) {
			String fileName = ze.getName();
			fileName = fileName.replace('/', separator);

			if (fileName.indexOf(separator) >= 0) {
				String path = fileName;
				// make dir
				String supPath = "";
				while (path.indexOf(separator) > 0) {
					String oneLeverPath = path.substring(0, path.indexOf(separator));
					File file = new File(outPath + supPath + oneLeverPath);
					file.mkdir();

					path = path.substring(path.indexOf(separator) + 1, path.length());
					supPath = supPath + oneLeverPath + separator;
				}
			}

			if (ze.isDirectory()) {
				dir = new File(ze.getName());
				dir.mkdir();
			} else {
				fileName = outPath + fileName;

				FileOutputStream oneFile = new FileOutputStream(fileName);
				byte[] bBuf = new byte[1024];
				int length = 0;
				while ((length = zis.read(bBuf)) != -1) {
					oneFile.write(bBuf, 0, length);
				}
				oneFile.close();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void doZip(String baseDir, String fileName)  throws Exception {
		List fileList = getSubFiles(new File(baseDir));  
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));  
		ZipEntry ze=null;  
		byte[] buf=new byte[1024];  
		int readLen=0;  
		for(int i = 0; i <fileList.size(); i++) {  
			File f = (File)fileList.get(i);  
			ze = new ZipEntry(getAbsFileName(baseDir, f));  
			ze.setSize(f.length());  
            ze.setTime(f.lastModified());     
            zos.putNextEntry(ze);  
            InputStream is=new BufferedInputStream(new FileInputStream(f));  
            while ((readLen=is.read(buf, 0, 1024))!=-1) {  
                zos.write(buf, 0, readLen);  
            }  
            is.close();  
        }  
        zos.close();  
	}
	
  /** 
    * 取得指定目录下的所有文件列表，包括子目录.
    *  
    * @param baseDir File 指定的目录 
    * @return 包含java.io.File的List 
    */  
    public static List<File> getSubFiles(File baseDir){  
        List<File> ret = new ArrayList<File>();  
        File[] tmp=baseDir.listFiles();  
        for (int i = 0; i <tmp.length; i++) {  
            if(tmp[i].isFile())  
                ret.add(tmp[i]);  
            if(tmp[i].isDirectory())  
                ret.addAll(getSubFiles(tmp[i]));  
        }  
        return ret;  
    } 
	 /** 
    * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径. 
    * @param baseDir java.lang.String 根目录 
    * @param realFileName java.io.File 实际的文件名 
    * @return 相对文件名 
    */  
    private static String getAbsFileName(String baseDir, File realFileName){  
        File real=realFileName;  
        File base=new File(baseDir);  
        String ret=real.getName();  
        while (true) {  
            real=real.getParentFile();  
            if(real==null)   
                break;  
            if(real.equals(base))   
                break;  
            else  
                ret=real.getName()+"/"+ret;  
        }  
        return ret;  
    } 
	
    /** 
     * 由doZip调用,递归完成目录文件读取
     *  
     * @param dir 
     * @param zipOut 
     * @throws IOException 
     */  
    private static void handleDir(File dir, ZipOutputStream zipOut) throws IOException {  
        FileInputStream fileIn;  
        File[] files = dir.listFiles();  
        int readedBytes;
        byte[] buf = new byte[512];
        
        if(files.length==0) {  
            zipOut.putNextEntry(new ZipEntry(dir.toString()+"/"));  
            zipOut.closeEntry();  
        } else {  
            for(int i=0;i<files.length;i++) {  
                File fileName=(File)files[i];  
                if(fileName.isDirectory()) {  
                    handleDir(fileName, zipOut);  
                } else {  
                    fileIn = new FileInputStream(fileName);  
                    zipOut.putNextEntry(new ZipEntry(fileName.toString()));  
                    while((readedBytes=fileIn.read(buf))>0) {  
                        zipOut.write(buf , 0 , readedBytes);   
                    }  
                    zipOut.closeEntry();   
                }  
            }  
        }
    }  

    public static void delete(File file) {
		if (file==null) {
			return;
		}
		try {
			if (file.isFile()) {
				file.delete();
				return;
			}
			File[] subs = file.listFiles();
			if (subs.length == 0) {
				file.delete();
			} else {
				for (int i = 0; i < subs.length; i++) {
					delete(subs[i]);
				}
				file.delete();
			}
		} catch (Exception ex) {
			log.error("delete file：" + file.getAbsolutePath() + " fail ：" + ex.getMessage());
		}
	}

    public static String getPath(String... fileNames) {
    	StringBuffer path = new StringBuffer("");
    	for (int i = 0; i < fileNames.length; i++) {
			String fileName = fileNames[i];
			if (fileName.indexOf("/")!=-1) {
				String[] arr = fileName.split(",");
				for (String name : arr) {
					if (i==0) {
						path.append(name);
					} else {
						path.append(File.separator).append(name);
					}
				}
			} else {
				if (i==0) {
					path.append(fileName);
				} else {
					path.append(File.separator).append(fileName);
				}
			}
		}
    	return path.toString();
    }

    public static void forceMkdir(File directory) throws IOException {
		if (directory.exists()) {
			if (directory.isFile()) {
				String message = "File " + directory + " exists and is "
						+ "not a directory. Unable to create directory.";
				throw new IOException(message);
			}
		} else {
			if (false == directory.mkdirs()) {
				String message = "Unable to create directory " + directory;
				throw new IOException(message);
			}
		}
	}
    
	/**
	 * <p>copy</p>
	 * 
	 * @param fins
	 * @param destine
	 */
	public static void copy(InputStream fins, File destine) {
		try {
			if (fins == null)
				return;
			destine.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(destine);
			byte[] buf = new byte[1024];
			int readLen;
			while ((readLen = fins.read(buf, 0, buf.length)) > 0) {
				fos.write(buf, 0, readLen);
			}
			fos.flush();
			fos.close();
			fins.close();
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}


	/**
	 * <p>createFile</p>
	 * 
	 * @param fileFullPath
	 * @throws RuntimeException
	 */
	public static File createFile(String fileFullPath) {
        try {
            File file = new File(fileFullPath);
            if (!file.exists()) {
            	String tmpFile = fileFullPath.replace('/',File.separatorChar);
            	String dir = fileFullPath.substring(0,tmpFile.lastIndexOf(File.separatorChar));
            	File dirFile = new File(dir);
            	if(!dirFile.exists()){
            		dirFile.mkdirs();
            	}
            	file.createNewFile();
            }
            return file;
        } catch (Exception e) {
        	log.error("create file error：" + e.getMessage());
            throw new RuntimeException(e.toString());
        }    
    }
	
	public static void writeProperty(final String key, final String value, final String propertiesFullPath) {
		if (StringUtil.hasLength(propertiesFullPath)) {
			File file = new File(propertiesFullPath);
	    	try {
	    		if (!file.exists()) {
					createFile(propertiesFullPath);
					Properties prop = new Properties();
					prop.setProperty(key, value);
					FileOutputStream fos = new FileOutputStream(propertiesFullPath);
		    		prop.store(fos, "Copyright (c) 2012 Illume Technology.");
		    		fos.close();
				} else {
					Properties prop = new Properties();
					FileInputStream fis = new FileInputStream(propertiesFullPath);
					prop.load(fis);
					fis.close();
					prop.setProperty(key, value);
					
					FileOutputStream fos = new FileOutputStream(propertiesFullPath); 
					prop.store(fos, "Copyright (c) 2012 Illume Technology."); 
					fos.close(); 
				}
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
		} else {
			log.error("properties file path is not null");
		}
	}
	
	public static Properties getProperties(final String propertiesFullPath) {
		if (StringUtil.hasLength(propertiesFullPath)) {
	    	try {
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(propertiesFullPath);
				prop.load(fis);
				fis.close();
				return prop;
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
		} else {
			log.error("properties file path is not null");
		}
		return null;
	}

	/**
	 * <p>cleanDirectory</p>
	 * 
	 * @param dirFile directory to clean
	 * @throws IOException in case cleaning is unsuccessful
	 */
	public static void cleanDirectory(File dirFile) {
		if (!dirFile.exists()) {
			String message = dirFile + " does not exist";
			throw new IllegalArgumentException(message);
		}
		if (!dirFile.isDirectory()) {
			String message = dirFile + " is not a directory";
			throw new IllegalArgumentException(message);
		}
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			delete(file);
		}
	}
	
	/**
	 * <p>saveBase64Decoder</p>
	 * 
	 * @param base64DecoderData
	 * @param fullFileName
	 */
	public static void saveBase64Decoder(String base64DecoderData, String fullFileName) {
		BufferedOutputStream stream = null;  
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes1 = decoder.decodeBuffer(base64DecoderData);
	        File ret = FileUtil.createFile(fullFileName);  
	        FileOutputStream fstream = new FileOutputStream(ret);  
	        stream = new BufferedOutputStream(fstream);  
	        stream.write(bytes1);  
		} catch (IOException e) {
			log.error("helper:get file from byte process error!");  
	        e.printStackTrace();  
		} finally {  
	        if (stream != null) {  
	            try {  
	                stream.close();  
	            } catch (IOException e) {  
	                log.error("helper:get file from byte process error!");  
	                e.printStackTrace();  
	            }  
	        }  
	    } 
	}

	public static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
    public static void main(String[] args) throws Exception {
//    	String url = Thread.currentThread().getContextClassLoader().getResource("").toString();
//    	Properties properties = getPropertiesFromClassPath("i18n/messages_zh_cn.properties");
//    	log.debug(properties.get("project.name").toString());
    	
    	File file1 = createFile("D:\\Workspace\\dbs\\test\\jmeter\\jmx\\me\\createUsers.txt");
    	File file2 = createFile("D:\\Workspace\\dbs\\test\\jmeter\\jmx\\me\\loginUsers.txt");
    	
    	try {
            BufferedWriter out1 = new BufferedWriter(new FileWriter(file1));
            for (int i = 1; i <= 300; i++) {
            	out1.write("testUser" + i + ",1,TestUser" + i + ",testUser" + i + "@dbs.com");
            	out1.newLine();
            }
            out1.close();
            
            BufferedWriter out2 = new BufferedWriter(new FileWriter(file2));
            for (int i = 1; i <= 300; i++) {
            	out2.write("testUser" + i + ",1");
            	out2.newLine();
            }
            out2.close();
        } catch (IOException e) {
        	log.error(e.getMessage());
        }
    	log.info("Success");
    	//extract("D:\\ccla\\Enrolment_20120529155906.zip", "D:\\ccla\\tmp");
    	//doZip("D:\\ccla\\tmp", "D:\\ccla\\tmp.zip");
    }
}
