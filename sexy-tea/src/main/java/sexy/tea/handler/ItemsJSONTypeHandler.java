package sexy.tea.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import sexy.tea.model.dto.shopping.BeverageItemDto;
import sexy.tea.model.dto.shopping.FoodItemDto;
import sexy.tea.model.dto.shopping.ItemDto;
import sexy.tea.model.dto.shopping.SelectionItemDto;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * MyBatis的TypeHandler
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-3 下午 09:50
 */
@MappedTypes(value = {ItemDto.class, BeverageItemDto.class, FoodItemDto.class, SelectionItemDto.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR}, includeNullJdbcType = true)
@Slf4j
public class ItemsJSONTypeHandler<T extends Object> extends BaseTypeHandler<List<T>> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Class<List<T>> clazz;

    public ItemsJSONTypeHandler(Class<List<T>> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        //特别要注意在转集合的情况下，第二个参数是List.class,而不是clazz会报奇怪的错误，我在这被坑了老久
        return toObject(rs.getString(columnName), List.class);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toObject(rs.getString(columnIndex), List.class);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toObject(cs.getString(columnIndex), List.class);
    }

    private String toJson(List<T> object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    private List<T> toObject(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (List<T>) MAPPER.readValue(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}