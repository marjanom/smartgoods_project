package com.example.smartgoods_project.rest.service;

import com.example.smartgoods_project.rest.model.RequirementAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.jar.Attributes;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequirementRestServiceTest {

    private String requirement;
    RequirementAttribute attributes;
    @Autowired
    RequirementRestService requirementRestService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCheckIfRuppSchemeWithCorrectInput() {
        requirement = "The system shall be able to generate tests.";

        attributes = requirementRestService.checkIfRuppScheme(requirement);

        assertTrue(attributes.isRuppScheme());
        assertEquals("", attributes.getHint());

    }

    @Test
    void testCheckIfRuppSchemeWithShallShouldWillMissing() {
        requirement = "The system may be able to generate tests.";

        attributes = requirementRestService.checkIfRuppScheme(requirement);

        assertFalse(attributes.isRuppScheme());
        assertEquals("Keyword (shall, should, will) is missing!", attributes.getHint());
    }

    @Test
    void testCheckIfRuppSchemeWithKeyphraseMissing() {
        requirement = "The system should.";

        attributes = requirementRestService.checkIfRuppScheme(requirement);

        assertFalse(attributes.isRuppScheme());
        assertEquals("Process verb or key phrase (be able to/ provide <someone> the ability to) is missing!", attributes.getHint());
    }

    @Test
    void testCheckIfRuppSchemeWithProcessverbMissing() {
        requirement = "The system should be able to precisely test.";

        attributes = requirementRestService.checkIfRuppScheme(requirement);

        assertFalse(attributes.isRuppScheme());
        assertEquals("Process verb after key phrase (be able to/ provide <someone> the abiility to) is missing!", attributes.getHint());
    }

    @Test
    void testCheckIfRuppSchemeWithNegativePhrase() {
        requirement = "The system should not be able to generate tests.";

        attributes = requirementRestService.checkIfRuppScheme(requirement);

        assertTrue(attributes.isRuppScheme());
        assertEquals("", attributes.getHint());
    }


}