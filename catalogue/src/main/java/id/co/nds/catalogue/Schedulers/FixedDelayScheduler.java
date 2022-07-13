package id.co.nds.catalogue.Schedulers;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayScheduler {
    static final Logger logger = LogManager.getLogger(FixedDelayScheduler.class);
    Integer counterB = 0;

    // @Scheduled(fixedDelay = 10000)
    public void fixedDelayScheduler() throws Exception {
        Integer counterA = 0;
        logger.debug("Start FixedDelayScheduler at " + Calendar.getInstance().getTime());
        logger.info("Counter A : " + counterA);
        logger.info("Counter B : " + counterB);
        counterA++;
        counterB++;
    }

    // @Scheduled(fixedDelayString = "{param.scheduler.fixedelay.ms}")
    public void fixedDelayParamSchedule() throws Exception {
        Integer counterA = 0;
        logger.debug("Start FiedDelaySchedule at " + Calendar.getInstance().getTime());
        logger.info("Counter-A : " + counterA);
        logger.info("Counter-B :" + counterB);
        counterA++;
        counterB++;

        Thread.sleep(15000);
    }
    
}
