# Change Log
All notable changes to this project will be documented in this file.

## [2.5.0] - 2017-04-26
### Added
- Improvements made by @thanhlcm90

## [2.4.0] - 2017-04-02
### Changed
- Using Gradle plugin version 2.3.0
- Overridden method `toString()` for class `ScenarioConfig` 

## [2.2.0] - 2017-03-17
### Removed
- Unused third party libraries

## [2.1.1] - 2017-03-02
### Added
- Added the helper methods `closeKeyboard()` and `pressBack()`
- Added method `beforeScenarioStarts(Scenario scenario, Locale locale)`

## [2.0.1] - 2017-02-24
### Added
- Support to launch the scenarios with different locales
- Support to take screenshots during the tests
- This CHANGELOG

### Changed
- The constructor of `GreenCoffeeTest`
- Updated to latest version of [Gherkin java](https://github.com/cucumber/gherkin-java)

### Removed
- Example app. Moved [here](https://github.com/vndly/green-coffee-example)

## [1.0.0] - 2016-09-04
### Added
- First stable version of the library