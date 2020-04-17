package com.wxhj.cloud.device.filter;

import lombok.Getter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wxpjf
 * @date 2020/4/17 11:02
 */
@Getter
public class LogResponseWrapper extends HttpServletResponseWrapper {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);

    public LogResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        return printWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                //super.write(b);
                byteArrayOutputStream.write(b);
            }
        };
    }
//    @Override
//    public void flush(){
//        try {
//            _pw.flush();
//            _pw.close();
//            _stream.flush();
//            _stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
