/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.mascotapp.desktop;

import com.mascotapp.core.Library;

public class View {
    public boolean isWorking() {
        return true;
    }

    public boolean isWorkingDependencyProject() {
        Library library = new Library();
        return library.someLibraryMethod();
    }
}