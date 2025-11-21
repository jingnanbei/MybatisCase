import com.project.mapper.UserMapper;
import com.project.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {
    @Test
    public void testFindAll() throws IOException {
        /***
         * （1）读取核心配置文件
         * （2）创建SqlSessionFactoryBuilder对象
         * （3）SqlSessionFactoryBuilder对象获取SqlSessionFactory对象
         * （4）SqlSessionFactory对象获取SqlSession对象
         * （5）SqlSession对象获取代理对象
         * （6）代理对象执行方法
         * （7）释放资源
         */
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resource);
        SqlSession sqlSession = build.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findAll();
        list.forEach(System.out::println);
        sqlSession.close();
        resource.close();
        /***
         * MyBatis核心对象
         * SqlSessionFactoryBuilder:SqlSession工厂构建者对象，使用构造者模式创建SqlSession工厂对象。
         * SqlSession工厂:使用工厂模式创建SqlSession对象。
         * SqlSession:该对象可以操作数据库，也可以使用动态代理模式创建持久层接口的代理对象操作数据库。
         * Mapper:持久层接口的代理对象，他具体实现了持久层接口，用来操作数据库。
         *
         * MyBatis工作流程
         * 创建SqlSessionFactoryBuilder对象
         * SqlSessionFactoryBuilder对象构建了SqlSessionFactory对象：构造者模式
         * SqlSessionFactory对象生产了SqlSession对象：工厂模式
         * SqlSession对象创建了持久层接口的代理对象：动态代理模式
         * 代理对象操作数据库
         */
    }

    /**
     * 使用sqlSession操作数据库
     * @throws IOException
     */
    @Test
    public void testFindAll2() throws IOException{
        InputStream resource = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resource);
        SqlSession sqlSession = build.openSession();
        List<User> list = sqlSession.selectList("com.project.mapper.UserMapper.findAll");
        list.forEach(System.out::println);
        sqlSession.close();
        resource.close();
    }
}
