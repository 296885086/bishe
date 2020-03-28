package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.AuditMapper;
import com.ljq.bishe.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditMapper ap;

    @Override
    public List allAuditList(String stuid) {
        return ap.allAuditList(stuid);
    }

    @Override
    public List teaAllAuditList(String teaid) {
        return ap.teaAllAuditList(teaid);
    }

    @Override
    public void giveBack(String auditid) {
        ap.giveBack(auditid);
    }

    @Override
    public void sureAudit(String auditid, String editScorse, String replybody,String stuid,String workid) {
        ap.sureAudit(auditid,editScorse,replybody,stuid,workid);
    }

    @Override
    public void deleteAudit(String auditid) {
        ap.deleteAudit(auditid);
    }
}
