package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.CodeViewMapper;
import com.fjut.oj.service.CodeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: axiang [2019/8/10]
 */
@Service("CodeViewService")
public class CodeViewServiceImpl implements CodeViewService {
    @Autowired
    CodeViewMapper codeViewMapper;

    @Override
    public boolean queryCanUserViewCodeByPid(String username, Integer pid) {
        Integer ans = codeViewMapper.queryCanUserViewCodeByPid(username, pid);
        return 0 != ans;
    }
}
