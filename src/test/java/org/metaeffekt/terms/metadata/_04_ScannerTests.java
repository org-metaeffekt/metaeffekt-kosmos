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
