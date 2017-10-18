package com.aynait.ddns.web.controller;

import com.aynait.ddns.core.exception.DSErrorCodes;
import com.aynait.ddns.core.manager.DnsDeleteManager;
import com.aynait.ddns.web.util.ErrorTextUtil;
import com.aynait.ddns.web.util.TokenValidator;
import com.aynait.ddns.web.vo.ResultVO;
import com.aynait.ddns.web.vo.UpdateARecordVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Tianya on 2017/10/11.
 */
@Controller
public class DnsDeleteController {

    @Resource
    private DnsDeleteManager dnsDeleteManager;

    @RequestMapping(value = "deleteDnsARecord")
    @ResponseBody
    public ResultVO deleteDnsARecord(UpdateARecordVO updateARecordVO) {
        String domain = updateARecordVO.getDomain();
        String ip = updateARecordVO.getIp();
        String token = updateARecordVO.getToken();

        if (StringUtils.isBlank(domain)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_DOMAIN_ERROR);
        }
        if (StringUtils.isBlank(ip)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_IP_ERROR);
        }
        if (!TokenValidator.checkToken(token)) {
            return ResultVO.newErrorResult(DSErrorCodes.RECORD_TOKEN_ERROR);
        }

        try {
            dnsDeleteManager.deleteARecord(domain, ip);
            return ResultVO.newSuccessResult();
        } catch (Exception e) {
            return ResultVO.newErrorResult(ErrorTextUtil.getErrorText(e));
        }
    }
}
