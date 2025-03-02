package com.cyx.findfriend.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportUser {
    public static void main(String[] args) {
        String fileName = "D:\\伙伴匹配\\find-friend-backend\\src\\main\\resources\\testExcel.xlsx";

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<TableUserInfo> userInfoList = EasyExcel.read(fileName).head(TableUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
        Map<String, List<TableUserInfo>> listMap =
                userInfoList.stream()
                        .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
                        .collect(Collectors.groupingBy(TableUserInfo::getUsername));
        for (Map.Entry<String, List<TableUserInfo>> stringListEntry : listMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("username = " + stringListEntry.getKey());
                System.out.println("1");
            }
        }

        System.out.println("不重复昵称数 = " + listMap.keySet().size());
    }
}
