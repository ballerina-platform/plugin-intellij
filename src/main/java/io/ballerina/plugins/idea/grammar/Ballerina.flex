/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.ballerina.plugins.idea;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.ballerina.plugins.idea.psi.BallerinaTypes;

%%

%{
    private boolean inTopLevelDefinition = false;
    private int braceCount = 0;
    private boolean rightBraceFound = true;

    private boolean inTopLevelDefinitionPipe = false;
    private int bracePipeCount = 0;
    private boolean rightBracePipeFound = true;
%}

%class BallerinaLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType


Digit = [0-9]
NonZeroDigit = [1-9]
DecimalNumber = (0|{NonZeroDigit}{Digit}*)
HexIndicator = 0[xX]
HexDigit = ({Digit}|[a-fA-F])
HexNumber = {HexDigit}+
HexIntLiteral = {HexIndicator}{HexNumber}
Sign = [+-]
ExponentIndicator = [eE]
HexExponentIndicator = [pP]
DecimalTypeSuffix = [dD]
FloatTypeSuffix = [fF]
FloatingPointTypeSuffix = ({DecimalTypeSuffix}|{FloatTypeSuffix})
Exponent = {ExponentIndicator}{Sign}?{Digit}+
DottedDecimalNumber = ({DecimalNumber}"."{Digit}+|"."{Digit}+)
DecimalFloatingPointNumber = ({DecimalNumber}{Exponent}{FloatingPointTypeSuffix}?|{DottedDecimalNumber}{Exponent}?{FloatingPointTypeSuffix}?|{DecimalNumber}{FloatingPointTypeSuffix})
HexExponent = {HexExponentIndicator}{Sign}?{Digit}+
DottedHexNumber = ({HexDigit}+"."{HexDigit}+|"."{HexDigit}+)
HexFloatingPointNumber = ({HexNumber}{HexExponent}|{DottedHexNumber}{HexExponent}?)
HexFloatingPointLiteral = {HexIndicator}{HexFloatingPointNumber}

CodePoint = {HexDigit}+
NumericEscape = \\u{CodePoint}
StringChar = [^\r\n\\\"]
StringSingleEscape = (\t | \n | \r | \\ | \\\")
StringEscape = ({StringSingleEscape}|{NumericEscape})
DoubleQuotedStringLiteral = \"({StringChar}|{StringEscape})*\"
stringLiteral = {DoubleQuotedStringLiteral}

Space = \u0020
Tab = \u0009
BlankSpace = ({Space}|{Tab})

IDENTIFIER = {UnquotedIdentifier} | {QuotedIdentifier}
UnquotedIdentifier = ({IdentifierInitialChar} | {IdentifierEscape}) ({IdentifierFollowingChar} | {IdentifierEscape})*
QuotedIdentifier = \' ({IdentifierFollowingChar} | {IdentifierEscape})+
IdentifierInitialChar =  {AsciiLetter} | _ | {UnicodeIdentifierChar}
IdentifierFollowingChar = {IdentifierInitialChar} | {Digit}
IdentifierEscape = {IdentifierSingleEscape} | {NumericEscape}
IdentifierSingleEscape = \\ [^a-zA-Z\u0009\u000A\u000D\u200E\u200F\u2028\u2029]
NumericEscape = \\"u{" {CodePoint} "}"
UnicodeIdentifierChar = [a-zA-Z_]
                           // Negates ( AsciiChar | UnicodeNonIdentifierChar )
                           | [^\u0000-\u007F\uE000-\uF8FF\u200E\u200F\u2028\u2029\u00A1-\u00A7\u00A9\u00AB-\u00AC\u00AE\u00B0-\u00B1\u00B6-\u00B7\u00BB\u00BF\u00D7\u00F7\u2010-\u2027\u2030-\u205E\u2190-\u2BFF\u3001-\u3003\u3008-\u3020\u3030\uFD3E-\uFD3F\uFE45-\uFE46\uDB80-\uDBBF\uDBC0-\uDBFF\uDC00-\uDFFF]


// check again
AsciiLetter = [a-zA-Z]
AnyCharButNewline = [^\n]  // Assumes 0xA is the newline character.
WhiteSpaceChar = [\t\n\r ] // 0x9 is tab, 0xA is newline, 0xD is carriage return, and 0x20 is space.

Comment = \/\/{AnyCharButNewline}*


STRING_TEMPLATE_LITERAL_START = {BACKTICK} | string[ \t\n\x0B\f\r]*`
STRING_TEMPLATE_LITERAL_END = \`
INTERPOLATION_START = "${"
STRING_LITERAL_ESCAPED_SEQUENCE = {DOLLAR}** \\ [\\'\"bnftr\{`]
STRING_TEMPLATE_VALID_CHAR_SEQUENCE = [^`$\\] | {DOLLAR}+ [^`$\{\\] | {BlankSpace} | {STRING_LITERAL_ESCAPED_SEQUENCE}
STRING_TEMPLATE_EXPRESSION_START = {INTERPOLATION_START}
STRING_TEMPLATE_TEXT = {STRING_TEMPLATE_VALID_CHAR_SEQUENCE}+
DOLLAR = \$


// MARKDOWN_DOCUMENTATION
MARKDOWN_DOCUMENTATION_LINE_START =  {HASH} {DOCUMENTATION_SPACE}?
PARAMETER_DOCUMENTATION_START = {HASH} {DOCUMENTATION_SPACE}? {ADD} {DOCUMENTATION_SPACE}*
RETURN_PARAMETER_DOCUMENTATION_START = {HASH} {DOCUMENTATION_SPACE}? {ADD} {DOCUMENTATION_SPACE}* {RETURN} {DOCUMENTATION_SPACE}* {SUB} {DOCUMENTATION_SPACE}*
DEPRECATED_DOCUMENTATION = {HASH} {DOCUMENTATION_SPACE} {HASH} {DOCUMENTATION_SPACE} {DEPRECATED} {DOCUMENTATION_SPACE}*
DEPRECATED_PARAMETER_DOCUMENTATION = {HASH} {DOCUMENTATION_SPACE} {HASH} {DOCUMENTATION_SPACE} {DEPRECATED_PARAMETERS} {DOCUMENTATION_SPACE}*
DEPRECATED = "Deprecated"
DEPRECATED_PARAMETERS = "Deprecated parameters"

DOCUMENTATION_SPACE = [ ]
BACKTICK = "`"

// MARKDOWN_DOCUMENTATION_MODE
DOCTYPE = "type" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCSERVICE = "service" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCVARIABLE = "variable" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCVAR = "var" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCANNOTATION = "annotation" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCMODULE = "module" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCFUNCTION = "function" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCPARAMETER = "parameter" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
DOCCONST = "const" {DOCUMENTATION_ESCAPED_CHARACTERS}+ {SINGLE_BACKTICK_MARKDOWN_START}
MARKDOWN_DOCUMENTATION_TEXT = {DOCUMENTATION_TEXT_CHARACTER}+
DOCUMENTATION_TEXT_CHARACTER =  [^`\n\r] | '\\' {BACKTICK}
DOCUMENTATION_ESCAPED_CHARACTERS = {DOCUMENTATION_SPACE}
MARKDOWN_DOCUMENTATION_LINE_END = [\n\r]

HASH = "#"
ADD = "+"
SUB = "-"
RETURN = "return"

// SINGLE_BACKTICKED_MARKDOWN
SINGLE_BACKTICK_CONTENT = (([^`] | '\\' {BACKTICK})* [\n])? ({MARKDOWN_DOCUMENTATION_LINE_START} ([^`] | '\\' {BACKTICK})* [\n]?)+ | ([^`] | '\\' {BACKTICK})+
SINGLE_BACKTICK_MARKDOWN_START = {BACKTICK}
SINGLE_BACKTICK_MARKDOWN_END =  {BACKTICK}

// MARKDOWN_PARAMETER_DOCUMENTATION
PARAMETER_NAME = {UnquotedIdentifier} | {QuotedIdentifier}
DESCRIPTION_SEPARATOR = {DOCUMENTATION_SPACE}* {SUB} {DOCUMENTATION_SPACE}*
PARAMETER_DOCUMENTATION_END = [\n]

backtick_start = xml[ \t\n\x0B\f\r]*` | re[ \t\n\x0B\f\r]*`
BACKTICK_ALL_CHAR = ([^`\\]|\\.)+
BACKTICK_LITERAL_END = "`"

%state TEMPLATE_MODE
%state END_MODE
%state MARKDOWN_DOCUMENTATION_MODE
%state MARKDOWN_PARAMETER_DOCUMENTATION_MODE
%state SINGLE_BACKTICKED_MARKDOWN_MODE
%state REEGX_MODE
%state FLAG_MODE
%state TEST
%state BACKTICK_MODE
%state EXPR_BACKTICK_MODE



%% // Start of lexical rules

<YYINITIAL> {
    "int"                        { return BallerinaTypes.INT_KEYWORD; }
    "float"                      { return BallerinaTypes.FLOAT_KEYWORD; }
    "string"                     { return BallerinaTypes.STRING_KEYWORD; }
    "boolean"                    { return BallerinaTypes.BOOLEAN_KEYWORD; }
    "decimal"                    { return BallerinaTypes.DECIMAL_KEYWORD; }
    "xml"                        { return BallerinaTypes.XML_KEYWORD; }
    "json"                       { return BallerinaTypes.JSON_KEYWORD; }
    "handle"                     { return BallerinaTypes.HANDLE_KEYWORD; }
    "any"                        { return BallerinaTypes.ANY_KEYWORD; }
    "anydata"                    { return BallerinaTypes.ANYDATA_KEYWORD; }
    "never"                      { return BallerinaTypes.NEVER_KEYWORD; }
    "byte"                       { return BallerinaTypes.BYTE_KEYWORD; }
    "public"                     { return BallerinaTypes.PUBLIC_KEYWORD; }
    "private"                    { return BallerinaTypes.PRIVATE_KEYWORD; }
    "function"                   { return BallerinaTypes.FUNCTION_KEYWORD; }
    "return"                     { return BallerinaTypes.RETURN_KEYWORD; }
    "returns"                    { return BallerinaTypes.RETURNS_KEYWORD; }
    "external"                   { return BallerinaTypes.EXTERNAL_KEYWORD; }
    "type"                       { return BallerinaTypes.TYPE_KEYWORD; }
    "record"                     { return BallerinaTypes.RECORD_KEYWORD; }
    "object"                     { return BallerinaTypes.OBJECT_KEYWORD; }
    "remote"                     { return BallerinaTypes.REMOTE_KEYWORD; }
    "abstract"                   { return BallerinaTypes.ABSTRACT_KEYWORD; }
    "client"                     { return BallerinaTypes.CLIENT_KEYWORD; }
    "if"                         { return BallerinaTypes.IF_KEYWORD; }
    "else if"                    { return BallerinaTypes.ELSEIF_KEYWORD; }
    "else"                       { return BallerinaTypes.ELSE_KEYWORD; }
    "while"                      { return BallerinaTypes.WHILE_KEYWORD; }
    "true"                       { return BallerinaTypes.TRUE_KEYWORD; }
    "false"                      { return BallerinaTypes.FALSE_KEYWORD; }
    "check"                      { return BallerinaTypes.CHECK_KEYWORD; }
    "fail"                       { return BallerinaTypes.FAIL_KEYWORD; }
    "checkpanic"                 { return BallerinaTypes.CHECKPANIC_KEYWORD; }
    "continue"                   { return BallerinaTypes.CONTINUE_KEYWORD; }
    "break"                      { return BallerinaTypes.BREAK_KEYWORD; }
    "panic"                      { return BallerinaTypes.PANIC_KEYWORD; }
    "import"                     { return BallerinaTypes.IMPORT_KEYWORD; }
    "as"                         { return BallerinaTypes.AS_KEYWORD; }
    "service"                    { return BallerinaTypes.SERVICE_KEYWORD; }
    "on"                         { return BallerinaTypes.ON_KEYWORD; }
    "resource"                   { return BallerinaTypes.RESOURCE_KEYWORD; }
    "listener"                   { return BallerinaTypes.LISTENER_KEYWORD; }
    "const"                      { return BallerinaTypes.CONST_KEYWORD; }
    "final"                      { return BallerinaTypes.FINAL_KEYWORD; }
    "typeof"                     { return BallerinaTypes.TYPEOF_KEYWORD; }
    "is"                         { return BallerinaTypes.IS_KEYWORD; }
    "null"                       { return BallerinaTypes.NULL_KEYWORD; }
    "lock"                       { return BallerinaTypes.LOCK_KEYWORD; }
    "annotation"                 { return BallerinaTypes.ANNOTATION_KEYWORD; }
    "source"                     { return BallerinaTypes.SOURCE_KEYWORD; }
    "var"                        { return BallerinaTypes.VAR_KEYWORD; }
    "worker"                     { return BallerinaTypes.WORKER_KEYWORD; }
    "parameter"                  { return BallerinaTypes.PARAMETER_KEYWORD; }
    "field"                      { return BallerinaTypes.FIELD_KEYWORD; }
    "isolated"                   { return BallerinaTypes.ISOLATED_KEYWORD; }
    "xmlns"                      { return BallerinaTypes.XMLNS_KEYWORD; }
    "fork"                       { return BallerinaTypes.FORK_KEYWORD; }
    "map"                        { return BallerinaTypes.MAP_KEYWORD; }
    "future"                     { return BallerinaTypes.FUTURE_KEYWORD; }
    "typedesc"                   { return BallerinaTypes.TYPEDESC_KEYWORD; }
    "trap"                       { return BallerinaTypes.TRAP_KEYWORD; }
    "in"                         { return BallerinaTypes.IN_KEYWORD; }
    "foreach"                    { return BallerinaTypes.FOREACH_KEYWORD; }
    "table"                      { return BallerinaTypes.TABLE_KEYWORD; }
    "error"                      { return BallerinaTypes.ERROR_KEYWORD; }
    "let"                        { return BallerinaTypes.LET_KEYWORD; }
    "stream"                     { return BallerinaTypes.STREAM_KEYWORD; }
    "new"                        { return BallerinaTypes.NEW_KEYWORD; }
    "readonly"                   { return BallerinaTypes.READONLY_KEYWORD; }
    "distinct"                   { return BallerinaTypes.DISTINCT_KEYWORD; }
    "from"                       { return BallerinaTypes.FROM_KEYWORD; }
    "start"                      { return BallerinaTypes.START_KEYWORD; }
    "flush"                      { return BallerinaTypes.FLUSH_KEYWORD; }
    "wait"                       { return BallerinaTypes.WAIT_KEYWORD; }
    "do"                         { return BallerinaTypes.DO_KEYWORD; }
    "transaction"                { return BallerinaTypes.TRANSACTION_KEYWORD; }
    "commit"                     { return BallerinaTypes.COMMIT_KEYWORD; }
    "retry"                      { return BallerinaTypes.RETRY_KEYWORD; }
    "rollback"                   { return BallerinaTypes.ROLLBACK_KEYWORD; }
    "transactional"              { return BallerinaTypes.TRANSACTIONAL_KEYWORD; }
    "enum"                       { return BallerinaTypes.ENUM_KEYWORD; }
    "base16"                     { return BallerinaTypes.BASE16_KEYWORD; }
    "base64"                     { return BallerinaTypes.BASE64_KEYWORD; }
    "match"                      { return BallerinaTypes.MATCH_KEYWORD; }
    "conflict"                   { return BallerinaTypes.CONFLICT_KEYWORD; }
    "class"                      { return BallerinaTypes.CLASS_KEYWORD; }
    "configurable"               { return BallerinaTypes.CONFIGURABLE_KEYWORD; }
    "where"                      { return BallerinaTypes.WHERE_KEYWORD; }
    "select"                     { return BallerinaTypes.SELECT_KEYWORD; }
    "limit"                      { return BallerinaTypes.LIMIT_KEYWORD; }
    "outer"                      { return BallerinaTypes.OUTER_KEYWORD; }
    "equals"                     { return BallerinaTypes.EQUALS_KEYWORD; }
    "order"                      { return BallerinaTypes.ORDER_KEYWORD; }
    "by"                         { return BallerinaTypes.BY_KEYWORD; }
    "ascending"                  { return BallerinaTypes.ASCENDING_KEYWORD; }
    "descending"                 { return BallerinaTypes.DESCENDING_KEYWORD; }
    "join"                       { return BallerinaTypes.JOIN_KEYWORD; }
    "key"                        { return BallerinaTypes.KEY_KEYWORD; }
    "collect"                        { return BallerinaTypes.COLLECT_KEYWORD; }
    {IDENTIFIER}                 { return BallerinaTypes.IDENTIFIER_TOKEN; }

    //---------------------------------//

        ":"                          { return BallerinaTypes.COLON_TOKEN; }
        ";"                          { return BallerinaTypes.SEMICOLON_TOKEN;
                                     }

        {DecimalFloatingPointNumber} { return BallerinaTypes.DECIMAL_FLOATING_POINT_LITERAL_TOKEN;}
        {HexFloatingPointLiteral}    { return BallerinaTypes.HEX_FLOATING_POINT_LITERAL_TOKEN;}

        // check import
        {HexIntLiteral}              { return BallerinaTypes.HEX_INTEGER_LITERAL_TOKEN;}
        {DecimalNumber}              { return BallerinaTypes.DECIMAL_INTEGER_LITERAL_TOKEN;}

        "..."                        { return BallerinaTypes.ELLIPSIS_TOKEN;}
        "..<"                        { return BallerinaTypes.DOUBLE_DOT_LT_TOKEN;}
        ".@"                         { return BallerinaTypes.ANNOT_CHAINING_TOKEN;}
        ".<"                         { return BallerinaTypes.DOT_LT_TOKEN;}
        "."                          { return BallerinaTypes.DOT_TOKEN; }


        ","                          { return BallerinaTypes.COMMA_TOKEN; }
        "("                          { return BallerinaTypes.OPEN_PAREN_TOKEN; }
        ")"                          { return BallerinaTypes.CLOSE_PAREN_TOKEN; }

        "{|"                         { if (inTopLevelDefinitionPipe) {
                                        bracePipeCount++;
                                        if (bracePipeCount==2) {
                                            return BallerinaTypes.OPEN_NESTED_BRACE_PIPE_TOKEN;
                                        }
                                        return BallerinaTypes.IGNORED_OPEN_BRACE_PIPE_TOKEN;
                                        } else {
                                            inTopLevelDefinitionPipe = true;
                                            bracePipeCount++;
                                            return BallerinaTypes.OPEN_BRACE_PIPE_TOKEN;
                                       }

                                     }
        "{"                          { if (inTopLevelDefinition) {
                                         braceCount++;
                                         if (braceCount==2) {
                                             return BallerinaTypes.OPEN_NESTED_BRACE_TOKEN;
                                         }
                                         return BallerinaTypes.IGNORED_OPEN_BRACE_TOKEN;
                                         } else {
                                             inTopLevelDefinition = true;
                                             braceCount++;
                                             return BallerinaTypes.OPEN_BRACE_TOKEN;
                                        }
                                     }

        "}"                          {
                                            braceCount--;
                                            if (braceCount==1) {
                                                return BallerinaTypes.CLOSE_NESTED_BRACE_TOKEN;
                                            } else if (braceCount <= 0) {
                                                inTopLevelDefinition = false;
                                                return BallerinaTypes.CLOSE_BRACE_TOKEN;
                                            }

                                            return BallerinaTypes.IGNORED_CLOSE_BRACE_TOKEN;

                                        }
        "["                          { return BallerinaTypes.OPEN_BRACKET_TOKEN; }
        "]"                          { return BallerinaTypes.CLOSE_BRACKET_TOKEN; }

        "|}"                         { bracePipeCount--;
                                       if (bracePipeCount==1) {
                                           return BallerinaTypes.CLOSE_NESTED_BRACE_PIPE_TOKEN;
                                       } else if (bracePipeCount <= 0) {
                                           inTopLevelDefinitionPipe = false;
                                           return BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN;
                                       }

                                       return BallerinaTypes.IGNORED_CLOSE_BRACE_PIPE_TOKEN;
                                     }
        "||"                         { return BallerinaTypes.LOGICAL_OR_TOKEN;}
        "|"                          { return BallerinaTypes.PIPE_TOKEN;}

        "?."                         {return BallerinaTypes.OPTIONAL_CHAINING_TOKEN;}
        "?:"                         {return BallerinaTypes.ELVIS_TOKEN;}
        "?"                          {return BallerinaTypes.QUESTION_MARK_TOKEN;}

        {stringLiteral}              { return BallerinaTypes.STRING_LITERAL_TOKEN;}

        "@"                          { return BallerinaTypes.AT_TOKEN; }

        "==="                        { return BallerinaTypes.TRIPPLE_EQUAL_TOKEN; }
        "=="                         { return BallerinaTypes.DOUBLE_EQUAL_TOKEN; }
        "=>"                         { return BallerinaTypes.RIGHT_DOUBLE_ARROW_TOKEN; }
        "="                          { return BallerinaTypes.EQUAL_TOKEN; }

        "+"                          { return BallerinaTypes.PLUS_TOKEN; }

        "->>"                        { return BallerinaTypes.SYNC_SEND_TOKEN;}
        "->"                         { return BallerinaTypes.RIGHT_ARROW_TOKEN;}
        "-"                          { return BallerinaTypes.MINUS_TOKEN;}

        "*"                          { return BallerinaTypes.ASTERISK_TOKEN; }


        "/**/<"                      { return BallerinaTypes.DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN; }
        "/*"                         { return BallerinaTypes.SLASH_ASTERISK_TOKEN; }
        "/"                          { return BallerinaTypes.SLASH_TOKEN; }

        "%"                          { return BallerinaTypes.PERCENT_TOKEN; }

        "<<"                         {return BallerinaTypes.DOUBLE_LT_TOKEN; }
        "<="                         {return BallerinaTypes.LT_EQUAL_TOKEN; }
        "<-"                         {return BallerinaTypes.LEFT_ARROW_TOKEN; }
        "<"                          {return BallerinaTypes.LT_TOKEN; }

        ">="                         {return BallerinaTypes.GT_EQUAL_TOKEN; }
        ">"                          {return BallerinaTypes.GT_TOKEN; }

        "!=="                        {return BallerinaTypes.NOT_DOUBLE_EQUAL_TOKEN; }
        "!="                         {return BallerinaTypes.NOT_EQUAL_TOKEN; }
        "!"                          {return BallerinaTypes.EXCLAMATION_MARK_TOKEN; }


        "&&"                         {return BallerinaTypes.LOGICAL_AND_TOKEN;}
        "&"                          {return BallerinaTypes.BITWISE_AND_TOKEN;}

        "^"                          { return BallerinaTypes.BITWISE_XOR_TOKEN; }
        "~"                          { return BallerinaTypes.NEGATION_TOKEN; }

        // check again
        {STRING_TEMPLATE_LITERAL_START}                          {  yybegin(TEMPLATE_MODE);
                                                                    return BallerinaTypes.STRING_TEMPLATE_START_TOKEN;
                                                                    }


        {Comment}                    { return BallerinaTypes.COMMENT_MINUTIAE;}

    //--------------------------------//
    {backtick_start}                  { yybegin(BACKTICK_MODE); return BallerinaTypes.STRING_TEMPLATE_START_TOKEN;}


    {DEPRECATED_DOCUMENTATION}                  { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.DEPRECATED_DOCUMENTATION; }
    {DEPRECATED_PARAMETER_DOCUMENTATION}        { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.DEPRECATED_PARAMETER_DOCUMENTATION; }
    {RETURN_PARAMETER_DOCUMENTATION_START}      { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START; }
    {PARAMETER_DOCUMENTATION_START}             { yybegin(MARKDOWN_PARAMETER_DOCUMENTATION_MODE); return BallerinaTypes.PARAMETER_DOCUMENTATION_START; }
    {MARKDOWN_DOCUMENTATION_LINE_START}         { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START; }

    {WhiteSpaceChar}             { return BallerinaTypes.WHITESPACE_MINUTIAE;}

    .                            { return BallerinaTypes.INVALID_TOKEN; }
}

<BACKTICK_MODE>{
    {BACKTICK_ALL_CHAR}          { return BallerinaTypes.TEMPLATE_STRING;}
    {BACKTICK_LITERAL_END}       { yybegin(YYINITIAL); return BallerinaTypes.STRING_TEMPLATE_END_TOKEN;}
    .                            { return BallerinaTypes.INVALID_TOKEN; }
}

<EXPR_BACKTICK_MODE>{
    {BACKTICK_ALL_CHAR}          { return BallerinaTypes.TEMPLATE_STRING;}
    {BACKTICK_LITERAL_END}       { yybegin(TEST); return BallerinaTypes.STRING_TEMPLATE_END_TOKEN;}
    .                            { return BallerinaTypes.INVALID_TOKEN; }
}

// needs more work
<TEMPLATE_MODE> {
    {STRING_TEMPLATE_LITERAL_END}                          {
                                                                yybegin(YYINITIAL);
                                                                return BallerinaTypes.STRING_TEMPLATE_END_TOKEN;
                                                            }
    {DOLLAR}*{STRING_TEMPLATE_EXPRESSION_START}                         { yybegin(TEST); return BallerinaTypes.INTERPOLATION_START_TOKEN;  }
    {STRING_TEMPLATE_TEXT}                       {return BallerinaTypes.TEMPLATE_STRING;  }
    .                            {yybegin(YYINITIAL); return BallerinaTypes.INVALID_TOKEN; }
}

// Todo: for now, we are using this mode to handle expressions inside the string template interpolations. Need to revisit this.

<TEST> {
           "int"                        { return BallerinaTypes.INT_KEYWORD; }
           "float"                      { return BallerinaTypes.FLOAT_KEYWORD; }
           "string"                     { return BallerinaTypes.STRING_KEYWORD; }
           "boolean"                    { return BallerinaTypes.BOOLEAN_KEYWORD; }
           "decimal"                    { return BallerinaTypes.DECIMAL_KEYWORD; }
           "xml"                        { return BallerinaTypes.XML_KEYWORD; }
           "json"                       { return BallerinaTypes.JSON_KEYWORD; }
           "handle"                     { return BallerinaTypes.HANDLE_KEYWORD; }
           "any"                        { return BallerinaTypes.ANY_KEYWORD; }
           "anydata"                    { return BallerinaTypes.ANYDATA_KEYWORD; }
           "never"                      { return BallerinaTypes.NEVER_KEYWORD; }
           "byte"                       { return BallerinaTypes.BYTE_KEYWORD; }
           "public"                     { return BallerinaTypes.PUBLIC_KEYWORD; }
           "private"                    { return BallerinaTypes.PRIVATE_KEYWORD; }
           "function"                   { return BallerinaTypes.FUNCTION_KEYWORD; }
           "return"                     { return BallerinaTypes.RETURN_KEYWORD; }
           "returns"                    { return BallerinaTypes.RETURNS_KEYWORD; }
           "external"                   { return BallerinaTypes.EXTERNAL_KEYWORD; }
           "type"                       { return BallerinaTypes.TYPE_KEYWORD; }
           "record"                     { return BallerinaTypes.RECORD_KEYWORD; }
           "object"                     { return BallerinaTypes.OBJECT_KEYWORD; }
           "remote"                     { return BallerinaTypes.REMOTE_KEYWORD; }
           "abstract"                   { return BallerinaTypes.ABSTRACT_KEYWORD; }
           "client"                     { return BallerinaTypes.CLIENT_KEYWORD; }
           "if"                         { return BallerinaTypes.IF_KEYWORD; }
           "else if"                    { return BallerinaTypes.ELSEIF_KEYWORD; }
           "else"                       { return BallerinaTypes.ELSE_KEYWORD; }
           "while"                      { return BallerinaTypes.WHILE_KEYWORD; }
           "true"                       { return BallerinaTypes.TRUE_KEYWORD; }
           "false"                      { return BallerinaTypes.FALSE_KEYWORD; }
           "check"                      { return BallerinaTypes.CHECK_KEYWORD; }
           "fail"                       { return BallerinaTypes.FAIL_KEYWORD; }
           "checkpanic"                 { return BallerinaTypes.CHECKPANIC_KEYWORD; }
           "continue"                   { return BallerinaTypes.CONTINUE_KEYWORD; }
           "break"                      { return BallerinaTypes.BREAK_KEYWORD; }
           "panic"                      { return BallerinaTypes.PANIC_KEYWORD; }
           "import"                     { return BallerinaTypes.IMPORT_KEYWORD; }
           "as"                         { return BallerinaTypes.AS_KEYWORD; }
           "service"                    { return BallerinaTypes.SERVICE_KEYWORD; }
           "on"                         { return BallerinaTypes.ON_KEYWORD; }
           "resource"                   { return BallerinaTypes.RESOURCE_KEYWORD; }
           "listener"                   { return BallerinaTypes.LISTENER_KEYWORD; }
           "const"                      { return BallerinaTypes.CONST_KEYWORD; }
           "final"                      { return BallerinaTypes.FINAL_KEYWORD; }
           "typeof"                     { return BallerinaTypes.TYPEOF_KEYWORD; }
           "is"                         { return BallerinaTypes.IS_KEYWORD; }
           "null"                       { return BallerinaTypes.NULL_KEYWORD; }
           "lock"                       { return BallerinaTypes.LOCK_KEYWORD; }
           "annotation"                 { return BallerinaTypes.ANNOTATION_KEYWORD; }
           "source"                     { return BallerinaTypes.SOURCE_KEYWORD; }
           "var"                        { return BallerinaTypes.VAR_KEYWORD; }
           "worker"                     { return BallerinaTypes.WORKER_KEYWORD; }
           "parameter"                  { return BallerinaTypes.PARAMETER_KEYWORD; }
           "field"                      { return BallerinaTypes.FIELD_KEYWORD; }
           "isolated"                   { return BallerinaTypes.ISOLATED_KEYWORD; }
           "xmlns"                      { return BallerinaTypes.XMLNS_KEYWORD; }
           "fork"                       { return BallerinaTypes.FORK_KEYWORD; }
           "map"                        { return BallerinaTypes.MAP_KEYWORD; }
           "future"                     { return BallerinaTypes.FUTURE_KEYWORD; }
           "typedesc"                   { return BallerinaTypes.TYPEDESC_KEYWORD; }
           "trap"                       { return BallerinaTypes.TRAP_KEYWORD; }
           "in"                         { return BallerinaTypes.IN_KEYWORD; }
           "foreach"                    { return BallerinaTypes.FOREACH_KEYWORD; }
           "table"                      { return BallerinaTypes.TABLE_KEYWORD; }
           "error"                      { return BallerinaTypes.ERROR_KEYWORD; }
           "let"                        { return BallerinaTypes.LET_KEYWORD; }
           "stream"                     { return BallerinaTypes.STREAM_KEYWORD; }
           "new"                        { return BallerinaTypes.NEW_KEYWORD; }
           "readonly"                   { return BallerinaTypes.READONLY_KEYWORD; }
           "distinct"                   { return BallerinaTypes.DISTINCT_KEYWORD; }
           "from"                       { return BallerinaTypes.FROM_KEYWORD; }
           "start"                      { return BallerinaTypes.START_KEYWORD; }
           "flush"                      { return BallerinaTypes.FLUSH_KEYWORD; }
           "wait"                       { return BallerinaTypes.WAIT_KEYWORD; }
           "do"                         { return BallerinaTypes.DO_KEYWORD; }
           "transaction"                { return BallerinaTypes.TRANSACTION_KEYWORD; }
           "commit"                     { return BallerinaTypes.COMMIT_KEYWORD; }
           "retry"                      { return BallerinaTypes.RETRY_KEYWORD; }
           "rollback"                   { return BallerinaTypes.ROLLBACK_KEYWORD; }
           "transactional"              { return BallerinaTypes.TRANSACTIONAL_KEYWORD; }
           "enum"                       { return BallerinaTypes.ENUM_KEYWORD; }
           "base16"                     { return BallerinaTypes.BASE16_KEYWORD; }
           "base64"                     { return BallerinaTypes.BASE64_KEYWORD; }
           "match"                      { return BallerinaTypes.MATCH_KEYWORD; }
           "conflict"                   { return BallerinaTypes.CONFLICT_KEYWORD; }
           "class"                      { return BallerinaTypes.CLASS_KEYWORD; }
           "configurable"               { return BallerinaTypes.CONFIGURABLE_KEYWORD; }
           "where"                      { return BallerinaTypes.WHERE_KEYWORD; }
           "select"                     { return BallerinaTypes.SELECT_KEYWORD; }
           "limit"                      { return BallerinaTypes.LIMIT_KEYWORD; }
           "outer"                      { return BallerinaTypes.OUTER_KEYWORD; }
           "equals"                     { return BallerinaTypes.EQUALS_KEYWORD; }
           "order"                      { return BallerinaTypes.ORDER_KEYWORD; }
           "by"                         { return BallerinaTypes.BY_KEYWORD; }
           "ascending"                  { return BallerinaTypes.ASCENDING_KEYWORD; }
           "descending"                 { return BallerinaTypes.DESCENDING_KEYWORD; }
           "join"                       { return BallerinaTypes.JOIN_KEYWORD; }
           "key"                        { return BallerinaTypes.KEY_KEYWORD; }
           "collect"                        { return BallerinaTypes.COLLECT_KEYWORD; }
            {IDENTIFIER}                 { return BallerinaTypes.IDENTIFIER_TOKEN; }

        //---------------------------------//

                   ":"                          { return BallerinaTypes.COLON_TOKEN; }
                   ";"                          { return BallerinaTypes.SEMICOLON_TOKEN;
                                                }

                   {DecimalFloatingPointNumber} { return BallerinaTypes.DECIMAL_FLOATING_POINT_LITERAL_TOKEN;}
                   {HexFloatingPointLiteral}    { return BallerinaTypes.HEX_FLOATING_POINT_LITERAL_TOKEN;}

                   // check import
                   {HexIntLiteral}              { return BallerinaTypes.HEX_INTEGER_LITERAL_TOKEN;}
                   {DecimalNumber}              { return BallerinaTypes.DECIMAL_INTEGER_LITERAL_TOKEN;}

                   "..."                        { return BallerinaTypes.ELLIPSIS_TOKEN;}
                   "..<"                        { return BallerinaTypes.DOUBLE_DOT_LT_TOKEN;}
                   ".@"                         { return BallerinaTypes.ANNOT_CHAINING_TOKEN;}
                   ".<"                         { return BallerinaTypes.DOT_LT_TOKEN;}
                   "."                          { return BallerinaTypes.DOT_TOKEN; }


                   ","                          { return BallerinaTypes.COMMA_TOKEN; }
                   "("                          { return BallerinaTypes.OPEN_PAREN_TOKEN; }
                   ")"                          { return BallerinaTypes.CLOSE_PAREN_TOKEN; }

                   "{|"                         { if (inTopLevelDefinitionPipe) {
                                                  bracePipeCount++;
                                                  if (bracePipeCount==2) {
                                                      return BallerinaTypes.OPEN_NESTED_BRACE_PIPE_TOKEN;
                                                  }
                                                  return BallerinaTypes.IGNORED_OPEN_BRACE_PIPE_TOKEN;
                                                  } else {
                                                      inTopLevelDefinitionPipe = true;
                                                      bracePipeCount++;
                                                      return BallerinaTypes.OPEN_BRACE_PIPE_TOKEN;
                                                 } }
                   "}"                          {
                                                  yybegin(TEMPLATE_MODE);
                                                  return BallerinaTypes.INTERPOLATION_END_TOKEN;

                                                   }
                   "["                          { return BallerinaTypes.OPEN_BRACKET_TOKEN; }
                   "]"                          { return BallerinaTypes.CLOSE_BRACKET_TOKEN; }

                   "|}"                         { bracePipeCount--;
                                                 if (bracePipeCount==1) {
                                                     return BallerinaTypes.CLOSE_NESTED_BRACE_PIPE_TOKEN;
                                                 } else if (bracePipeCount <= 0) {
                                                     inTopLevelDefinitionPipe = false;
                                                     return BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN;
                                                 }

                                                 return BallerinaTypes.IGNORED_CLOSE_BRACE_PIPE_TOKEN;}
                   "||"                         { return BallerinaTypes.LOGICAL_OR_TOKEN;}
                   "|"                          { return BallerinaTypes.PIPE_TOKEN;}

                   "?."                         {return BallerinaTypes.OPTIONAL_CHAINING_TOKEN;}
                   "?:"                         {return BallerinaTypes.ELVIS_TOKEN;}
                   "?"                          {return BallerinaTypes.QUESTION_MARK_TOKEN;}

                   {stringLiteral}              { return BallerinaTypes.STRING_LITERAL_TOKEN;}

                   "@"                          { return BallerinaTypes.AT_TOKEN; }

                   "==="                        { return BallerinaTypes.TRIPPLE_EQUAL_TOKEN; }
                   "=="                         { return BallerinaTypes.DOUBLE_EQUAL_TOKEN; }
                   "=>"                         { return BallerinaTypes.RIGHT_DOUBLE_ARROW_TOKEN; }
                   "="                          { return BallerinaTypes.EQUAL_TOKEN; }

                   "+"                          { return BallerinaTypes.PLUS_TOKEN; }

                   "->>"                        { return BallerinaTypes.SYNC_SEND_TOKEN;}
                   "->"                         { return BallerinaTypes.RIGHT_ARROW_TOKEN;}
                   "-"                          { return BallerinaTypes.MINUS_TOKEN;}

                   "*"                          { return BallerinaTypes.ASTERISK_TOKEN; }


                   "/**/<"                      { return BallerinaTypes.DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN; }
                   "/*"                         { return BallerinaTypes.SLASH_ASTERISK_TOKEN; }
                   "/"                          { return BallerinaTypes.SLASH_TOKEN; }

                   "%"                          { return BallerinaTypes.PERCENT_TOKEN; }

                   "<<"                         {return BallerinaTypes.DOUBLE_LT_TOKEN; }
                   "<="                         {return BallerinaTypes.LT_EQUAL_TOKEN; }
                   "<-"                         {return BallerinaTypes.LEFT_ARROW_TOKEN; }
                   "<"                          {return BallerinaTypes.LT_TOKEN; }

                   ">>>"                        {return BallerinaTypes.TRIPPLE_GT_TOKEN; }
                   ">>"                         {return BallerinaTypes.DOUBLE_GT_TOKEN; }
                   ">="                         {return BallerinaTypes.GT_EQUAL_TOKEN; }
                   ">"                          {return BallerinaTypes.GT_TOKEN; }

                   "!=="                        {return BallerinaTypes.NOT_DOUBLE_EQUAL_TOKEN; }
                   "!="                         {return BallerinaTypes.NOT_EQUAL_TOKEN; }
                   "!"                          {return BallerinaTypes.EXCLAMATION_MARK_TOKEN; }

                   "&&"                         {return BallerinaTypes.LOGICAL_AND_TOKEN;}
                   "&"                          {return BallerinaTypes.BITWISE_AND_TOKEN;}

                   "^"                          { return BallerinaTypes.BITWISE_XOR_TOKEN; }
                   "~"                          { return BallerinaTypes.NEGATION_TOKEN; }


                   {Comment}                    { return BallerinaTypes.COMMENT_MINUTIAE;}

        //--------------------------------//

           {backtick_start}                  { yybegin(EXPR_BACKTICK_MODE); return BallerinaTypes.STRING_TEMPLATE_START_TOKEN;}


           {DEPRECATED_DOCUMENTATION}                  { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.DEPRECATED_DOCUMENTATION; }
           {DEPRECATED_PARAMETER_DOCUMENTATION}        { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.DEPRECATED_PARAMETER_DOCUMENTATION; }
           {RETURN_PARAMETER_DOCUMENTATION_START}      { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START; }
           {PARAMETER_DOCUMENTATION_START}             { yybegin(MARKDOWN_PARAMETER_DOCUMENTATION_MODE); return BallerinaTypes.PARAMETER_DOCUMENTATION_START; }
           {MARKDOWN_DOCUMENTATION_LINE_START}         { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START; }

           {WhiteSpaceChar}             { return BallerinaTypes.WHITESPACE_MINUTIAE;}

           .                            { return BallerinaTypes.INVALID_TOKEN; }
       }




<MARKDOWN_DOCUMENTATION_MODE>{
    {MARKDOWN_DOCUMENTATION_LINE_END}           { yybegin(YYINITIAL); }
    {DOCTYPE}                                   { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCTYPE; }
    {DOCSERVICE}                                { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCSERVICE; }
    {DOCVARIABLE}                               { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCVARIABLE; }
    {DOCVAR}                                    { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCVAR; }
    {DOCANNOTATION}                             { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCANNOTATION; }
    {DOCMODULE}                                 { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCMODULE; }
    {DOCFUNCTION}                               { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCFUNCTION; }
    {DOCPARAMETER}                              { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCPARAMETER; }
    {DOCCONST}                                  { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.DOCCONST; }
    {SINGLE_BACKTICK_MARKDOWN_START}            { yybegin(SINGLE_BACKTICKED_MARKDOWN_MODE); return BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_START; }
    {MARKDOWN_DOCUMENTATION_TEXT}               { return BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT; }
      .                            { return BallerinaTypes.INVALID_TOKEN; }

}

<SINGLE_BACKTICKED_MARKDOWN_MODE>{
    {SINGLE_BACKTICK_MARKDOWN_END}              { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_END; }
    {SINGLE_BACKTICK_CONTENT}                   { return BallerinaTypes.SINGLE_BACKTICK_CONTENT; }
      .                            { return BallerinaTypes.INVALID_TOKEN; }
}


<MARKDOWN_PARAMETER_DOCUMENTATION_MODE> {
    {PARAMETER_DOCUMENTATION_END}               { yybegin(YYINITIAL); }
    {PARAMETER_NAME}                            { return BallerinaTypes.PARAMETER_NAME; }
    {DESCRIPTION_SEPARATOR}                     { yybegin(MARKDOWN_DOCUMENTATION_MODE); return BallerinaTypes.DESCRIPTION_SEPARATOR; }
      .                            { return BallerinaTypes.INVALID_TOKEN; }
}

[^] {
    return BallerinaTypes.INVALID_TOKEN;
}