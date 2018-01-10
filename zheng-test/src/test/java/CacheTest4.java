import com.cache.entity.Account;
import com.cache.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * 测试切换数据源。
 * 切面先后顺序：缓存》事务
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-applicationContext.xml"})
public class CacheTest4 {

    @Autowired
    private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(CacheTest4.class);

    @Test
    public void test(){
        String key = "fafaxxx";
        Account account;
        for(int i=0;i<2;i++){
            account = this.accountService.getAccountByName(key);
            try {
                this.logger.info("sleeping................");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.accountService.update(key);
        this.logger.info("cache evicted....");
        for(int i=0;i<2;i++){
            account = this.accountService.getAccountByName(key);
            try {
                this.logger.info("sleeping................");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
