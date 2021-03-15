package com.example.listeners;

import org.kie.api.event.process.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessTraceEventListener implements ProcessEventListener {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProcessTraceEventListener.class);

    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        LOGGER.info("BeforeNodeTriggered: " + event.toString());
    }

    public void beforeProcessStarted(ProcessStartedEvent event) {
        LOGGER.info("BeforeProcessStarted: " + event.toString());
    }

    public void afterProcessCompleted(ProcessCompletedEvent event) {
        LOGGER.info("AfterProcessCompleted: " + event.toString());
    }

    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        LOGGER.info("BeforeProcessCompleted: " + event.toString());
    }

    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        LOGGER.info("AfterNodeLeft: " + event.toString());
    }

    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        LOGGER.info("beforeEvaluateContextEntry");
    }

    public void afterProcessStarted(ProcessStartedEvent event) {
        LOGGER.info("AfterProcessStarted: " + event.toString());
    }

    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        LOGGER.info("AfterVariableChanged: " + event.toString());
    }

    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        LOGGER.info("BeforeNodeLeft: " + event.toString());
    }

    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        LOGGER.info("BeforeVariableChanged: " + event.toString());
    }

}