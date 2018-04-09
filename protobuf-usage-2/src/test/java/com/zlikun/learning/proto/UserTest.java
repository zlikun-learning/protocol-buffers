package com.zlikun.learning.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试生成的实体类
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018-04-09 11:39
 */
public class UserTest {

    @Test
    public void test() throws InvalidProtocolBufferException {

        // 通过构造器构造用户实例
        UserInfo.User user = UserInfo.User.newBuilder()
                .setId(10086L)
                .setName("zlikun")
                .setGender(UserInfo.Gender.MALE)
                .addHobbies("编程")
                .addHobbies("摄影")
                .addHobbies("游泳")
                .putSocials("QQ", "qq_zlikun")
                .putSocials("微信", "wx_zlikun")
                .putSocials("微博", "wb_zlikun")
                .build();

        /* ---------------------------------------------------------------
        id: 10086
        name: "zlikun"
        hobbies: "\347\274\226\347\250\213"
        hobbies: "\346\221\204\345\275\261"
        hobbies: "\346\270\270\346\263\263"
        socials {
          key: "QQ"
          value: "qq_zlikun"
        }
        socials {
          key: "\345\276\256\344\277\241"
          value: "wx_zlikun"
        }
        socials {
          key: "\345\276\256\345\215\232"
          value: "wb_zlikun"
        }
        --------------------------------------------------------------- */
        System.out.println(user);

        assertEquals("zlikun", user.getName());
        assertEquals(UserInfo.Gender.MALE, user.getGender());
        assertEquals("编程", user.getHobbies(0));
        assertEquals("wx_zlikun", user.getSocialsOrDefault("微信", null));

        // 执行序列化
        byte [] data = user.toByteArray();
        assertEquals(94, data.length);

        // 执行反序列化
        UserInfo.User user2 = UserInfo.User.parseFrom(data);

        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getGender(), user2.getGender());
        assertEquals(user.getHobbiesCount(), user2.getHobbiesCount());
        assertEquals(user.getSocialsCount(), user2.getSocialsCount());

    }

}
