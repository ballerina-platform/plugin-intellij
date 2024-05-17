# Plugin Features

The following features are supported by the plugin, as listed below.

- [Running Ballerina Programs](#running-ballerina-programs)
- [Import modules On-the-fly](#import-modules-on-the-fly)
- [Find Usage](#find-usage)
- [Go to Definition](#go-to-definition)
- [Formatting](#formatting)
- [Signature help](#signature-help)
- [Quick Documentation](#quick-documentation)
- [Code completions](#code-completions)
- [Code snippets](#code-snippets)
- [Code actions](#code-actions)
- [Error diagnostics](#error-diagnostics)
- [File Templates](#file-templates)
- [Spell Checking](#spell-checking)

## Running Ballerina Programs

You can run Ballerina main/service programs with only few clicks. You don't have to add or change any configurations.
You can click on the gutter icon, right click menu or the run button
to execute a ballerina program.

![alt text](images/run.gif)

You can also run ballerina tests easily like running ballerina programs with right click actions and gutter icons.

![alt text](images/test.gif)

You can customize run configurations with custom environment variables, command options and program arguments.

![alt text](images/runConfig.png)

## Import Modules On-the-fly

This feature adds import declarations on the fly. All you need to do is to select the module name from the lookup list and then the module declaration will be added automatically.

![alt text](images/import.gif)

## Find Usage

You can find usage of variables, functions, etc.

![alt text](images/usage.gif)

## Go to Definition

You can go to definition of variables, function invocations, etc by `Ctrl`+`Click` on the reference.

![alt text](images/definition.gif)

## Formatting

You can reformat the Ballerina codes by pressing `Ctrl`+`Alt`+`L`.

![alt text](images/format.gif)

## Signature help

You can view the required parameters of a functions, remote functions, etc. after adding parenthesis or comma while typing

![alt text](images/signature.gif)

## Quick Documentation

You can view the documentation of a functions, remote functions, etc by pressing `Ctrl`+`Q` or hovering over the element while pressing `Ctrl`.

![alt text](images/hover.gif)

## Code completions

Completion suggestions are provided for all identifiers, keywords, module imports, etc.

![alt text](images/completions.gif)

## Code Snippets

Code snippets contain boilerplate codes and allows you to enter them easily. This will be available in completion suggestions.

![alt text](images/snippet.gif)

## Code actions

Code actions are provided for various purposes such as adding missing imports, fixing code errors, adding missing documentation, etc.
Click on the light bulb icon or press `Alt`+`Enter` to view the available code actions after placing the cursor on the error or the code.

![alt text](images/codeActions.gif)

## Error diagnostics

Ballerina IDEA plugin provides semantic analyzing and diagnostics capabilities through Ballerina Language Server.  
These errors and warnings will be highlighted and you can view the error messages by hovering over the highlighted code.

![alt text](images/diagnostics.gif)

## File Templates

Three types of Ballerina file templates are available.
1) Ballerina Main - Contains a sample main program
2) Ballerina Service - Contains a sample service
3) Empty File

![alt text](images/fileTemp.gif)

## Spell Checking

Spell checking is enabled for all identifiers, comments and markdown text. You can get the suggested words by clicking the code action bulb.

![alt text](images/spell.gif)


