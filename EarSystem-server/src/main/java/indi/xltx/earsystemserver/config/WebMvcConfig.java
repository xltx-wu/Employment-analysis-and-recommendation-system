package indi.xltx.earsystemserver.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
        converters.add(new HttpMessageConverter<Map<String, ?>>() {

            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public HashMap<String, Integer> read(Class<? extends Map<String, ?>> clazz,
                    HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void write(Map<String, ?> t, MediaType contentType, HttpOutputMessage outputMessage)
                    throws IOException, HttpMessageNotWritableException {
                String outString = "{";
                for (String key : t.keySet()) {

                    String value = t.get(key).toString();
                    outString = outString + "\"" + key + "\":" + value + ",";
                }
                outString = outString.substring(0, outString.length() - 1);
                outString = outString + "}";
                outputMessage.getBody().write(outString.getBytes());
            }

        });
    }

}
