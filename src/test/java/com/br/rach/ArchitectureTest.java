package com.br.rach;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.br.rach")
class ArchitectureTest {

    @ArchTest
    static final ArchRule layered_architecture_should_be_respected = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Application").definedBy("..application..")
            .layer("Domain").definedBy("..domain..")
            .layer("Infrastructure").definedBy("..infrastructure..")

            .whereLayer("Application").mayNotBeAccessedByAnyLayer()
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure")
            .whereLayer("Infrastructure").mayNotAccessAnyLayer();

    @ArchTest
    static final ArchRule entities_should_be_in_domain_entities =
            classes()
                    .that().areAnnotatedWith(jakarta.persistence.Entity.class)
                    .should().resideInAPackage("..domain.entities..");

    @ArchTest
    static final ArchRule repositories_should_be_in_infrastructure =
            classes()
                    .that().areAnnotatedWith(org.springframework.stereotype.Repository.class)
                    .should().resideInAPackage("..infrastructure.repositories..");

    @ArchTest
    static final ArchRule controllers_should_be_in_application_resources =
            classes()
                    .that().areAnnotatedWith(org.springframework.web.bind.annotation.RestController.class)
                    .should().resideInAPackage("..application.resources..");

    @ArchTest
    static final ArchRule services_should_be_in_domain_services =
            classes()
                    .that().areAnnotatedWith(org.springframework.stereotype.Service.class)
                    .should().resideInAPackage("..domain.services..");
}
