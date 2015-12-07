package com.scratchy.web;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TopResource {

  private static final Map<String, String> iconUrlCache = iconUrlMapping();

  public static Map<String, String> iconUrlMapping() {
    try (Jedis jedis = Data.jedis()) {
      long llen = jedis.llen(Data.channelsPack());
      List<String> channels = jedis.lrange(Data.channelsPack(), 0, llen);

      Map<String, String> iconMapping = new HashMap<>();
      for (String channel : channels) {
        String reKey = Data.emoticonRegexps(channel);
        String urlKey = Data.emoticonUrls(channel);
        long llen1 = jedis.llen(reKey);
        long llen2 = jedis.llen(urlKey);
        if (llen1 != llen2)
          throw new IllegalStateException("unable to map \'regexp\' <-> \'url\'");

        List<String> regexps = jedis.lrange(reKey, 0, llen1);
        List<String> urls = jedis.lrange(urlKey, 0, llen1);
        IntStream.range(0, regexps.size()).forEach(i -> iconMapping.put(regexps.get(i), urls.get(i)));
      }

      return iconMapping;
    }
  }

  public static List<RankEntry> list(int n) {
    try (Jedis jedis = Data.jedis()) {
      long ilen = jedis.llen(Data.topIconsKey());
      long rlen = jedis.llen(Data.topValuesKey());
      if (ilen != rlen)
        throw new IllegalStateException("invalid \'icon\' <-> \'count\' mapping");

      int len = Math.min((int)ilen, n);
      List<String> icons = jedis.lrange(Data.topIconsKey(), 0, len);
      List<Integer> vals = jedis.lrange(Data.topValuesKey(), 0, len).stream().map(Integer::valueOf).collect(Collectors.toList());
      return
          IntStream
              .range(0, len)
              .mapToObj(i -> {
                String icon = icons.get(i);
                return new RankEntry(i + 1, vals.get(i), icon, iconUrlCache.get(icon));
              })
              .collect(Collectors.toList());
    }
  }

}
