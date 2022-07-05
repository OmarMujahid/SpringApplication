package com.accumed.ae.history;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "History")
public class History {
    @Id
    @GeneratedValue
    private Long requestID;
    private String ipAddress;
    private String method;
    private Integer statusCode;
    @Column(name = "requestBody", length = 1000)
    private String requestBody;
    private java.time.LocalDateTime timeOfRequest;
    @Column(name = "responseBody", length = 1000)
    private String responseBody;
    private String uri;
    private String type;
    private String clientID;


    public History(){}
    public History(String method, String ipAddress, Integer statusCode, String requestBody,
                   java.time.LocalDateTime timeOfRequest, String responseBody, String uri, String type, String clientID) {
        this.ipAddress = ipAddress;
        this.method = method;
        this.statusCode = statusCode;
        this.requestBody = requestBody;
        this.timeOfRequest = timeOfRequest;
        this.responseBody = responseBody;
        this.uri = uri;
        this.type = type;
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
        this.requestID = requestID;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public
    java.time.LocalDateTime getTimeOfRequest() {
        return timeOfRequest;
    }

    public void setTimeOfRequest(
            java.time.LocalDateTime timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}

