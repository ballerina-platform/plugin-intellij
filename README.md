
# Ballerina plugin for IntelliJ IDEA

[![Version](https://img.shields.io/jetbrains/plugin/v/9520-ballerina.svg)](https://plugins.jetbrains.com/plugin/9520-ballerina)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/9520-ballerina.svg)](https://plugins.jetbrains.com/plugin/9520-ballerina)

---

## Overview
The Ballerina IntelliJ plugin provides the [Ballerina programming language](https://ballerina.io/) development capabilities within IntelliJ IDEA. While it offers a subset of features compared to the official Ballerina VSCode extension, it is designed to meet the specific needs of those working within the IntelliJ ecosystem.

> **Note:** This plugin is being developed as an experimental and community-driven project. The plugin is fully open-source, and we welcome contributions!

## Target Audience

This plugin is mainly targeted at **Ballerina library developers** who seek:
- Robust interoperability between Ballerina and Java
- A native development experience within IntelliJ IDEs

If you do not fall into the above categories, we recommend using the official [Ballerina VSCode extension](https://ballerina.io/vscode) for a more feature-rich experience.

## Plugin Guide

Click the links below for instructions on how to download, install, and use the features of the IntelliJ plugin.

- [**Installing the plugin**](getting-started/plugin-installation/README.md#installing-the-plugin)
    - [Installing using the ZIP file](getting-started/plugin-installation/README.md#installing-using-the-zip-file)
- [**Using the plugin**](getting-started/using-the-plugin/README.md#using-the-plugin)
    - [Setting up Ballerina SDK for an existing project](getting-started/using-the-plugin/setting-up-ballerina-sdk/README.md#setting-up-ballerina-sdk)
    - [Creating a new Ballerina file](getting-started/using-the-plugin/new-ballerina-project/README.md#create-new-ballerina-files)
- [**Using the features of the plugin**](getting-started/plugin-features/README.md#plugin-features)
  - [Running ballerina programs](getting-started/plugin-features/README.md#running-ballerina-programs)
  - [Running ballerina tests](getting-started/plugin-features/README.md#running-ballerina-tests)
  - [Import modules on-the-fly](getting-started/plugin-features/README.md#import-modules-on-the-fly)
  - [Find usage](getting-started/plugin-features/README.md#find-usage)
  - [Go to definition](getting-started/plugin-features/README.md#go-to-definition)
  - [Formatting](getting-started/plugin-features/README.md#formatting)
  - [Signature help](getting-started/plugin-features/README.md#signature-help)
  - [Quick documentation](getting-started/plugin-features/README.md#quick-documentation)
  - [Code completions](getting-started/plugin-features/README.md#code-completions)
  - [Live templates](getting-started/plugin-features/README.md#live-templates)
  - [Code actions](getting-started/plugin-features/README.md#code-actions)
  - [Error diagnostics](getting-started/plugin-features/README.md#error-diagnostics)
  - [Spell checking](getting-started/plugin-features/README.md#spell-checking)
  - [Code folding](getting-started/plugin-features/README.md#code-folding)
- [**Developer Guide**](getting-started/plugin-developer-guide/README.md#plugin-developer-guide)
    - [Testing/Debugging the plugin using IntelliJ](getting-started/plugin-developer-guide/README.md#testingdebugging-the-plugin-using-intellij-idea)

## Release Versions Schema

Below you can see the plugin versions that correspond to the versions of the IntelliJ Platform.

| **Plugin Version** | **Platform Version Compatibility** |
|:------------------:|:----------------------------------:|
|   0.8.0 - 0.8.2    |   IntelliJ IDEA 2016.3 - 2016.4    |
|  0.8.3 - 0.981.0   |   IntelliJ IDEA 2016.3 - 2017.2    |
| 0.982.0 - 0.991.0  |   IntelliJ IDEA 2017.3 - 2018.2    |
|  0.991.1 - 1.2.1   |   IntelliJ IDEA 2018.3 - 2019.3    |
|   1.2.2 - 1.2.x    |   IntelliJ IDEA 2018.3 - 2020.2    |
|       2.0.0        |   IntelliJ IDEA 2022.0 - 2024.1    |

## Useful links
* Use the ballerina-dev@googlegroups.com mailing list to discuss code changes to the Ballerina project.
* Chat live with us on our [Discord server](https://discord.gg/ballerinalang).
