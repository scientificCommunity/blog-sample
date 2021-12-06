package org.baichuan.sample.mybatis_plus.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import org.baichuan.sample.mybatis_plus.enums.PaymentMethod


@TableName(value = "\"test\"", autoResultMap = true)
class TestEntity : Model<TestEntity>() {
    var id: String? = null

    var paymentMethod: PaymentMethod? = null
}