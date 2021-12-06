package org.baichuan.sample.mybatis_plus

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.assertj.core.api.Assertions.assertThat
import org.baichuan.sample.mybatis_plus.entity.TestEntity
import org.baichuan.sample.mybatis_plus.enums.PaymentMethod
import org.baichuan.sample.mybatis_plus.mapper.TestMapper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.Resource

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/12/6
 */
@SpringBootTest
class PostgresTest {
    @Resource
    lateinit var mapper: TestMapper

    @Test
    fun aInsert() {
        val test = TestEntity()
        test.paymentMethod = PaymentMethod.ALIPAY
        assertThat(mapper.insert(test)).isGreaterThan(0)
        // 成功直接拿回写的 ID
        assertThat(test.id).isNotNull
    }

    @Test
    fun find() {
        val test = TestEntity()
        test.paymentMethod = PaymentMethod.ALIPAY
        val condition = QueryWrapper<TestEntity>()
        condition.eq("payment_method", PaymentMethod.WECHAT)

        assertThat(mapper.selectOne(condition)).isNotNull
        // 成功直接拿回写的 ID
        assertThat(test.id).isNotNull
    }
}