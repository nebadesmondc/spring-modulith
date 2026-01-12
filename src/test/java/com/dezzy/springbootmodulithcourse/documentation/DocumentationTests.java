package com.dezzy.springbootmodulithcourse.documentation;

import com.dezzy.springbootmodulithcourse.SpringBootModulithCourseApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class DocumentationTests {

    ApplicationModules modules = ApplicationModules.of(SpringBootModulithCourseApplication.class);

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModulesAsPlantUml(
                        Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML)
                )
                .writeIndividualModulesAsPlantUml()
                .writeModuleCanvases();
    }
}
