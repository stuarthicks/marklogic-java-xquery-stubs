package me.stuarthicks.marklogic;

import me.stuarthicks.xqueryjunit.XQueryConstants;
import me.stuarthicks.xqueryjunit.stubbing.XQueryFunctionStub;
import me.stuarthicks.xqueryjunit.stubbing.XQueryFunctionStubBuilder;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.SequenceType;

import java.io.IOException;

import static me.stuarthicks.xqueryjunit.SaxonHelpers.nodeFromFile;

public final class Xdmp {

    private static final String PREFIX = "xdmp";
    private static final String NAMESPACE = "http://marklogic.com/xdmp";

    private final MarkLogicStub markLogicStub;

    public Xdmp(MarkLogicStub markLogicStub) {
        this.markLogicStub = markLogicStub;
    }

    /**
     * Stubs the function xdmp:document-get() to always return the given classpath resource
     * @param document
     * @return
     * @throws IOException
     * @throws SaxonApiException
     */
    public XQueryFunctionStub documentGet(String document) throws IOException, SaxonApiException, XPathException {
        return xdmpFunctionStubBuilder("document-get")
                .withFunctionSignature(XQueryConstants.ARGUMENTS_SINGLE_STRING)
                .withReturnType(SequenceType.SINGLE_NODE)
                .withReturnValue(nodeFromFile(document))
        .done();
    }

    private XQueryFunctionStubBuilder xdmpFunctionStubBuilder(String functionName) {
        return this.markLogicStub.getContext().buildXQueryFunctionStub()
                .withFunctionName(functionName)
                .withPrefix(PREFIX)
                .withNamespaceURI(NAMESPACE);
    }

}
