package com.lz.redis.luaandtransaction.support.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @Author: dzy
 * @Date: 2019/3/28 9:19
 * @Describe: Http帮助类
 */
public class HttpHelper {

    private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    /**
     * 获取请求Body
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            logger.error("读取请求时出现异常:", e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.error("关闭字节流时(inputstream)出现异常:", e);
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (Exception e) {
                    logger.error("关闭字符串流(reader)时出现异常:", e);
                }
            }
        }

        return sb.toString();
    }


}