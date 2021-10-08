package org.baichuan.example.notion.utils

object TeeUtils {
    /**
     * 驼峰转下划线
     *
     * @param param _
     * @return _
     */
    fun camelToUnderline(param: String?): String? {
        if (param == null || "" == param.trim { it <= ' ' }) {
            return param
        }
        val len = param.length
        val sb = StringBuilder(len)
        for (i in 0 until len) {
            val c = param[i]
            if (Character.isUpperCase(c)) {
                sb.append(TeeUtils.UNDERLINE)
                sb.append(Character.toLowerCase(c))
            } else {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    private const val UNDERLINE = '_'
}