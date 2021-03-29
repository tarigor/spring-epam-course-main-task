package com.epam.movieTheater.utility;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
@Scope("prototype")
public class CsvToBeanBuilderUtility {
    public FileReader toReadFile(String filePath) throws FileNotFoundException {
        return new FileReader(filePath);
    }

    public List getListOfBeansFromCsv(String filePath, Object className) throws FileNotFoundException {
        return new CsvToBeanBuilder(toReadFile(filePath)).withType((Class) className).withSeparator(';').build().parse();
    }
}
