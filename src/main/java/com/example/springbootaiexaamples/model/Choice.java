package com.example.springbootaiexaamples.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choice {
    private int index;
    private Message message;
    private Object logprobs; // Can be replaced with a specific type if needed
    @JsonProperty("finish_reason")
    private String finishReason;

    // Constructor
    public Choice() {}

    public Choice(int index, Message message, Object logprobs, String finishReason) {
        this.index = index;
        this.message = message;
        this.logprobs = logprobs;
        this.finishReason = finishReason;
    }

    // Getters and Setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "index=" + index +
                ", message=" + message +
                ", logprobs=" + logprobs +
                ", finishReason='" + finishReason + '\'' +
                '}';
    }
}
