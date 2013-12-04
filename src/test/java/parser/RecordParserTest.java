package parser;

import org.junit.Test;

public class RecordParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullRecordIsRejected() throws Exception {
        RecordParser parser = new RecordParser();
        parser.parse(null);
    }
}
