package com.lab.prompttempalete.service;

import com.lab.prompttempalete.model.Answer;
import com.lab.prompttempalete.model.Question;
import org.springframework.stereotype.Service;


@Service
public interface GetRecipeGenerator {
    public Answer generateRecipe(Question question);
}
