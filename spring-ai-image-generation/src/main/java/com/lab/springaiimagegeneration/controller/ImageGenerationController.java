package com.lab.springaiimagegeneration.controller;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ImageGenerationController {
    OpenAiImageModel openAiImageModel;

    public ImageGenerationController(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/image_generation")
    public String imageGeneration(@RequestParam(value = "prompt", defaultValue = "A beautiful sunset over the ocean") String prompt){
        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder().N(1).width(1024).height(1024).quality("hd").build()));

        return imageResponse.getResult().getOutput().getUrl();
    }
}
