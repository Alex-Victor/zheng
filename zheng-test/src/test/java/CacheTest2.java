import com.alibaba.fastjson.JSON;
import com.cache.entity.Account;
import com.cache.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试spring cache的@Cacheable和@CacheEvict、@CacheExpire注解
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-applicationContext.xml"})
public class CacheTest2 {
    @Autowired
    private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(CacheTest2.class);
    @Test
    public void test(){
        String name = "spring-cache-redis";
        Account account;
        for(int i=0;i<10;i++){
            account = this.accountService.getAccountByName(name);
            this.logger.info(JSON.toJSONString(account));
        }
        this.accountService.update(name);//evict from cache
        for(int i=0;i<10;i++){
            account = this.accountService.getAccountByName(name);
            this.logger.info(JSON.toJSONString(account));
        }
    }
}
