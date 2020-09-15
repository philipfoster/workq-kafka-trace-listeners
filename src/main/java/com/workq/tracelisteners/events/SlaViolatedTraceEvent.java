package com.workq.tracelisteners.events;

public class SlaViolatedTraceEvent extends TraceEvent {

    private String responsibleNodeName;
    private long nodeId;

    public SlaViolatedTraceEvent() {
    }

    public String getResponsibleNodeName() {
        return responsibleNodeName;
    }

    public void setResponsibleNodeName(String responsibleNodeName) {
        this.responsibleNodeName = responsibleNodeName;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "SlaViolatedEvent{" +
            "responsibleNode='" + responsibleNodeName + '\'' +
            ", taskId='" + nodeId + '\'' +
            ", parent=" + super.toString() +
            '}';
    }
}

