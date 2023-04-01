package cn.panda.spider.util;

public class Utils {

    public static String processUrl(String videoLink) {
        String[] _1split = videoLink.split("\\?");
        String _2split = _1split[1];
        String[] split1 = _2split.split("\\&");
        String url = _1split[0] + "?" + split1[0];
        return url;
    }


}
