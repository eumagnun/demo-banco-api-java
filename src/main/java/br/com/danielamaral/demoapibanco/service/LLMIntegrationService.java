package br.com.danielamaral.demoapibanco.service;

import br.com.danielamaral.demoapibanco.dto.LlmRequestDto;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class LLMIntegrationService {

    public String query(LlmRequestDto llmRequestDto) {

        try (VertexAI vertexAi = new VertexAI(System.getenv("PROJECT_ID"), System.getenv("LOCATION"));) {

            GenerationConfig generationConfig = getGenerationConfig();

            List<SafetySetting> safetySettings = getSafetySettings();

            GenerativeModel model =
                    new GenerativeModel.Builder()
                            .setModelName(System.getenv("MODEL"))
                            .setVertexAi(vertexAi)
                            .setGenerationConfig(generationConfig)
                            .setSafetySettings(safetySettings)
                            .build();

            Content content = setupContentRequest(llmRequestDto);

            GenerateContentResponse generateContentResponse = model.generateContent(content);

            return ResponseHandler.getText(generateContentResponse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Content setupContentRequest(LlmRequestDto llmRequestDto) {
        Content content;
        Part file;

        if(llmRequestDto.getUrl() !=null && !"".equals(llmRequestDto.getUrl())){

            String mimeType = detectMimeType(llmRequestDto.getUrl());

            file = PartMaker.fromMimeTypeAndData(mimeType, llmRequestDto.getUrl());

            content = ContentMaker.fromMultiModalData(file, llmRequestDto.getSolicitacao());
        }else{
            content = ContentMaker.fromString(llmRequestDto.getSolicitacao());
        }
        return content;
    }

    private static String detectMimeType(String url) {
        String extension = url.substring(url.lastIndexOf(".")+1);
        String mimeType = "";
        switch (extension){
            case "png":
                mimeType = "image/png";
                break;
            case "jpg":
                mimeType = "image/jpg";
                break;
            case "wav":
                mimeType = "audio/wav";
                break;
            case "pdf":
                mimeType = "application/pdf";
                break;
        }
        return mimeType;
    }

    private static GenerationConfig getGenerationConfig() {
        GenerationConfig generationConfig =
                GenerationConfig.newBuilder()
                        .setMaxOutputTokens(8192)
                        .setTemperature(1F)
                        .setTopP(0.95F)
                        .build();
        return generationConfig;
    }

    private static List<SafetySetting> getSafetySettings() {
        List<SafetySetting> safetySettings = Arrays.asList(
                SafetySetting.newBuilder()
                        .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                        .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                        .build(),
                SafetySetting.newBuilder()
                        .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                        .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                        .build(),
                SafetySetting.newBuilder()
                        .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                        .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                        .build(),
                SafetySetting.newBuilder()
                        .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                        .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                        .build()
        );
        return safetySettings;
    }



}
