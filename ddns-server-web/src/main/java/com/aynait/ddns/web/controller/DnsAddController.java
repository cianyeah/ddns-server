package com.aynait.ddns.web.controller;

import com.aynait.ddns.core.exception.DSErrorCodes;
import com.aynait.ddns.core.manager.DnsAddManager;
import com.aynait.ddns.web.util.ErrorTextUtil;
import com.aynait.ddns.web.util.RequestValidator;
import com.aynait.ddns.web.vo.ResultVO;
import com.aynait.ddns.web.vo.UpdateARecordVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tianya on 2017/10/10.
 */
@Controller
public class DnsAddController {

    @Resource
    private DnsAddManager dnsAddManager;

    @Resource
    private HttpServletRequest request;

    @RequestMapping(value = "addDnsARecord")
    @ResponseBody
    public ResultVO addDnsARecord(UpdateARecordVO updateARecordVO) {
        String domain = updateARecordVO.getDomain();
        String ip = updateARecordVO.getIp();
        String token = updateARecordVO.getToken();

        if (StringUtils.isBlank(ip)) {
            ip = this.getRemoteIp();
        }

        if (StringUtils.isBlank(domain)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_DOMAIN_ERROR);
        }
        if (!RequestValidator.checkIp(ip)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_IP_ERROR);
        }
        if (!RequestValidator.checkToken(token)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_TOKEN_ERROR);
        }

        try {
            dnsAddManager.addARecord(domain, ip);
            return ResultVO.newSuccessResult();
        } catch (Exception e) {
            return ResultVO.newErrorResult(ErrorTextUtil.getErrorText(e));
        }
    }

    private String getRemoteIp() {
        String ip = request.getHeader("Cdn-Src-Ip");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
