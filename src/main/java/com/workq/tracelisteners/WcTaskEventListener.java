package com.workq.tracelisteners;

import com.workq.tracelisteners.events.TaskTraceEvent;
import com.workq.tracelisteners.events.TraceEventType;
import com.workq.tracelisteners.messaging.KafkaPublisher;
import com.workq.tracelisteners.messaging.MessagePublisher;
import com.workq.tracelisteners.model.TaskChangeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.jbpm.services.task.events.DefaultTaskEventListener;
import org.kie.api.task.TaskEvent;
import org.kie.api.task.model.OrganizationalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Extract person who started a task, who assigned to, who claimed it, start and end date.
 * Extract SLA if possible. Can PAM kick out event to notify if SLA is past due. Explore how to do this.
 */
@SuppressWarnings("unused")
public class WcTaskEventListener extends DefaultTaskEventListener {

    private static final MessagePublisher publisher = new KafkaPublisher();
    private static final Logger LOGGER = LoggerFactory.getLogger(WcTaskEventListener.class);

    @Override
    public void afterTaskActivatedEvent(TaskEvent event) {
        LOGGER.info("Received task activated {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.ACTIVATED);
        publisher.publishMessage(message);
    }

    /**
     * This one
     * @param event
     */
    @Override
    public void afterTaskClaimedEvent(TaskEvent event) {
        LOGGER.info("Received task claimed {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.CLAIMED);
        publisher.publishMessage(message);
    }

    @Override
    public void afterTaskStartedEvent(TaskEvent event) {
        LOGGER.info("Received task started {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.STARTED);
        publisher.publishMessage(message);
    }

    /**
     * This one
     * @param event
     */
    @Override
    public void afterTaskCompletedEvent(TaskEvent event) {
        LOGGER.info("Received task completed event {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.COMPLETED);
        publisher.publishMessage(message);
    }

    @Override
    public void beforeTaskFailedEvent(TaskEvent event) {
        LOGGER.info("Received task failed {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.FAILED);
        publisher.publishMessage(message);
    }
//
//    @Override
//    public void beforeTaskReleasedEvent(TaskEvent event) {
//        super.beforeTaskReleasedEvent(event);
//    }
//
//    @Override
//    public void beforeTaskNominatedEvent(TaskEvent event) {
//        super.beforeTaskNominatedEvent(event);
//    }
//
//    @Override
//    public void beforeTaskUpdatedEvent(TaskEvent event) {
//
//    }

    @Override
    public void beforeTaskReassignedEvent(TaskEvent event) {
        LOGGER.info("Received task reassigned {}", event);
        TaskTraceEvent message = buildMessage(event, TaskChangeType.REASSIGNED);
        publisher.publishMessage(message);
    }

    public TaskTraceEvent buildMessage(TaskEvent event, TaskChangeType type) {
        TaskTraceEvent result = new TaskTraceEvent();

        result.setTaskId(String.valueOf(event.getTask().getId()));
        result.setTaskName(event.getTask().getName());
        result.setOwner(event.getTaskContext().getUserId());
        result.setTaskChangeType(type);
        result.setTimeStamp(LocalDateTime.now());

        result.setProcessInstanceId(String.valueOf(event.getTask().getTaskData().getProcessInstanceId()));
        result.setTraceEventType(TraceEventType.TaskTraceEvent);

        if (event.getTask().getPeopleAssignments() != null &&
            event.getTask().getPeopleAssignments().getTaskInitiator() != null) {

            result.setTaskInitiator(event.getTask().getPeopleAssignments().getTaskInitiator().getId());
        }

        List<String> potentialOwnerIds = event.getTask().getPeopleAssignments().getPotentialOwners().stream()
            .map(OrganizationalEntity::getId)
            .collect(Collectors.toList());

        result.setPotentialOwners(potentialOwnerIds);
        return result;
    }
}
