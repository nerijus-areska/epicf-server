package com.blank.epicfserver.payload;

public class WebsocketMessage {
    private String type;
    private Object payload;

    public WebsocketMessage(String type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }
}
