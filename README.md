# metæffekt Kosmos

## Structure

The primary data is located in 

    src/main/resources/ae-terms-metadata

For details see [ae-terms-metadata Structure](src/main/resources/ae-terms-metadata/README.md).

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

TODO: licensing

