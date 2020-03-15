package com.mybaitisplus.demo.service;

import com.mybaitisplus.demo.entity.TestPlus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author psy
 * @since 2020-03-15
 */
public interface ITestPlusService extends IService<TestPlus> {

    TestPlus queryName();

}
