package org.baichuan.sample.algorithms.leetcode.middle;

/**
 * @author: kuntang (rivers.boat.snow@gmail.com)
 * @date: 2022/4/18
 * 面试题 17.11. 单词距离
 * https://leetcode-cn.com/problems/find-closest-lcci/
 * 解法：双指针
 * <p>
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 * <p>
 * 示例：
 * <p>
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 * <p>
 * words.length <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-closest-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindClosest {
    public int findClosest(String[] words, String word1, String word2) {
        int pos1 = Integer.MAX_VALUE;
        int pos2 = Integer.MAX_VALUE + 1;
        int ans = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                pos1 = i;
                ans = Math.min(Math.abs(pos2 - pos1), ans);
            } else if (words[i].equals(word2)) {
                pos2 = i;
                ans = Math.min(Math.abs(pos2 - pos1), ans);
            }
            //把此处代码移到上面的判断里可以每一轮循环少两个判断
            /*if (pos1 > 0 && pos2 > 0) {
                ans = Math.min(Math.abs(pos2 - pos1), ans);
            }*/
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"vg", "ti", "te", "yd", "ja", "vqx", "a", "rtg", "bn", "rdg", "v", "sxz", "t", "gn", "bdc", "bp", "fk", "nl", "qr", "qh", "vo", "d", "ab", "ui", "gu", "ft", "kfr", "a", "jt", "gyq", "gmw", "jqo", "f", "iy", "pu", "kky", "cur", "qug", "z", "gb", "osn", "khg", "szd", "zhb", "g", "n", "wbv", "p", "f", "h", "hka", "j", "u", "n", "o", "mod", "hgc", "bev", "e", "cx", "jk", "z", "in", "cua", "on", "nis", "c", "qpq", "e", "rs", "ew", "ms", "d", "nh", "h", "cvu", "oup", "m", "a", "yb", "v", "t", "rc", "s", "hfa", "ehn", "w", "dox", "ptt", "r", "jzm", "l", "urm", "qd", "va", "emi", "sf", "dc", "h", "wh", "eh", "z", "c", "mx", "pm", "bnn", "fpk", "sky", "uyd", "cwk", "su", "xqc", "xv", "w", "ai", "dd", "zic", "qg", "vmc", "yrd", "os", "el", "ylz", "jac", "b", "mpw", "nso", "nzx", "d", "c", "nl", "qo", "vbe", "p", "lq", "u", "np", "u", "keo", "gg", "myh", "emt", "to", "mfo", "zlv", "v", "m", "nm", "i", "lml", "v", "wly", "rd", "bu", "ymn", "iu", "br", "r", "gwz", "nou", "qod", "ei", "gt", "ws", "j", "wgo", "e", "sn", "rem", "jmy", "hy", "dm", "qy", "gmo", "qz", "xcm", "iy", "spz", "yr", "fc", "cqo", "pol", "ib", "u", "ldd", "fj", "vj", "v", "hu", "nr", "e", "hf", "fwv", "eu", "dd", "o", "zft", "j", "uz", "j", "ce", "hwn", "dcs", "z", "xyn", "o", "b", "kx", "kg", "pjv", "ooh", "k", "r", "fgj", "wc", "v", "tjs", "cq", "aia", "z", "exs", "be", "py", "zif", "b", "oy", "o", "aox", "yf", "i", "tzn", "ny", "xst", "eo", "soq", "waz", "cr", "bf", "l", "yf", "bj", "p", "eez", "io", "er", "xe", "w", "iqh", "as", "wmx", "pk", "ge", "iet", "xel", "acc", "yc", "ek", "bb", "qip", "igg", "ejf", "far", "wkp", "fzl", "hhm", "r", "czh", "vpw", "z", "jei", "tmi", "m", "zdw", "obg", "cq", "ot", "h", "qj", "m", "nu", "cbu", "ffv", "u", "fc", "vb", "n", "vr", "myt", "so", "g", "pkl", "gya", "f", "ahb", "sn", "ym", "gk", "i", "nfn", "e", "efr", "yu", "xr", "k", "h", "esf", "uay", "kws", "czh", "fwr", "no", "trb", "wo", "ukc", "p", "dn", "me", "up", "jde", "fko", "y", "ghw", "v", "g", "lnq", "ztl", "ppr", "s", "o", "wsj", "z", "rxc", "o", "b", "gmv", "kk", "nak", "i", "gy", "qv", "yl", "qn", "lhs", "r", "n", "ymy", "d", "ko", "y", "nvm", "sdu", "dru", "k", "uk", "yu", "yg", "dds", "hoh", "v", "iv", "tu", "fe", "k", "d", "ggz", "d", "yw", "al", "vnu", "b", "u", "f", "up", "krv", "em", "slq", "cee", "sbt", "vl", "t", "tep", "fz", "db", "q", "z", "m", "x", "l", "ho", "bcm", "fcy", "x", "l", "ps", "e", "yld", "rou", "q", "d", "ldo", "rjn", "nb", "a", "zr", "kle", "nyz", "xu", "h", "q", "fxe", "aam", "fho", "kj", "sw", "i", "jwa", "joo", "y", "zrg", "vdy", "oc", "o", "cj", "re", "qw", "wkk", "s", "of", "nnj", "or", "p", "ih", "ps", "o", "u", "xsg", "hi", "w", "bof", "mvn", "lgy", "p", "m", "muq", "avm", "w", "f", "vl", "ntu", "b", "b", "hbz", "iv", "qk", "ttl", "g", "v", "je", "nej", "e", "s", "hnu", "pn", "ygy", "vos", "ui", "mvj", "ae", "sz", "onr", "mo", "ar", "ffs", "oa", "v", "qf", "zi", "ou", "utq", "fx", "yb", "u", "t", "jy", "oh", "jyv", "tju", "w", "vl", "qft", "s", "hlb", "hyb", "su", "ol", "hzr", "xd", "j", "kt", "x", "kzi", "d", "epx", "gf", "lxy", "pmy", "d", "not", "sbc", "h", "ial", "rz", "ypf", "yd", "p", "hp", "gck", "d", "r", "hlb", "r", "zl", "jbr", "uaw", "wz", "pjc", "v", "bs", "gh", "rqv", "msp", "f", "oaf", "zp", "mrh", "az", "br", "ro", "oj", "zfa", "ejr", "gu", "z", "po", "bc", "p", "we", "yzw", "qrg", "j", "wwv", "bh", "evz", "m", "h", "m", "mv", "m", "c", "qz", "b", "vnx", "hju", "r", "sdu", "qum", "m", "eq", "doo", "nbw", "lvm", "o", "kfn", "t", "z", "fpl", "yqv", "nf", "h", "gr", "i", "udm", "j", "v", "sz", "w", "eef", "vy", "gow", "ro", "zmx", "ts", "dub", "g", "dvy", "ye", "gmr", "b", "b", "y", "y", "hxn", "dr", "a", "pmy", "ick", "fb", "ny", "hzb", "x", "nm", "sqr", "n", "ial", "xo", "aw", "egi", "mrm", "uh", "i", "unn", "j", "kg", "tk", "lar", "o", "iy", "yh", "ag", "ss", "jx", "ory", "p", "k", "o", "c", "vng", "d", "wa", "ff", "fks", "v", "cz", "c", "u", "rxf", "ccv", "yx", "n", "rt", "pzn", "pih", "b", "goy", "znl", "iw", "fym", "sy", "hq", "dc", "a", "bbp", "ql", "oyx", "afi", "gry", "of", "gfg", "lys", "lz", "ky", "ssv", "k", "y", "dj", "woq", "pw", "wbz", "e", "x", "o", "b", "ptl", "n", "lte", "gv", "cg", "sx", "v", "q", "qm", "xs", "dip", "r", "pyr", "j", "i", "w", "qot", "by", "sla", "fum", "e", "jx", "s", "ws", "ntq", "xb", "sq", "t", "ky", "zzd", "ykj", "no", "sy", "zlc", "v", "cj", "f", "mzm", "n", "sh", "ao", "t", "ky", "rrh", "s", "wb", "bsx", "kko", "jxv", "j", "iwd", "vzo", "kh", "q", "ulq", "tba", "er", "oic", "ca", "qee", "e", "phs", "ew", "aj", "hm", "a", "hm", "o", "nn", "w", "g", "oi", "di", "urj", "nn", "dp", "di", "tu", "lll", "u", "xya", "o", "e", "rz", "y", "e", "ybn", "nkb", "o", "aj", "dim", "my", "xdf", "bcb", "rf", "rbr", "b", "ip", "e", "lw", "oez", "k", "zx", "hvj", "tmh", "cb", "ghy", "qju", "hy", "d", "d", "j", "qu", "zoq", "r", "z", "l", "bmz", "tb", "z", "hsw", "ctu", "yd", "wx", "pka", "b", "xrl", "ob", "gp", "e", "r", "vm", "gt", "qxo", "c", "fur", "djn", "vd", "p", "zq", "xm", "a", "g", "t", "c", "kh", "uh", "nsq", "vjc", "mys", "v", "q", "o", "vc", "wee", "rms", "ah", "se", "fg", "uo", "fl", "n", "io", "z", "ww", "e", "m", "o", "kif", "yqu", "r", "fqy", "e", "yuc", "pvf", "jni", "ss", "fi", "wn", "ct", "lrd", "dyt", "j", "kxs", "oon", "mw", "mdk", "ejj", "m", "uv", "gid", "ldu", "ah", "d", "cxh", "bb", "lr", "c", "an", "ey", "g", "sqs", "z", "zx", "x", "ejy", "qi", "c", "q", "p", "nm", "fn", "v", "z", "rfz", "sus", "vh", "nxy", "oa", "xdg", "vc", "l", "fvb", "i", "z", "igu", "ay", "t", "xvp", "n", "xrl", "v", "e", "cq", "qv", "or", "ff", "apj", "za", "hlx", "p", "y", "tz", "hng", "d", "j", "ykt", "ta", "lp", "lc", "fhs", "xp", "gfl", "tzi", "v", "x", "tgo", "jv", "xoj", "gux", "eyx", "adg", "gjc", "p", "gw", "hnl", "p", "jt", "mlj", "p", "nv", "trl", "tp", "dap", "mc", "lx", "g", "l", "kjg", "cax", "jct", "q", "t", "d", "ww", "vwd", "zcl", "yhr", "fft", "qq", "y", "wwz", "sek", "a", "mc", "vkq", "m", "yn", "ope", "kna", "y", "ve", "w", "z", "mwm", "a", "i", "d", "ta", "s", "fs", "pix", "n", "lio", "xka", "wbn", "kag", "v"};
        String word1 = "o";
        String word2 = "fk";
        int closest = new FindClosest().findClosest(words, word1, word2);
        System.out.println(closest);
    }
}
