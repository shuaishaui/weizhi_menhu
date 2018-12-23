package com.wizz.demo;

import com.wizz.demo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserDao userMapper;

    @Test
    @Rollback
    public void contextLoads() throws Exception{
        User u1=userMapper.getById(1);
        Assert.assertEquals("wyk",u1.getName());

    }

}
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class DemoApplicationTests {

    @Autowired
    private UserDao userMapper;

    @Test
    @Rollback
    public void testUserMapper() throws Exception {

    }
}

