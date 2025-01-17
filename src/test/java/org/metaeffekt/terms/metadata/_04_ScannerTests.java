/*
 * Copyright 2021-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.metaeffekt.terms.metadata;

import org.junit.jupiter.api.Test;
import org.metaeffekt.terms.metadata.scanner.ScannerIntegrationTest;

import java.io.IOException;

public class _04_ScannerTests {

    /**
     * Run this test to start all tests regarding the scanner.
     */
    @Test
    public void testAll() throws IOException {
        new ScannerIntegrationTest().assertUniqueLicenseIdentification();
    }
}
