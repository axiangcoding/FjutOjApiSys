package com.fjut.oj.mapper;

import com.fjut.oj.pojo.TableKeyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [20190829]
 */
public interface KeyValueMapper {
    /**
     * 插入key-value键值对到数据库中
     * @param tableKeyValue
     * @return
     */
    Integer insertKeyValue(@Param("tableKeyValue") TableKeyValue tableKeyValue);

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
    TableKeyValue queryValueByKey(@Param("key") String key);

}
