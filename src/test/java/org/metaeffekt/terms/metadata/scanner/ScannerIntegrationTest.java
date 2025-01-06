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

        // NKR: this seems broken. Cannot identify cause of issue:
        // KKL: this is an issue in the combiner logic
        excludes.add("ANY + GNU Library General Public License 2.0 (or any later version)");

        // ignored reference that gets matched:
        // KKL: ignored licenses should not be 'removed' from match result
        excludes.add("Freemarker Historic Note");

        // ignored, deprecated, too vague to be modelled. Needs to be discussed:
        // KKL: ignored licenses should not be 'removed' from match result
        excludes.add("Linum Software License");

        // ignored, deprecated, too vague to be modelled. Needs to be discussed:
        // KKL: ignored licenses should not be 'removed' from match result
        excludes.add("Philippe De Muyter License");

        // these came up because of the most recent fix regarding expectedMatches
        // FIXME-KKL: check license mismatches caused by Python License 2.0.1 & CNRI Open Source License Agreement (Python 1.6.1)
        excludes.add("Python License 2.0");

        assertUniqueLicenseIdentification(nmd, excludes, skipUntilLicense, deleteAnalysisFolder);
    }
}

