marklogic-java-xquery-stubs
===========================
License: Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0

## What is this?

A set of common stubs for [MarkLogic](http://marklogic.com) XQuery extensions, and a way to run and test that XQuery from within a Java application.

## Why?

So I can test MarkLogic-bound XQuery code locally without the need for an actual MarkLogic instance.

## Usage

### Maven dependency

There is currently no release version and this has not been pushed into any public maven repository. Build locally then include the following dependency in your pom.xml:

```xml
<dependency>
  <groupId>me.stuarthicks</groupId>
  <artifactId>marklogic-java-stubs</artifactId>
  <version>1-SNAPSHOT</version>
</dependency>
```

### Stubbing Example

More examples in tests, but browse the MarkLogicStub object and it's available namespaces and functions. In each case, you can declare what that function should return. For functions you don't stub, they won't exist and your XQuery will error.

Let's take this example XQuery document `xdmp_document-get.xqy`:
```xquery
declare namespace xdmp = "http://marklogic.com/xdmp";
xdmp:document-get("foo")
```

and let's have an xml document `foobar.xml`:
```xml
<bar/>
```

We can stub MarkLogic's `xdmp:document-get()` to return our `foobar.xml`. We can assert we tried to fetch the right document and we can check the return value of the XQuery evaluation:
```java
@Test
public void itProvides_DocumentGet() throws Exception {
    xq = new XQueryContext();
    marklogicStub = new MarkLogicStub(xq);
    
    XQueryFunctionStub documentGet = this.marklogicStub.xdmp().documentGet("/foobar.xml");
    
    String result = this.xq.evaluateXQueryFile("/xdmp_document-get.xqy").toString().trim();
    
    assertEquals("foo", documentGet.getArguments().get(0));
    assertEquals("<bar/>", result);
}
```

## Limitations

There are LOADS of MarkLogic XQuery functions. I'll add them here as and when I need to test against them. Feel free to implement more and submit a pull request.