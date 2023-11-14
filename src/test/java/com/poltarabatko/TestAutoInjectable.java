package com.poltarabatko;

import com.poltarabatko.Injector.Injector;
import com.poltarabatko.somepackage.SomeBean;
import org.junit.jupiter.api.TestInstance;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


/**
 * @author r.poltarabatko
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestAutoInjectable {

    private final static ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Constructs a new AutoInjectableUnitTests instance.
     * This class contains JUnit tests for the AutoInjectable framework.
     */
    public TestAutoInjectable() {}

    /**
     * Sets up the PrintStream redirection before running any tests.
     * This method is annotated with {@link BeforeAll}.
     */
    @BeforeAll
    public static void interceptPrintStream() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Clears the captured output after each test.
     * This method is annotated with {@link AfterEach}.
     */
    @AfterEach
    public void clearPrintStream() {
        output.reset();
    }

    private final static String pathOne = "src/test/resources/first.properties";
    private final static String pathTwo = "src/test/resources/second.properties";

    /**
     * Tests the injection of dependencies A and C into SomeBean instance using the first properties file.
     * Verifies that the expected output matches the injected values.
     * This method is annotated with {@link Test}.
     */
    @Test
    public void testInjectAC() {
        SomeBean someBean = null;
        try {
            someBean = (new Injector().inject(new SomeBean(), pathOne));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("A" + lineSeparator + "C" + lineSeparator, output.toString());
    }

    /**
     * Tests the injection of dependencies B and C into SomeBean instance using the second properties file.
     * Verifies that the expected output matches the injected values.
     * This method is annotated with {@link Test}.
     */
    @Test
    public void testInjectBC() {
        SomeBean someBean = null;
        try {
            someBean = (new Injector().inject(new SomeBean(), pathTwo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();
        String lineSeparator = System.getProperty("line.separator");
        assertEquals("B" + lineSeparator + "C" + lineSeparator, output.toString());
    }

    /**
     * Tests that calling the foo method on an uninitialized SomeBean instance
     * results in a {@link NullPointerException}.
     * This method is annotated with {@link Test}.
     */
    @Test
    public void testFieldsNotInitialized() {
        assertThrows(NullPointerException.class, () -> {
            (new SomeBean()).foo();
        });
    }
}
