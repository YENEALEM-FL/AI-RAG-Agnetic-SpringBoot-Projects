package com.lab.modelservicecontrollerspringai.service;

import com.lab.modelservicecontrollerspringai.model.Answer;
import com.lab.modelservicecontrollerspringai.model.Question;
import org.springframework.stereotype.Service;


@Service
public interface GetRecipeGenerator {
    public Answer generateRecipe(Question question);
}
