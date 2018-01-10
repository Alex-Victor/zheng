import com.cache.config.CacheTemplate;
import com.cache.config.DbCallback;
import com.cache.dao.CacheDao;
import com.cache.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * 测试手动调用CacheTemplate
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-applicationContext.xml"})
public class CacheTest3 {

    @Autowired
    private CacheTemplate cacheTemplate;
    @Autowired@Qualifier("mockDao")
    private CacheDao<Account> mockDao;
    private Logger logger = LoggerFactory.getLogger(CacheTest3.class);

    @Test
    public void test(){
        String key = "my-test";
        Account account;
        for(int i=0;i<10;i++){
            account = this.cacheTemplate.get(key, 3, new DbCallback<Account>() {
                @Override
                public Account doInDb() {
                    return mockDao.get(100L);
                }
            });
        }
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            this.logger.error(e.getMessage(), e);
        }
        //this.cacheTemplate.evict(key);
//        this.logger.info("data evicted from cache");
        for(int i=0;i<10;i++){
            account = this.cacheTemplate.get(key, 30, new DbCallback<Account>() {
                @Override
                public Account doInDb() {
                    return mockDao.get(100L);
                }
            });
        }

    }
}
