package id.co.nds.catalogue.Schedulers;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FixedRateScheduler {
    static final Logger logger = LogManager.getLogger(FixedRateScheduler.class);
    Integer counterB = 0;

    // @Scheduled(fixedRate = 10000)
    public void fixedRateScheduler() throws Exception {

        Integer counterA = 0;
        logger.debug("Start FixedRateScheduler" + Calendar.getInstance().getTime());
        logger.info("Counter-A :"+ counterA);
        logger.info("Counter-B :"+ counterB);
        counterA++;
        counterB++;

        Thread.sleep(15000);
    }

    // @Scheduled(fixedRateString = "${param.scheduler.fixedrate.ms}")
    public void fixedRateParamSchedule() throws Exception{
        Integer counterA = 0; 
        logger.debug("Start FixedRateScheduler"+ Calendar.getInstance().getTime());
        logger.info("Counter-A : " + counterA);
        logger.info("Counter-B : " + counterB);
        counterA++;
        counterB++;   

        // Thread.sleep(15000);
    }
            
}
