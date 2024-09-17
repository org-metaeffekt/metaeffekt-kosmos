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

    private final static NormalizationMetaData nmd = TermsMetaDataResolver.get();
    private final NormalizationMetaDataUtils normalizationMetaDataUtils = new NormalizationMetaDataUtils(new File("src/main/resources/ae-terms-metadata"));

    /**
     * {@inheritDoc}
     */
    @Test
    public void assertUniqueLicenseIdentification_metadata() throws IOException {
        // to speed up validation the reports can be disabled; this save 2/3 of the time
        final boolean updateMatchReports = false;

        // when a run has failed enter the failing license here to continue with the license that failed validation
        final String skipUntilLicense = "";

        assertUniqueLicenseIdentification_metadata(nmd, skipUntilLicense, updateMatchReports);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void assertUniqueLicenseIdentification_metadataCategory() throws IOException {
        final String categories = "BSD";
        final NormalizationMetaData normalizationMetaData = new NormalizationMetaData(normalizationMetaDataUtils.getLicenseMetaDataDir());

        assertUniqueLicenseIdentification_metadataCategory(normalizationMetaData, categories);
    }

    @Disabled
    @Test
    public void assertUniqueLicenseIdentification_metadataSingle() throws IOException {
        final String licenseName = "Apache License 2.0";
        NormalizationMetaData normalizationMetaData = new NormalizationMetaData(normalizationMetaDataUtils.getLicenseMetaDataDir());

        assertUniqueLicenseIdentification_metadataSingle(normalizationMetaData, licenseName);
    }

    @Test
    public void reportAndRemoveObsoleteMetaFolder() throws IOException {
        File baseDir = new File("src/main/resources/ae-terms-metadata");
        reportAndRemoveObsoleteMetaFolder(baseDir);
    }

    @Test
    public void reportAndRemoveEmptyFolders() throws IOException {
        File baseDir = new File("src/main/resources/ae-terms-metadata");
        reportAndRemoveObsoleteMetaFolder(baseDir);
    }

    @Test
    @Disabled
    public void deleteMetaFolder() throws IOException {
        File baseDir = new File("src/main/resources/ae-terms-metadata");
        deleteMetaFolder(baseDir);
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

    @Test
    public void revertContainsAtLeastAWord() throws IOException {
        revertContainsAtLeastAWord(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testEulaNameAndMarkerConsistency() {
        testEulaNameAndMarkerConsistency(nmd);
    }
}
