package com.yzming;

import com.yzming.Utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 连接
//        jedis = new Jedis("r-2ze4ulc2psmev308vdpd.tairpena.rds.aliyuncs.com", 6379);
//        jedis.auth("hrg", "*******");
        // 连接池
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
    }

    @Test
    void tesetString() {
        String result = jedis.set("name", "hrg");
        System.out.println("result = " + result);

        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void tesetHash() {
        jedis.hset("user:1", "name", "hrg");
        jedis.hset("user:1", "age", "21");

        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);

    }

    @AfterEach
    void  tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
