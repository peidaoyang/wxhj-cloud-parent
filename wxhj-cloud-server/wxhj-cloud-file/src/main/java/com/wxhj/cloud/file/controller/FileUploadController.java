/**
 * @fileName: FileUploadController.java
 * @author: pjf
 * @date: 2019年10月21日 下午2:42:47
 */

package com.wxhj.cloud.file.controller;

import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.file.vo.FileListUploadVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pjf
 * @className FileUploadController.java
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
            @RequestParam(name = "multipartFiles") MultipartFile[] multipartFiles,
            @RequestParam(name = "defSuffix", defaultValue = "") String defSuffix) throws IOException {
        List<FileListUploadVO> fileList = new ArrayList<FileListUploadVO>();
        for (MultipartFile muFile : multipartFiles) {
            String fileUuid;
            if (Strings.isNullOrEmpty(defSuffix)) {
                defSuffix = Files.getFileExtension(muFile.getOriginalFilename());
            }
            fileUuid = ZipUtil.generateFile(defSuffix.replace(".", ""));
            boolean isSaveSuccess = fileStorageService.saveFileInputStream(muFile.getInputStream(), fileUuid);
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
        if (!fileStorageService.existFile(fileName)) {
            throw new Exception();
        }
//
        InputStream inputStream = fileStorageService.getFileInputStream(fileName);
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        // res.setHeader(" Content-Length",inputStream.available());

        // byte[] file = fileStorageService.getFile(fileName);

        ByteStreams.copy(inputStream, out);
    }

}
