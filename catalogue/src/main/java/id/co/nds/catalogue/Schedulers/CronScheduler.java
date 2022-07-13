package id.co.nds.catalogue.Schedulers;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import id.co.nds.catalogue.services.ProductServices;

@Component
public class CronScheduler {

    // @Autowired
    // ProductServices productServices;

    static final Logger logger = LogManager.getLogger(CronScheduler.class);
    Integer counterB = 0;

    // @Scheduled(cron = "0/10 * * * * ?")
    public void cronScheduler() throws Exception {
        Integer counterA = 0;
        logger.debug("Start Cron at", Calendar.getInstance().getTime());
        logger.info("Counter A : " + counterA);
        logger.info("Counter B : " + counterB);
        counterA++;
        counterB++;
    }

    // @Scheduled(cron = "${param.scheduler.cron}")
    public void cronParamSchedule() throws Exception {

        Integer counterA = 0;
        logger.debug("Start CronScheduler" + Calendar.getInstance().getTime());
        logger.info("Counter A : " + counterA);
        logger.info("Counter B :" + counterB);
        counterA++;
        counterB++;

    }
}
