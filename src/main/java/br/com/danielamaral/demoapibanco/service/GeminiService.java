package br.com.danielamaral.demoapibanco.service;

import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.vertexai.VertexAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
@Service
public class GeminiService {

    public String consultar(String duvida){

        ChatLanguageModel model = VertexAiGeminiChatModel.builder()
                .project(System.getenv("PROJECT_ID"))
                .location(System.getenv("LOCATION"))
                .modelName(System.getenv("MODEL"))
                .build();

        return model.generate(duvida);
    }
}
