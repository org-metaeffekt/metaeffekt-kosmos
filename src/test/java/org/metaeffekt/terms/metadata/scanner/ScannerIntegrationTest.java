package org.metaeffekt.terms.metadata.scanner;

import com.metaeffekt.artifact.analysis.common.tmd.scanner.AbstractScannerIntegrationTest;
import com.metaeffekt.artifact.analysis.common.tmd.utils.NormalizationMetaDataUtils;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Slf4j
public class ScannerIntegrationTest extends AbstractScannerIntegrationTest {

    private static final File tmdBaseDir = new File("src/main/resources/ae-terms-metadata");
    private static final NormalizationMetaData nmd = new NormalizationMetaData(tmdBaseDir);

    /**
     * {@inheritDoc}
     */
    @Disabled // invoked from _04_ScannerTests
    @Test
    public void assertUniqueLicenseIdentification() throws IOException {
        String skipUntilLicense = "";
        boolean deleteAnalysisFolder = true;
        List<String> excludes = new ArrayList<>();

        // this is in issue in combiner; bug
        excludes.add("ANY + GNU Library General Public License 2.0 (or any later version)");

        excludes.add("Linum Software License");
        excludes.add("Net License SNMP");
        excludes.add("Philippe De Muyter License");
        excludes.add("Python License 2.0");
        //excludes.add("ACE License");

        // ignored reference that gets matched:
        excludes.add("Freemarker Historic Note");

        assertUniqueLicenseIdentification(nmd, excludes, skipUntilLicense, deleteAnalysisFolder);
    }
}

