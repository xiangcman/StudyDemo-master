package com.single.studydemo.imageloader.cache;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.MemoryCache;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiangcheng on 18/2/1.
 * 打造一个使用次数的memorycache
 */

public class LimitCountMemoryCache implements MemoryCache {
    //定义一个存储所有bitmap使用次数的map
    private final Map<Bitmap, Integer> limitCountMap = Collections.synchronizedMap(new HashMap<Bitmap, Integer>());

    //限制使用次数由用户决定
    public LimitCountMemoryCache(int limitCount) {
        this.limitCount = limitCount;
        this.map = new LinkedHashMap<String, Bitmap>(0, 0.75f, true);
    }

    private int limitCount;

    private final LinkedHashMap<String, Bitmap> map;

    @Override
    public final Bitmap get(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        synchronized (this) {
            Bitmap current = map.get(key);
            if (current != null) {
                //如果存在当前的bitmap将次数加1
                if (limitCountMap.get(current) != null) {
                    Integer count = limitCountMap.get(current);
                    limitCountMap.put(current, count++);
                } else {
                    //如果不存在说明还是第一次使用，因此只是初始化次数=1
                    limitCountMap.put(current, 1);
                }
                //这里判断使用的次数了，如果大于limitCount，则需要移除该对象了
                if (limitCountMap.get(current) > limitCount) {
                    map.remove(current);
                    limitCountMap.remove(current);
                }
            }

            return current;
        }
    }

    /**
     * Caches {@code Bitmap} for {@code key}. The Bitmap is moved to the head of the queue.
     */
    @Override
    public final boolean put(String key, Bitmap value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }

        synchronized (this) {
            map.put(key, value);
        }

        return true;
    }

    @Override
    public final Bitmap remove(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        synchronized (this) {
            Bitmap previous = map.remove(key);
            return previous;
        }
    }

    @Override
    public Collection<String> keys() {
        synchronized (this) {
            return new HashSet<String>(map.keySet());
        }
    }

    @Override
    public void clear() {
        map.clear();
        limitCountMap.clear();
    }
}
