import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.order.ServerOrderApplication;
import com.order.entity.User;
import com.order.mapper.UserMapper;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ServerOrderApplication.class }) // 指定启动类
public class ApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testOne() {
		userMapper.insert(new User(4, "张三7"));
	}

	@Test
	public void test5() {
		Example example = new Example(User.class);
		example.createCriteria().andLike("name", "张%");
		System.out.println(JSONObject.toJSONString(userMapper.selectByExample(example)));
	}

	@Test
	public void test2() {
		System.out.println(JSONObject.toJSONString(userMapper.selectAll()));
	}

	@Test
	public void test3() {
		userMapper.delete(new User(1, "涨势难分"));
	}

	@Test
	public void test4() {
		userMapper.updateByPrimaryKey(new User(1, "涨势难分"));
	}

}