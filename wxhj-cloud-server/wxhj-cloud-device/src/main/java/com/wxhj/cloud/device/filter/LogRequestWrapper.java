package com.wxhj.cloud.device.filter;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import lombok.Getter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wxpjf
 * @date 2020/4/17 10:45
 */
@Getter
public class LogRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body;
    private String bodyString;

    public LogRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.bodyString = CharStreams.toString(request.getReader());
        this.body = bodyString.getBytes(Charsets.UTF_8);
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}
