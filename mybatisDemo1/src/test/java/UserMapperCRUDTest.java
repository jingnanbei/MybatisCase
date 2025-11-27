import com.project.mapper.UserMapper;
import com.project.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class UserMapperCRUDTest {
    InputStream inputStream = null;
    SqlSession sqlSession = null;
    UserMapper userMapper = null;
    @Before
    public void before() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);
        sqlSession = build.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testUpdate(){
        User user = new User(8, "程序员", "女", "深圳",new Date(230000));
        userMapper.update(user);
        sqlSession.commit();
    }

    @Test
    public void testDelete(){
        userMapper.delete(7);
        sqlSession.commit();
    }

    @Test
    public void testFindById(){
        User user = userMapper.findById(1);
        System.out.println(user);
    }
}
