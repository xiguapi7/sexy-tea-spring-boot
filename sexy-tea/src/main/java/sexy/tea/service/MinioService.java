package sexy.tea.service;

import sexy.tea.model.common.Result;

import java.io.InputStream;

/**
 * MinIO服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 20:12 2020-10-08
 * description:
 */
public interface MinioService {

    Result statObject(String bucketName, String objectName);

    Result listObjects(String bucketName, String prefix, boolean recursive);

    Result listObjects(String bucketName);

    Result removeBucket(String bucketName);

    Result bucketExists(String bucketName);

    Result listBuckets();

    Result breakPointDownload(String bucketName, String objectName, long offset, long length, String filePath);

    Result download(String bucketName, String objectName, String filePath);

    Result uploadImage(String bucketName, String objectName, InputStream is);

    Result upload(String bucketName, String objectName, String fileName);

    Result upload(String bucketName, String objectName, InputStream stream, String contentType);

    Result makeBucket(String bucketName);
}
