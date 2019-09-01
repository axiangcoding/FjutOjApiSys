package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.KeyValueMapper;
import com.fjut.oj.pojo.KeyValuePO;
import com.fjut.oj.service.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [20190829]
 */
@Service("KeyValueService")
public class KeyValueServiceImpl implements KeyValueService {
    @Autowired
    KeyValueMapper KeyValueMapper;

    @Override
    public Integer insertKeyValue(KeyValuePO tableKeyValue) {
        return KeyValueMapper.insertKeyValue(tableKeyValue);
    }

    @Override
    public List<KeyValuePO> queryAllKeyValues() {
        return KeyValueMapper.queryAllKeyValues();
    }

    @Override
    public KeyValuePO queryValueByKey(String key) {
        return KeyValueMapper.queryValueByKey(key);
    }
}
