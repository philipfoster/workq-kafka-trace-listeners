package com.workq.tracelisteners.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.workq.tracelisteners.marshalling.LocalDateTimeDeserializer;
import com.workq.tracelisteners.marshalling.LocalDateTimeSerializer;
import java.time.LocalDateTime;

/**
 * Data model for a generic trace event. IF additional trace types are added, they should inherit from this class.
 */
@JsonPropertyOrder({"ProcessInstanceId", "TimeStamp", "TraceEventType"})
public class TraceEvent {

    private String ProcessInstanceId;
    private LocalDateTime timestamp;
    private TraceEventType traceEventType = TraceEventType.None;

    @JsonProperty("ProcessInstanceId")
    public String getProcessInstanceId() {
        return this.ProcessInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.ProcessInstanceId = processInstanceId;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("TimeStamp")
    public LocalDateTime getTimeStamp() {
        return this.timestamp;
    }

    public void setTimeStamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("TraceEventType")
    public TraceEventType getTraceEventType() {
        return this.traceEventType;
    }

    public void setTraceEventType(TraceEventType traceEventType) {
        this.traceEventType = traceEventType;
    }

    @Override
    public String toString() {
        return "TraceEvent{" +
            "ID='" + ProcessInstanceId + '\'' +
            ", timestamp=" + timestamp +
            ", eventType=" + traceEventType +
            '}';
    }
}
