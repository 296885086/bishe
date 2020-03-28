package com.ljq.bishe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuditMapper {
    public List allAuditList(String stuid);
    public List teaAllAuditList(String teaid);
    public void giveBack(String auditid);
    public void sureAudit(@Param("auditid") String auditid,
                          @Param("editScorse") String editScorse,
                          @Param("replybody") String replybody,
                          @Param("stuid") String stuid,
                          @Param("workid") String workid);
    public void deleteAudit(String auditid);
}
