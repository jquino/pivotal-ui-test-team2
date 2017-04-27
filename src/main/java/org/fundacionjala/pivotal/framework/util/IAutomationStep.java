package org.fundacionjala.pivotal.framework.util;

/**
 * Class interface that automates test cases steps.
 */
@FunctionalInterface
public interface IAutomationStep {
    /**
     * Method to execute steps according to the
     * subclasses defined.
     */
    void executeStep();
}
