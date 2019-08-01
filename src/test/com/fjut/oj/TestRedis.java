package com.fjut.oj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class TestRedis {
    //注入template对象
    @Autowired
    private RedisTemplate redisTemplate;

    //----------------------Hash类型的操作:经常用---------------------------------
    //（1）存入值
    @Test
    public void boundHashOpsSet() {
        redisTemplate.boundHashOps("namehash").put("country1", "中国");
        redisTemplate.boundHashOps("namehash").put("country2", "日本");
        redisTemplate.boundHashOps("namehash").put("country3", "韩国");
    }

    //（2）提取所有的KEY
    @Test
    public void boundHashOpsKeys() {
        Set keys = redisTemplate.boundHashOps("namehash").keys();
        System.out.println(keys);
    }

    //（3）提取所有的值
    @Test
    public void boundHashOpsValues() {
        List values = redisTemplate.boundHashOps("namehash").values();
        System.out.println(values);
    }

    //(4) 根据KEY提取值
    @Test
    public void boundHashOpsByKey() {
        Object name = redisTemplate.boundHashOps("namehash").get("country1");
        System.out.println(name);
    }

    //根据KEY移除值
    @Test
    public void boundHashOpsDelByKey() {
        redisTemplate.boundHashOps("namehash").delete("country2");
    }

    //----------------------------值类型的操作:因为操作数量少,所以不长用---------------------------------
    @Test
    public void setValue() {
        redisTemplate.boundValueOps("name").set("王五");
    }

    @Test
    public void getValue() {
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

    @Test
    public void deleteValue() {
        redisTemplate.delete("name");
    }

//----------------------set类型的操作:因为操作数量少,所以不长用---------------------------------

    /**
     * 存入值
     */
    @Test
    public void boundSetOpsAdd() {
        redisTemplate.boundSetOps("nameset").add("曹操"); //放入值
        redisTemplate.boundSetOps("nameset").add("刘备");
        redisTemplate.boundSetOps("nameset").add("孙权");
    }

    /**
     * 提取值
     */
    @Test
    public void boundSetOpsGet() {
        Set names = redisTemplate.boundSetOps("nameset").members();//取出值
        System.out.println(names);
    }

    /**
     * 删除集合中的某一个值
     */
    @Test
    public void boundSetOpsDelete() {
        redisTemplate.boundSetOps("nameset").remove("曹操");
    }

    /**
     * 删除整个集合
     */
    @Test
    public void boundSetOpsDeleteAll() {
        redisTemplate.delete("nameset");
    }

    //----------------------list类型的操作:因为操作数量少,所以不长用---------------------------------

    /**
     * 右压栈：后添加的对象排在后边
     * 右压栈用的多,因为快,原理是
     */
    @Test
    public void boundListrightPush() {
        redisTemplate.boundListOps("namelist").rightPush("赵子龙");
        redisTemplate.boundListOps("namelist").rightPush("张飞");
        redisTemplate.boundListOps("namelist").rightPush("关羽");
    }

    /**
     * 显示右压栈集合
     */
    @Test
    public void boundListRange() {
        List namelist = redisTemplate.boundListOps("namelist").range(0, 10);
        System.out.println(namelist);
    }
    /**
     * 查询:根据索引查询集合某个元素
     */
    @Test
    public void boundListIndex() {
        Object name = redisTemplate.boundListOps("namelist").index(1);
        System.out.println(name);
    }

    /**
     * 删除: 根据值移除集合某个元素
     */
    @Test
    public void bondListRemove() {
        redisTemplate.boundListOps("namelist").remove(1, "关羽");
    }

    /**
     * 删除: 删除全部
     */
    @Test
    public void bondListDelete() {
        redisTemplate.delete("namelist");
    }
}

