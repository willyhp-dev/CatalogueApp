package id.co.nds.catalogue.Schedulers;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import id.co.nds.catalogue.entities.ProductEntity;

import id.co.nds.catalogue.repos.ParamRepo;
import id.co.nds.catalogue.services.ProductServices;

@Component
public class CheckQuantityScheduler implements SchedulingConfigurer {

    @Autowired
    private ParamRepo paramRepo;

    @Autowired
    private ProductServices productServices;

    private static final String PARAM_KEY = "CRON_10_Seconds";

    Integer counter = 0;

    private static ScheduledTaskRegistrar scheduledTaskRegistrar;
    @SuppressWarnings("rawtypes")
    private static ScheduledFuture future;

    static final Logger logger = LogManager
            .getLogger(CheckQuantityScheduler.class);
    private static String cronVal = "";
    public static boolean stopScheduler = false;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix(
                "DbParamaterScheduler-ThreadPoolTaskSchedule - ##");
        scheduler.setPoolSize(2); // paralel
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (scheduledTaskRegistrar == null) {
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (taskRegistrar.getScheduler() == null) {
            taskRegistrar.setScheduler(poolScheduler());
        }

        reloadParamScheduler();

        CronTrigger croneTrigger = new CronTrigger(cronVal,
                TimeZone.getDefault());

        future = taskRegistrar.getScheduler().schedule(() -> scheduleCronTask(),
                croneTrigger);
    }

    public void scheduleCronTask() {
        logger.debug("scheduleCron: run scheduler with configuration -> {"
                + cronVal + "}...");

        try {
            List<ProductEntity> products = productServices
                    .findProductLessThan5Qauntity();
            // ResponseModel response = new ResponseModel();
            // response.setData(products);
            logger.debug(
                    "Start Scheduler at " + Calendar.getInstance().getTime());

            logger.info("Product Which less than 5 quantity");
            for (int i = 0; i < products.size(); i++) {
                logger.info(products.get(i).getName()
                        + " only have " + products.get(i).getQuantity()
                        + " left ");

            }
            logger.info("Please add More there products ");

            /*
             * here, put the business logic.
             */

            // call serviceGantiStatusCicilanAktif
            // call serviceGantiStatusCicJatuhTempo
            // call serviceGantiStatusCicTerlambat
            // call serviceHitungDenda

            logger.info("finish executing task......");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stopScheduler) {
                cancelTasks(true);
                scheduledTaskRegistrar = null;
                logger.debug("Stopping scheduler Task...");

            }
        }

        reloadParamScheduler();

    }

    private void reloadParamScheduler() {
        if (cronVal.trim().equalsIgnoreCase("")) {
            cronVal = paramRepo.findById(PARAM_KEY).orElse(null)
                    .getParamValue();
        } else {
            String newCronFromDb = "";
            newCronFromDb = paramRepo.findById(PARAM_KEY).orElse(null)
                    .getParamValue();

            if (!stopScheduler && !newCronFromDb.equalsIgnoreCase(cronVal)) {
                cronVal = newCronFromDb;

                // reload new scheduler
                logger.info(
                        "scheduleCron: Next execution time of this taken from cron expression -> {"
                                + newCronFromDb + "}");
                cancelTasks(false);
                activateScheduler();
            }
        }
    }

    /**
     * @param mayInterruptIfRunning {@code true} if the thread executing this
     *                              task should be interrupted; otherwise,
     *                              in-progress tasks are allowed to complete
     */
    public void cancelTasks(boolean mayInterruptIfRunning) {
        logger.info("###Cancelling all tasks...");
        future.cancel(mayInterruptIfRunning); // set to false if you want the
                                              // running task to be completed
                                              // first.
    }

    public void activateScheduler() {
        logger.info("###Reload Scheduler..");
        configureTasks(scheduledTaskRegistrar);
    }

    public static void shutdownScheduler() {
        logger.info("###ShuttingDown Scheduler..");
        stopScheduler = true;
    }

}
