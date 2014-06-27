package me.stuarthicks.marklogic;

import me.stuarthicks.xqueryjunit.XQueryContext;
import me.stuarthicks.xqueryjunit.stubbing.XQueryFunctionStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XdmpTest {

    private MarkLogicStub marklogicStub;
    private XQueryContext xq;

    @Before
    public void before() {
        this.xq = new XQueryContext();
        this.marklogicStub = new MarkLogicStub(xq);
    }

    @Test
    public void itProvides_DocumentGet() throws Exception {
        XQueryFunctionStub documentGet = this.marklogicStub.xdmp().documentGet("/foobar.xml");

        String result = this.xq.evaluateXQueryFile("/xdmp_document-get.xqy").toString().trim();

        assertEquals("foo", documentGet.getArguments().get(0));
        assertEquals("<bar/>", result);
    }

}
