package com.ruoyi.code.domain;

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
                    toCode = "0";
                    break;
                default:
                    toCode = fromCode;
                    break;
            }
        }
        return toCode;
    }
}
