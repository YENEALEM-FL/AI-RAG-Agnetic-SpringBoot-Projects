package com.lab.springaigeneratorevaluatorpattern.model;

import java.util.List;

public record RefinedResponse(String solution, List<Generation> chainOfThought) {}