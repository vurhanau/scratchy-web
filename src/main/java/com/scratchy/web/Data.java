package com.scratchy.web;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Data {
  private final static JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

  public static String topIconsKey() {
    return String.format("twitch:emoticons:topn:symbols");
  }

  public static String topValuesKey() {
    return String.format("twitch:emoticons:topn:val");
  }

  public static Jedis jedis() {
    return pool.getResource();
  }
}
