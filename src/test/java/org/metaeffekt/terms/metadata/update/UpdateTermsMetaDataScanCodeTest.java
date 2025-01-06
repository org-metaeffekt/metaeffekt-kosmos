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
