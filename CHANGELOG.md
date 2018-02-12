# Change Log
All notable changes to this project are documented in this file.

## [3.0.0] - 2018-02-12
### Changed
- Updated to Java 8
- Updated Android build tools, compile version and Gradle version
- Updated visibility of some classes and methods
- Some constructor parameters of the class `ScenarioConfig` are optional

## [2.9.0] - 2017-09-18
### Changed
- Using Espresso `3.0.1`

## [2.8.1] - 2017-07-06
### Added
- Added method `text()` to `ActionableObject`
- Added method `onViewWithAll(Matcher<? super View>... matchers)`
- Added class `TimedIdlingResource`

## [2.8.0] - 2017-07-05
### Added
- Added boolean methods for actionable elements
- Improved data matchers

## [2.7.0] - 2017-07-04
### Added
- Added methods `waitFor(long millis)` and `waitFor(long value, TimeUnit timeUnit)`
- Added class `SpinnerMatcher`

## [2.6.0] - 2017-04-28
### Added
- Improvements in `ActionableView` (by @thanhlcm90)
- Added callback `afterScenarioEnds(Scenario scenario, Locale locale)`
- Added method `grantPermission(String permission)`

## [2.5.0] - 2017-04-26
### Added
- Improvements in `ActionableView` (by @thanhlcm90)

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