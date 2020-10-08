package sexy.tea.utils;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

/**
 * Minio工具类
 *
 * @author 大大大西西瓜皮🍉
 * @date 01:08 2020-09-26
 * description:
 */
@Slf4j
public class MinioUtils {

    /**
     * MinIO服务器地址
     */
    private static final String host = "http://127.0.0.1";

    /**
     * MinIO服务端端口
     */
    private static final int port = 9000;

    /**
     * 登录账号
     */
    private static final String accessKey = "minioadmin";

    /**
     * 登录密码
     */
    private static final String secretKey = "minioadmin";

    /**
     * 默认存储桶
     */
    private static final String defaultBucketName = "images";

    /**
     * MinIO客户端对象
     */
    private static final MinioClient client;

    static {
        // 静态代码块中实例化客户端对象
        client = MinioClient.builder()
                .endpoint(host, port, false)
                .credentials(accessKey, secretKey)
                .build();

        // 如果默认的存储桶不存在则创建默认存储桶
        if (!bucketExists(defaultBucketName)) {
            makeBucket(defaultBucketName);
        }
    }

    // TODO Minio工具类封装

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    public static void makeBucket(String bucketName) {
        try {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | RegionConflictException | ServerException | XmlParserException e) {
            log.error("创建桶失败, 请检查客户端连接是否成功。参数： {}。异常信息： {}", bucketName, e.getMessage());
        }
    }


    public static void upload() {

    }

    public static void uploadImage() {

    }

    /**
     * 从服务端下载对象并保存到本地
     *
     * @param bucketName 要下载对象存储的桶名称
     * @param objectName 要下载的对象名称
     * @param filePath   本地路径
     */
    public static void download(String bucketName, String objectName, String filePath) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            // 以流的形式下载一个对象
            InputStream is = client.getObject(args);

            FileOutputStream fos = new FileOutputStream(filePath);
            int index;
            byte[] data = new byte[1024];
            while ((index = is.read(data)) != -1) {
                // 写数据
                fos.write(data, 0, index);
                fos.flush();
            }
            // 释放资源
            fos.close();
            is.close();
        } catch (Exception e) {
            log.error("Minio下载失败, bucketName: {}, objectName: {}, exceptionMsg: {}", bucketName, objectName, e.getMessage());
        }
    }

    public static void breakPointDownload(String bucketName, String objectName, long offset, long length, String filePath) {
        try {
            // 调用statObject()来判断是否存在, 如果不存在, 方法将会抛出异常, 否则代表对象存在.
            client.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());

            // 获取指定offset和length的输入流.
            InputStream stream = client.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).offset(offset).length(length).build());
            FileOutputStream fos = new FileOutputStream(filePath);

            // 读取输入流直到EOF并保存到本地
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                fos.write(buf, 0, bytesRead);
                fos.flush();
            }

            fos.close();
            stream.close();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列举所有桶对象
     *
     * @return 桶对象列表
     */
    public static List<Bucket> listBuckets() {
        try {
            return client.listBuckets();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("Bucket列表出错，请检查客户端连接是否成功。异常信息： {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName 桶名称
     * @return 判断结果
     */
    public static boolean bucketExists(String bucketName) {
        try {
            return client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("Bucket存在判断出错，请检查客户端连接是否成功。参数： {}。异常信息： {}", bucketName, e.getMessage());
            return false;
        }
    }

    /**
     * 删除一个存储桶, 但不会删除桶中对象
     *
     * @param bucketName 存储桶名称
     */
    public static void removeBucket(String bucketName) {
        try {
            client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("删除存储桶失败，请检查客户端连接是否成功。参数： {}。异常信息： {}", bucketName, e.getMessage());
        }
    }

    /**
     * 列出桶中所有对象
     *
     * @param bucketName 桶名称
     * @param prefix     对象前缀
     * @param recursive  是否递归查询
     * @return 迭代器对象. Iterable<Result<Item>>
     */
    public static Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive) {
        return client.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(recursive).build());
    }

    /**
     * 列出桶中所有对象, 默认没有前缀和递归查找
     *
     * @param bucketName 桶名称
     * @return 迭代器对象. Iterable<Result<Item>>
     */
    public static Iterable<Result<Item>> listObjects(String bucketName) {
        return listObjects(bucketName, "", true);
    }


}
