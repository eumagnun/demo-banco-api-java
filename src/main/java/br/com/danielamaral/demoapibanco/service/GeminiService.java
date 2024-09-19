package br.com.danielamaral.demoapibanco.service;

import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.vertexai.VertexAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
@Service
public class GeminiService {


    public String consultar(String duvida){

        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
                .project("i-monolith-433517-u5")
                .location("us-central1")
                .modelName("gemini-1.5-flash-001")
                .build();

        return model.generate(duvida);
    }
}
