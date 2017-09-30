package com.aynait.ddns.core.common;

/**
 * Created by Tianya on 2017/9/30.
 */
public class DSConstant {

    /**
     * 特殊字符
     */
    public static final String SEPARATOR_COLON = ":";
    public static final String SEPARATOR_SEMICOLON = ";";
    public static final String SEPARATOR_POINT = ".";
    public static final String SEPARATOR_COMMA = ",";
    public static final String SEPARATOR_UNDERLINE = "_";
    public static final String SEPARATOR_CARRIAGE_RETURN = "\r";
    public static final String SEPARATOR_LINE_FEED = "\n";
    public static final String SEPARATOR_VERTICAL_BAR = "|";

    /**
     * zone文件
     */
    public static final String AYNAIT_COM_ZONE = "ddns.aynait.com.zone";

    /**
     * zone文件配置
     */
    public static final String DNS_TTL = "$TTL 86400";
    public static final String DNS_SOA = "@ IN SOA localhost. centos.aynait.com. (0 1D 1H 1W 3H)";
    public static final String DNS_NS = " NS localhost.";
    public static final String DNS_A = " A ";
}
