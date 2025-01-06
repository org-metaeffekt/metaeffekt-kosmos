# metæffekt Kosmos

## Structure

The primary data is located in

    src/main/resources/ae-terms-metadata

For details see [ae-terms-metadata Structure](https://github.com/org-metaeffekt/metaeffekt-documentation/blob/main/metaeffekt-universe/docs/yaml-format.md).

## Format Definition

The TMD is based on definitions of licenses or other terms and conditions (terms). The definition are expressed in
a YAML format. See [YAML Format](doc/yaml-format.md).

## Tests

We have several tests, validating our database to guarantee explicit matching as well as a correct usage of our
conventions.

Tests can be found under:

    src > test > java > org > metaeffekt > terms

For details see [test](src/test/README.md).

## References

The metæffekt Kosmos is build based on various external sources including:

* https://fedoraproject.org/wiki/Licensing:Main?rd=Licensing
* https://spdx.org/licenses/
* https://spdx.org/licenses/exceptions-index.html
* https://github.com/nexB/scancode-toolkit
* https://wikijs.opencode.de/de/Hilfestellungen_und_Richtlinien/Lizenzcompliance

## Licensing

- [SPDX](https://spdx.org/licenses/) - The license list and license templates are used under
  [Creative Commons BY-3.0](http://spdx.org/licenses/CC-BY-3.0).

  © 2018 SPDX Workgroup a Linux Foundation Project. All Rights Reserved.

- [ScanCode Toolkit](https://github.com/nexB/scancode-toolkit) - ScanCode license data is used under
  [Creative Commons BY-4.0](https://github.com/nexB/scancode-toolkit/blob/develop/cc-by-4.0.LICENSE).

  Copyright (c) nexB Inc. and others. All rights reserved.
  ScanCode is a trademark of nexB Inc.

- [Open CoDE License Compliance](https://wikijs.opencode.de/de/Hilfestellungen_und_Richtlinien/Lizenzcompliance)
  approval information is Open CoDE Public Domain. The status information shown reflects version 1.4 from
  January 2024.

The content provided in {metæffekt}-universe is licensed under [Creative Commons BY-4.0](LICENSE).

Copyright © metaeffekt GmbH 2021-2025. All rights reserved.

## Contribution
As this repository is largely showing converted data. A direct contribution in the form of patches is
currently not anticipated. You may nevertheless create issues on Github, create branches and pull requests, 
or send requests for corrections or questions to [contact@metaeffekt.com](mailto:contact@metaeffekt.com). 
Modifications will then be applied to the internal dataset to produce the corrected outputs.
