package sexy.tea.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.model.constant.ResultConstant;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis的TypeHandler
 *
 * @author 大大大西西瓜皮🍉
 * @summary
 * @since 2020-11-3 下午 09:50
 */
public class JSONTypeHandler<T> extends BaseTypeHandler<T> {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(JSONTypeHandler.class);

    private final Class<T> clazz;

    public JSONTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.toJSON(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), clazz);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex), clazz);
    }

    private String toJSON(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("转换JSON字符串失败, 参数:{}, 错误信息:{}", object, e.getMessage());
            throw new BusinessException(ResultConstant.SERVER_ERROR, "转换JSON字符串失败");
        }
    }

    private T toObject(String content, Class<T> clazz) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return mapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            log.error("JSON字符串转换为对象失败, 参数:{}, clazz:{}, 错误信息:{}", content, clazz, e.getMessage());
            throw new BusinessException(ResultConstant.SERVER_ERROR, "JSON字符串转换为对象失败");
        }
    }
}
