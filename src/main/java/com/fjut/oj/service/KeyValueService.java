package com.fjut.oj.service;

import com.fjut.oj.pojo.TableKeyValue;

import java.util.List;

/**
 * @author axiang [20190829]
 */
public interface KeyValueService {

    /**
     * 插入key-value键值对到数据库中
     * @param tableKeyValue
     * @return
     */
    Integer insertKeyValue(TableKeyValue tableKeyValue);

    /**
     * 查询表内全部key-value键值对
     * @return
     */
    List<TableKeyValue> queryAllKeyValues();

    /**
     * 根据Key获取Value
     * @param key
     * @return
     */
    TableKeyValue queryValueByKey(String key);

}
