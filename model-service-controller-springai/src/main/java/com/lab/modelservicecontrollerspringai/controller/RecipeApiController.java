package com.lab.modelservicecontrollerspringai.controller;

import com.lab.modelservicecontrollerspringai.model.Answer;
import com.lab.modelservicecontrollerspringai.model.Question;
import com.lab.modelservicecontrollerspringai.service.GetRecipeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class RecipeApiController {

        @Autowired
        private final GetRecipeGenerator getRecipeGenerator;

        public RecipeApiController(GetRecipeGenerator getRecipeGenerator) {
            this.getRecipeGenerator = getRecipeGenerator;
        }

        @GetMapping("/generate-recipe")
        public Answer getRecipeController(@RequestParam(value="question",
                defaultValue="Give me simple recope from my food") String question){
            return getRecipeGenerator.generateRecipe(new Question(question));
        }

    }
