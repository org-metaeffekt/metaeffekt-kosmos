# Tests

## _01_LicenseWorkbench

The main method in licenseWorkbench can be executed when actively working on tmd. As soon as a yaml file is changed the
test checks if the yaml file can be uniquely identified.
It also executes a full scan on the files that are placed in the following folder:
* src > test > resources > workbench > terms-metadata.test > 999-test > input

The results of this scan are generated into the input-analysis folder.

## _02_IncrementalTests

Incremental Tests should be executed after some changes have been made. They check for general mistakes in the yaml
files, e.g. convention violations.

## _03_OverallTests

OverallTest should be executed before committing to a VCS. This test cross-checks all licenses on a detailed level and
executes IncrementalTests.

