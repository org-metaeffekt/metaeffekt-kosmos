package org.metaeffekt.terms.metadata.update;

import com.metaeffekt.artifact.analysis.common.tmd.update.AbstractUpdateTermsMetadataSpdxTest;
import com.metaeffekt.artifact.analysis.common.tmd.utils.NormalizationMetaDataUtils;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class UpdateTermsMetadataSpdxTest extends AbstractUpdateTermsMetadataSpdxTest {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateTermsMetadataSpdxTest.class);

    /**
     * {@inheritDoc}
     */
    @Test
    public void updateTermsMetaDataFromSPDX() throws IOException {

        final File localProperties = new File(".local-properties");
        updateTermsMetaDataFromSPDX(localProperties);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkReferencedSpdxLicenseExists() throws IOException {

        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();
        final File localProperties = new File(".local-properties");

        checkReferencedSpdxLicenseExists(normalizationMetaData, localProperties);
    }
}
