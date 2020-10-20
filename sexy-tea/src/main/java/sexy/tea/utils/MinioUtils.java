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
import java.util.Optional;

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
        log.info("静态代码块初始化MinIO客户端对象. host: {}, port: {}, defaultName: {}", host, port, defaultBucketName);

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

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    public static void makeBucket(String bucketName) {
        try {
            log.info("创建存储桶. bucketName: {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | RegionConflictException | ServerException | XmlParserException e) {
            log.error("创建桶失败, 请检查客户端连接是否成功。参数： {}。异常信息： {}", bucketName, e.getMessage());
        }
    }


    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称
     * @param stream      输入流
     * @param contentType 内容类型
     */
    public static void upload(String bucketName, String objectName, InputStream stream, String contentType) {
        try {
            log.info("上传文件. bucketName: {}, objectName: {}, stream: {}, contentType: {}", bucketName, objectName, stream.available(), contentType);
            client.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(stream, stream.available(), 24 * 1024 * 1024)
                    .contentType(contentType)
                    .build());

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件路径上传文件
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param fileName   文件路径
     */
    public static void upload(String bucketName, String objectName, String fileName) {
        try {
            log.info("上传文件. bucketName: {}, objectName: {}, fileName: {}", bucketName, objectName, fileName);
            client.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param is         输入流
     */
    public static void uploadImage(String bucketName, String objectName, InputStream is) {
        upload(bucketName, objectName, is, "image/jpeg");
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
            log.info("下载对象到本地. bucketName: {}, objectName: {}, filePath: {}", bucketName, objectName, filePath);

            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            // 以流的形式下载一个对象
            InputStream is = client.getObject(args);

            FileOutputStream fos = new FileOutputStream(filePath);
            int bytesRead;
            byte[] buf = new byte[1024];
            while ((bytesRead = is.read(buf)) != -1) {
                // 写数据
                fos.write(buf, 0, bytesRead);
                fos.flush();
            }
            // 释放资源
            fos.close();
            is.close();
        } catch (Exception e) {
            log.error("Minio下载失败, bucketName: {}, objectName: {}, exceptionMsg: {}", bucketName, objectName, e.getMessage());
        }
    }

    /**
     * 断点下载, 下载到本地
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param offset     偏移量
     * @param length     长度
     * @param filePath   文件路径
     */
    public static void breakPointDownload(String bucketName, String objectName, long offset, long length, String filePath) {
        try {
            log.info("断点下载. bucketName: {}, objectName: {}, offset: {}, length: {}, filePath: {}", bucketName, objectName, offset, length, filePath);
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
            // 释放资源
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
            log.info("列举所有桶对象.");
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
            log.info("判断桶是否存在. bucketName: {}", bucketName);
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
            log.info("删除存储桶. bucketName: {}", bucketName);
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
        log.info("列出桶中所有对象. bucketName: {}, prefix: {}, recursive: {}", bucketName, prefix, recursive);
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

    public static ObjectStat statObject(String bucketName, String objectName) {
        try {
            log.info("获取对象元数据: bucketName: {}, objectName: {}", bucketName, objectName);
            return client.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("获取对象元数据失败. 错误信息: {}", e.getMessage());
            Optional<ObjectStat> optional = Optional.empty();
            return optional.get();
        }
    }
}
