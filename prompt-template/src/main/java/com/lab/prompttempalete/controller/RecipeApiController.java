package com.lab.prompttempalete.controller;

import com.lab.prompttempalete.model.Answer;
import com.lab.prompttempalete.model.Question;
import com.lab.prompttempalete.service.GetRecipeGenerator;
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
                defaultValue="What are the ingredients?") String question, @RequestParam(value="foodName", defaultValue="pizza") String foodName){
            return getRecipeGenerator.generateRecipe(new Question(question, foodName));
        }

    }
