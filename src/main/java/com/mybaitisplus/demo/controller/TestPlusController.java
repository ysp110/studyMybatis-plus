package com.mybaitisplus.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybaitisplus.demo.entity.TestPlus;
import com.mybaitisplus.demo.service.ITestPlusService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author psy
 * @since 2020-03-15
 */
@RestController
@RequestMapping("/test-plus")
@Slf4j
public class TestPlusController {
    @Autowired
    private ITestPlusService testPlusService;

    @PostMapping("/add")
    public String addData(TestPlus testPlus) {
        testPlus.setCreateTime(LocalDateTime.now());
        BaseMapper<TestPlus> baseMapper = testPlusService.getBaseMapper();
        int insert = baseMapper.insert(testPlus);
        if (insert == 1) return "成功";
        else return "error";
    }


    @PostMapping("/update")
    public String update(TestPlus testPlus) {

        BaseMapper<TestPlus> baseMapper = testPlusService.getBaseMapper();
        int update = 0;
//       将对应的Id数据的年龄更为44岁，同时创建时间也进行修改
        update = baseMapper.update(testPlus, new UpdateWrapper<TestPlus>()
                .lambda().eq(TestPlus::getId, testPlus.getId())
                .set(TestPlus::getAge, 44)
                .set(TestPlus::getCreateTime, LocalDateTime.now()));
//        直接调用封装好的根据id更新，将修改的数据进行更新，不修的数据会保持原有状态。
        update = baseMapper.updateById(testPlus);

        if (update == 1) return "成功";
        else return "error";
    }

    @PostMapping("/delete")
//    全部为硬删除。
    public String delete(TestPlus testPlus) {
//     删除id为1的数据。
        BaseMapper<TestPlus> baseMapper = testPlusService.getBaseMapper();
        int i = baseMapper.deleteById(1);
//        通过id的集合删除，
//        baseMapper.deleteBatchIds(new ArrayList<>());
        if (i == 1) return "成功";
        else return "error";
    }

    @PostMapping("/queryByPage")
    public IPage<TestPlus> queryByPage(TestPlus testPlus, Integer page, Integer limit) {
//     根据邮箱的名字进行动态模糊查询，如果邮箱不为空，就like查询(%%),like可以选择左like,右like,当然你也可以写eq,ge,le等相关的条件。查询的page为传人的 testPlusPage，
//     如果不用mybatis-plus提供的查询，自己只要单独写一个mapper层，同样把创建的testPlusPage对象传人即可（此法不再演示，类似于传统的mybatis）
//     同时包含in的查询
        Page<TestPlus> testPlusPage = new Page<>(page, limit);
        ArrayList<Integer> ages = new ArrayList<>();
        ages.add(11);
        ages.add(12);
        ages.add(31);
        ages.add(21);
        IPage<TestPlus> testPlusIPages = testPlusService.getBaseMapper().selectPage(testPlusPage, new QueryWrapper<TestPlus>().lambda()
                .like(StringUtils.isNotEmpty(testPlus.getEmail()), TestPlus::getEmail, testPlus.getEmail())
                .in(TestPlus::getAge,ages));
        return testPlusIPages;
    }

    @PostMapping("/queryById")
    public TestPlus delete(String id) {
//     根据id查询单个数据。service有很多的方法可以调用。这里只说一个。
//        eq说明的是id与之相等，注意这里的id我传人的是String类型的，但是也可以查询到数据，mybatis-plus会很聪明的自动进行转换。
//        last说明的是在末尾拼接sql（可参照文章开头的表达式表格），查询一条数据（id也可以查询一条，笔者只不过为了说明这个写法的意思）
//        select的意思是我只查询TestPlus中email和emailPassword所对应的数据库字段，其他的字段内容我不查询
        TestPlus one = testPlusService.getOne(new QueryWrapper<TestPlus>().lambda()
                .eq(TestPlus::getId, id)
                .last("limit 1")
                .select(TestPlus::getEmail, TestPlus::getEmailPassword));
        return one;

    }


    @PostMapping("/saveBatch")
    public void saveBatch() {
        List<TestPlus> testPluses = new ArrayList<>();
        TestPlus testPlus;
        for (int i = 0; i < 10; i++) {
            testPlus = new TestPlus();
            testPlus.setCreateTime(LocalDateTime.now());
            testPlus.setAge(10 + i);
            testPlus.setEmail("zhangsan" + i);
            testPlus.setEmailPassword("zhangsanpassword" + i);
            testPlus.setPassword("pss" + i);
            testPlus.setUsername("psy" + i);
            testPluses.add(testPlus);
        }
//        传递size的作用是告诉大小，默认是1000,1000是比较消耗性能的
        boolean b = testPlusService.saveBatch(testPluses, testPluses.size());
        if (b) {
            log.info("批量入库完毕！");
        }

    }
}
