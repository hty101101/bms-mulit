package io.renren;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhan
 * @date 2020/04/08 17:19:14
 * @description 地址解析工具类
 * see https://blog.csdn.net/qq_43699339/article/details/105443874
 */
public class AddressUtilTest {
    /**
     * 解析地址
     * @param address
     * @return
     */
    public static Map<String,String> getAddressInfo(String address) {
        //1级 省 自治区  2级 市 自治州 地区 3级：区县市旗(镇？)
        String province = null, city = null, provinceAndCity = null, town = null ,detailAddress = null;
        Map<String, String> row = new LinkedHashMap<>();
        List<Map<String, String>> table = new ArrayList<>();
        Map<String,String> resultMap = new HashMap<>(4);

        if (address.startsWith("香港特别行政区")) {
            resultMap.put("province","香港");
            return resultMap;
        } else if (address.contains("澳门特别行政区")) {
            resultMap.put("province","澳门");
            return resultMap;
        } else if (address.contains("台湾")) {
            resultMap.put("province","台湾");
            return resultMap;
        } else {
            //普通地址
            String regex = "((?<provinceAndCity>[^市]+市|.*?自治州|.*?区|.*县)(?<town>[^区]+区|.*?市|.*?县|.*?路|.*?街|.*?道|.*?镇|.*?旗)(?<detailAddress>.*))";
            Matcher m = Pattern.compile(regex).matcher(address);
            while (m.find()) {
                provinceAndCity = m.group("provinceAndCity");
                String regex2 = "((?<province>[^省]+省|.+自治区|上海市|北京市|天津市|重庆市|上海|北京|天津|重庆)(?<city>.*))";
                Matcher m2 = Pattern.compile(regex2).matcher(provinceAndCity);
                while (m2.find()) {
                    province = m2.group("province");
                    row.put("province", province == null ? "" : province.trim());
                    city = m2.group("city");
                    row.put("city", city == null ? "" : city.trim());
                }
                town = m.group("town");
                row.put("town", town == null ? "" : town.trim());
                table.add(row);

                detailAddress = m.group("detailAddress");
                row.put("detailAddress", detailAddress == null ? "" : detailAddress.trim());
                table.add(row);
            }
        }
        if (table.size() > 0) {
            if (StringUtils.isNotBlank(table.get(0).get("province"))) {
                province = table.get(0).get("province");
                // 截取有问题，西藏自治区，内蒙古自治区，广西壮族自治区，宁夏回族自治区，新疆维吾尔自治区
                //对自治区进行处理
                // if (province.contains("自治区")) {
                //     if (province.contains("内蒙古")) {
                //         province = province.substring(0,4);
                //     }  else {
                //         province = province.substring(0,3);
                //     }
                // }
            }
            // 对省份进行处理
            if (StringUtils.isNotBlank(province)) {
                if (StringUtils.isNotBlank(table.get(0).get("city"))) {
                    city = table.get(0).get("city");
                    if ("上海市".equals(city) || "重庆市".equals(city) || "北京市".equals(city) || "天津市".equals(city)) {
                        province = table.get(0).get("city");
                    }
                }

                else if ("上海市".equals(province) || "重庆市".equals(province) || province.equals("北京市") || province.equals("天津市")) {
                    city = province;
                }
                if (StringUtils.isNotBlank(table.get(0).get("town"))) {
                    town = table.get(0).get("town");
                }
                if (StringUtils.isNotBlank(table.get(0).get("detailAddress"))) {
                    detailAddress = table.get(0).get("detailAddress");
                }
                // 去除省份中的最后一个字
                // province = province.substring(0,province.length() - 1);
            }

        } else {
            resultMap.put("province","");
            resultMap.put("city","");
            resultMap.put("district","");
            resultMap.put("detailAddress",address);
            return resultMap;
        }
        resultMap.put("province",province);
        resultMap.put("city",city);
        resultMap.put("district",town);
        resultMap.put("detailAddress",detailAddress);

        return resultMap;
    }

    public static void main(String[] args) {
       // Map<String, String> map = getAddressInfo("广东省深圳市南山区东滨路205号");
       // Map<String, String > map = getAddressInfo("上海市虹口区飞虹路518号");
       // Map<String, String > map = getAddressInfo("河北省廊坊市三河市燕顺路1140号");
       // Map<String, String> map = getAddressInfo("香港特别行政区油尖旺区广华街58号");
       // Map<String, String> map = getAddressInfo("黑龙江省大兴安岭地区呼玛县合兴街");
       // Map<String, String> map = getAddressInfo("江苏省南京市江宁区202县道");
       // Map<String, String> map = getAddressInfo("海南省陵水黎族自治县陵水黎族自治县提蒙大道215号");

       // Map<String, String> map = getAddressInfo("山东省烟台市龙口市062县道");
       // Map<String, String> map = getAddressInfo("新疆维吾尔自治区乌鲁木齐市沙依巴克区阿里山街");
       // Map<String, String> map = getAddressInfo("内蒙古自治区呼伦贝尔市鄂温克族自治旗");
       // Map<String, String> map = getAddressInfo("日本滋贺县甲賀市県道507号線");

       Map<String, String> map = getAddressInfo("广东省广州市海珠区新港西路135号");

        map.forEach((key, value) -> System.out.println(key + ":" + value));

    }
}
