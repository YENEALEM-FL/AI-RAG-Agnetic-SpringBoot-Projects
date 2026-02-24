## ChatResponse String Representation



```code 
ChatResponse [
    metadata={
        id: chatcmpl-BkADLWRYUsLaXvc2M4EEAdfN9HUm5,
        usage: DefaultUsage{
            promptTokens=17,
            completionTokens=17,
            totalTokens=34
        },
        rateLimit: {
            @type: org.springframework.ai.openai.metadata.OpenAiRateLimit,
            requestsLimit: 200,
            requestsRemaining: 198,
            requestsReset: PT23M15S,
            tokensLimit: 60000,
            tokensRemaining: 59987,
            tokensReset: PT0.013S
        }
    },
    generations=[
        Generation[
            assistantMessage=AssistantMessage [
                messageType=ASSISTANT,
                toolCalls=[],
                textContent=Why did the scarecrow win an award? Because he was outstanding in his field!,
                metadata={
                    role=ASSISTANT,
                    messageType=ASSISTANT,
                    finishReason=STOP,
                    refusal=,
                    index=0,
                    annotations=[],
                    id=chatcmpl-BkADLWRYUsLaXvc2M4EEAdfN9HUm5
                }
            ],
            chatGenerationMetadata=DefaultChatGenerationMetadata[
                finishReason='STOP',
                filters=0,
                metadata=0
            ]
        ]
    ]
]
``` 