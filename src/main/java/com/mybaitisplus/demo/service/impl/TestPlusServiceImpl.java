package com.mybaitisplus.demo.service.impl;

import com.mybaitisplus.demo.entity.TestPlus;
import com.mybaitisplus.demo.mapper.TestPlusMapper;
import com.mybaitisplus.demo.service.ITestPlusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author psy
 * @since 2020-03-15
 */
@Service
public class TestPlusServiceImpl extends ServiceImpl<TestPlusMapper, TestPlus> implements ITestPlusService {
    @Override
    public TestPlus queryName() {
        TestPlus testPlus = baseMapper.selectById(1);

        return testPlus;
    }
}
