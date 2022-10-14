//package org.baichuan.sample.algorithms.leetcode.middle;
//
//import java.util.*;
//
///**
// * @author: kuntang (rivers.boat.snow@gmail.com)
// * @date: 2022/5/22
// * 面试题 17.07. 婴儿名字
// * https://leetcode-cn.com/problems/baby-names-lcci/
// */
//public class TrulyMostPopular {
//    public String[] trulyMostPopular(String[] names, String[] synonyms) {
//        Map<String, StringBuilder> map = new HashMap<>();
////        Map<String, StringBuilder> rootMap = new HashMap<>();
//        List<String> ans = new ArrayList<>();
//
//        for (String s : synonyms) {
//            StringBuilder key = new StringBuilder();
//            StringBuilder miniLetterKey = null;
////            List<StringBuilder> keys = new ArrayList<>();
//            StringBuilder minorKey = new StringBuilder();
//            for (char c : s.toCharArray()) {
//                if (c == ',' || c == ')') {
//                    if (miniLetterKey != null) {
//                        int len = Math.min(miniLetterKey.length(), key.length());
//                        for (int i = 0; i < len; i++) {
//                            if (miniLetterKey.charAt(i) == key.charAt(i)) {
//                                continue;
//                            }
//                            if (miniLetterKey.charAt(i) > key.charAt(i)) {
//                                minorKey = miniLetterKey;
//                                miniLetterKey = key;
//                            } else {
//                                minorKey = key;
//                            }
//                            break;
//                        }
//                    } else {
//                        miniLetterKey = key;
//                    }
//                    //keys.add(key);
//                    if (c == ',') {
//                        key = new StringBuilder();
//                    }
//                } else if (c != '(') {
//                    key.append(c);
//                }
//            }
//            if (map.containsKey(miniLetterKey.toString())) {
//                miniLetterKey = map.get(miniLetterKey.toString());
//            }
//
//            if (map.containsKey(minorKey.toString()) && !map.get(minorKey.toString()).toString().equals(miniLetterKey.toString())) {
//                map.put(minorKey.toString(), miniLetterKey);
//            }
//            map.put(stringBuilder.toString(), miniLetterKey);
//        }
//
//        Map<String, Integer> ansMap = new HashMap<>();
//        for (String name : names) {
//            StringBuilder n = new StringBuilder();
//            StringBuilder count = new StringBuilder();
//            boolean flag = false;
//            for (char c : name.toCharArray()) {
//                if (c == '(') {
//                    flag = true;
//                    continue;
//                }
//                if (!flag) {
//                    n.append(c);
//                } else if (c != ')') {
//                    count.append(c);
//                }
//            }
//            if (map.get(n.toString()) == null) {
//                ansMap.put(n.toString(), Integer.parseInt(count.toString()));
//                continue;
//            }
//            if (ansMap.containsKey(rootMap.get(map.get(n.toString()).toString()).toString())) {
//                ansMap.put(rootMap.get(map.get(n.toString()).toString()).toString(), ansMap.get(rootMap.get(map.get(n.toString()).toString()).toString()) + Integer.parseInt(count.toString()));
//            } else {
//                ansMap.put(rootMap.get(map.get(n.toString()).toString()).toString(), Integer.parseInt(count.toString()));
//            }
//        }
//        ansMap.forEach((k, v) -> {
//            ans.add(k + "(" + v + ")");
//        });
//
//        return ans.toArray(new String[0]);
//    }
//
//    private String checkPriority(String key1, String key2) {
//        int len = Math.min(key1.length(), key2.length());
//        for (int i = 0; i < len; i++) {
//            if (key1.charAt(i) == key2.charAt(i)) {
//                continue;
//            }
//            if (key1.charAt(i) > key2.charAt(i)) {
//                return key2;
//            }
//            break;
//        }
//        return key1;
//    }
//
//    public static void main(String[] args) {
//        //["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"]
//        //["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
////        String[] names = new String[]{"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"};
////        String[] synonyms = new String[]{"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"};
//
//        //"Fcclu(70)","Ommjh(63)","Dnsay(60)","Qbmk(45)","Unsb(26)","Gauuk(75)","Wzyyim(34)","Bnea(55)","Kri(71)","Qnaakk(76)","Gnplfi(68)","Hfp(97)","Qoi(70)","Ijveol(46)","Iidh(64)","Qiy(26)","Mcnef(59)","Hvueqc(91)","Obcbxb(54)","Dhe(79)","Jfq(26)","Uwjsu(41)","Wfmspz(39)","Ebov(96)","Ofl(72)","Uvkdpn(71)","Avcp(41)","Msyr(9)","Pgfpma(95)","Vbp(89)","Koaak(53)","Qyqifg(85)","Dwayf(97)","Oltadg(95)","Mwwvj(70)","Uxf(74)","Qvjp(6)","Grqrg(81)","Naf(3)","Xjjol(62)","Ibink(32)","Qxabri(41)","Ucqh(51)","Mtz(72)","Aeax(82)","Kxutz(5)","Qweye(15)","Ard(82)","Chycnm(4)","Hcvcgc(97)","Knpuq(61)","Yeekgc(11)","Ntfr(70)","Lucf(62)","Uhsg(23)","Csh(39)","Txixz(87)","Kgabb(80)","Weusps(79)","Nuq(61)","Drzsnw(87)","Xxmsn(98)","Onnev(77)","Owh(64)","Fpaf(46)","Hvia(6)","Kufa(95)","Chhmx(66)","Avmzs(39)","Okwuq(96)","Hrschk(30)","Ffwni(67)","Wpagta(25)","Npilye(14)","Axwtno(57)","Qxkjt(31)","Dwifi(51)","Kasgmw(95)","Vgxj(11)","Nsgbth(26)","Nzaz(51)","Owk(87)","Yjc(94)","Hljt(21)","Jvqg(47)","Alrksy(69)","Tlv(95)","Acohsf(86)","Qejo(60)","Gbclj(20)","Nekuam(17)","Meutux(64)","Tuvzkd(85)","Fvkhz(98)","Rngl(12)","Gbkq(77)","Uzgx(65)","Ghc(15)","Qsc(48)","Siv(47)"
////        String[] names = new String[]{"Fcclu(70)", "Ommjh(63)", "Dnsay(60)", "Qbmk(45)", "Unsb(26)", "Gauuk(75)", "Wzyyim(34)", "Bnea(55)", "Kri(71)", "Qnaakk(76)", "Gnplfi(68)", "Hfp(97)", "Qoi(70)", "Ijveol(46)", "Iidh(64)", "Qiy(26)", "Mcnef(59)", "Hvueqc(91)", "Obcbxb(54)", "Dhe(79)", "Jfq(26)", "Uwjsu(41)", "Wfmspz(39)", "Ebov(96)", "Ofl(72)", "Uvkdpn(71)", "Avcp(41)", "Msyr(9)", "Pgfpma(95)", "Vbp(89)", "Koaak(53)", "Qyqifg(85)", "Dwayf(97)", "Oltadg(95)", "Mwwvj(70)", "Uxf(74)", "Qvjp(6)", "Grqrg(81)", "Naf(3)", "Xjjol(62)", "Ibink(32)", "Qxabri(41)", "Ucqh(51)", "Mtz(72)", "Aeax(82)", "Kxutz(5)", "Qweye(15)", "Ard(82)", "Chycnm(4)", "Hcvcgc(97)", "Knpuq(61)", "Yeekgc(11)", "Ntfr(70)", "Lucf(62)", "Uhsg(23)", "Csh(39)", "Txixz(87)", "Kgabb(80)", "Weusps(79)", "Nuq(61)", "Drzsnw(87)", "Xxmsn(98)", "Onnev(77)", "Owh(64)", "Fpaf(46)", "Hvia(6)", "Kufa(95)", "Chhmx(66)", "Avmzs(39)", "Okwuq(96)", "Hrschk(30)", "Ffwni(67)", "Wpagta(25)", "Npilye(14)", "Axwtno(57)", "Qxkjt(31)", "Dwifi(51)", "Kasgmw(95)", "Vgxj(11)", "Nsgbth(26)", "Nzaz(51)", "Owk(87)", "Yjc(94)", "Hljt(21)", "Jvqg(47)", "Alrksy(69)", "Tlv(95)", "Acohsf(86)", "Qejo(60)", "Gbclj(20)", "Nekuam(17)", "Meutux(64)", "Tuvzkd(85)", "Fvkhz(98)", "Rngl(12)", "Gbkq(77)", "Uzgx(65)", "Ghc(15)", "Qsc(48)", "Siv(47)"};
////        String[] synonyms = new String[]{"(Gnplfi,Qxabri)", "(Uzgx,Siv)", "(Bnea,Lucf)", "(Qnaakk,Msyr)", "(Grqrg,Gbclj)", "(Uhsg,Qejo)", "(Csh,Wpagta)", "(Xjjol,Lucf)", "(Qoi,Obcbxb)", "(Npilye,Vgxj)", "(Aeax,Ghc)", "(Txixz,Ffwni)", "(Qweye,Qsc)", "(Kri,Tuvzkd)", "(Ommjh,Vbp)", "(Pgfpma,Xxmsn)", "(Uhsg,Csh)", "(Qvjp,Kxutz)", "(Qxkjt,Tlv)", "(Wfmspz,Owk)", "(Dwayf,Chycnm)", "(Iidh,Qvjp)", "(Dnsay,Rngl)", "(Qweye,Tlv)", "(Wzyyim,Kxutz)", "(Hvueqc,Qejo)", "(Tlv,Ghc)", "(Hvia,Fvkhz)", "(Msyr,Owk)", "(Hrschk,Hljt)", "(Owh,Gbclj)", "(Dwifi,Uzgx)", "(Iidh,Fpaf)", "(Iidh,Meutux)", "(Txixz,Ghc)", "(Gbclj,Qsc)", "(Kgabb,Tuvzkd)", "(Uwjsu,Grqrg)", "(Vbp,Dwayf)", "(Xxmsn,Chhmx)", "(Uxf,Uzgx)"};
//
//        String[] names = new String[]{"Fcclu(70)", "Ommjh(63)", "Dnsay(60)", "Qbmk(45)", "Unsb(26)", "Gauuk(75)", "Wzyyim(34)", "Bnea(55)", "Kri(71)", "Qnaakk(76)", "Gnplfi(68)", "Hfp(97)", "Qoi(70)", "Ijveol(46)", "Iidh(64)", "Qiy(26)", "Mcnef(59)", "Hvueqc(91)", "Obcbxb(54)", "Dhe(79)", "Jfq(26)", "Uwjsu(41)", "Wfmspz(39)", "Ebov(96)", "Ofl(72)", "Uvkdpn(71)", "Avcp(41)", "Msyr(9)", "Pgfpma(95)", "Vbp(89)", "Koaak(53)", "Qyqifg(85)", "Dwayf(97)", "Oltadg(95)", "Mwwvj(70)", "Uxf(74)", "Qvjp(6)", "Grqrg(81)", "Naf(3)", "Xjjol(62)", "Ibink(32)", "Qxabri(41)", "Ucqh(51)", "Mtz(72)", "Aeax(82)", "Kxutz(5)", "Qweye(15)", "Ard(82)", "Chycnm(4)", "Hcvcgc(97)", "Knpuq(61)", "Yeekgc(11)", "Ntfr(70)", "Lucf(62)", "Uhsg(23)", "Csh(39)", "Txixz(87)", "Kgabb(80)", "Weusps(79)", "Nuq(61)", "Drzsnw(87)", "Xxmsn(98)", "Onnev(77)", "Owh(64)", "Fpaf(46)", "Hvia(6)", "Kufa(95)", "Chhmx(66)", "Avmzs(39)", "Okwuq(96)", "Hrschk(30)", "Ffwni(67)", "Wpagta(25)", "Npilye(14)", "Axwtno(57)", "Qxkjt(31)", "Dwifi(51)", "Kasgmw(95)", "Vgxj(11)", "Nsgbth(26)", "Nzaz(51)", "Owk(87)", "Yjc(94)", "Hljt(21)", "Jvqg(47)", "Alrksy(69)", "Tlv(95)", "Acohsf(86)", "Qejo(60)", "Gbclj(20)", "Nekuam(17)", "Meutux(64)", "Tuvzkd(85)", "Fvkhz(98)", "Rngl(12)", "Gbkq(77)", "Uzgx(65)", "Ghc(15)", "Qsc(48)", "Siv(47)"};
//        String[] synonyms = new String[]{"(Gnplfi,Qxabri)", "(Uzgx,Siv)", "(Bnea,Lucf)", "(Qnaakk,Msyr)", "(Grqrg,Gbclj)", "(Uhsg,Qejo)", "(Csh,Wpagta)", "(Xjjol,Lucf)", "(Qoi,Obcbxb)", "(Npilye,Vgxj)", "(Aeax,Ghc)", "(Txixz,Ffwni)", "(Qweye,Qsc)", "(Kri,Tuvzkd)", "(Ommjh,Vbp)", "(Pgfpma,Xxmsn)", "(Uhsg,Csh)", "(Qvjp,Kxutz)", "(Qxkjt,Tlv)", "(Wfmspz,Owk)", "(Dwayf,Chycnm)", "(Iidh,Qvjp)", "(Dnsay,Rngl)", "(Qweye,Tlv)", "(Wzyyim,Kxutz)", "(Hvueqc,Qejo)", "(Tlv,Ghc)", "(Hvia,Fvkhz)", "(Msyr,Owk)", "(Hrschk,Hljt)", "(Owh,Gbclj)", "(Dwifi,Uzgx)", "(Iidh,Fpaf)", "(Iidh,Meutux)", "(Txixz,Ghc)", "(Gbclj,Qsc)", "(Kgabb,Tuvzkd)", "(Uwjsu,Grqrg)", "(Vbp,Dwayf)", "(Xxmsn,Chhmx)", "(Uxf,Uzgx)"};
//        String[] strings = new TrulyMostPopular().trulyMostPopular(names, synonyms);
//        System.out.println(123);
//    }
//    //(Aeax,Ghc),(Tlv,Ghc),(Txixz,Ghc),(Qweye,Tlv),(Qxkjt,Tlv),(Txixz,Ffwni),(Qweye,Qsc),(Gbclj,Qsc),(Owh,Gbclj),(Grqrg,Gbclj),(Uwjsu,Grqrg)
//}
