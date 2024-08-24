package com.mtasci.foreign_exchange.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    private static final String BASE_PATH = "src/test/resources/";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String readJsonFileAsString(String filePath) throws IOException {
        filePath = BASE_PATH + filePath;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static <T> T readJsonFileAsString(String filePath, Class<T> clazz) throws IOException {
        final String fileContent = readJsonFileAsString(filePath);
        return mapper.readValue(fileContent, clazz);
    }

    public static <T> List<T> readJsonFileAsStringToList(String filePath, Class<T> clazz) throws IOException {
        final String fileContent = readJsonFileAsString(filePath);
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(fileContent, collectionType);
    }

}
