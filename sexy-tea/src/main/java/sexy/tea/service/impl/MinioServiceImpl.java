package sexy.tea.service.impl;

import io.minio.ObjectStat;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sexy.tea.model.common.Result;
import sexy.tea.service.MinioService;
import sexy.tea.utils.MinioUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Minio服务接口实现类
 *
 * @author 大大大西西瓜皮🍉
 * @date 20:25 2020-10-08
 * description:
 */
@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    /**
     * 文件访问路径的前缀
     */
    @Value("${minio.prefix}")
    private String prefix;

    @Override
    public Result statObject(String bucketName, String objectName) {
        ObjectStat stat = MinioUtils.statObject(bucketName, objectName);
        return Result.success("获取对象信息, 桶: " + bucketName + ", 对象名称: " + objectName, stat);
    }

    @Override
    public Result listObjects(String bucketName, String prefix, boolean recursive) {
        // TODO 功能待测试
        return Result.success("桶:" + bucketName + ", 前缀:" + prefix, this.getList(MinioUtils.listObjects(bucketName, prefix, recursive)));
    }

    @Override
    public Result listObjects(String bucketName) {
        return Result.success("桶:" + bucketName, this.getList(MinioUtils.listObjects(bucketName)));
    }

    @Override
    public Result removeBucket(String bucketName) {
        MinioUtils.removeBucket(bucketName);
        return Result.success();
    }

    @Override
    public Result bucketExists(String bucketName) {
        return Result.success("桶:" + bucketName, MinioUtils.bucketExists(bucketName));
    }

    @Override
    public Result listBuckets() {
        return Result.success("所有桶对象", MinioUtils.listBuckets());
    }

    @Override
    public Result breakPointDownload(String bucketName, String objectName, long offset, long length, String filePath) {
        MinioUtils.breakPointDownload(bucketName, objectName, offset, length, filePath);
        return Result.success();
    }

    @Override
    public Result download(String bucketName, String objectName, String filePath) {
        MinioUtils.download(bucketName, objectName, filePath);
        return Result.success();
    }

    @Override
    public Result uploadImage(String bucketName, String objectName, InputStream is) {
        MinioUtils.uploadImage(bucketName, objectName, is);
        return Result.success("上传图片, 桶: " + bucketName + ", 对象名称: " + objectName, prefix + objectName);
    }

    @Override
    public Result upload(String bucketName, String objectName, String fileName) {
        MinioUtils.upload(bucketName, objectName, fileName);
        return Result.success("上传文件, 桶: " + bucketName + ", 对象名称: " + objectName + ", 文件名称: " + fileName, prefix + objectName);
    }

    @Override
    public Result upload(String bucketName, String objectName, InputStream stream, String contentType) {
        MinioUtils.upload(bucketName, objectName, stream, contentType);
        return Result.success("上传文件, 桶: " + bucketName + ", 对象名称: " + objectName, prefix + objectName);
    }

    @Override
    public Result makeBucket(String bucketName) {
        MinioUtils.makeBucket(bucketName);
        return Result.success();
    }

    private List<Item> getList(Iterable<io.minio.Result<Item>> results) {
        List<Item> itemList = new ArrayList<>();
        results.forEach(itemResult -> {
            try {
                itemList.add(itemResult.get());
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
                log.error("列举对象错误. {}", e.getMessage());
            }
        });
        return itemList;
    }
}
