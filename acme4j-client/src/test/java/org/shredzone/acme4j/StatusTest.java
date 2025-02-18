/*
 * acme4j - Java ACME client
 *
 * Copyright (C) 2015 Richard "Shred" Körber
 *   http://acme4j.shredzone.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package org.shredzone.acme4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link Status} enumeration.
 */
public class StatusTest {

    /**
     * Test that invoking {@link Status#parse(String)} gives the correct status.
     */
    @Test
    public void testParse() {
        for (Status s : Status.values()) {
            Status parsed = Status.parse(s.name().toLowerCase());
            assertThat(parsed, is(s));
        }

        // unknown status returns UNKNOWN
        assertThat(Status.parse("foo"), is(Status.UNKNOWN));
    }

}