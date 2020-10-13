package sexy.tea.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 大大大西西瓜皮🍉
 * @date 20:00 2020-10-13
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinioDto {

    /**
     * 上传文件对象
     */
    private MultipartFile file;

    /**
     * 上传文件类型
     */
    private String contentType;

    /**
     * 源文件名称
     */
    private String originFilename;

    /**
     * 文件名后缀
     */
    private String suffix;

    /**
     * 文件大小
     */
    private long size;
}
