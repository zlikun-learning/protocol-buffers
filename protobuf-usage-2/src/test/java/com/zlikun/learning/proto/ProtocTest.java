package com.zlikun.learning.proto;

import org.junit.Test;

import java.io.*;

/**
 * 通过`protoc`工具编译代码
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018-04-09 11:09
 */
public class ProtocTest {

    @Test
    public void compile() throws IOException, InterruptedException {

        // 代码相当于在工程根目录下执行如下命令：
        // $ protoc --proto_path src/main/proto/ --java_out src/main/java/ User.proto
        // 需要执行命令执行目录(否则可能会找到文件)
        Process process = Runtime.getRuntime()
                .exec("protoc --proto_path=src/main/proto --java_out src/main/java User.proto",
                        new String [0],
                        new File("./"));
        // 输出命令提示信息
        print(process.getInputStream());
        // 等待命令执行完成
        int status = process.waitFor();
        System.out.println("程序执行完成 。。。");

    }

    /**
     * 打印输入流到控制台
     *
     * @param in
     * @throws IOException
     */
    private void print(InputStream in) throws IOException {
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}
