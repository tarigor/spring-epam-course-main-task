package com.epam.movieTheater.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@Scope("prototype")
public class PropertiesFilesInputStream {

    public PropertiesFilesInputStream() {
    }

    public FileInputStream getPath(String path, List<String> propertiesFiles, Integer i) throws FileNotFoundException {
        return new FileInputStream(String.format(path, propertiesFiles.get(i)));
    }

    public FileInputStream getPath(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}
