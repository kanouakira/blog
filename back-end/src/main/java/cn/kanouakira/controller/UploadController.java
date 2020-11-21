package cn.kanouakira.controller;

import cn.kanouakira.common.lang.Result;
import cn.kanouakira.service.UploadPictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传表现层
 */
@RestController
@Api(description = "上传操作", tags = {"上传操作接口"})
@RequestMapping("/api/upload")
public class UploadController {
    @Autowired
    private UploadPictureService uploadPictureService;

    /**
     * 上传图片
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @ApiOperation("上传图片")
    @PostMapping("/picture")
    @CrossOrigin
    public Result uploadPicture(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String pictureUrl = uploadPictureService.uploadPicture(multipartFile);
        return Result.succ(pictureUrl);
    }
}
