package com.poltarabatko.somepackage;
import com.poltarabatko.annotations.AutoInjectable;

/**
 * @author r.poltarabatko
 */
public class SomeBean {
    /**
     * Default constructor.
     */
    public SomeBean() {}

    /**
     * Auto-injectable field representing SomeInterface.
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Auto-injectable field representing SomeOtherInterface.
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Performs the action associated with the injected fields.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}
