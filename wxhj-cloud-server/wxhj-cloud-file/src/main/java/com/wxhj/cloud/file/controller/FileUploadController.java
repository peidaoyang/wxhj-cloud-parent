/** 
 * @fileName: FileUploadController.java  
 * @author: pjf
 * @date: 2019年10月21日 下午2:42:47 
 */

package com.wxhj.cloud.file.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.ByteStreams;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FileUtil;
import com.wxhj.cloud.file.vo.FileListUploadVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className FileUploadController.java
 * @author pjf
 * @date 2019年10月21日 下午2:42:47
 */
@RestController
@Slf4j
public class FileUploadController {
	@Resource
	FileStorageService fileStorageService;

	@PostMapping(value = "/timeLimitFileListUpload")
	@ApiOperation("限时多文件上传")
	public WebApiReturnResultModel timeLimitFileListUpload(
			@RequestParam(name = "multipartFiles") MultipartFile[] multipartFiles) throws IOException {
		List<FileListUploadVO> fileList = new ArrayList<FileListUploadVO>();
		for (MultipartFile muFile : multipartFiles) {
			// byte[] fileByte = muFile.getBytes();
			String fileUuid = FileUtil.generateFile(muFile.getOriginalFilename().split("\\.")[1]);
			boolean isSaveSuccess = fileStorageService.saveFileInputStream(muFile.getInputStream(), fileUuid);
//fileStorageService.saveFile(fileByte, fileUuid);
			if (!isSaveSuccess) {
				continue;
			}
			// 发送消息
			fileList.add(new FileListUploadVO(muFile.getOriginalFilename(), fileUuid));
		}

		return WebApiReturnResultModel.ofSuccess(fileList);
	}

	@GetMapping(value = "/fileAsyncDownload")
	@ApiOperation("文件下载")
	public void fileAsyncDownload(@RequestParam String fileName, HttpServletResponse res, OutputStream out)
			throws Exception {
		if(!fileStorageService.existFile(fileName)) {
			throw new Exception();
		}
		
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		// byte[] file = fileStorageService.getFile(fileName);

		ByteStreams.copy(fileStorageService.getFileInputStream(fileName), out);
	}

}
