package com.houlu.java.spring.data.datasource.proxy;

import com.mysql.jdbc.ResultSetImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lu Hou
 * @date 2017/9/23
 * @time 下午8:18
 */
public class PrepareStatementProxy extends ProxyTemplate<PreparedStatement> {


    @Override
    public Object invokePoint(Object proxy, Method method, Object[] args) throws Exception {
        ResultSet resultSet = null;
        for (PreparedStatement preparedStatement : getProxyContext().getPreparedStatementList()) {
            resultSet = (ResultSet) method.invoke(preparedStatement, args);
            if (null != resultSet) {
                getProxyContext().getResultSetList().add(resultSet);
            }
        }
        return ProxyFactory.getResultSetProxy(null, getProxyContext());

    }
}
