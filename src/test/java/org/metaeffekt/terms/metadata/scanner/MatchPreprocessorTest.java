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

import com.metaeffekt.artifact.analysis.common.tmd.scanner.AbstractMatchPreprocessorTest;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.FileSegmentation;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MatchPreprocessorTest extends AbstractMatchPreprocessorTest {

    /**
     * {@inheritDoc}
     */
    @Test
    public void testApplySegmentation() throws IOException {

        List<String> excludes = new ArrayList<>();

        // excludes can be added here that may behave out of order, the excludes need to be checked

        // Mike95 License seems to be the only license where the license text falsely ends with a segment marker
        excludes.add("Mike95 License");

        // needs to be checked
        excludes.add("Permission Terms (RUC)");
        excludes.add("CxImage License");
        excludes.add("Syntext Serna Exception");

        // when a run has failed enter the license name here to skip to it during the next run
        final String skipUntilLicense = "";

        testApplySegmentation(skipUntilLicense, excludes);
    }

    @Test
    /**
     * {@inheritDoc}
     */
    public void testFileSegmentation() {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();
        List<String> segmentationTrigger = new ArrayList<>();

        segmentationTrigger.add("# Begin");
        segmentationTrigger.add("    License:");
        segmentationTrigger.add("\nThe following license :");
        segmentationTrigger.add("\nThe following copyright :");
        segmentationTrigger.add("\nThe following notice :");
        segmentationTrigger.add("\nThe following applies :");
        segmentationTrigger.add("\nThe following apply :");
        segmentationTrigger.add("\nThe following terms :");
        segmentationTrigger.add("\nThe following conditions :");
        segmentationTrigger.add("\nTest Some files are :");
        segmentationTrigger.add("\nTest is copyright :");
        segmentationTrigger.add("\nThe file inet/rcmd.c is under a UCB copyright and the following:");
        segmentationTrigger.add("\n ---------------------");
        segmentationTrigger.add("\n =======");
        segmentationTrigger.add("\n***********");
        segmentationTrigger.add("\n[2a]");
        segmentationTrigger.add("  Some code in");
        segmentationTrigger.add("   The levenshtein automata tables in");
        segmentationTrigger.add("           The following license applies to");
        segmentationTrigger.add("* BSD_3Clause");
        segmentationTrigger.add("* X License");
        segmentationTrigger.add("The documentation is licensed under ");
        segmentationTrigger.add(" ***** NEW LIBC LICENCE********************************");
        segmentationTrigger.add(" (21) Red Hat Incorporated");
        segmentationTrigger.add(" (21) University of California, Berkeley");
        segmentationTrigger.add(" (21) David M. Gay (AT&T 1991, Lucent 1998)");
        segmentationTrigger.add(" (21) Advanced Micro Devices");
        segmentationTrigger.add(" (21) Sun Microsystems");
        segmentationTrigger.add(" (21) Hewlett Packard");
        segmentationTrigger.add(" (21) Hans-Peter Nilsson");
        segmentationTrigger.add(" (21) Stephane Carrez (m68hc11-elf/m68hc12-elf targets only)");
        segmentationTrigger.add(" (21) Christopher G. Demetriou");
        segmentationTrigger.add(" (21) SuperH, Inc.");
        segmentationTrigger.add(" (21) Royal Institute of Technology");
        segmentationTrigger.add(" (21) Alexey Zelkin");
        segmentationTrigger.add(" (21) Andrey A. Chernov");
        segmentationTrigger.add(" (21) FreeBSD");
        segmentationTrigger.add(" (21) S. L. Moshier");
        segmentationTrigger.add(" (21) Citrus Project");
        segmentationTrigger.add(" (21) Todd C. Miller");
        segmentationTrigger.add(" (21) DJ Delorie (i386)");
        segmentationTrigger.add(" (21) Free Software Foundation LGPL License (*-linux* targets only)");
        segmentationTrigger.add(" (21) Xavier Leroy LGPL License (i[3456]86-*-linux* targets only)");
        segmentationTrigger.add(" (21) Intel (i960)");
        segmentationTrigger.add(" (21) Hewlett-Packard (hppa targets only)\n");
        segmentationTrigger.add(" (21) Henry Spencer (only *-linux targets)");
        segmentationTrigger.add(" (21) Mike Barcroft");
        segmentationTrigger.add(" (21) Konstantin Chuguev (--enable-newlib-iconv)");
        segmentationTrigger.add(" (21) Artem Bityuckiy (--enable-newlib-iconv)");
        segmentationTrigger.add(" (21) IBM, Sony, Toshiba (only spu-* targets)");
        segmentationTrigger.add(" (21) - Alex Tatmanjants (targets using libc/posix)");
        segmentationTrigger.add(" (21) - M. Warner Losh (targets using libc/posix)");
        segmentationTrigger.add(" (21) - Andrey A. Chernov (targets using libc/posix)");
        segmentationTrigger.add(" (21) - Daniel Eischen (targets using libc/posix)");
        segmentationTrigger.add(" (21) - Jon Beniston (only lm32-* targets)");
        segmentationTrigger.add(" (21) - ARM Ltd (arm and thumb variant targets only)");
        segmentationTrigger.add(" (21) - Xilinx, Inc. (microblaze-* and powerpc-* targets)");
        segmentationTrigger.add(" (21) Texas Instruments Incorporated (tic6x-*, *-tirtos targets)");
        segmentationTrigger.add(" (21) National Semiconductor (cr16-* and crx-* targets)");
        segmentationTrigger.add(" (21) - Adapteva, Inc. (epiphany-* targets)");
        segmentationTrigger.add(" (21) - Altera Corportion (nios2-* targets)");
        segmentationTrigger.add(" (21) Ed Schouten - Free BSD");
        segmentationTrigger.add(" (21) Red Hat Incorporated");
        segmentationTrigger.add(" (21) Artem Bityuckiy (--enable-newlib-iconv)");
        segmentationTrigger.add(" (21) Artem Bityuckiy");
        segmentationTrigger.add("The license used for this is: Test");
        segmentationTrigger.add("SOFTWARE.\n\n   Permission is hereby granted,");
        segmentationTrigger.add("    (\\.c:)");
        segmentationTrigger.add("    (\\.h:)");
        segmentationTrigger.add("    /*.h:");
        segmentationTrigger.add("    /*.c:");
        segmentationTrigger.add("  /*.[hc]:");
        segmentationTrigger.add("  /*.[ch]:");
        segmentationTrigger.add(" refclock_jjy:");
        segmentationTrigger.add("[2b]");
        segmentationTrigger.add(" -    /*\n\n\n");
        segmentationTrigger.add("\n\n Copyright");
        segmentationTrigger.add("\n\n The GNU General Public License");

        for (int i = 0; i < segmentationTrigger.size(); i++) {
            String segmentationTest = String.format("first segment of license file\n%s\nsecond segment of license file", segmentationTrigger.get(i));
            FileSegmentation fileSegmentation = new FileSegmentation(segmentationTest, normalizationMetaData);
            Assertions.assertEquals(2, fileSegmentation.getFileSegments().size());
        }
    }

    @Test
    public void testFileSegmentation001() {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        String test = "BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR " +
                "LICENSE-SEGMENT-MARKER OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE " +
                "OR OTHER DEALINGS IN THE SOFTWARE";

        FileSegmentation fileSegmentation = new FileSegmentation(test, normalizationMetaData);
        Assertions.assertEquals(1, fileSegmentation.getFileSegments().size());
    }

    @Test
    public void testFileSegmentation002() {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        String test = "Redistribution and use in source and binary forms, with or without modification, are permitted " +
                "provided that the following conditions are met: LICENSE-SEGMENT-MARKER " +
                " ";

        FileSegmentation fileSegmentation = new FileSegmentation(test, normalizationMetaData);
        Assertions.assertEquals(1, fileSegmentation.getFileSegments().size());
    }
}
