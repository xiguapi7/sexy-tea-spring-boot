package sexy.tea.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sexy.tea.model.common.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author 大大大西西瓜皮🍉
 * @date 23:58 2020-10-16
 * description:
 */
@Slf4j
public class UploadUtils {

    public static Result upload(HttpServletRequest request) {
        // 转换为MultipartHttpServletRequest
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 通过表单中的参数来接收文件流（可用 file.getInputStream() 来接收输入流）
            MultipartFile file = multipartHttpServletRequest.getFile("file");
            assert file != null;
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();

            log.info("上传文件的名称: {}", originalFilename);
            log.info("上传文件的大小: {}", size);

            assert originalFilename != null;
            String[] arr = originalFilename.split("\\.");
            String ext = arr[arr.length - 1];

            try {
                InputStream is = file.getInputStream();
                MinioUtils.upload("image", originalFilename, is, "image/" + ext);
                return Result.success("上传成功", originalFilename);
            } catch (IOException e) {
                log.error("上传失败." + e.getMessage(), Optional.empty());
            }
        }
        return Result.business("上传失败.", Optional.empty());
    }
}
