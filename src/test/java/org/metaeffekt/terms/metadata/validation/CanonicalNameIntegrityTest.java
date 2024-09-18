package org.metaeffekt.terms.metadata.validation;

import com.metaeffekt.artifact.analysis.common.tmd.validation.AbstractCanonicalNameIntegrityTest;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CanonicalNameIntegrityTest extends AbstractCanonicalNameIntegrityTest{

    /**
     * {@inheritDoc}
     */
    @Disabled // creation is not implicit; this is a helper method
    @Test
    public void createCanonicalNamesFile() throws IOException {
        final File canonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/all-canonical-names.txt");
        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        createCanonicalNamesFile(canonicalNamesFile, normalizationMetaData);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testCanonicalNames() throws IOException {

        final File canonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/all-canonical-names.txt");
        final File excludedCanonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/excluded-canonical-names.txt");
        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        testCanonicalNames(canonicalNamesFile, excludedCanonicalNamesFile, normalizationMetaData);
    }
}
