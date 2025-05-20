package com.example.asyncdocpro.dto;

public class DocumentResponse {
    private String metadata;
    private String virusScanResult;
    private String message;

    public DocumentResponse() {}

    public DocumentResponse(String metadata, String virusScanResult, String message) {
        this.metadata = metadata;
        this.virusScanResult = virusScanResult;
        this.message = message;
    }

    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }

    public String getVirusScanResult() { return virusScanResult; }
    public void setVirusScanResult(String virusScanResult) { this.virusScanResult = virusScanResult; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}