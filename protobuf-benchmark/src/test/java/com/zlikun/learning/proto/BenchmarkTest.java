package com.zlikun.learning.proto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基准测试（性能），本例仅针对小数据量的测试
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018-04-09 13:26
 */
public class BenchmarkTest {

    private long time ;

    @Before
    public void init() {
        time = System.currentTimeMillis();
    }

    @After
    public void destroy() {
        System.out.println(String.format("程序执行耗时：%d 毫秒!", System.currentTimeMillis() - time));
    }

    /**
     * 下面是连续5次测试(100万次循环)的结果
     * 1257 / 1048 / 1055 / 1067 / 1024
     * 字节数大小会影响测试结果
     * 将循环次数改为1000万，测试性能
     * 7249 / 6868 / 7079
     * @throws IOException
     */
    @Test
    public void single() throws IOException {

        // 单线程，100万次循环测试
        for (long i = 0; i < 10_000_000; i++) {
            execute(i + 1);
        }

    }

    /**
     * 下面是连续5次测试(100万次循环)的结果
     * 3812 / 3781 / 3831 / 3625 / 3653
     * 实际上线程池的存在，反而影响了性能，线程间切换造成性能损耗
     * 将循环次数改为1000万，测试性能
     * 41922 / 43570 / 44296
     * 将线程数改为2时，测试性能
     * 22775 / 23218 / 23218
     * 减少了线程上下文切换，实际测试性能有所提升，但仍不及单线程处理速度，应该是线程对象的开销造成的影响
     * @throws IOException
     */
    @Test
    public void multi() throws IOException {

        ExecutorService exec = Executors.newFixedThreadPool(2);

        // 多线程，100万次循环测试
        for (long i = 0; i < 10_000_000; i++) {
            long id = i + 1;
            exec.execute(() -> {
                try {
                    execute(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        exec.shutdown();
        while (!exec.isTerminated());

    }

    /**
     * 执行序列化、反序列化
     * @param id
     * @throws IOException
     */
    private void execute(long id) throws IOException {
        // 准备数据
        UserOuterClass.User user = UserOuterClass.User.newBuilder()
                .setId(id)
                .setName("zlikun_" + id)
                .setActive(id % 2 == 0)
                // 100个字节，避免测试数据过小
                .setRemark("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789")
                .build();

        // 序列化，这里直接使用字节传输，不使用文件、网络等(传输部分与序列化协议无关)
        byte [] data = user.toByteArray();

        // 读取数据，反序列化
        UserOuterClass.User.parseFrom(data);
    }

}
