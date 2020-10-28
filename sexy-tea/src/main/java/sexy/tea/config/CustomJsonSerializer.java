package sexy.tea.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 自定义序列化器
 *
 * @author 大大大西西瓜皮🍉
 * @summary 自定义JSON序列化器
 * @since 2020-10-28 下午 05:11
 */
public class CustomJsonSerializer extends JsonSerializer<Double> {

    private final DecimalFormat df = new DecimalFormat("##.00");

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            gen.writeString(df.format(value));
        }
    }

}
