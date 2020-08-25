package com.workq.tracelisteners.messaging;

import com.workq.tracelisteners.events.ProcessTraceEvent;


/**
 * Provides the API for publishing a {@link com.workq.tracelisteners.events.TraceEvent} to some message broker asynchronously
 */
public interface MessagePublisher {

    //    void publishMessage(RuleTraceEvent event) throws PublishingFailedException;
//    void publishMessage(WorkingMemoryTraceEvent event) throws PublishingFailedException;
    void publishMessage(ProcessTraceEvent event) throws PublishingFailedException;

}
