package com.workq.tracelisteners.events;

import com.workq.tracelisteners.model.TaskChangeType;
import java.util.List;

public class TaskTraceEvent extends TraceEvent {

    private String taskName;
    private TaskChangeType taskChangeType;
    private String taskId;
    private String taskInitiator;
    private List<String> potentialOwners;
    private String owner;

    public TaskTraceEvent() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskChangeType getTaskChangeType() {
        return taskChangeType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTaskChangeType(TaskChangeType state) {
        this.taskChangeType = state;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskInitiator() {
        return taskInitiator;
    }

    public void setTaskInitiator(String taskInitiator) {
        this.taskInitiator = taskInitiator;
    }

    public List<String> getPotentialOwners() {
        return potentialOwners;
    }

    public void setPotentialOwners(List<String> potentialOwners) {
        this.potentialOwners = potentialOwners;
    }

    @Override
    public String toString() {
        return "TaskTraceEvent{" +
            "taskName='" + taskName + '\'' +
            ", taskChangeType=" + taskChangeType +
            ", taskId='" + taskId + '\'' +
            ", taskInitiator='" + taskInitiator + '\'' +
            ", potentialOwners=" + potentialOwners +
            ", owner='" + owner + '\'' +
            '}';
    }
}
