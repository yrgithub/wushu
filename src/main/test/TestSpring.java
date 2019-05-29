import com.xiongdiyibeizi.config.ServerPathConfig;
import com.xiongdiyibeizi.shiro.FilterFactoryBeanMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.LinkedHashMap;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class TestSpring {

    @Autowired
    private ServerPathConfig serverPathConfig;

    @Autowired
    private FilterFactoryBeanMap filterFactoryBeanMap;
    @Test
    public void testd()
    {
        System.out.println(serverPathConfig.toString());

        System.out.println(serverPathConfig.getServerPath());
        System.out.println(serverPathConfig.getModuleName());
        System.out.println(serverPathConfig.getUploadPath());
    }

    @Test
    public void test2()
    {
        LinkedHashMap hashMap = filterFactoryBeanMap.getShiroFilterChainMap();
        System.out.println(hashMap);
    }
}
