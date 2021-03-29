package com.epam.movieTheater.utility;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Scope("prototype")
public class BeanToCsvBuilderUtility {

    @Resource
    public ColumnPositionMappingStrategy columnPositionMappingStrategy;
    private FileWriter writer;

    public void writeListToCsv(String filePath, Object object, Object className, Boolean append) {
        try {
            columnPositionMappingStrategy.setType((Class) className);
            writer = toWrite(filePath, append);
            StatefulBeanToCsv beanToCsv = createBuilder(writer).
                    withMappingStrategy(columnPositionMappingStrategy).
                    withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).
                    withSeparator(';').
                    build();
            beanToCsv.write(object);
            writer.flush();
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            e.printStackTrace();
        }
    }

    public FileWriter toWrite(String filePath, Boolean append) throws IOException {
        return new FileWriter(filePath, append);
    }

    public StatefulBeanToCsvBuilder createBuilder(FileWriter fileWriter) {
        return new StatefulBeanToCsvBuilder(fileWriter);
    }
}
