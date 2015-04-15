package com.permoveo.apps.jumboshrimp.factory;

import com.permoveo.apps.jumboshrimp.constants.DataSource;
import com.permoveo.apps.jumboshrimp.parser.BigOvenParser;
import com.permoveo.apps.jumboshrimp.parser.Parser;

/**
 * Created by byfieldj on 4/14/15.
 */
public class ParserFactory {


    public static Parser getParser(DataSource source) {

        if (source.equals(DataSource.BigOven)) {
            return new BigOvenParser();
        }

        return null;
    }


}
