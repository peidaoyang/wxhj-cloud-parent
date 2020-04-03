package com.wxhj.cloud.component.service;

import java.io.InputStream;

public interface FileStorageService {
	boolean saveFileInputStream(InputStream inputStream, String fileUuid);
	@Deprecated
	boolean saveFile(byte[] file, String fileUuid);

	@Deprecated
	byte[] getFile(String fileUuid);

	InputStream getFileInputStream(String fileUuid);

	boolean deleteFile(String fileUuid);

	boolean timedDeleteFile(String fileUuid);

	boolean existFile(String fileUuid);

	boolean notDeleteFile(String fileUuid);

	String generateFileUrl(String fileUuid);
}
