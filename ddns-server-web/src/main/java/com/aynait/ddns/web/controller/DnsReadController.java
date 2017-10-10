package com.aynait.ddns.web.controller;

import com.aynait.ddns.core.manager.DnsReadManager;
import com.aynait.ddns.core.model.DnsARecord;
import com.aynait.ddns.web.util.ErrorTextUtil;
import com.aynait.ddns.web.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Tianya on 2017/10/10.
 */
@Controller
@RequestMapping(value = "dns/read")
public class DnsReadController {

    @Resource
    private DnsReadManager dnsReadManager;

    @RequestMapping(value = "getDnsARecord")
    @ResponseBody
    public ResultVO getDnsARecord() {
        try {
            Set<DnsARecord> dnsARecordSet = dnsReadManager.readARecord();
            return ResultVO.newSuccessResult(dnsARecordSet);
        } catch (Exception e) {
            return ResultVO.newErrorResult(ErrorTextUtil.getErrorText(e));
        }
    }
}
