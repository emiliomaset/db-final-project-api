package com.example.streaming_service.config;

// Source - https://stackoverflow.com/a
// Posted by Sachin Gupta, modified by community. See post 'Timeline' for change history
// Retrieved 2025-11-25, License - CC BY-SA 4.0

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomJSONDeserializer extends JsonDeserializer<String> { // converts blank strings into nulls upon JSON deserialization
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.readValueAsTree();
        if (node.asText().isBlank()) {
            return null;
        }
        return node.asText();
    }

}
