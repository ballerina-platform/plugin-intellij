/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com)
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

package io.ballerina.plugins.idea.psi;

import com.intellij.psi.tree.TokenSet;

/**
 * Defines sets of token types used in Ballerina language parsing within the IntelliJ plugin.
 *
 * @since 2.0.0
 */
public interface BallerinaTokenSets {

    TokenSet KEYWORDS = TokenSet.create(
            BallerinaTypes.PUBLIC_KEYWORD,
            BallerinaTypes.PRIVATE_KEYWORD,
            BallerinaTypes.REMOTE_KEYWORD,
            BallerinaTypes.ABSTRACT_KEYWORD,
            BallerinaTypes.CLIENT_KEYWORD,
            BallerinaTypes.IMPORT_KEYWORD,
            BallerinaTypes.FUNCTION_KEYWORD,
            BallerinaTypes.CONST_KEYWORD,
            BallerinaTypes.LISTENER_KEYWORD,
            BallerinaTypes.SERVICE_KEYWORD,
            BallerinaTypes.XMLNS_KEYWORD,
            BallerinaTypes.ANNOTATION_KEYWORD,
            BallerinaTypes.TYPE_KEYWORD,
            BallerinaTypes.RECORD_KEYWORD,
            BallerinaTypes.OBJECT_KEYWORD,
            BallerinaTypes.AS_KEYWORD,
            BallerinaTypes.ON_KEYWORD,
            BallerinaTypes.RESOURCE_KEYWORD,
            BallerinaTypes.FINAL_KEYWORD,
            BallerinaTypes.SOURCE_KEYWORD,
            BallerinaTypes.WORKER_KEYWORD,
            BallerinaTypes.PARAMETER_KEYWORD,
            BallerinaTypes.FIELD_KEYWORD,
            BallerinaTypes.ISOLATED_KEYWORD,
            BallerinaTypes.RETURNS_KEYWORD,
            BallerinaTypes.RETURN_KEYWORD,
            BallerinaTypes.EXTERNAL_KEYWORD,
            BallerinaTypes.TRUE_KEYWORD,
            BallerinaTypes.FALSE_KEYWORD,
            BallerinaTypes.IF_KEYWORD,
            BallerinaTypes.ELSE_KEYWORD,
            BallerinaTypes.ELSEIF_KEYWORD,
            BallerinaTypes.WHILE_KEYWORD,
            BallerinaTypes.CHECK_KEYWORD,
            BallerinaTypes.CHECKPANIC_KEYWORD,
            BallerinaTypes.PANIC_KEYWORD,
            BallerinaTypes.CONTINUE_KEYWORD,
            BallerinaTypes.BREAK_KEYWORD,
            BallerinaTypes.TYPEOF_KEYWORD,
            BallerinaTypes.IS_KEYWORD,
            BallerinaTypes.NULL_KEYWORD,
            BallerinaTypes.LOCK_KEYWORD,
            BallerinaTypes.FORK_KEYWORD,
            BallerinaTypes.TRAP_KEYWORD,
            BallerinaTypes.IN_KEYWORD,
            BallerinaTypes.FOREACH_KEYWORD,
            BallerinaTypes.TABLE_KEYWORD,
            BallerinaTypes.KEY_KEYWORD,
            BallerinaTypes.LET_KEYWORD,
            BallerinaTypes.NEW_KEYWORD,
            BallerinaTypes.FROM_KEYWORD,
            BallerinaTypes.WHERE_KEYWORD,
            BallerinaTypes.SELECT_KEYWORD,
            BallerinaTypes.START_KEYWORD,
            BallerinaTypes.FLUSH_KEYWORD,
            BallerinaTypes.CONFIGURABLE_KEYWORD,
            BallerinaTypes.WAIT_KEYWORD,
            BallerinaTypes.DO_KEYWORD,
            BallerinaTypes.TRANSACTION_KEYWORD,
            BallerinaTypes.TRANSACTIONAL_KEYWORD,
            BallerinaTypes.COMMIT_KEYWORD,
            BallerinaTypes.ROLLBACK_KEYWORD,
            BallerinaTypes.RETRY_KEYWORD,
            BallerinaTypes.ENUM_KEYWORD,
            BallerinaTypes.BASE16_KEYWORD,
            BallerinaTypes.BASE64_KEYWORD,
            BallerinaTypes.MATCH_KEYWORD,
            BallerinaTypes.CONFLICT_KEYWORD,
            BallerinaTypes.LIMIT_KEYWORD,
            BallerinaTypes.JOIN_KEYWORD,
            BallerinaTypes.OUTER_KEYWORD,
            BallerinaTypes.EQUALS_KEYWORD,
            BallerinaTypes.CLASS_KEYWORD,
            BallerinaTypes.ORDER_KEYWORD,
            BallerinaTypes.BY_KEYWORD,
            BallerinaTypes.ASCENDING_KEYWORD,
            BallerinaTypes.DESCENDING_KEYWORD,
            BallerinaTypes.UNDERSCORE_KEYWORD,
            BallerinaTypes.NOT_IS_KEYWORD
                                       );

    TokenSet TYPES = TokenSet.create(
            BallerinaTypes.INT_KEYWORD,
            BallerinaTypes.BYTE_KEYWORD,
            BallerinaTypes.FLOAT_KEYWORD,
            BallerinaTypes.DECIMAL_KEYWORD,
            BallerinaTypes.STRING_KEYWORD,
            BallerinaTypes.BOOLEAN_KEYWORD,
            BallerinaTypes.XML_KEYWORD,
            BallerinaTypes.JSON_KEYWORD,
            BallerinaTypes.HANDLE_KEYWORD,
            BallerinaTypes.ANY_KEYWORD,
            BallerinaTypes.ANYDATA_KEYWORD,
            BallerinaTypes.NEVER_KEYWORD,
            BallerinaTypes.VAR_KEYWORD,
            BallerinaTypes.MAP_KEYWORD,
            BallerinaTypes.FUTURE_KEYWORD,
            BallerinaTypes.TYPEDESC_KEYWORD,
            BallerinaTypes.ERROR_KEYWORD,
            BallerinaTypes.STREAM_KEYWORD,
            BallerinaTypes.READONLY_KEYWORD,
            BallerinaTypes.DISTINCT_KEYWORD,
            BallerinaTypes.FAIL_KEYWORD
                                    );

    TokenSet CONTEXTUALS =
            TokenSet.create(BallerinaTypes.RE_KEYWORD, BallerinaTypes.GROUP_KEYWORD, BallerinaTypes.COLLECT_KEYWORD);

    TokenSet SEPARATORS = TokenSet.create(
            BallerinaTypes.OPEN_BRACE_TOKEN,
            BallerinaTypes.OPEN_NESTED_BRACE_TOKEN,
            BallerinaTypes.IGNORED_OPEN_BRACE_TOKEN,
            BallerinaTypes.CLOSE_BRACE_TOKEN,
            BallerinaTypes.CLOSE_NESTED_BRACE_TOKEN,
            BallerinaTypes.IGNORED_CLOSE_BRACE_TOKEN,
            BallerinaTypes.OPEN_PAREN_TOKEN,
            BallerinaTypes.CLOSE_PAREN_TOKEN,
            BallerinaTypes.OPEN_BRACKET_TOKEN,
            BallerinaTypes.CLOSE_BRACKET_TOKEN,
            BallerinaTypes.SEMICOLON_TOKEN,
            BallerinaTypes.IGNORED_SEMICOLON_TOKEN,
            BallerinaTypes.DOT_TOKEN,
            BallerinaTypes.COLON_TOKEN,
            BallerinaTypes.COMMA_TOKEN,
            BallerinaTypes.ELLIPSIS_TOKEN,
            BallerinaTypes.OPEN_BRACE_PIPE_TOKEN,
            BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN,
            BallerinaTypes.AT_TOKEN,
            BallerinaTypes.HASH_TOKEN,
            BallerinaTypes.BACKTICK_TOKEN,
            BallerinaTypes.DOUBLE_QUOTE_TOKEN,
            BallerinaTypes.SINGLE_QUOTE_TOKEN,
            BallerinaTypes.DOUBLE_BACKTICK_TOKEN,
            BallerinaTypes.TRIPLE_BACKTICK_TOKEN
                                         );

    TokenSet OPERATORS = TokenSet.create(
            BallerinaTypes.EQUAL_TOKEN,
            BallerinaTypes.DOUBLE_EQUAL_TOKEN,
            BallerinaTypes.TRIPPLE_EQUAL_TOKEN,
            BallerinaTypes.PLUS_TOKEN,
            BallerinaTypes.MINUS_TOKEN,
            BallerinaTypes.SLASH_TOKEN,
            BallerinaTypes.PERCENT_TOKEN,
            BallerinaTypes.ASTERISK_TOKEN,
            BallerinaTypes.LT_TOKEN,
            BallerinaTypes.LT_EQUAL_TOKEN,
            BallerinaTypes.GT_TOKEN,
            BallerinaTypes.RIGHT_DOUBLE_ARROW_TOKEN,
            BallerinaTypes.QUESTION_MARK_TOKEN,
            BallerinaTypes.PIPE_TOKEN,
            BallerinaTypes.GT_EQUAL_TOKEN,
            BallerinaTypes.EXCLAMATION_MARK_TOKEN,
            BallerinaTypes.NOT_EQUAL_TOKEN,
            BallerinaTypes.NOT_DOUBLE_EQUAL_TOKEN,
            BallerinaTypes.BITWISE_AND_TOKEN,
            BallerinaTypes.BITWISE_XOR_TOKEN,
            BallerinaTypes.LOGICAL_AND_TOKEN,
            BallerinaTypes.LOGICAL_OR_TOKEN,
            BallerinaTypes.NEGATION_TOKEN,
            BallerinaTypes.RIGHT_ARROW_TOKEN,
            BallerinaTypes.INTERPOLATION_START_TOKEN,
            BallerinaTypes.XML_PI_START_TOKEN,
            BallerinaTypes.XML_PI_END_TOKEN,
            BallerinaTypes.XML_COMMENT_START_TOKEN,
            BallerinaTypes.XML_COMMENT_END_TOKEN,
            BallerinaTypes.SYNC_SEND_TOKEN,
            BallerinaTypes.LEFT_ARROW_TOKEN,
            BallerinaTypes.DOUBLE_DOT_LT_TOKEN,
            BallerinaTypes.DOUBLE_LT_TOKEN,
            BallerinaTypes.ANNOT_CHAINING_TOKEN,
            BallerinaTypes.OPTIONAL_CHAINING_TOKEN,
            BallerinaTypes.ELVIS_TOKEN,
            BallerinaTypes.DOT_LT_TOKEN,
            BallerinaTypes.SLASH_LT_TOKEN,
            BallerinaTypes.DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN,
            BallerinaTypes.SLASH_ASTERISK_TOKEN,
            BallerinaTypes.DOUBLE_GT_TOKEN,
            BallerinaTypes.TRIPPLE_GT_TOKEN,
            BallerinaTypes.XML_CDATA_START_TOKEN,
            BallerinaTypes.XML_CDATA_END_TOKEN,
            BallerinaTypes.BACK_SLASH_TOKEN,
            BallerinaTypes.DOLLAR_TOKEN,
            BallerinaTypes.ESCAPED_MINUS_TOKEN
                                        );

//    TokenSet DOC_REFS = TokenSet.create(BallerinaTypes.DOC_REF);

    TokenSet DOCUMENTATIONS = TokenSet.create(BallerinaTypes.MARKDOWN_DOCUMENTATION_LINE_START,
            BallerinaTypes.SINGLE_BACKTICK_CONTENT,
            BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_END,
            BallerinaTypes.DOCTYPE,
            BallerinaTypes.DOCSERVICE,
            BallerinaTypes.DOCVARIABLE,
            BallerinaTypes.DOCVAR,
            BallerinaTypes.DOCANNOTATION,
            BallerinaTypes.DOCMODULE,
            BallerinaTypes.DOCFUNCTION,
            BallerinaTypes.DOCPARAMETER,
            BallerinaTypes.DOCCONST,
            BallerinaTypes.PARAMETER_DOCUMENTATION_START,
            BallerinaTypes.PARAMETER_NAME,
            BallerinaTypes.DESCRIPTION_SEPARATOR,
            BallerinaTypes.RETURN_PARAMETER_DOCUMENTATION_START,
            BallerinaTypes.DEPRECATED_DOCUMENTATION,
            BallerinaTypes.DEPRECATED_PARAMETER_DOCUMENTATION,
            BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_START,
            BallerinaTypes.SINGLE_BACKTICK_CONTENT,
            BallerinaTypes.SINGLE_BACKTICK_MARKDOWN_END,
            BallerinaTypes.DOUBLE_BACKTICK_MARKDOWN_START,
            BallerinaTypes.DOUBLE_BACKTICK_CONTENT,
            BallerinaTypes.DOUBLE_BACKTICK_MARKDOWN_END,
            BallerinaTypes.TRIPLE_BACKTICK_MARKDOWN_START,
            BallerinaTypes.TRIPLE_BACKTICK_CONTENT,
            BallerinaTypes.TRIPLE_BACKTICK_MARKDOWN_END,
            BallerinaTypes.MARKDOWN_DOCUMENTATION_TEXT,
            BallerinaTypes.DOCUMENTATION_ESCAPED_CHARACTERS);

    TokenSet LITERALS = TokenSet.create(
            BallerinaTypes.IDENTIFIER_TOKEN,
            BallerinaTypes.STRING_LITERAL_TOKEN,
            BallerinaTypes.DECIMAL_INTEGER_LITERAL_TOKEN,
            BallerinaTypes.HEX_INTEGER_LITERAL_TOKEN,
            BallerinaTypes.DECIMAL_FLOATING_POINT_LITERAL_TOKEN,
            BallerinaTypes.HEX_FLOATING_POINT_LITERAL_TOKEN,
            BallerinaTypes.XML_TEXT_CONTENT,
            BallerinaTypes.TEMPLATE_STRING);

    TokenSet INVALIDS = TokenSet.create(BallerinaTypes.INVALID);

    TokenSet WHITESPACES = TokenSet.create(BallerinaTypes.WHITESPACE_MINUTIAE);

    TokenSet COMMENTS = TokenSet.create(BallerinaTypes.COMMENT_MINUTIAE);

    TokenSet OTHERS = TokenSet.create(BallerinaTypes.OTHER);

}
