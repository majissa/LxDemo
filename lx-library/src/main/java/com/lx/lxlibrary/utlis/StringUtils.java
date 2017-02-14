package com.lx.lxlibrary.utlis;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lx on 2016/3/22.
 */
public class StringUtils {

    /**
     * 字符串不为空
     *
     * @param srcStr
     * @return
     */
    public static final boolean isEmptyOrNull(String srcStr) {
        if ((srcStr == null) || (srcStr.trim().length() <= 0)) {
            return true;
        }
        return "null".equals(srcStr);
    }


    /**
     * 匹配地址
     *
     * @param address 输入的中文地址
     * @return 匹配的中文地址
     * <p/>
     * 匹配的原则为：
     * 如：输入：福建省福州市台江区  返回：台江区
     * 如：输入：福建省福州市台江    返回：福州市
     * 如：输入：福建省福州台江区    返回：福州台江区
     * 如：输入：福建省福州台江      返回：福建省
     * 如：输入：福建福州台江区      返回：台江区
     * 如：输入：福建福州市台江      返回：福建福州市
     * 如：输入：福建福州台江区      返回：台江区
     * 如：输入：福建福州台江        返回：福建
     * 如：输入：福州市台江区        返回：福州市
     * 如：输入：台江区福马路        返回：台江区
     *
     *
     * 输入小于2个字符输出原输入的地址
     * 不匹配的省份有：内蒙古自治区  广西壮族自治区 西藏自治区  宁夏回族自治区  新疆维吾尔自治区
     * 香港特别行政区	 澳门特别行政区
     */

    public static String addressFormat(String address) {
        String result = null;
        if (address.length() > 0) {
            if (address.length() > 2) {
                if (address.contains("区")) {
                    int areaIndex = address.lastIndexOf("区");
                    String i = address.substring(0,areaIndex);
                    String[] split = address.split("区");
                    if (split[0].contains("市")) {
                        int lastIndexOf = split[0].lastIndexOf("市");
                        String citysubstring = split[0].substring(0, lastIndexOf);
                        String areasubstring = split[0].substring(lastIndexOf + 1);
                        if (citysubstring.contains("省")) {
                            int provinceindexx = citysubstring.lastIndexOf("省");
                            result = citysubstring.substring(provinceindexx + 1);

                        }
                        result = areasubstring + "区";

                    } else if (split[0] != null) {
                        //System.out.println(split[0]);
                        result = split[0] + "区";
                    }
                    String areabefore = address.substring(0, areaIndex + 1);
                    if (areabefore.contains("市")) {
                        int cityindex = areabefore.lastIndexOf("市");
                        String citybefore = areabefore.substring(0, cityindex + 1);
                        String area = areabefore.substring(cityindex + 1, areaIndex + 1);
                        //System.out.println("区域:"+area);
                        if (citybefore.contains("省")) {
                            int provinceBefore = citybefore.lastIndexOf("省");
                            String city = citybefore.substring(provinceBefore + 1, cityindex + 1);
                            String province = citybefore.substring(0, provinceBefore + 1);
                            //System.out.println("市区:"+city);
                            //System.out.println("省份："+province);
                            if (area != null) {
                                result = area;
                            } else if (city != null) {
                                result = city;
                            } else if (province != null) {
                                result = province;
                            }
                        }
                    } else if (areabefore.contains("省")) {
                        int SHENlastIndexOf = areabefore.lastIndexOf("省");
                        result = areabefore.substring(SHENlastIndexOf + 1);
                    } else {
                        result = i+"区";
                    }

                } else if (address.contains("市")) {
                    int cityindex = address.indexOf("市");
                    String[] split = address.split("市");
                    //System.out.println("市区:"+split[0]);
                    if (split[0] != null) {
                        result = split[0] + "市";
                    }
                    String citybefore = address.substring(0, cityindex + 1);
                    if (citybefore.contains("省")) {
                        int provinceBefore = citybefore.indexOf("省");
                        String city = citybefore.substring(provinceBefore + 1, cityindex + 1);
                        String province = citybefore.substring(0, provinceBefore + 1);
                        //System.out.println("市区:"+city);
                        //System.out.println("省份："+province);
                        if (city != null) {
                            result = city;
                        } else if (province != null) {
                            result = province;
                        }

                    }
                } else if (address.contains("省")) {
                    int provinceBefore = address.indexOf("省");
                    String province = address.substring(0, provinceBefore + 1);
                    if (province != null) {
                        //System.out.println("省份："+province);
                        result = province;
                    }

                } else {
                    String add = address.substring(0, 2);
                    if (add != null) {
                        result = add;
                        //System.out.println(add);
                    }

                }

            } else
                result = address;
            //System.out.println(address);
        }

        return result;


    }



    public static String addressFormat1(String address)
    {
        String finaladdress = null;
        if(!TextUtils.isEmpty(address))
        {
            if(address.length()>2)
            {
                finaladdress= address.substring(0,2);
            }else
                 finaladdress = address;
        }
        return finaladdress;

    }


    /**
     * 字符串不为空也不为0
     *
     * @param srcStr
     * @return
     */
    public static final boolean isEmptyOrZero(String srcStr) {
        return (isEmptyOrNull(srcStr)) || ("0".equals(srcStr));
    }

    /**
     * 正则式匹配
     *
     * @param srcStr
     * @param pattern
     * @return
     */
    public static final boolean isPattern(String srcStr, String pattern) {
        //生成Pattern对象并且编译一个简单的正则表达式pattern
        Pattern p = Pattern.compile(pattern);
        //用Pattern类的matcher()方法生成一个Matcher对象
        Matcher m = p.matcher(srcStr);
        //使用find()方法查找第一个匹配的对象
        return m.find();
    }

    /**
     * 格式化输出
     *
     * @param num     要转换的数
     * @param pattern 转换格式 eg："0.00"(两位小数)
     * @return
     */
    public static final String fomatInteger(int num, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(num);
    }

    /**
     * 将对象装成字符串
     *
     * @param o
     * @return
     */
    public static final String valueOf(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 转成gbk格式
     *
     * @param szSrcData 被复制字符
     * @param nOffset   起始位置
     * @param nLen      长度
     * @return
     */
    public static String encodeWithGBK(byte[] szSrcData, int nOffset, int nLen) {
        try {
            byte[] szTemp = new byte[nLen];
            System.arraycopy(szSrcData, nOffset, szTemp, 0, nLen);
            return new String(szTemp, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 截取len-1 长度的字符串
     *
     * @param srcStr
     * @return
     */
    public static String GetSubString(String srcStr) {
        int index = srcStr.indexOf(0);
        if (index != -1) {
            return srcStr.substring(0, index);
        }
        return srcStr;
    }

    /**
     * 截取指定位置的字符串和其他字符串结合
     *
     * @param srcStr
     * @param nLen      位置
     * @param formatStr
     * @return
     */
    public static String getSubstrByLen(String srcStr, int nLen, String formatStr) {
        if (srcStr.length() > nLen) {
            return srcStr.substring(0, nLen) + formatStr;
        }
        return srcStr;
    }

    /**
     * 截取指定开始和结束位置的字符串
     *
     * @param srcStr
     * @param begin
     * @param end
     * @return
     */
    public static String getSubstrByBeginEnd(String srcStr, int begin, int end) {
        if (srcStr.length() > end && begin < end) {
            return srcStr.substring(begin, end);
        }
        return srcStr;
    }

    /**
     * 手机号验证
     *
     * @param mobiles
     * @return 验证通过返回 true    不通过fase
     */
    public static final boolean isMobileNum(String mobiles) {
        System.out.println("===========================");
        String telRegex = "[1][34587]\\d{9}";//"[1]"代表第1位为数字1，"[3587]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 验证密码的长度
     *
     * @return true 符合长度
     */
    public static boolean chickPwdLength(String pwd1) {
        if (pwd1.length() < 6 || pwd1.length() > 12) {
            return false;
        }
        return true;
    }

    /**
     * 验证密码的是否相同
     *
     * @return true 相同
     * false  不相同
     */
    public static boolean isTheSamePwd(String pwd1, String pew2) {
        if (pwd1.equals(pew2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证密码的是否由数字和字符组成numbers and characters
     *
     * @return true 由数字和字符组成
     */
    public static boolean makeUpNumACharacters(String pwd1) {
        String telRegex = "(?!^\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,12}";
        if (TextUtils.isEmpty(pwd1)) return false;
        else if (pwd1.matches(telRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证邮政编码
     *
     * @param post
     * @return true 邮编正确
     */
    public static boolean checkPost(String post) {
        if (post.matches("[1-9]\\d{5}(?!\\d)")) {
            return true;
        } else {
            return false;
        }
    }


}