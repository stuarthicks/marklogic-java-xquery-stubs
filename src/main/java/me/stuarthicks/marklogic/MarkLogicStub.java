package me.stuarthicks.marklogic;

import me.stuarthicks.xqueryjunit.XQueryContext;

public class MarkLogicStub {

    private final XQueryContext xq;

    public MarkLogicStub(XQueryContext xq) {
        this.xq = xq;
    }

    public Xdmp xdmp() {
        return new Xdmp(this);
    }

    protected XQueryContext getContext() {
        return this.xq;
    }
}
