package com.pines.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tester {
    private final static Logger logger = LoggerFactory.getLogger(Tester.class);
    public static void logTest(){
        logger.info("App started");
        logger.debug("Debugging something");
        logger.warn("Warning issued");
    }
}
