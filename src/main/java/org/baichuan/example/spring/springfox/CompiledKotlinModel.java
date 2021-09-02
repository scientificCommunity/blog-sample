package org.baichuan.example.spring.springfox;

import io.swagger.annotations.ApiModelProperty;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/9/2
 */
public class CompiledKotlinModel {
    @ApiModelProperty
    @Nullable
    private String field;
//    @ApiModelProperty
    public String field1;

    @Nullable
    public final String getField() {
        return this.field;
    }

    public final void setField(@NotNull String var1) {
        this.field = var1;
    }

    @NotNull
    public final String getField1() {
        String var1 = this.field1;
        if (var1 != null) {
            return var1;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("field1");
            throw null;
        }
    }

    public final void setField1(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.field1 = var1;
    }
}
