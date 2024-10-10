package org.metaeffekt.terms.metadata.update;

import com.metaeffekt.artifact.analysis.common.tmd.update.AbstractUpdateTermsMetaDataScanCodeTest;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Disabled
public class UpdateTermsMetaDataScanCodeTest extends AbstractUpdateTermsMetaDataScanCodeTest {

    final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();
    final File localProperties = new File(".local-properties");

    /**
     * {@inheritDoc}
     */
    @Test
    public void matchLicensesUsingScanCode() throws IOException {
        String skipUntilLicense = "";
        matchLicensesUsingScanCode(normalizationMetaData, localProperties, skipUntilLicense);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void updateTermsMetaDataFromScanCode() throws IOException {
        updateTermsMetaDataFromScanCode(normalizationMetaData, localProperties);
    }
}
