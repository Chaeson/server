package com.cjw.server.reader;

import com.cjw.server.model.SalesModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class SalesReader implements ItemReader<List<SalesModel>> {
    @Override
    public List<SalesModel> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
