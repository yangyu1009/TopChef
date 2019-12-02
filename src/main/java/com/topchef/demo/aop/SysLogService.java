package com.topchef.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogService {
    @Autowired
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void save(SysLog syslog) {
//        StringBuffer sql = new StringBuffer("insert into sys_log ");
//        sql.append("(id,username,operation,time,method,params,ip,create_time) ");
//        sql.append("values(seq_sys_log.nextval,:username,:operation,:time,:method,");
//        sql.append(":params,:ip,:createTime)");
        String sql = "insert into sys_log(username,operation,time,method,params,ip,create_time) values(?,?,?,?,?,?,?) ";
        jdbcTemplate.update(sql, syslog.getUsername(), syslog.getOperation(), syslog.getTime(),syslog.getMethod(), syslog.getParams(), syslog.getIp(), syslog.getCreateTime());
    }
}
