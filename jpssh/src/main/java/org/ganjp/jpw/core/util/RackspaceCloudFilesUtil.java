package org.ganjp.jpw.core.util;

import static com.google.common.io.Closeables.closeQuietly;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.openstack.swift.CommonSwiftAsyncClient;
import org.jclouds.openstack.swift.CommonSwiftClient;
import org.jclouds.openstack.swift.domain.ContainerMetadata;
import org.jclouds.openstack.swift.domain.ObjectInfo;
import org.jclouds.openstack.swift.domain.SwiftObject;
import org.jclouds.rest.RestContext;

public class RackspaceCloudFilesUtil {
	
	public static final String URL_HTTP_AUDIO = "http://b10dca32c9e3497b40da-fe6fe2c009f8206f658050a7f83789d2.r34.cf1.rackcdn.com";
	public static final String URL_HTTPS_AUDIO = "https://627396917a5ad640e643-fe6fe2c009f8206f658050a7f83789d2.ssl.cf1.rackcdn.com";
	public static final String URL_STREAM_AUDIO = "http://e3da65e7c9ec02a505d0-fe6fe2c009f8206f658050a7f83789d2.r34.stream.cf1.rackcdn.com";
	public static final String URL_IOS_STREAM_AUDIO = "http://7d812f67d8671f6e01e6-fe6fe2c009f8206f658050a7f83789d2.iosr.cf1.rackcdn.com";
		
	public static final String URL_HTTP_IMAGE = "http://9da5f016bf2a7bd02f97-9bead101e19416bd4303c48efb571ff3.r43.cf1.rackcdn.com";
	public static final String URL_HTTPS_IMAGE = "https://6ac83497b9ded244ddac-9bead101e19416bd4303c48efb571ff3.ssl.cf1.rackcdn.com";
	public static final String URL_STREAM_IMAGE = "http://9f1f52b92a91b4346e16-9bead101e19416bd4303c48efb571ff3.r43.stream.cf1.rackcdn.com";
	public static final String URL_IOS_STREAM_IMAGE = "http://1ecfa469aeb52c289b57-9bead101e19416bd4303c48efb571ff3.iosr.cf1.rackcdn.com";
	
	public static final String URL_HTTP_VIDEO = "http://867e0097bca7d05a0b4c-1de7856d3f8a06479d6fdc3dc3087f4b.r99.cf1.rackcdn.com";
	public static final String URL_HTTPS_VIDEO = "https://c21976fb04da817a8bae-1de7856d3f8a06479d6fdc3dc3087f4b.ssl.cf1.rackcdn.com";
	public static final String URL_STREAM_VIDEO = "http://a1ede0af970dfa8eb621-1de7856d3f8a06479d6fdc3dc3087f4b.r99.stream.cf1.rackcdn.com";
	public static final String URL_IOS_STREAM_VIDEO = "http://ee144e6cc25eb3ea8d74-1de7856d3f8a06479d6fdc3dc3087f4b.iosr.cf1.rackcdn.com";
	
	public static final String URL_HTTP_FILE = "http://5d0908e56cb0087f7fea-ef66df22c4e8f14ac88ea0250f34abb7.r11.cf1.rackcdn.com";
	public static final String URL_HTTPS_FILE = "https://c21976fb04da817a8bae-1de7856d3f8a06479d6fdc3dc3087f4b.ssl.cf1.rackcdn.com";
	public static final String URL_STREAM_FILE = "http://a1ede0af970dfa8eb621-1de7856d3f8a06479d6fdc3dc3087f4b.r99.stream.cf1.rackcdn.com";
	public static final String URL_IOS_STREAM_FILE = "http://ee144e6cc25eb3ea8d74-1de7856d3f8a06479d6fdc3dc3087f4b.iosr.cf1.rackcdn.com";
	
	public static final String DIRECTORY_IMAGE = "Image";
	public static final String DIRECTORY_AUDIO = "Audio";
	public static final String DIRECTORY_VIDEO = "Video";
	public static final String DIRECTORY_FILE = "File";
	
	public static final String USER_NAME = "ganjpxm";
	public static final String API_KEY = "4a839d49ea2a9846eff4018b6c307232";
	public static final String PROVIDER = "cloudfiles-us";// The provider configures jclouds To use the Rackspace Cloud (US), To use the Rackspace Cloud (UK) set the provider to "cloudfiles-uk"
    
    private BlobStore storage;
	private RestContext<CommonSwiftClient, CommonSwiftAsyncClient> swift;

	public static RestContext<CommonSwiftClient, CommonSwiftAsyncClient> getRestContext() {
		RackspaceCloudFilesUtil cloudFilesUtil = new RackspaceCloudFilesUtil();
	    try {
	        cloudFilesUtil.init();
	        return cloudFilesUtil.swift;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	cloudFilesUtil.close();
	    }
	    return null;
	}

	public static void createDirectory(String dirName) {
		RackspaceCloudFilesUtil cloudFilesUtil = new RackspaceCloudFilesUtil();
	    try {
	        cloudFilesUtil.init();
	        cloudFilesUtil.createContainer(dirName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	cloudFilesUtil.close();
	    }
	}

	public static void uploadFile(String dirName, InputStream inputStream, String fileName) {
		RackspaceCloudFilesUtil cloudFilesUtil = new RackspaceCloudFilesUtil();
	    try {
	        cloudFilesUtil.init();
	        cloudFilesUtil.uploadObjectFromFile(dirName, inputStream, fileName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	cloudFilesUtil.close();
	    }
	}
	
	private void init() {
		BlobStoreContext context = ContextBuilder.newBuilder(PROVIDER).credentials(USER_NAME, API_KEY).buildView(BlobStoreContext.class);
	    storage = context.getBlobStore();
	    swift = context.unwrap();
	    System.out.println("Rackspace Cloud Files Authenticated");
	}
	
	private void createContainer(String containerName) {
    	//CreateContainerOptions options = CreateContainerOptions.Builder.withMetadata(ImmutableMap.of("key1", "value1", "key2", "value2"));
    	swift.getApi().createContainer(containerName);
    	System.out.println("Create Container " + containerName);
	}

	/**
     * Upload an object from a File using the Swift API. 
	 */
	private void uploadObjectFromFile(String containName, InputStream inputStream, String fileName) throws IOException {
		
		Set<ObjectInfo> objects = getRestContext().getApi().listObjects(containName);
        for (ObjectInfo objectInfo: objects) {
        	System.out.println(objectInfo.getName());
        	if (objectInfo.getName().equalsIgnoreCase(fileName)) {
        		System.out.println("The file has been exist");
        		return;
        	}
        }
	    SwiftObject object = swift.getApi().newSwiftObject();
	    object.getInfo().setName(fileName);
	    object.setPayload(inputStream);
	    swift.getApi().putObject(containName, object);
	    System.out.println("Upload Object From File");
	}
	
	private void listContainers() {
      System.out.println("List Containers");
      Set<ContainerMetadata> containers = swift.getApi().listContainers();

      for (ContainerMetadata container: containers) {
         System.out.println("  " + container);
      }
    }

	/**
	 * Always close your service when you're done with it.
	 */
	public void close() {
		closeQuietly(storage.getContext());
	}
	
	public static void main(String[] args) {
		RackspaceCloudFilesUtil.createDirectory(DIRECTORY_AUDIO);
		try {
			List<File> files = FileUtil.getSubFiles(new File("D:/Resources/Cloud/Rackspace/Audio"));
			for(File file : files) {
				RackspaceCloudFilesUtil.uploadFile(DIRECTORY_AUDIO, new FileInputStream(file), file.getName());//DIRECTORY_IMAGE,DIRECTORY_VIDEO
				System.out.println(file.getName());
			}
//			RackspaceCloudFilesUtil.uploadFile(DIRECTORY_IMAGE, new FileInputStream("D:/Resources/Cloud/Rackspace/Image/Timothy-Cook-1-Enterprise.jpg"), "Timothy-Cook-1-Enterprise.jpg");
			
//			Set<ContainerMetadata> containers = getRestContext().getApi().listContainers();
//			for (ContainerMetadata containerMetadata: containers) {
//				System.out.println(containerMetadata.getName());
//			}
			
//			Set<ObjectInfo> objects = getRestContext().getApi().listObjects(DIRECTORY_IMAGE);
//	        for (ObjectInfo objectInfo: objects) {
//	        	System.out.println("  " + objectInfo);
//	        }
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
