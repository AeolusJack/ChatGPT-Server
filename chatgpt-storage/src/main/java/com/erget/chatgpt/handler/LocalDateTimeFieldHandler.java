package com.erget.chatgpt.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * 日期字段处理
 */
public class LocalDateTimeFieldHandler extends LocalDateTimeTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        // 保留时间至秒
        ps.setObject(i, parameter.withNano(0));
    }

}
