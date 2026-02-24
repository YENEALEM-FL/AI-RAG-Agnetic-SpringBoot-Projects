package com.lab.springailmstudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Choice {

    private int index;
    private Message message;
    private Object logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

}
