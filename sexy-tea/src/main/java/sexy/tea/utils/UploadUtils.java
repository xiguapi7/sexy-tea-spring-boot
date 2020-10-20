package sexy.tea.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author 大大大西西瓜皮🍉
 * @date 23:58 2020-10-16
 * description:
 */
@Slf4j
public class UploadUtils {

    public static Result upload(HttpServletRequest request, UploadService service) {
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

            // String name = multipartHttpServletRequest.getParameter("name");
            // String content = multipartHttpServletRequest.getParameter("content");
            // 获取饮料ID
            String id = multipartHttpServletRequest.getParameter("id");

            // log.info("name: {}", name);
            // log.info("content: {}", content);

            assert originalFilename != null;
            String[] arr = originalFilename.split("\\.");
            String ext = arr[arr.length - 1];
            MinioDto dto = MinioDto.builder()
                    .file(file)
                    .size(size)
                    .contentType("image/" + ext)
                    .originFilename(originalFilename)
                    .suffix("." + ext)
                    .build();
            log.info("上传对象参数: {}", dto.toString());

            return service.uploadImage(dto, id);
        }
        return Result.business("上传失败.", Optional.empty());
    }
}
