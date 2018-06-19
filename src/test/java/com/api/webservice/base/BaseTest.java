package com.api.webservice.base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by caihe on 17-8-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/applicationContext.xml"})
@Transactional
@Rollback
public abstract class BaseTest {
    protected Logger log;

    public BaseTest() {
        this.log = LogManager.getLogger(this.getClass().getName());
    }

}
