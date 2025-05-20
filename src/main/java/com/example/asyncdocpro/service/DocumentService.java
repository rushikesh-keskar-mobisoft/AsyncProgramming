package com.example.asyncdocpro.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class DocumentService {

    private final ExecutorService executor;

    public DocumentService(ExecutorService executor) {
        this.executor = executor;
    }

    public CompletableFuture<String> extractMetadata(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Extracting metadata for " + fileName);
            sleep(3000);
            return "Metadata: Title=Report, Pages=12";
        }, executor).completeOnTimeout("Metadata extraction timeout!", 2, TimeUnit.SECONDS)
          .exceptionally(ex -> "Metadata failed: " + ex.getMessage());
    }

    public CompletableFuture<String> scanForVirus(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Scanning for virus: " + fileName);
            sleep(2000);
            if (Math.random() < 0.3) {
                throw new RuntimeException("Virus scan engine error");
            }
            return "Scan Result: No virus found";
        }, executor).completeOnTimeout("Scan timeout!", 3, TimeUnit.SECONDS)
          .exceptionally(ex -> "Scan failed: " + ex.getMessage());
    }



    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}