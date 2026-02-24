package com.lab.springaitexttospeech.controller;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
public class TextToSpeachController {

    SpeechModel speechModel;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public TextToSpeachController(SpeechModel speechModel) {
        this.speechModel = speechModel;
    }

    @GetMapping("/text_to_speech")

    public byte[] getSpeechForText(@RequestParam(value = "message", defaultValue = "Hello, World! This is a sample sentence.") String message) {

    return speechModel
        .call(new SpeechPrompt(message,
                OpenAiAudioSpeechOptions.builder()
                        .input(message)
                        .speed(1.25f)
                        .build()))
        .getResult()
        .getOutput();
    }

    @GetMapping("/text-to-speech-audio-streaming")
    public Flux<byte[]> getStreaming() throws IOException{
        String content = Objects.requireNonNull(getClass().getResourceAsStream("/textForSpeech.txt")).toString();
        var openAiAudioKey = OpenAiAudioApi.builder().apiKey(apiKey).build();
        var openAiAudioSpeechModel = new OpenAiAudioSpeechModel(openAiAudioKey);

        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .speed(1.23f)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .model(OpenAiAudioApi.TtsModel.TTS_1.value)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt(content,options);

        Flux<SpeechResponse> responseStream = openAiAudioSpeechModel.stream(speechPrompt);

        return responseStream.flatMap(speechResponse -> Flux.just(speechResponse.getResult().getOutput()));


    }


}
