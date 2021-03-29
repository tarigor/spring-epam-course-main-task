package com.epam.movieTheater.utility;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class PropertyFilesScanner {

    private List<String> propertiesFiles;

    public List<String> getAllPropertiesFiles(String path) {
        try {
            return propertiesFiles = Files.walk(Paths.get(path))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(n -> n.endsWith(".properties"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFiles;
    }
}
