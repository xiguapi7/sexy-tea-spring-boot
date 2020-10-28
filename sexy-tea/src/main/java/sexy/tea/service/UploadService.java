package sexy.tea.service;

import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;

/**
 * 公共空接口
 *
 * author 大大大西西瓜皮🍉
 * date 00:01 2020-10-17
 * description:
 */
public interface UploadService {

    Result uploadImage(MinioDto dto, Long id);
}
