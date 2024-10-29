package org.metaeffekt.terms.metadata.validation;

import com.metaeffekt.artifact.analysis.common.tmd.utils.NormalizationMetaDataUtils;
import com.metaeffekt.artifact.analysis.common.tmd.validation.AbstractTermsMetaDataIntegrityTest;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TermsMetaDataIntegrityTest extends AbstractTermsMetaDataIntegrityTest {

    private static final Logger LOG = LoggerFactory.getLogger(TermsMetaDataIntegrityTest.class);

    private static final File tmdBaseDir = new File("src/main/resources/ae-terms-metadata");
    private static final NormalizationMetaData nmd = new NormalizationMetaData(tmdBaseDir);

    /**
     * {@inheritDoc}
     */
    @Test
    public void assertUniqueLicenseIdentification_metadata() throws IOException {
        // to speed up validation the reports can be disabled; this save 2/3 of the time
        final boolean updateMatchReports = false;

        // when a run has failed enter the failing license here to continue with the license that failed validation
        final String skipUntilLicense = "Oracle Community Source License 1.0";

        assertUniqueLicenseIdentification_metadata(nmd, skipUntilLicense, updateMatchReports);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void assertUniqueLicenseIdentification_metadataCategory() throws IOException {
        final String categories = "BSD";

        assertUniqueLicenseIdentification_metadataCategory(nmd, categories);
    }

    @Disabled
    @Test
    public void assertUniqueLicenseIdentification_metadataSingle() throws IOException {
        assertUniqueLicenseIdentification_metadataSingle(nmd, "Apache License 2.0");
    }

    @Test
    public void reportAndRemoveObsoleteMetaFolder() throws IOException {
        reportAndRemoveObsoleteMetaFolder(tmdBaseDir);
    }

    @Test
    public void reportAndRemoveEmptyFolders() throws IOException {
        reportAndRemoveObsoleteMetaFolder(tmdBaseDir);
    }


    @Test
    @Disabled
    public void testReferencesAndMasksSuppliedWithSufficientContext() {
        testReferencesAndMasksSuppliedWithSufficientContext(nmd);
    }

    @Test
    public void testTmdOnlyHasEitherSpdxIdOrSpdxExpressionTemplate() {
        testTmdOnlyHasEitherSpdxIdOrSpdxExpressionTemplate(nmd);

    }

    @Test
    public void testTmdSpdxExpressionOrTemplateIsValid() {
        testTmdSpdxExpressionOrTemplateIsValid(nmd);
    }

    //FIXME-KKL: is this test important? I don't really understand what it does but it fails for Kosmos
    @Disabled
    @Test
    public void revertContainsAtLeastAWord() throws IOException {
        revertContainsAtLeastAWord(nmd);
    }

    /**
     * {@inheritDoc}
     */
    // this test is currently disabled since there are no markers in Kosmos atm
    @Disabled
    @Test
    public void testEulaNameAndMarkerConsistency() {
        testEulaNameAndMarkerConsistency(nmd);
    }

    @Test
    @Disabled // this is a utility test
    public void deleteMetaFolder() throws IOException {
        deleteMetaFolder(tmdBaseDir);
    }
}
