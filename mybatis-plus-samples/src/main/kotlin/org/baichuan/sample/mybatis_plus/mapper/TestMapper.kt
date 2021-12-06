package org.baichuan.sample.mybatis_plus.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.baichuan.sample.mybatis_plus.entity.TestEntity

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/8/18
 */
@Mapper
interface TestMapper : BaseMapper<TestEntity> {
}