package com.lab.springaiaudiotranscription.controller;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OpenAiTranscriptionController {

    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    public OpenAiTranscriptionController(OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel) {
        this.openAiAudioTranscriptionModel = openAiAudioTranscriptionModel;
    }

    @Value("classpath:audiofile.wav")
    Resource resource;

    @GetMapping("/transcription")
    public String transcribe(){

        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .temperature(0f)
                .build();

        AudioTranscriptionPrompt audioTranscriptionPrompt = new AudioTranscriptionPrompt(resource,options);

        AudioTranscriptionResponse audioTranscriptionResponse = openAiAudioTranscriptionModel
                .call(audioTranscriptionPrompt);

        return audioTranscriptionResponse.getResult().getOutput();
    }

}
