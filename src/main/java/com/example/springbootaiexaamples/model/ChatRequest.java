package com.example.springbootaiexaamples.model;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ChatRequest {
    @Value("${lmstudio.ai.model}")
    private String model;
    private List<Message> messages;
    private double temperature;
    private int maxTokens;
    private boolean stream;

    // Constructor
    public ChatRequest() {}

    public ChatRequest(String model, List<Message> messages, double temperature, int maxTokens, boolean stream) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
        this.stream = stream;
    }

    // Getters and Setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    // toString method
    @Override
    public String toString() {
        return "ChatRequest{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                ", temperature=" + temperature +
                ", maxTokens=" + maxTokens +
                ", stream=" + stream +
                '}';
    }
}
