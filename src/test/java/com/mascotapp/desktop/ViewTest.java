/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.mascotapp.desktop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    @Test void isWorkingMethodReturnsTrue() {
        View classUnderTest = new View();
        assertTrue(classUnderTest.isWorking(), "isWorking should return 'true'");
    }

    @Test void isWorkingDependencyProjectMethodReturnsTrue() {
        View classUnderTest = new View();
        assertTrue(classUnderTest.isWorkingDependencyProject(), "isWorkingDependencyProject should return 'true'");
    }
}
