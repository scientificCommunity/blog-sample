package transformer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: tk (rivers.boat.snow at gmail dot com)
 * @date: 2021/6/3
 */
public class ThreadPoolExecutorTransformer extends AbstractTransformer {
    private static final HashSet<String> TO_BE_TRANSFORMED_CLASS_NAMES = new HashSet<>();
    private static final Map<String, String> TO_BE_WRAPPED_PARAM_NAMES = new HashMap<>();

    static {
        TO_BE_TRANSFORMED_CLASS_NAMES.add("java.util.concurrent.ThreadPoolExecutor");
        TO_BE_WRAPPED_PARAM_NAMES.put("java.lang.Runnable", "org.baichuan.example.vertx.transformer.WrappedRunnable");
    }

    @Override
    protected HashSet<String> toBeTransformedClassNames() {
        return TO_BE_TRANSFORMED_CLASS_NAMES;
    }

    @Override
    protected Map<String, String> toBeWrappedParam() {
        return TO_BE_WRAPPED_PARAM_NAMES;
    }

    @Override
    protected String transformSpecificParamCodes(int paramIndex, String paramTypeName) {
        return String.format("$%d=new %s($%d);",
                paramIndex, TO_BE_WRAPPED_PARAM_NAMES.get(paramTypeName), paramIndex);
    }
}
