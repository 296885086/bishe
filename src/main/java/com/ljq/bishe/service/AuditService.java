package com.ljq.bishe.service;

import java.util.List;

public interface AuditService {
    public List allAuditList(String stuid);
    public List teaAllAuditList(String teaid);
    public void giveBack(String auditid);
    public void sureAudit(String auditid,String editScorse,String replybody,String stuid,String workid);
    public void deleteAudit(String auditid);
}
