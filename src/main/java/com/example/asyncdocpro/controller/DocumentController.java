package com.example.asyncdocpro.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.asyncdocpro.dto.DocumentResponse;
import com.example.asyncdocpro.service.DocumentService;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/process")
    public DocumentResponse processDocument(@RequestParam(defaultValue = "report.pdf") String file) throws Exception {
        CompletableFuture<String> metadataFuture = documentService.extractMetadata(file);
        CompletableFuture<String> scanFuture = documentService.scanForVirus(file);

        CompletableFuture.allOf(metadataFuture, scanFuture).join();

        String metadata = metadataFuture.get();
        String scanResult = scanFuture.get();

        return new DocumentResponse(metadata, scanResult, "Document processed.");
    }
}