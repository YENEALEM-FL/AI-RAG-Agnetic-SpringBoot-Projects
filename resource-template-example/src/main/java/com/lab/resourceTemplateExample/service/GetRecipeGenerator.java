package com.lab.resourceTemplateExample.service;

import com.lab.resourceTemplateExample.model.Answer;
import com.lab.resourceTemplateExample.model.Question;
import org.springframework.stereotype.Service;


@Service
public interface GetRecipeGenerator {
    public Answer generateRecipe(Question question);
}
