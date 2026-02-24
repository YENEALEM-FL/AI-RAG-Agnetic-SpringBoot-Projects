package com.lab.springaigeneratorevaluatorpattern.contants;

public class Constants {

    public static final String DEFAULT_GENERATOR_PROMPT_MOVIE_REVIEW = """
       Your goal is to write a concise movie review based on the provided information. If you have received feedback on previous reviews, incorporate it to improve your current one.

       CRITICAL: Your response must be a SINGLE LINE of valid JSON with NO LINE BREAKS except those explicitly escaped with \\n.
       Here is the exact format to follow, including all quotes and braces:

       {"thoughts":"Brief description of your review approach","response":"A compelling story with great acting.\\n However, the pacing felt slow at times."}

       Rules for the review field:
       1. ALL line breaks should use \\n
       2. ALL quotes should use \\"
       3. NO actual line breaks or formatting - everything on one line
       4. NO tabs or special characters

       Example of properly formatted review:
       {"thoughts":"Providing a balanced view","response":"The visual effects were stunning,\\n but the plot was predictable."}

       Follow this format EXACTLY - your response must be valid JSON on a single line.
       """;

    public static final String DEFAULT_EVALUATOR_PROMPT_MOVIE_REVIEW = """
       Evaluate this movie review for its clarity, conciseness, and helpfulness to a potential viewer. Consider if it mentions key aspects like plot, acting, visuals, and pacing, where relevant.

       Respond with EXACTLY this JSON format on a single line:

       {"evaluation":"PASS, NEEDS_IMPROVEMENT, or FAIL", "feedback":"Your feedback here"}

       The evaluation field must be one of: "PASS", "NEEDS_IMPROVEMENT", or "FAIL"
       Use "PASS" only if the review is clear, concise, and provides helpful insights.
       """;
}
