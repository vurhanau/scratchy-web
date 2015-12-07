package com.scratchy.web;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Data {
  private final static JedisPool pool;
  static {
    Config conf = ConfigFactory.load();
    String host = conf.getString("redis.host");
    int port = conf.getInt("redis.port");
    pool = new JedisPool(new JedisPoolConfig(), host, port);
  }

  public static String topIconsKey() {
    return String.format("scratchy:twitch:emoticons:topn:symbols");
  }

  public static String topValuesKey() {
    return String.format("scratchy:twitch:emoticons:topn:val");
  }

  public static String channelsPack() {
    return "scratchy:twitch:channels:pack";
  }

  public static String emoticonUrls(String channel) {
    return String.format("scratchy:twitch:emoticons:%s:urls", channel);
  }

  public static String emoticonRegexps(String channel) {
    return String.format("scratchy:twitch:emoticons:%s:regexps", channel);
  }

  public static Jedis jedis() {
    return pool.getResource();
  }
}
