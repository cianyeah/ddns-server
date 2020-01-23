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
     * 编码格式
     */
    public static final String UTF8 = "UTF-8";
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * zone文件
     */
    public static final String AYNAIT_COM_PATH = "/var/named/";
    public static final String AYNAIT_COM_NAME = "ddns.aynait.com.zone";
    public static final String AYNAIT_COM_FILE = AYNAIT_COM_PATH + AYNAIT_COM_NAME;

    /**
     * zone文件配置
     */
    public static final String DNS_TTL = "$TTL 86400";
    public static final String DNS_SOA = "@ IN SOA localhost. bwh.aynait.com. (0 1D 1H 1W 3H)";
    public static final String DNS_NS = " NS localhost.";
    public static final String DNS_A = " A ";

    /**
     * linux命令
     */
    public static final String EXEC_STOP_NAMED = "systemctl stop named";
    public static final String EXEC_START_NAMED = "systemctl start named";
}
