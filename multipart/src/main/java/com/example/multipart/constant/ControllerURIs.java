package com.example.multipart.constant;

public final class ControllerURIs {
	
	public static final String FILE_UPLOAD_ONE = "/file/uploadOne";
	public static final String FILE_UPLOAD_MANY = "/file/uploadMany";
	public static final String FILE_DOWNLOAD = "/file/download";
	public static final String FILE_DOWNLOAD_WITH_FORMAT = FILE_DOWNLOAD + "/{filename:.+}";

}
