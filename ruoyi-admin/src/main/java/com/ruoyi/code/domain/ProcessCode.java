package com.ruoyi.code.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessCode {

    /*审核流程状态标识
    0，待提交【默认值】；     10委托单位退回；
    1，委托单位待审核；
    2，待二次提交；           32鉴定单位退回；
    3，鉴定单位待审核；
    4，待检验；
    5，待生成鉴定文书；       65授权人退回； 75机构负责人退回待生成；
    5a，第二鉴定人审核
    6，待授权人审核文书；     76机构负责人退回授权人审核；
    7，待机构负责人审核文书；
    8，待发放
    9，待签收
    10.已签收（完成）。*/
    public final static String wtj = "0";           public final static String wtdwth = "10";
    public final static String wtdwdsh = "1";
    public final static String dectj = "2";         public final static String jddwth = "32";
    public final static String jddwdsh = "3";
    public final static String djy = "4";
    public final static String dscjdws = "5";       public final static String sqrth = "65";  public final static String dejdrth = "5a5";
    public final static String ddejdrshws = "5a";
    public final static String dsqrshws = "6";      public final static String jgfzrthsqrsh = "76";
    public final static String djgfzrshws = "7";    public final static String jgfzrthdsc = "75";
    public final static String dff = "8";
    public final static String dqs = "9";
    public final static String end = "x";

    //管理员
    public static Integer admin = 1;
    //普通角色
    public static Integer common = 2;
    //委托申请人
    public static Integer wtsqr = 100;
    //申请单位负责人(所长)
    public static Integer sqdwfzr = 101;
    //鉴定单位委托接收人
    public static Integer jddwwtjsr = 102;
    //鉴定人
    public static Integer jdr = 103;
    //签字授权人
    public static Integer qzsqr = 104;
    //机构负责人
    public static Integer jgfzr = 105;

    /**
     * 获取流程code
     */
    public static String getProcessCode(String fromCode, String status) {
        String toCode = fromCode;
        if ("pass".equals(status)) {
            switch (fromCode) {
                case "0":
                case "10":
                    toCode = "1";
                    break;
                case "1":
                    toCode = "3";
                    break;
                case "3":
                    toCode = "4";
                    break;
                case "4":
                    toCode = "5";
                    break;
                case "5":
                case "5a5":
                case "65":
                case "75":
                    toCode = "5a";
                    break;
                case "5a":
                    toCode = "6";
                    break;
                case "6":
                case "76":
                    toCode = "7";
                    break;
                case "7":
                    toCode = "8";
                    break;
                case "8":
                    toCode = "9";
                    break;
                case "9":
                    toCode = "x";
                    break;
                default:
                    toCode = fromCode;
                    break;
            }
        } else {
            switch (fromCode) {
                case "7":
                case "6":
                case "5a":
                    toCode = "5";
                    break;
                case "3":
                case "1":
                    toCode = "10";
                    break;
                default:
                    toCode = fromCode;
                    break;
            }
        }
        return toCode;
    }
    /**
     * 获取流程状态
     */
    public static String getStatus(String processCode){
        String status = "";
        switch (processCode) {
            case "0":
                status = "待提交";
                break;
            case "10":
                status = "所长退回";
                break;
            case "1":
                status = "所长待审核";
                break;
            case "2":
                status = "待二次提交";
                break;
            case "32":
                status = "鉴定单位退回";
                break;
            case "3":
                status = "待鉴定单位审核";
                break;
            case "4":
                status = "待检验";
                break;
            case "5":
                status = "待生成鉴定文书";
                break;
            case "5a":
                status = "待第二鉴定人审核";
                break;
            case "65":
                status = "授权人退回";
                break;
            case "75":
                status = "机构负责人退回";
                break;
            case "6":
                status = "待授权人审核文书";
                break;
            case "76":
                status = "机构负责人退回";
                break;
            case "7":
                status = "待机构负责人审核文书";
                break;
            case "8":
                status = "待发放";
                break;
            case "9":
                status = "待签收";
                break;
            case "x":
                status = "已签收";
                break;
            default:
                status = "";
        }
        return status;
    }
    /**
     * 根据角色获取待审核的状态(可能存在一个角色审核多个状态的情况)
     */
    public static List<String> getHandleStatus(Integer role){
        List<String> status = new ArrayList<>();
//        if (wtsqr == role) {
//            status.add("10");
//        }
        if (sqdwfzr == role) {
            status.add("0");
        }

        return status;
    }
}
