package controllers;

import play.modules.redis.Redis;
import play.mvc.Controller;

import java.util.Set;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-17
 * Time: 下午3:04
 */
public class RedisToSSDB extends Controller{
    public enum TYPE {String,List,Set,None}
    public static void testKey(){
        Set<String> keys = Redis.keys("*");
        for(String key : keys){
            System.out.println(Redis.type(key));
        }

        renderText(keys);
    }
}
