package uk.co.virtual1.service;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Tkachenko created on 25.04.16 14:48
 */
@Service
public class Scheduler {
    private final static Logger LOGGER = Logger.getLogger(Scheduler.class);


    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void getConversationDetails() {

    }


}
