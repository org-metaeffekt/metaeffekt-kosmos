package org.metaeffekt.terms.metadata;

import org.junit.jupiter.api.Test;
import org.metaeffekt.terms.metadata.validation.TermsMetaDataIntegrityTest;

import java.io.IOException;

public class _03_OverallTests {

    /**
     * Validates all rules regarding the terms metadata.
     *
     * @throws IOException
     */
    @Test
    // run from time to time (before commit)
    public void testAll() throws IOException {
        new _02_IncrementalTests().testAll();
        new TermsMetaDataIntegrityTest().assertUniqueLicenseIdentification_metadata();
    }
}
