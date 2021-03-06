package com.afklm.test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.afklm.test");

        noClasses()
            .that()
            .resideInAnyPackage("com.afklm.test.service..")
            .or()
            .resideInAnyPackage("com.afklm.test.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.afklm.test.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
