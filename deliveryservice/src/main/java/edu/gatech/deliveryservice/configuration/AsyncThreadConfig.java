package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.threadexecutor.AsyncThreadPoolLogExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncThreadConfig {

    @Value("${threadCorePoolSize.corePoolSize}")
    private int corePoolSize;
    @Value("${threadCorePoolSize.maxPoolSize}")
    private int maxPoolSize;
    @Value("${threadCorePoolSize.queueCapacity}")
    private int queueCapacity;


    @Bean
    public Executor asyncMakeStoreThread() {//10,30,30,30
        log.info("Open thread pool for making store");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("MakeStore-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }


    @Bean
    public Executor asyncSellItemThread() {//10,30,30,30
        log.info("Open thread pool for selling item");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("SellItem-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Bean
    public Executor asyncMakePilotThread() {//10,30,30,30
        log.info("Open thread pool for making pilot");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("MakePilot-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }


    @Bean
    public Executor asyncAddCustomerThread() {//10,30,30,30
        log.info("Open thread pool for adding Customer");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("AddCustomer-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }



    @Bean
    public Executor asyncMakeDroneThread() {//10,30,30,30
        log.info("Open thread pool for making drone");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("MakeDrone-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }


    @Bean
    public Executor asyncFlyDroneThread() {//10,30,30,30
        log.info("Open thread pool for flying drone");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("FlyDrone-service-");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Bean
    public Executor asyncStartOrderThread() {//10,30,30,30
        log.info("Open thread pool for starting Order");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("StartOrder-service");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Bean
    public Executor asyncRequestItemThread() {//10,30,30,30
        log.info("Open thread pool for requesting item");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("RequestItem-service");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }


    @Bean
    public Executor asyncPurchaseOrderThread() {//10,30,30,30
        log.info("Open thread pool for purchasing Order");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);

        executor.setThreadNamePrefix("PurchaseOrder-service");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }


    @Bean
    public Executor asyncCancelOrderThread() {//10,30,30,30
        log.info("Open thread pool for canceling Order");
        //ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new AsyncThreadPoolLogExecutor();
        //Set CorePoolSize
        executor.setCorePoolSize(corePoolSize);
        //Set MaxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        //Set setQueueCapacity
        executor.setQueueCapacity(queueCapacity);


        executor.setThreadNamePrefix("CancelOrder-service");

        // rejection-policy：when pool reach max size, how to deal with the new tasks
        // use main thread to deal with the task
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

}
