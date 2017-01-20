package org.suren.autotest.platform.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class DbUtils {
    
    //GC不理static
    private static SqlSessionFactory factory=null;
    public static SqlSessionFactory getSqlSessionFactory(){
        if(factory==null){
        // 获得环境配置文件流
        InputStream config = DbUtils.class.getClassLoader().getResourceAsStream("mybatis.xml");
        // 创建sql会话工厂
        factory = new SqlSessionFactoryBuilder().build(config);
        }
        return factory;
    }
    
    //获得会话
    public static SqlSession getSession(){
        return getSqlSessionFactory().openSession(true);
    }
    
    /**
     * 获得得sql会话
     * @param isAutoCommit 是否自动提交，如果为false则需要sqlSession.commit();rollback();
     * @return sql会话
     */
    public static SqlSession getSession(boolean isAutoCommit){
        return getSqlSessionFactory().openSession(isAutoCommit);
    }
}