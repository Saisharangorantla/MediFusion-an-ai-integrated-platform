package com.yourorg.telemedicine.videocall.entity;

public class SignalMessage {
    private String from;
    private String type;
    private String payload;

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
}
