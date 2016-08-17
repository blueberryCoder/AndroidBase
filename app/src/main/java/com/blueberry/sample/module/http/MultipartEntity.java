package com.blueberry.sample.module.http;

import android.text.TextUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by blueberry on 2016/8/16.
 *
 * 最终生成的报文格式大致如下：
 *
 * POST /api/ HTTP/1.1
 * Content-Type: multipart/form-data; boundary=03fdafareqjk2-5542jkfda
 * User-Agent:Dalvik/1.6.0 (Linux;U anroid 4.4.4;M040 Build/KTU84P)
 * Host: www.myhost.com
 * Connection: Keep:Alive
 * Accept-Encoding: gzip
 * Content-Length:168324
 *
 * --03fdafareqjk2-5542jkfda
 * Content-Type: text/plain;charset=UTF-8
 * Content-Disposition: form-data;name="type"
 * Content-Transfer-Encoding: 8bit
 *
 * This is my type
 *
 * --03fdafareqjk2-5542jkfda
 * Content-Type: application/octet-stream
 * Content-Disposition: form-data; name="image";filename="no-file"
 * Content-Transfer-Encoding:binary
 *
 * --03fdafareqjk2-5542jkfda
 * Content-Type: application/octet-stream
 * Content-Disposition: form-data; name="file";filename="image.jpg"
 * Content-Transfer-Encoding：binary
 *
 * --03fdafareqjk2-5542jkfda--
 */
public class MultipartEntity implements HttpEntity {

    private final static char[] MULTIPART_CHARS = ("-123456789abcdefghihkl" +
            "mnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray();

    // 回车符和换行符
    private final String NEW_LINE_STR = "\r\n";
    private final String CONTENT_TYPE = "Content-Type: ";
    private final String CONTENT_DISPOSITION = "Content-Disposition: ";
    // 文本参数和字符集
    private final String TYPE_TEXT_CHARSET = "text/plain; charset=UTF-8";

    // 字节流参数
    private final String TYPE_OCTET_STREAM = "application/octet-stream";
    // 字节数组参数
    private final byte[] BINARY_ENCODING = "Content-Transfer-Encoding: binary\r\n\r\n".getBytes();
    // 文本参数
    private final byte[] BIT_ENCODING = "Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes();
    // 参数分割符
    private String mBoundary = null;

    // 输出流，用于缓存参数数据
    ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    public MultipartEntity() {
        this.mBoundary = generateBoundary();
    }

    private String generateBoundary() {
        final StringBuffer buf = new StringBuffer();
        final Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        return buf.toString();
    }

    // 参数开头的分割符
    private void writeFirstBoundary() throws IOException {
        mOutputStream.write(("--" + mBoundary + "\r\n").getBytes());
    }

    // 添加文本参数
    public void addStringPart(final String paramName, final String value) {
        writeToOutputString(paramName, value.getBytes(), TYPE_TEXT_CHARSET, BIT_ENCODING, "");
    }

    /**
     * 添加字节数组参数,例如Bitmap的字节流参数
     * @param paramsName 参数名
     * @param rawData 字节数组数据
     */
    public void addByteArrayPart(String paramsName,final byte[] rawData){
        writeToOutputString(paramsName,rawData,TYPE_OCTET_STREAM,BINARY_ENCODING,"no-file");
    }

    /**
     * 添加文件参数，可以实现文件上传功能
     * @param key 参数名
     * @param file 文件参数
     */
    public void addFilePart(final String key,final File file){
        InputStream fin =null;
        try {
            fin = new FileInputStream(file);
            writeFirstBoundary();
            final String type = CONTENT_TYPE+TYPE_OCTET_STREAM+NEW_LINE_STR;
            mOutputStream.write(type.getBytes());
            mOutputStream.write(getContentDispositionBytes(key,file.getName()));
            mOutputStream.write(BINARY_ENCODING);
            final byte[] tmp= new byte[4096];
            int len = 0;
            while((len=fin.read(tmp))!=-1){
                mOutputStream.write(tmp,0,len);
            }
            mOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeSilently(fin);
        }
    }

    private void closeSilently(InputStream fin) {
        if(fin!=null) try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据写出到输出流中
     *
     * @param key           参数名
     * @param rawData       原始的字节数据
     * @param type          类型
     * @param encodingBytes 编码类型
     * @param fileName      文件名
     */
    private void writeToOutputString(String key, byte[] rawData, String type,
                                     byte[] encodingBytes, String fileName) {
        try {
            writeFirstBoundary();
            mOutputStream.write(getContentDispositionBytes(key, fileName));
            mOutputStream.write((CONTENT_TYPE + type + NEW_LINE_STR).getBytes());
            mOutputStream.write(encodingBytes);
            mOutputStream.write(rawData);
            mOutputStream.write(NEW_LINE_STR.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getContentDispositionBytes(String key, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CONTENT_DISPOSITION + "form-data;name=\"" + key + "\"");
        // 文本参数没有filename参数 ,设置为空即可
        if (!TextUtils.isEmpty(fileName)) {
            stringBuilder.append("; filename=\"" + fileName + "\"");
        }
        return stringBuilder.append(NEW_LINE_STR).toString().getBytes();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        return mOutputStream.toByteArray().length;
    }

    @Override
    public Header getContentType() {
        return new BasicHeader("Content-Type","multipart/form-data; boundary="+mBoundary);
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        return new ByteArrayInputStream(mOutputStream.toByteArray());
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        final String endString = "\r\n--"+mBoundary+"--\r\n";
        //写入结束符
//        mOutputStream.write(endString.getBytes());
        // 将缓存在mOutputStream 中的数据全部写入到outputStream中
        outputStream.write(mOutputStream.toByteArray());
        outputStream.write(endString.getBytes());
        outputStream.flush();
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {
        if(isStreaming()){
            throw new UnsupportedEncodingException("Streaming" +
                    " entity dose not implement #consumeContent()");
        }
    }
}
