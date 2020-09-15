package com.workq.tracelisteners.messaging;

import com.workq.tracelisteners.events.ProcessTraceEvent;
import com.workq.tracelisteners.events.SlaViolatedTraceEvent;
import com.workq.tracelisteners.events.TaskTraceEvent;


/**
 * Provides the API for publishing a {@link com.workq.tracelisteners.events.TraceEvent} to some message broker asynchronously
 */
public interface MessagePublisher {

    void publishMessage(ProcessTraceEvent event) throws PublishingFailedException;

    void publishMessage(SlaViolatedTraceEvent event) throws PublishingFailedException;

    void publishMessage(TaskTraceEvent event) throws PublishingFailedException;

}
