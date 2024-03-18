// This is a generated file. Not intended for manual editing.
package io.ballerina.plugins.idea.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.ballerina.plugins.idea.psi.BallerinaTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class BallerinaParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return ballerinaFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ANYDATA_TYPE_DESCRIPTOR, ANY_TYPE_DESCRIPTOR, ARRAY_TYPE_DESCRIPTOR, BEHAVIORAL_TYPE_DESCRIPTOR,
      BYTE_TYPE_DESCRIPTOR, DISTINCT_TYPE_DESCRIPTOR, INTERSECTION_TYPE_DESCRIPTOR, JSON_TYPE_DESCRIPTOR,
      LAST_TYPE_DESCRIPTOR, MAPPING_TYPE_DESCRIPTOR, NEVER_TYPE_DESCRIPTOR, OPTIONAL_TYPE_DESCRIPTOR,
      READONLY_TYPE_DESCRIPTOR, SEQUENCE_TYPE_DESCRIPTOR, SIMPLE_TYPE_DESCRIPTOR, SINGLETON_TYPE_DESCRIPTOR,
      TABLE_TYPE_DESCRIPTOR, TUPLE_TYPE_DESCRIPTOR, TYPE_DESCRIPTOR, TYPE_REFERENCE,
      UNION_TYPE_DESCRIPTOR),
    create_token_set_(ADDITIVE_EXPR, ANNOT_ACCESS_EXPR, ANONYMOUS_FUNCTION_EXPR, BITWISE_AND_EXPR,
      BITWISE_OR_EXPR, BITWISE_XOR_EXPR, CHECKING_EXPR, EQUALITY_EXPR,
      ERROR_CONSTRUCTOR_EXPR, EXPRESSION, FIELD_ACCESS_EXPR, FUNCTION_CALL_EXPR,
      IS_EXPR, LAST_EXPRESSION, LET_EXPR, LITERALS,
      LOGICAL_AND_EXPR, LOGICAL_OR_EXPR, MEMBER_ACCESS_EXPR, METHOD_CALL_EXPR,
      MULTIPLICATIVE_EXPR, NEW_EXPR, NIL_CONDITIONAL_EXPR, OBJECT_CONSTRUCTOR_EXPR,
      OPTIONAL_FIELD_ACCESS_EXPR, QUERY_EXPR, RANGE_EXPR, RELATIONAL_EXPR,
      SHIFT_EXPR, STRUCTURAL_CONSTRUCTOR_EXPR, TEMPLATE_EXPR, TERNARY_CONDITIONAL_EXPR,
      TERNARY_IS_CONDITIONAL_EXPR_1, TERNARY_IS_CONDITIONAL_EXPR_2, TRANSACTIONAL_EXPR, TRAP_EXPR,
      TYPEOF_EXPR, TYPE_CAST_EXPR, UNARY_LOGICAL_EXPR, UNARY_NUMERIC_EXPR,
      VARIABLE_REFERENCE_EXPR, XML_FILTER_EXPR, XML_OPTIONAL_ATTRIBUTE_ACCESS_EXPR, XML_REQUIRED_ATTRIBUTE_ACCESS_EXPR,
      XML_STEP_EXPR),
  };

  /* ********************************************************** */
  // COLON_TOKEN
  static boolean NoSpaceColon(PsiBuilder b, int l) {
    return consumeToken(b, COLON_TOKEN);
  }

  /* ********************************************************** */
  // PLUS_TOKEN | MINUS_TOKEN
  public static boolean Sign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Sign")) return false;
    if (!nextTokenIs(b, "<sign>", MINUS_TOKEN, PLUS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIGN, "<sign>");
    r = consumeToken(b, PLUS_TOKEN);
    if (!r) r = consumeToken(b, MINUS_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // allTokens*
  public static boolean Tokens(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Tokens")) return false;
    Marker m = enter_section_(b, l, _NONE_, TOKENS, "<tokens>");
    while (true) {
      int c = current_position_(b);
      if (!allTokens(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Tokens", c)) break;
    }
    exit_section_(b, l, m, true, false, BallerinaParser::recoverClass);
    return true;
  }

  /* ********************************************************** */
  // allTokensIgnore*
  public static boolean TokensIgnore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TokensIgnore")) return false;
    Marker m = enter_section_(b, l, _NONE_, TOKENS_IGNORE, "<tokens ignore>");
    while (true) {
      int c = current_position_(b);
      if (!allTokensIgnore(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TokensIgnore", c)) break;
    }
    exit_section_(b, l, m, true, false, BallerinaParser::recoverIgnore);
    return true;
  }

  /* ********************************************************** */
  // allTokensPipe*
  public static boolean TokensPipe(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TokensPipe")) return false;
    Marker m = enter_section_(b, l, _NONE_, TOKENS_PIPE, "<tokens pipe>");
    while (true) {
      int c = current_position_(b);
      if (!allTokensPipe(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TokensPipe", c)) break;
    }
    exit_section_(b, l, m, true, false, BallerinaParser::recoverPipe);
    return true;
  }

  /* ********************************************************** */
  // (SLASH_TOKEN resource_path_segment_name)+ | root_resource_path
  public static boolean absolute_resource_path(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "absolute_resource_path")) return false;
    if (!nextTokenIs(b, SLASH_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = absolute_resource_path_0(b, l + 1);
    if (!r) r = root_resource_path(b, l + 1);
    exit_section_(b, m, ABSOLUTE_RESOURCE_PATH, r);
    return r;
  }

  // (SLASH_TOKEN resource_path_segment_name)+
  private static boolean absolute_resource_path_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "absolute_resource_path_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = absolute_resource_path_0_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!absolute_resource_path_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "absolute_resource_path_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // SLASH_TOKEN resource_path_segment_name
  private static boolean absolute_resource_path_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "absolute_resource_path_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_TOKEN);
    r = r && resource_path_segment_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean aggregated_variable_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aggregated_variable_reference")) return false;
    if (!nextTokenIs(b, "<aggregated variable reference>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AGGREGATED_VARIABLE_REFERENCE, "<aggregated variable reference>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // keyword | type | separatorIn | operator | literal | docRef | contextual
  // | REMULTIFLAG | REDOTALLFLAG | REIGNORECASEFLAG | RECOMMENTFLAG | REESCAPE | RECHARSETLITERALCHAR | RELITERALCHAR
  // | DOCTYPE | DOCSERVICE | DOCVARIABLE | DOCVAR | DOCANNOTATION | DOCMODULE | DOCFUNCTION | DOCPARAMETER | DOCCONST | MARKDOWN_DOCUMENTATION_LINE_START | PARAMETER_DOCUMENTATION_START | RETURN_PARAMETER_DOCUMENTATION_START | MARKDOWN_DOCUMENTATION_TEXT | PARAMETER_NAME | DESCRIPTION_SEPARATOR
  // | DEPRECATED_DOCUMENTATION | DEPRECATED_PARAMETER_DOCUMENTATION | SINGLE_BACKTICK_MARKDOWN_START | SINGLE_BACKTICK_MARKDOWN_END | DOUBLE_BACKTICK_MARKDOWN_START | DOUBLE_BACKTICK_MARKDOWN_END | TRIPLE_BACKTICK_MARKDOWN_START | TRIPLE_BACKTICK_MARKDOWN_END | DOCUMENTATION_ESCAPED_CHARACTERS | SINGLE_BACKTICK_CONTENT | DOUBLE_BACKTICK_CONTENT | TRIPLE_BACKTICK_CONTENT
  static boolean allTokens(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allTokens")) return false;
    boolean r;
    r = keyword(b, l + 1);
    if (!r) r = type(b, l + 1);
    if (!r) r = separatorIn(b, l + 1);
    if (!r) r = operator(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = docRef(b, l + 1);
    if (!r) r = contextual(b, l + 1);
    if (!r) r = consumeToken(b, REMULTIFLAG);
    if (!r) r = consumeToken(b, REDOTALLFLAG);
    if (!r) r = consumeToken(b, REIGNORECASEFLAG);
    if (!r) r = consumeToken(b, RECOMMENTFLAG);
    if (!r) r = consumeToken(b, REESCAPE);
    if (!r) r = consumeToken(b, RECHARSETLITERALCHAR);
    if (!r) r = consumeToken(b, RELITERALCHAR);
    if (!r) r = consumeToken(b, DOCTYPE);
    if (!r) r = consumeToken(b, DOCSERVICE);
    if (!r) r = consumeToken(b, DOCVARIABLE);
    if (!r) r = consumeToken(b, DOCVAR);
    if (!r) r = consumeToken(b, DOCANNOTATION);
    if (!r) r = consumeToken(b, DOCMODULE);
    if (!r) r = consumeToken(b, DOCFUNCTION);
    if (!r) r = consumeToken(b, DOCPARAMETER);
    if (!r) r = consumeToken(b, DOCCONST);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    if (!r) r = consumeToken(b, PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, RETURN_PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_TEXT);
    if (!r) r = consumeToken(b, PARAMETER_NAME);
    if (!r) r = consumeToken(b, DESCRIPTION_SEPARATOR);
    if (!r) r = consumeToken(b, DEPRECATED_DOCUMENTATION);
    if (!r) r = consumeToken(b, DEPRECATED_PARAMETER_DOCUMENTATION);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOCUMENTATION_ESCAPED_CHARACTERS);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_CONTENT);
    return r;
  }

  /* ********************************************************** */
  // keyword | type | separatorIgnore | operator | literal | docRef | contextual
  // | REMULTIFLAG | REDOTALLFLAG | REIGNORECASEFLAG | RECOMMENTFLAG | REESCAPE | RECHARSETLITERALCHAR | RELITERALCHAR
  // | DOCTYPE | DOCSERVICE | DOCVARIABLE | DOCVAR | DOCANNOTATION | DOCMODULE | DOCFUNCTION | DOCPARAMETER | DOCCONST | MARKDOWN_DOCUMENTATION_LINE_START | PARAMETER_DOCUMENTATION_START | RETURN_PARAMETER_DOCUMENTATION_START | MARKDOWN_DOCUMENTATION_TEXT | PARAMETER_NAME | DESCRIPTION_SEPARATOR
  // | DEPRECATED_DOCUMENTATION | DEPRECATED_PARAMETER_DOCUMENTATION | SINGLE_BACKTICK_MARKDOWN_START | SINGLE_BACKTICK_MARKDOWN_END | DOUBLE_BACKTICK_MARKDOWN_START | DOUBLE_BACKTICK_MARKDOWN_END | TRIPLE_BACKTICK_MARKDOWN_START | TRIPLE_BACKTICK_MARKDOWN_END | DOCUMENTATION_ESCAPED_CHARACTERS | SINGLE_BACKTICK_CONTENT | DOUBLE_BACKTICK_CONTENT | TRIPLE_BACKTICK_CONTENT
  static boolean allTokensIgnore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allTokensIgnore")) return false;
    boolean r;
    r = keyword(b, l + 1);
    if (!r) r = type(b, l + 1);
    if (!r) r = separatorIgnore(b, l + 1);
    if (!r) r = operator(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = docRef(b, l + 1);
    if (!r) r = contextual(b, l + 1);
    if (!r) r = consumeToken(b, REMULTIFLAG);
    if (!r) r = consumeToken(b, REDOTALLFLAG);
    if (!r) r = consumeToken(b, REIGNORECASEFLAG);
    if (!r) r = consumeToken(b, RECOMMENTFLAG);
    if (!r) r = consumeToken(b, REESCAPE);
    if (!r) r = consumeToken(b, RECHARSETLITERALCHAR);
    if (!r) r = consumeToken(b, RELITERALCHAR);
    if (!r) r = consumeToken(b, DOCTYPE);
    if (!r) r = consumeToken(b, DOCSERVICE);
    if (!r) r = consumeToken(b, DOCVARIABLE);
    if (!r) r = consumeToken(b, DOCVAR);
    if (!r) r = consumeToken(b, DOCANNOTATION);
    if (!r) r = consumeToken(b, DOCMODULE);
    if (!r) r = consumeToken(b, DOCFUNCTION);
    if (!r) r = consumeToken(b, DOCPARAMETER);
    if (!r) r = consumeToken(b, DOCCONST);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    if (!r) r = consumeToken(b, PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, RETURN_PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_TEXT);
    if (!r) r = consumeToken(b, PARAMETER_NAME);
    if (!r) r = consumeToken(b, DESCRIPTION_SEPARATOR);
    if (!r) r = consumeToken(b, DEPRECATED_DOCUMENTATION);
    if (!r) r = consumeToken(b, DEPRECATED_PARAMETER_DOCUMENTATION);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOCUMENTATION_ESCAPED_CHARACTERS);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_CONTENT);
    return r;
  }

  /* ********************************************************** */
  // keyword | type | separatorPipe | operator | literal | docRef | contextual
  // | REMULTIFLAG | REDOTALLFLAG | REIGNORECASEFLAG | RECOMMENTFLAG | REESCAPE | RECHARSETLITERALCHAR | RELITERALCHAR
  // | DOCTYPE | DOCSERVICE | DOCVARIABLE | DOCVAR | DOCANNOTATION | DOCMODULE | DOCFUNCTION | DOCPARAMETER | DOCCONST | MARKDOWN_DOCUMENTATION_LINE_START | PARAMETER_DOCUMENTATION_START | RETURN_PARAMETER_DOCUMENTATION_START | MARKDOWN_DOCUMENTATION_TEXT | PARAMETER_NAME | DESCRIPTION_SEPARATOR
  // | DEPRECATED_DOCUMENTATION | DEPRECATED_PARAMETER_DOCUMENTATION | SINGLE_BACKTICK_MARKDOWN_START | SINGLE_BACKTICK_MARKDOWN_END | DOUBLE_BACKTICK_MARKDOWN_START | DOUBLE_BACKTICK_MARKDOWN_END | TRIPLE_BACKTICK_MARKDOWN_START | TRIPLE_BACKTICK_MARKDOWN_END | DOCUMENTATION_ESCAPED_CHARACTERS | SINGLE_BACKTICK_CONTENT | DOUBLE_BACKTICK_CONTENT | TRIPLE_BACKTICK_CONTENT
  static boolean allTokensPipe(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allTokensPipe")) return false;
    boolean r;
    r = keyword(b, l + 1);
    if (!r) r = type(b, l + 1);
    if (!r) r = separatorPipe(b, l + 1);
    if (!r) r = operator(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = docRef(b, l + 1);
    if (!r) r = contextual(b, l + 1);
    if (!r) r = consumeToken(b, REMULTIFLAG);
    if (!r) r = consumeToken(b, REDOTALLFLAG);
    if (!r) r = consumeToken(b, REIGNORECASEFLAG);
    if (!r) r = consumeToken(b, RECOMMENTFLAG);
    if (!r) r = consumeToken(b, REESCAPE);
    if (!r) r = consumeToken(b, RECHARSETLITERALCHAR);
    if (!r) r = consumeToken(b, RELITERALCHAR);
    if (!r) r = consumeToken(b, DOCTYPE);
    if (!r) r = consumeToken(b, DOCSERVICE);
    if (!r) r = consumeToken(b, DOCVARIABLE);
    if (!r) r = consumeToken(b, DOCVAR);
    if (!r) r = consumeToken(b, DOCANNOTATION);
    if (!r) r = consumeToken(b, DOCMODULE);
    if (!r) r = consumeToken(b, DOCFUNCTION);
    if (!r) r = consumeToken(b, DOCPARAMETER);
    if (!r) r = consumeToken(b, DOCCONST);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    if (!r) r = consumeToken(b, PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, RETURN_PARAMETER_DOCUMENTATION_START);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_TEXT);
    if (!r) r = consumeToken(b, PARAMETER_NAME);
    if (!r) r = consumeToken(b, DESCRIPTION_SEPARATOR);
    if (!r) r = consumeToken(b, DEPRECATED_DOCUMENTATION);
    if (!r) r = consumeToken(b, DEPRECATED_PARAMETER_DOCUMENTATION);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_START);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_MARKDOWN_END);
    if (!r) r = consumeToken(b, DOCUMENTATION_ESCAPED_CHARACTERS);
    if (!r) r = consumeToken(b, SINGLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_CONTENT);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_CONTENT);
    return r;
  }

  /* ********************************************************** */
  // dual_attach_point
  //    | source_only_attach_point
  public static boolean annot_attach_point(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_attach_point")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOT_ATTACH_POINT, "<annot attach point>");
    r = dual_attach_point(b, l + 1);
    if (!r) r = source_only_attach_point(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // annot_attach_point (COMMA_TOKEN annot_attach_point)*
  public static boolean annot_attach_points(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_attach_points")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOT_ATTACH_POINTS, "<annot attach points>");
    r = annot_attach_point(b, l + 1);
    r = r && annot_attach_points_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN annot_attach_point)*
  private static boolean annot_attach_points_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_attach_points_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annot_attach_points_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annot_attach_points_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN annot_attach_point
  private static boolean annot_attach_points_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_attach_points_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && annot_attach_point(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_NESTED_BRACE_TOKEN TokensIgnore CLOSE_NESTED_BRACE_TOKEN
  public static boolean annot_body_nested(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_body_nested")) return false;
    if (!nextTokenIs(b, OPEN_NESTED_BRACE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    r = r && TokensIgnore(b, l + 1);
    r = r && consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    exit_section_(b, m, ANNOT_BODY_NESTED, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACE_TOKEN Tokens CLOSE_BRACE_TOKEN
  public static boolean annot_body_open(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_body_open")) return false;
    if (!nextTokenIs(b, OPEN_BRACE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACE_TOKEN);
    r = r && Tokens(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE_TOKEN);
    exit_section_(b, m, ANNOT_BODY_OPEN, r);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean annot_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_tag")) return false;
    if (!nextTokenIs(b, "<annot tag>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOT_TAG, "<annot tag>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // qualified_identifier | identifier
  static boolean annot_tag_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_tag_reference")) return false;
    boolean r;
    r = qualified_identifier(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // [annot_body_open | annot_body_nested | mapping_constructor_expr]
  public static boolean annot_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_value")) return false;
    Marker m = enter_section_(b, l, _NONE_, ANNOT_VALUE, "<annot value>");
    annot_value_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // annot_body_open | annot_body_nested | mapping_constructor_expr
  private static boolean annot_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_value_0")) return false;
    boolean r;
    r = annot_body_open(b, l + 1);
    if (!r) r = annot_body_nested(b, l + 1);
    if (!r) r = mapping_constructor_expr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // AT_TOKEN annot_tag_reference annot_value
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, AT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_TOKEN);
    r = r && annot_tag_reference(b, l + 1);
    r = r && annot_value(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  /* ********************************************************** */
  // (metadata
  //    [PUBLIC_KEYWORD] [CONST_KEYWORD] ANNOTATION_KEYWORD annot_tag
  //    [ON_KEYWORD annot_attach_points] SEMICOLON_TOKEN)
  //     | metadata
  //          [PUBLIC_KEYWORD] [CONST_KEYWORD] ANNOTATION_KEYWORD [type_descriptor] annot_tag
  //          [ON_KEYWORD annot_attach_points] SEMICOLON_TOKEN
  public static boolean annotation_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_DECL, "<annotation decl>");
    r = annotation_decl_0(b, l + 1);
    if (!r) r = annotation_decl_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // metadata
  //    [PUBLIC_KEYWORD] [CONST_KEYWORD] ANNOTATION_KEYWORD annot_tag
  //    [ON_KEYWORD annot_attach_points] SEMICOLON_TOKEN
  private static boolean annotation_decl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && annotation_decl_0_1(b, l + 1);
    r = r && annotation_decl_0_2(b, l + 1);
    r = r && consumeToken(b, ANNOTATION_KEYWORD);
    r = r && annot_tag(b, l + 1);
    r = r && annotation_decl_0_5(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean annotation_decl_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_0_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [CONST_KEYWORD]
  private static boolean annotation_decl_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_0_2")) return false;
    consumeToken(b, CONST_KEYWORD);
    return true;
  }

  // [ON_KEYWORD annot_attach_points]
  private static boolean annotation_decl_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_0_5")) return false;
    annotation_decl_0_5_0(b, l + 1);
    return true;
  }

  // ON_KEYWORD annot_attach_points
  private static boolean annotation_decl_0_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_0_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ON_KEYWORD);
    r = r && annot_attach_points(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // metadata
  //          [PUBLIC_KEYWORD] [CONST_KEYWORD] ANNOTATION_KEYWORD [type_descriptor] annot_tag
  //          [ON_KEYWORD annot_attach_points] SEMICOLON_TOKEN
  private static boolean annotation_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && annotation_decl_1_1(b, l + 1);
    r = r && annotation_decl_1_2(b, l + 1);
    r = r && consumeToken(b, ANNOTATION_KEYWORD);
    r = r && annotation_decl_1_4(b, l + 1);
    r = r && annot_tag(b, l + 1);
    r = r && annotation_decl_1_6(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean annotation_decl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [CONST_KEYWORD]
  private static boolean annotation_decl_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1_2")) return false;
    consumeToken(b, CONST_KEYWORD);
    return true;
  }

  // [type_descriptor]
  private static boolean annotation_decl_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1_4")) return false;
    type_descriptor(b, l + 1, -1);
    return true;
  }

  // [ON_KEYWORD annot_attach_points]
  private static boolean annotation_decl_1_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1_6")) return false;
    annotation_decl_1_6_0(b, l + 1);
    return true;
  }

  // ON_KEYWORD annot_attach_points
  private static boolean annotation_decl_1_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_decl_1_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ON_KEYWORD);
    r = r && annot_attach_points(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // annotation+
  public static boolean annots(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annots")) return false;
    if (!nextTokenIs(b, AT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotation(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annots", c)) break;
    }
    exit_section_(b, m, ANNOTS, r);
    return r;
  }

  /* ********************************************************** */
  // positional_args [COMMA_TOKEN other_args] | [other_args]
  public static boolean arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARG_LIST, "<arg list>");
    r = arg_list_0(b, l + 1);
    if (!r) r = arg_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // positional_args [COMMA_TOKEN other_args]
  private static boolean arg_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = positional_args(b, l + 1);
    r = r && arg_list_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN other_args]
  private static boolean arg_list_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_0_1")) return false;
    arg_list_0_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN other_args
  private static boolean arg_list_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && other_args(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [other_args]
  private static boolean arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1")) return false;
    other_args(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // identifier
  public static boolean arg_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_name")) return false;
    if (!nextTokenIs(b, "<arg name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARG_NAME, "<arg name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN [ array_length ] CLOSE_BRACKET_TOKEN
  public static boolean array_dimension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_dimension")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && array_dimension_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, ARRAY_DIMENSION, r);
    return r;
  }

  // [ array_length ]
  private static boolean array_dimension_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_dimension_1")) return false;
    array_length(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // int_literal | constant_reference_expr
  public static boolean array_length(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_length")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_LENGTH, "<array length>");
    r = int_literal(b, l + 1);
    if (!r) r = constant_reference_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // absolute_resource_path | string_literal
  public static boolean attach_point(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attach_point")) return false;
    if (!nextTokenIs(b, "<attach point>", SLASH_TOKEN, STRING_LITERAL_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTACH_POINT, "<attach point>");
    r = absolute_resource_path(b, l + 1);
    if (!r) r = string_literal(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // singleBacktickedBlock
  public static boolean backtickedBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "backtickedBlock")) return false;
    if (!nextTokenIs(b, SINGLE_BACKTICK_MARKDOWN_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = singleBacktickedBlock(b, l + 1);
    exit_section_(b, m, BACKTICKED_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // module_part <<eof>>
  static boolean ballerinaFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ballerinaFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_part(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // capture_binding_pattern
  //    | wildcard_binding_pattern
  //    | list_binding_pattern
  //    | mapping_binding_pattern
  //    | error_binding_pattern
  public static boolean binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINDING_PATTERN, "<binding pattern>");
    r = capture_binding_pattern(b, l + 1);
    if (!r) r = wildcard_binding_pattern(b, l + 1);
    if (!r) r = list_binding_pattern(b, l + 1);
    if (!r) r = mapping_binding_pattern(b, l + 1);
    if (!r) r = error_binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TRUE_KEYWORD | FALSE_KEYWORD
  public static boolean boolean_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_literal")) return false;
    if (!nextTokenIs(b, "<boolean literal>", FALSE_KEYWORD, TRUE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN_LITERAL, "<boolean literal>");
    r = consumeToken(b, TRUE_KEYWORD);
    if (!r) r = consumeToken(b, FALSE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN_KEYWORD
  public static boolean boolean_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_type_descriptor")) return false;
    if (!nextTokenIs(b, BOOLEAN_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BOOLEAN_KEYWORD);
    exit_section_(b, m, BOOLEAN_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // (BASE16_KEYWORD string_template_expr) | (BASE64_KEYWORD string_template_expr)
  public static boolean byte_array_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "byte_array_literal")) return false;
    if (!nextTokenIs(b, "<byte array literal>", BASE16_KEYWORD, BASE64_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BYTE_ARRAY_LITERAL, "<byte array literal>");
    r = byte_array_literal_0(b, l + 1);
    if (!r) r = byte_array_literal_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BASE16_KEYWORD string_template_expr
  private static boolean byte_array_literal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "byte_array_literal_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BASE16_KEYWORD);
    r = r && string_template_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BASE64_KEYWORD string_template_expr
  private static boolean byte_array_literal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "byte_array_literal_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BASE64_KEYWORD);
    r = r && string_template_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // variable_name
  public static boolean capture_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "capture_binding_pattern")) return false;
    if (!nextTokenIs(b, "<capture binding pattern>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CAPTURE_BINDING_PATTERN, "<capture binding pattern>");
    r = variable_name(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CHECK_KEYWORD | CHECKPANIC_KEYWORD
  public static boolean checking_keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "checking_keyword")) return false;
    if (!nextTokenIs(b, "<checking keyword>", CHECKPANIC_KEYWORD, CHECK_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CHECKING_KEYWORD, "<checking keyword>");
    r = consumeToken(b, CHECK_KEYWORD);
    if (!r) r = consumeToken(b, CHECKPANIC_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // qualified_identifier | identifier | stream_type_descriptor
  public static boolean class_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DESCRIPTOR, "<class descriptor>");
    r = qualified_identifier(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    if (!r) r = stream_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // object_field
  //    | method_defn
  //    | remote_method_defn
  //    | resource_method_defn
  //    | object_type_inclusion
  public static boolean class_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_MEMBER, "<class member>");
    r = object_field(b, l + 1);
    if (!r) r = method_defn(b, l + 1);
    if (!r) r = remote_method_defn(b, l + 1);
    if (!r) r = resource_method_defn(b, l + 1);
    if (!r) r = object_type_inclusion(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // class_member*
  public static boolean class_members(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_members")) return false;
    Marker m = enter_section_(b, l, _NONE_, CLASS_MEMBERS, "<class members>");
    while (true) {
      int c = current_position_(b);
      if (!class_member(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "class_members", c)) break;
    }
    exit_section_(b, l, m, true, false, BallerinaParser::recoverClass);
    return true;
  }

  /* ********************************************************** */
  // (DISTINCT_KEYWORD | READONLY_KEYWORD | isolated_qual | object_network_qual)*
  public static boolean class_type_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_type_quals")) return false;
    Marker m = enter_section_(b, l, _NONE_, CLASS_TYPE_QUALS, "<class type quals>");
    while (true) {
      int c = current_position_(b);
      if (!class_type_quals_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "class_type_quals", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // DISTINCT_KEYWORD | READONLY_KEYWORD | isolated_qual | object_network_qual
  private static boolean class_type_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "class_type_quals_0")) return false;
    boolean r;
    r = consumeToken(b, DISTINCT_KEYWORD);
    if (!r) r = consumeToken(b, READONLY_KEYWORD);
    if (!r) r = isolated_qual(b, l + 1);
    if (!r) r = object_network_qual(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // COLLECT_KEYWORD expression
  public static boolean collect_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "collect_clause")) return false;
    if (!nextTokenIs(b, COLLECT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLLECT_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, COLLECT_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN field_name_expr CLOSE_BRACKET_TOKEN COLON_TOKEN value_expr
  public static boolean computed_name_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "computed_name_field")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && field_name_expr(b, l + 1);
    r = r && consumeTokens(b, 0, CLOSE_BRACKET_TOKEN, COLON_TOKEN);
    r = r && value_expr(b, l + 1);
    exit_section_(b, m, COMPUTED_NAME_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // literal
  //    | template_expr
  //    | structural_constructor_expr
  //    | constant_reference_expr
  //    | type_cast_expr
  //    | unary_logical_expr
  //    | nil_lifted_expr
  //    | range_expr
  //    | relational_expr
  //    | is_expr
  //    | conditional_expr
  //    | equality_expr
  //    | logical_expr
  //    | last_const_expr
  public static boolean const_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "const_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONST_EXPR, "<const expr>");
    r = literal(b, l + 1);
    if (!r) r = template_expr(b, l + 1);
    if (!r) r = structural_constructor_expr(b, l + 1);
    if (!r) r = constant_reference_expr(b, l + 1);
    if (!r) r = type_cast_expr(b, l + 1);
    if (!r) r = unary_logical_expr(b, l + 1);
    if (!r) r = expression(b, l + 1, 17);
    if (!r) r = expression(b, l + 1, 18);
    if (!r) r = expression(b, l + 1, 21);
    if (!r) r = expression(b, l + 1, 19);
    if (!r) r = expression(b, l + 1, 20);
    if (!r) r = expression(b, l + 1, 22);
    if (!r) r = expression(b, l + 1, 23);
    if (!r) r = last_const_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // variable_reference_expr
  public static boolean constant_reference_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constant_reference_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT_REFERENCE_EXPR, "<constant reference expr>");
    r = variable_reference_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // RE_KEYWORD
  //     |GROUP_KEYWORD
  //     |COLLECT_KEYWORD
  static boolean contextual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextual")) return false;
    boolean r;
    r = consumeToken(b, RE_KEYWORD);
    if (!r) r = consumeToken(b, GROUP_KEYWORD);
    if (!r) r = consumeToken(b, COLLECT_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean default_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "default_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEFAULT_EXPRESSION, "<default expression>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [annots] type_descriptor [param_name] EQUAL_TOKEN (default_expression | inferred_typedesc_default)
  public static boolean defaultable_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEFAULTABLE_PARAM, "<defaultable param>");
    r = defaultable_param_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && defaultable_param_2(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && defaultable_param_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean defaultable_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_param_0")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean defaultable_param_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_param_2")) return false;
    param_name(b, l + 1);
    return true;
  }

  // default_expression | inferred_typedesc_default
  private static boolean defaultable_param_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_param_4")) return false;
    boolean r;
    r = default_expression(b, l + 1);
    if (!r) r = inferred_typedesc_default(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // defaultable_param (COMMA_TOKEN defaultable_param)*
  public static boolean defaultable_params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_params")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEFAULTABLE_PARAMS, "<defaultable params>");
    r = defaultable_param(b, l + 1);
    r = r && defaultable_params_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN defaultable_param)*
  private static boolean defaultable_params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_params_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!defaultable_params_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "defaultable_params_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN defaultable_param
  private static boolean defaultable_params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defaultable_params_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && defaultable_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MARKDOWN_DOCUMENTATION_LINE_START documentationText?
  public static boolean deprecateAnnotationDescriptionLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecateAnnotationDescriptionLine")) return false;
    if (!nextTokenIs(b, MARKDOWN_DOCUMENTATION_LINE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    r = r && deprecateAnnotationDescriptionLine_1(b, l + 1);
    exit_section_(b, m, DEPRECATE_ANNOTATION_DESCRIPTION_LINE, r);
    return r;
  }

  // documentationText?
  private static boolean deprecateAnnotationDescriptionLine_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecateAnnotationDescriptionLine_1")) return false;
    documentationText(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DEPRECATED_DOCUMENTATION
  public static boolean deprecatedAnnotationDocumentation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedAnnotationDocumentation")) return false;
    if (!nextTokenIs(b, DEPRECATED_DOCUMENTATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEPRECATED_DOCUMENTATION);
    exit_section_(b, m, DEPRECATED_ANNOTATION_DOCUMENTATION, r);
    return r;
  }

  /* ********************************************************** */
  // deprecatedAnnotationDocumentation deprecateAnnotationDescriptionLine*
  public static boolean deprecatedAnnotationDocumentationLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedAnnotationDocumentationLine")) return false;
    if (!nextTokenIs(b, DEPRECATED_DOCUMENTATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = deprecatedAnnotationDocumentation(b, l + 1);
    r = r && deprecatedAnnotationDocumentationLine_1(b, l + 1);
    exit_section_(b, m, DEPRECATED_ANNOTATION_DOCUMENTATION_LINE, r);
    return r;
  }

  // deprecateAnnotationDescriptionLine*
  private static boolean deprecatedAnnotationDocumentationLine_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedAnnotationDocumentationLine_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!deprecateAnnotationDescriptionLine(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "deprecatedAnnotationDocumentationLine_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DEPRECATED_PARAMETER_DOCUMENTATION
  public static boolean deprecatedParametersDocumentation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedParametersDocumentation")) return false;
    if (!nextTokenIs(b, DEPRECATED_PARAMETER_DOCUMENTATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEPRECATED_PARAMETER_DOCUMENTATION);
    exit_section_(b, m, DEPRECATED_PARAMETERS_DOCUMENTATION, r);
    return r;
  }

  /* ********************************************************** */
  // deprecatedParametersDocumentation parameterDocumentationLine+
  public static boolean deprecatedParametersDocumentationLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedParametersDocumentationLine")) return false;
    if (!nextTokenIs(b, DEPRECATED_PARAMETER_DOCUMENTATION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = deprecatedParametersDocumentation(b, l + 1);
    r = r && deprecatedParametersDocumentationLine_1(b, l + 1);
    exit_section_(b, m, DEPRECATED_PARAMETERS_DOCUMENTATION_LINE, r);
    return r;
  }

  // parameterDocumentationLine+
  private static boolean deprecatedParametersDocumentationLine_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deprecatedParametersDocumentationLine_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterDocumentationLine(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!parameterDocumentationLine(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "deprecatedParametersDocumentationLine_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // documentationText?
  public static boolean docParameterDescription(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docParameterDescription")) return false;
    Marker m = enter_section_(b, l, _NONE_, DOC_PARAMETER_DESCRIPTION, "<doc parameter description>");
    documentationText(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // TYPE_DOC_REFERENCE_TOKEN
  //     |SERVICE_DOC_REFERENCE_TOKEN
  //     |VARIABLE_DOC_REFERENCE_TOKEN
  //     |VAR_DOC_REFERENCE_TOKEN
  //     |ANNOTATION_DOC_REFERENCE_TOKEN
  //     |MODULE_DOC_REFERENCE_TOKEN
  //     |FUNCTION_DOC_REFERENCE_TOKEN
  //     |PARAMETER_DOC_REFERENCE_TOKEN
  //     |CONST_DOC_REFERENCE_TOKEN
  static boolean docRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docRef")) return false;
    boolean r;
    r = consumeToken(b, TYPE_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, SERVICE_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, VARIABLE_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, VAR_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, ANNOTATION_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, MODULE_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, FUNCTION_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, PARAMETER_DOC_REFERENCE_TOKEN);
    if (!r) r = consumeToken(b, CONST_DOC_REFERENCE_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // documentationText?
  public static boolean documentationContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationContent")) return false;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENTATION_CONTENT, "<documentation content>");
    documentationText(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // MARKDOWN_DOCUMENTATION_LINE_START documentationContent
  public static boolean documentationLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationLine")) return false;
    if (!nextTokenIs(b, MARKDOWN_DOCUMENTATION_LINE_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENTATION_LINE, null);
    r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    p = r; // pin = 1
    r = r && documentationContent(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // referenceType SINGLE_BACKTICK_CONTENT SINGLE_BACKTICK_MARKDOWN_END
  public static boolean documentationReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationReference")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENTATION_REFERENCE, "<documentation reference>");
    r = referenceType(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeTokens(b, -1, SINGLE_BACKTICK_CONTENT, SINGLE_BACKTICK_MARKDOWN_END));
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // documentationLine+ parameterDocumentationLine* returnParameterDocumentationLine? deprecatedParametersDocumentationLine? deprecatedAnnotationDocumentationLine?
  public static boolean documentationString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString")) return false;
    if (!nextTokenIs(b, MARKDOWN_DOCUMENTATION_LINE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = documentationString_0(b, l + 1);
    r = r && documentationString_1(b, l + 1);
    r = r && documentationString_2(b, l + 1);
    r = r && documentationString_3(b, l + 1);
    r = r && documentationString_4(b, l + 1);
    exit_section_(b, m, DOCUMENTATION_STRING, r);
    return r;
  }

  // documentationLine+
  private static boolean documentationString_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = documentationLine(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!documentationLine(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "documentationString_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // parameterDocumentationLine*
  private static boolean documentationString_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterDocumentationLine(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "documentationString_1", c)) break;
    }
    return true;
  }

  // returnParameterDocumentationLine?
  private static boolean documentationString_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString_2")) return false;
    returnParameterDocumentationLine(b, l + 1);
    return true;
  }

  // deprecatedParametersDocumentationLine?
  private static boolean documentationString_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString_3")) return false;
    deprecatedParametersDocumentationLine(b, l + 1);
    return true;
  }

  // deprecatedAnnotationDocumentationLine?
  private static boolean documentationString_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationString_4")) return false;
    deprecatedAnnotationDocumentationLine(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (documentationReference | referenceType | backtickedBlock | documentationTextContent)+
  public static boolean documentationText(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationText")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENTATION_TEXT, "<documentation text>");
    r = documentationText_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!documentationText_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "documentationText", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // documentationReference | referenceType | backtickedBlock | documentationTextContent
  private static boolean documentationText_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationText_0")) return false;
    boolean r;
    r = documentationReference(b, l + 1);
    if (!r) r = referenceType(b, l + 1);
    if (!r) r = backtickedBlock(b, l + 1);
    if (!r) r = documentationTextContent(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // MARKDOWN_DOCUMENTATION_TEXT | DOCUMENTATION_ESCAPED_CHARACTERS
  public static boolean documentationTextContent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "documentationTextContent")) return false;
    if (!nextTokenIs(b, "<documentation text content>", DOCUMENTATION_ESCAPED_CHARACTERS, MARKDOWN_DOCUMENTATION_TEXT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENTATION_TEXT_CONTENT, "<documentation text content>");
    r = consumeToken(b, MARKDOWN_DOCUMENTATION_TEXT);
    if (!r) r = consumeToken(b, DOCUMENTATION_ESCAPED_CHARACTERS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT_TOKEN
  public static boolean dot_resource_path(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dot_resource_path")) return false;
    if (!nextTokenIs(b, DOT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT_TOKEN);
    exit_section_(b, m, DOT_RESOURCE_PATH, r);
    return r;
  }

  /* ********************************************************** */
  // [SOURCE_KEYWORD] dual_attach_point_ident
  public static boolean dual_attach_point(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DUAL_ATTACH_POINT, "<dual attach point>");
    r = dual_attach_point_0(b, l + 1);
    r = r && dual_attach_point_ident(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [SOURCE_KEYWORD]
  private static boolean dual_attach_point_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_0")) return false;
    consumeToken(b, SOURCE_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // TYPE_KEYWORD
  //    | CLASS_KEYWORD
  //    | PARAMETER_KEYWORD
  //    | RETURN_KEYWORD
  //    | [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD] FUNCTION_KEYWORD
  //    | [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD] REMOTE_KEYWORD FUNCTION_KEYWORD
  //    | SERVICE_KEYWORD
  //    | [OBJECT_KEYWORD|RECORD_KEYWORD] FIELD_KEYWORD
  public static boolean dual_attach_point_ident(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DUAL_ATTACH_POINT_IDENT, "<dual attach point ident>");
    r = consumeToken(b, TYPE_KEYWORD);
    if (!r) r = consumeToken(b, CLASS_KEYWORD);
    if (!r) r = consumeToken(b, PARAMETER_KEYWORD);
    if (!r) r = consumeToken(b, RETURN_KEYWORD);
    if (!r) r = dual_attach_point_ident_4(b, l + 1);
    if (!r) r = dual_attach_point_ident_5(b, l + 1);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = dual_attach_point_ident_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD] FUNCTION_KEYWORD
  private static boolean dual_attach_point_ident_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dual_attach_point_ident_4_0(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD]
  private static boolean dual_attach_point_ident_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_4_0")) return false;
    dual_attach_point_ident_4_0_0(b, l + 1);
    return true;
  }

  // OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD
  private static boolean dual_attach_point_ident_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_4_0_0")) return false;
    boolean r;
    r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = consumeToken(b, REMOTE_KEYWORD);
    return r;
  }

  // [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD] REMOTE_KEYWORD FUNCTION_KEYWORD
  private static boolean dual_attach_point_ident_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dual_attach_point_ident_5_0(b, l + 1);
    r = r && consumeTokens(b, 0, REMOTE_KEYWORD, FUNCTION_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // [OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD]
  private static boolean dual_attach_point_ident_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_5_0")) return false;
    dual_attach_point_ident_5_0_0(b, l + 1);
    return true;
  }

  // OBJECT_KEYWORD|SERVICE_KEYWORD|REMOTE_KEYWORD
  private static boolean dual_attach_point_ident_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_5_0_0")) return false;
    boolean r;
    r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = consumeToken(b, REMOTE_KEYWORD);
    return r;
  }

  // [OBJECT_KEYWORD|RECORD_KEYWORD] FIELD_KEYWORD
  private static boolean dual_attach_point_ident_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dual_attach_point_ident_7_0(b, l + 1);
    r = r && consumeToken(b, FIELD_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // [OBJECT_KEYWORD|RECORD_KEYWORD]
  private static boolean dual_attach_point_ident_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_7_0")) return false;
    dual_attach_point_ident_7_0_0(b, l + 1);
    return true;
  }

  // OBJECT_KEYWORD|RECORD_KEYWORD
  private static boolean dual_attach_point_ident_7_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dual_attach_point_ident_7_0_0")) return false;
    boolean r;
    r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, RECORD_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // (named_arg | positional_arg) [COMMA_TOKEN (named_arg | positional_arg)] (COMMA_TOKEN named_arg)*
  public static boolean error_arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_ARG_LIST, "<error arg list>");
    r = error_arg_list_0(b, l + 1);
    r = r && error_arg_list_1(b, l + 1);
    r = r && error_arg_list_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // named_arg | positional_arg
  private static boolean error_arg_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_0")) return false;
    boolean r;
    r = named_arg(b, l + 1);
    if (!r) r = positional_arg(b, l + 1);
    return r;
  }

  // [COMMA_TOKEN (named_arg | positional_arg)]
  private static boolean error_arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_1")) return false;
    error_arg_list_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN (named_arg | positional_arg)
  private static boolean error_arg_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && error_arg_list_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // named_arg | positional_arg
  private static boolean error_arg_list_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_1_0_1")) return false;
    boolean r;
    r = named_arg(b, l + 1);
    if (!r) r = positional_arg(b, l + 1);
    return r;
  }

  // (COMMA_TOKEN named_arg)*
  private static boolean error_arg_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!error_arg_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "error_arg_list_2", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN named_arg
  private static boolean error_arg_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && named_arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // error_message_binding_pattern [COMMA_TOKEN error_cause_binding_pattern] [COMMA_TOKEN error_field_binding_patterns]
  //    | [error_field_binding_patterns]
  public static boolean error_arg_list_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_ARG_LIST_BINDING_PATTERN, "<error arg list binding pattern>");
    r = error_arg_list_binding_pattern_0(b, l + 1);
    if (!r) r = error_arg_list_binding_pattern_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // error_message_binding_pattern [COMMA_TOKEN error_cause_binding_pattern] [COMMA_TOKEN error_field_binding_patterns]
  private static boolean error_arg_list_binding_pattern_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = error_message_binding_pattern(b, l + 1);
    r = r && error_arg_list_binding_pattern_0_1(b, l + 1);
    r = r && error_arg_list_binding_pattern_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN error_cause_binding_pattern]
  private static boolean error_arg_list_binding_pattern_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_0_1")) return false;
    error_arg_list_binding_pattern_0_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN error_cause_binding_pattern
  private static boolean error_arg_list_binding_pattern_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && error_cause_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN error_field_binding_patterns]
  private static boolean error_arg_list_binding_pattern_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_0_2")) return false;
    error_arg_list_binding_pattern_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN error_field_binding_patterns
  private static boolean error_arg_list_binding_pattern_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && error_field_binding_patterns(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [error_field_binding_patterns]
  private static boolean error_arg_list_binding_pattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_arg_list_binding_pattern_1")) return false;
    error_field_binding_patterns(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ERROR_KEYWORD [error_type_reference] OPEN_PAREN_TOKEN error_arg_list_binding_pattern CLOSE_PAREN_TOKEN
  public static boolean error_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_binding_pattern")) return false;
    if (!nextTokenIs(b, ERROR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ERROR_KEYWORD);
    r = r && error_binding_pattern_1(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && error_arg_list_binding_pattern(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, ERROR_BINDING_PATTERN, r);
    return r;
  }

  // [error_type_reference]
  private static boolean error_binding_pattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_binding_pattern_1")) return false;
    error_type_reference(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // simple_binding_pattern | error_binding_pattern
  public static boolean error_cause_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_cause_binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_CAUSE_BINDING_PATTERN, "<error cause binding pattern>");
    r = simple_binding_pattern(b, l + 1);
    if (!r) r = error_binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // named_arg_binding_pattern (COMMA_TOKEN named_arg_binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  //    | rest_binding_pattern
  public static boolean error_field_binding_patterns(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_FIELD_BINDING_PATTERNS, "<error field binding patterns>");
    r = error_field_binding_patterns_0(b, l + 1);
    if (!r) r = rest_binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // named_arg_binding_pattern (COMMA_TOKEN named_arg_binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  private static boolean error_field_binding_patterns_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = named_arg_binding_pattern(b, l + 1);
    r = r && error_field_binding_patterns_0_1(b, l + 1);
    r = r && error_field_binding_patterns_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN named_arg_binding_pattern)*
  private static boolean error_field_binding_patterns_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!error_field_binding_patterns_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "error_field_binding_patterns_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN named_arg_binding_pattern
  private static boolean error_field_binding_patterns_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && named_arg_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_binding_pattern]
  private static boolean error_field_binding_patterns_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns_0_2")) return false;
    error_field_binding_patterns_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_binding_pattern
  private static boolean error_field_binding_patterns_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_field_binding_patterns_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // simple_binding_pattern
  public static boolean error_message_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_message_binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_MESSAGE_BINDING_PATTERN, "<error message binding pattern>");
    r = simple_binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ERROR_KEYWORD [type_parameter]
  public static boolean error_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_type_descriptor")) return false;
    if (!nextTokenIs(b, ERROR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ERROR_KEYWORD);
    r = r && error_type_descriptor_1(b, l + 1);
    exit_section_(b, m, ERROR_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [type_parameter]
  private static boolean error_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_type_descriptor_1")) return false;
    type_parameter(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // type_reference
  public static boolean error_type_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_type_reference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ERROR_TYPE_REFERENCE, "<error type reference>");
    r = type_reference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (RECORD_KEYWORD OPEN_BRACE_PIPE_TOKEN TokensPipe CLOSE_BRACE_PIPE_TOKEN)
  //     | (RECORD_KEYWORD OPEN_NESTED_BRACE_PIPE_TOKEN field_descriptor* [record_rest_descriptor] CLOSE_NESTED_BRACE_PIPE_TOKEN)
  public static boolean exclusive_record_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusive_record_type_descriptor")) return false;
    if (!nextTokenIs(b, RECORD_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = exclusive_record_type_descriptor_0(b, l + 1);
    if (!r) r = exclusive_record_type_descriptor_1(b, l + 1);
    exit_section_(b, m, EXCLUSIVE_RECORD_TYPE_DESCRIPTOR, r);
    return r;
  }

  // RECORD_KEYWORD OPEN_BRACE_PIPE_TOKEN TokensPipe CLOSE_BRACE_PIPE_TOKEN
  private static boolean exclusive_record_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusive_record_type_descriptor_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RECORD_KEYWORD, OPEN_BRACE_PIPE_TOKEN);
    r = r && TokensPipe(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE_PIPE_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // RECORD_KEYWORD OPEN_NESTED_BRACE_PIPE_TOKEN field_descriptor* [record_rest_descriptor] CLOSE_NESTED_BRACE_PIPE_TOKEN
  private static boolean exclusive_record_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusive_record_type_descriptor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RECORD_KEYWORD, OPEN_NESTED_BRACE_PIPE_TOKEN);
    r = r && exclusive_record_type_descriptor_1_2(b, l + 1);
    r = r && exclusive_record_type_descriptor_1_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_NESTED_BRACE_PIPE_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // field_descriptor*
  private static boolean exclusive_record_type_descriptor_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusive_record_type_descriptor_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!field_descriptor(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exclusive_record_type_descriptor_1_2", c)) break;
    }
    return true;
  }

  // [record_rest_descriptor]
  private static boolean exclusive_record_type_descriptor_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exclusive_record_type_descriptor_1_3")) return false;
    record_rest_descriptor(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNCTION_KEYWORD function_signature ( function_defn_body | expr_function_body )
  public static boolean explicit_anonymous_function_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicit_anonymous_function_expr")) return false;
    if (!nextTokenIs(b, FUNCTION_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNCTION_KEYWORD);
    r = r && function_signature(b, l + 1);
    r = r && explicit_anonymous_function_expr_2(b, l + 1);
    exit_section_(b, m, EXPLICIT_ANONYMOUS_FUNCTION_EXPR, r);
    return r;
  }

  // function_defn_body | expr_function_body
  private static boolean explicit_anonymous_function_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicit_anonymous_function_expr_2")) return false;
    boolean r;
    r = function_defn_body(b, l + 1);
    if (!r) r = expr_function_body(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // NEW_KEYWORD class_descriptor OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN
  public static boolean explicit_new_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicit_new_expr")) return false;
    if (!nextTokenIs(b, NEW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_KEYWORD);
    r = r && class_descriptor(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && arg_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, EXPLICIT_NEW_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // RIGHT_DOUBLE_ARROW_TOKEN expression
  public static boolean expr_function_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_function_body")) return false;
    if (!nextTokenIs(b, RIGHT_DOUBLE_ARROW_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RIGHT_DOUBLE_ARROW_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, EXPR_FUNCTION_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA_TOKEN expression)*
  public static boolean expression_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_LIST, "<expression list>");
    r = expression(b, l + 1, -1);
    r = r && expression_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN expression)*
  private static boolean expression_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expression_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expression_list_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN expression
  private static boolean expression_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EQUAL_TOKEN [annots] EXTERNAL_KEYWORD
  public static boolean external_function_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "external_function_body")) return false;
    if (!nextTokenIs(b, EQUAL_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL_TOKEN);
    r = r && external_function_body_1(b, l + 1);
    r = r && consumeToken(b, EXTERNAL_KEYWORD);
    exit_section_(b, m, EXTERNAL_FUNCTION_BODY, r);
    return r;
  }

  // [annots]
  private static boolean external_function_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "external_function_body_1")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // specific_field
  //   | computed_name_field
  //   | spread_field
  public static boolean field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = specific_field(b, l + 1);
    if (!r) r = computed_name_field(b, l + 1);
    if (!r) r = spread_field(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // field_name COLON_TOKEN binding_pattern
  //    | variable_name
  public static boolean field_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_pattern")) return false;
    if (!nextTokenIs(b, "<field binding pattern>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_BINDING_PATTERN, "<field binding pattern>");
    r = field_binding_pattern_0(b, l + 1);
    if (!r) r = variable_name(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // field_name COLON_TOKEN binding_pattern
  private static boolean field_binding_pattern_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_pattern_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = field_name(b, l + 1);
    r = r && consumeToken(b, COLON_TOKEN);
    r = r && binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // field_binding_pattern (COMMA_TOKEN field_binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  //    | [ rest_binding_pattern ]
  public static boolean field_binding_patterns(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_BINDING_PATTERNS, "<field binding patterns>");
    r = field_binding_patterns_0(b, l + 1);
    if (!r) r = field_binding_patterns_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // field_binding_pattern (COMMA_TOKEN field_binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  private static boolean field_binding_patterns_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = field_binding_pattern(b, l + 1);
    r = r && field_binding_patterns_0_1(b, l + 1);
    r = r && field_binding_patterns_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN field_binding_pattern)*
  private static boolean field_binding_patterns_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!field_binding_patterns_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "field_binding_patterns_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN field_binding_pattern
  private static boolean field_binding_patterns_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && field_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_binding_pattern]
  private static boolean field_binding_patterns_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_0_2")) return false;
    field_binding_patterns_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_binding_pattern
  private static boolean field_binding_patterns_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ rest_binding_pattern ]
  private static boolean field_binding_patterns_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_binding_patterns_1")) return false;
    rest_binding_pattern(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // individual_field_descriptor | record_type_inclusion
  public static boolean field_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DESCRIPTOR, "<field descriptor>");
    r = individual_field_descriptor(b, l + 1);
    if (!r) r = record_type_inclusion(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean field_initializer(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_initializer")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_INITIALIZER, "<field initializer>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean field_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_name")) return false;
    if (!nextTokenIs(b, "<field name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_NAME, "<field name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean field_name_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_name_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_NAME_EXPR, "<field name expr>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DECIMAL_FLOATING_POINT_LITERAL_TOKEN | HEX_FLOATING_POINT_LITERAL_TOKEN
  public static boolean floating_point_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "floating_point_literal")) return false;
    if (!nextTokenIs(b, "<floating point literal>", DECIMAL_FLOATING_POINT_LITERAL_TOKEN, HEX_FLOATING_POINT_LITERAL_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLOATING_POINT_LITERAL, "<floating point literal>");
    r = consumeToken(b, DECIMAL_FLOATING_POINT_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, HEX_FLOATING_POINT_LITERAL_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FLOAT_KEYWORD | DECIMAL_KEYWORD
  public static boolean floating_point_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "floating_point_type_descriptor")) return false;
    if (!nextTokenIs(b, "<floating point type descriptor>", DECIMAL_KEYWORD, FLOAT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLOATING_POINT_TYPE_DESCRIPTOR, "<floating point type descriptor>");
    r = consumeToken(b, FLOAT_KEYWORD);
    if (!r) r = consumeToken(b, DECIMAL_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // FROM_KEYWORD typed_binding_pattern IN_KEYWORD expression
  public static boolean from_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "from_clause")) return false;
    if (!nextTokenIs(b, FROM_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FROM_KEYWORD);
    r = r && typed_binding_pattern(b, l + 1);
    r = r && consumeToken(b, IN_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, FROM_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // (metadata [PUBLIC_KEYWORD] [FINAL_KEYWORD] function_type_descriptor IDENTIFIER_TOKEN SEMICOLON_TOKEN)
  //  |  (metadata
  //    [PUBLIC_KEYWORD] function_quals
  //    FUNCTION_KEYWORD [identifier] function_signature function_defn_body)
  public static boolean function_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DEFN, "<function defn>");
    r = function_defn_0(b, l + 1);
    if (!r) r = function_defn_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // metadata [PUBLIC_KEYWORD] [FINAL_KEYWORD] function_type_descriptor IDENTIFIER_TOKEN SEMICOLON_TOKEN
  private static boolean function_defn_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && function_defn_0_1(b, l + 1);
    r = r && function_defn_0_2(b, l + 1);
    r = r && function_type_descriptor(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER_TOKEN, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean function_defn_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_0_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [FINAL_KEYWORD]
  private static boolean function_defn_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_0_2")) return false;
    consumeToken(b, FINAL_KEYWORD);
    return true;
  }

  // metadata
  //    [PUBLIC_KEYWORD] function_quals
  //    FUNCTION_KEYWORD [identifier] function_signature function_defn_body
  private static boolean function_defn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && function_defn_1_1(b, l + 1);
    r = r && function_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && function_defn_1_4(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && function_defn_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean function_defn_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_1_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [identifier]
  private static boolean function_defn_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_1_4")) return false;
    identifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ((OPEN_NESTED_BRACE_TOKEN) TokensIgnore (CLOSE_NESTED_BRACE_TOKEN) [SEMICOLON_TOKEN])
  //    | ((OPEN_BRACE_TOKEN) Tokens (CLOSE_BRACE_TOKEN) [SEMICOLON_TOKEN])
  //    | expr_function_body SEMICOLON_TOKEN
  //    | external_function_body SEMICOLON_TOKEN
  public static boolean function_defn_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DEFN_BODY, "<function defn body>");
    r = function_defn_body_0(b, l + 1);
    if (!r) r = function_defn_body_1(b, l + 1);
    if (!r) r = function_defn_body_2(b, l + 1);
    if (!r) r = function_defn_body_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (OPEN_NESTED_BRACE_TOKEN) TokensIgnore (CLOSE_NESTED_BRACE_TOKEN) [SEMICOLON_TOKEN]
  private static boolean function_defn_body_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    r = r && TokensIgnore(b, l + 1);
    r = r && consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    r = r && function_defn_body_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SEMICOLON_TOKEN]
  private static boolean function_defn_body_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_0_3")) return false;
    consumeToken(b, SEMICOLON_TOKEN);
    return true;
  }

  // (OPEN_BRACE_TOKEN) Tokens (CLOSE_BRACE_TOKEN) [SEMICOLON_TOKEN]
  private static boolean function_defn_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACE_TOKEN);
    r = r && Tokens(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE_TOKEN);
    r = r && function_defn_body_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SEMICOLON_TOKEN]
  private static boolean function_defn_body_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_1_3")) return false;
    consumeToken(b, SEMICOLON_TOKEN);
    return true;
  }

  // expr_function_body SEMICOLON_TOKEN
  private static boolean function_defn_body_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr_function_body(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // external_function_body SEMICOLON_TOKEN
  private static boolean function_defn_body_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_defn_body_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = external_function_body(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // transactional_qual [isolated_qual]
  //    | [isolated_qual] [transactional_qual]
  public static boolean function_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_QUALS, "<function quals>");
    r = function_quals_0(b, l + 1);
    if (!r) r = function_quals_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // transactional_qual [isolated_qual]
  private static boolean function_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transactional_qual(b, l + 1);
    r = r && function_quals_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean function_quals_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals_0_1")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  // [isolated_qual] [transactional_qual]
  private static boolean function_quals_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_quals_1_0(b, l + 1);
    r = r && function_quals_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean function_quals_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals_1_0")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  // [transactional_qual]
  private static boolean function_quals_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_quals_1_1")) return false;
    transactional_qual(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // variable_reference
  public static boolean function_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_reference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_REFERENCE, "<function reference>");
    r = variable_reference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPEN_PAREN_TOKEN param_list CLOSE_PAREN_TOKEN return_type_descriptor
  public static boolean function_signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature")) return false;
    if (!nextTokenIs(b, OPEN_PAREN_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && param_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    r = r && return_type_descriptor(b, l + 1);
    exit_section_(b, m, FUNCTION_SIGNATURE, r);
    return r;
  }

  /* ********************************************************** */
  // function_quals FUNCTION_KEYWORD function_signature
  //    | [isolated_qual] FUNCTION_KEYWORD
  public static boolean function_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_TYPE_DESCRIPTOR, "<function type descriptor>");
    r = function_type_descriptor_0(b, l + 1);
    if (!r) r = function_type_descriptor_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // function_quals FUNCTION_KEYWORD function_signature
  private static boolean function_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_type_descriptor_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && function_signature(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual] FUNCTION_KEYWORD
  private static boolean function_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_type_descriptor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_type_descriptor_1_0(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean function_type_descriptor_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_type_descriptor_1_0")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUTURE_KEYWORD [type_parameter]
  public static boolean future_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "future_type_descriptor")) return false;
    if (!nextTokenIs(b, FUTURE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUTURE_KEYWORD);
    r = r && future_type_descriptor_1(b, l + 1);
    exit_section_(b, m, FUTURE_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [type_parameter]
  private static boolean future_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "future_type_descriptor_1")) return false;
    type_parameter(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // GROUP_KEYWORD BY_KEYWORD grouping_key (COMMA_TOKEN grouping_key)*
  public static boolean group_by_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "group_by_clause")) return false;
    if (!nextTokenIs(b, GROUP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, GROUP_KEYWORD, BY_KEYWORD);
    r = r && grouping_key(b, l + 1);
    r = r && group_by_clause_3(b, l + 1);
    exit_section_(b, m, GROUP_BY_CLAUSE, r);
    return r;
  }

  // (COMMA_TOKEN grouping_key)*
  private static boolean group_by_clause_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "group_by_clause_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!group_by_clause_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "group_by_clause_3", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN grouping_key
  private static boolean group_by_clause_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "group_by_clause_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && grouping_key(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // variable_name
  //    | inferable_type_descriptor variable_name EQUAL_TOKEN expression
  public static boolean grouping_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "grouping_key")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GROUPING_KEY, "<grouping key>");
    r = variable_name(b, l + 1);
    if (!r) r = grouping_key_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // inferable_type_descriptor variable_name EQUAL_TOKEN expression
  private static boolean grouping_key_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "grouping_key_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = inferable_type_descriptor(b, l + 1);
    r = r && variable_name(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HANDLE_KEYWORD
  public static boolean handle_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "handle_type_descriptor")) return false;
    if (!nextTokenIs(b, HANDLE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HANDLE_KEYWORD);
    exit_section_(b, m, HANDLE_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_TOKEN | KEY_KEYWORD
  static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, "", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER_TOKEN);
    if (!r) r = consumeToken(b, KEY_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // NEW_KEYWORD [OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN]
  public static boolean implicit_new_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicit_new_expr")) return false;
    if (!nextTokenIs(b, NEW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEW_KEYWORD);
    r = r && implicit_new_expr_1(b, l + 1);
    exit_section_(b, m, IMPLICIT_NEW_EXPR, r);
    return r;
  }

  // [OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN]
  private static boolean implicit_new_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicit_new_expr_1")) return false;
    implicit_new_expr_1_0(b, l + 1);
    return true;
  }

  // OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN
  private static boolean implicit_new_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicit_new_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && arg_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT_KEYWORD [org_name SLASH_TOKEN] module_name [AS_KEYWORD import_prefix] SEMICOLON_TOKEN
  public static boolean import_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_decl")) return false;
    if (!nextTokenIs(b, IMPORT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_DECL, null);
    r = consumeToken(b, IMPORT_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, import_decl_1(b, l + 1));
    r = p && report_error_(b, module_name(b, l + 1)) && r;
    r = p && report_error_(b, import_decl_3(b, l + 1)) && r;
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [org_name SLASH_TOKEN]
  private static boolean import_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_decl_1")) return false;
    import_decl_1_0(b, l + 1);
    return true;
  }

  // org_name SLASH_TOKEN
  private static boolean import_decl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_decl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = org_name(b, l + 1);
    r = r && consumeToken(b, SLASH_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [AS_KEYWORD import_prefix]
  private static boolean import_decl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_decl_3")) return false;
    import_decl_3_0(b, l + 1);
    return true;
  }

  // AS_KEYWORD import_prefix
  private static boolean import_decl_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_decl_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS_KEYWORD);
    r = r && import_prefix(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean import_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_identifier")) return false;
    if (!nextTokenIs(b, "<import identifier>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_IDENTIFIER, "<import identifier>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // module_prefix | UNDERSCORE_KEYWORD
  public static boolean import_prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_prefix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_PREFIX, "<import prefix>");
    r = module_prefix(b, l + 1);
    if (!r) r = consumeToken(b, UNDERSCORE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [annots] ASTERISK_TOKEN type_reference [param_name]
  public static boolean included_record_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_param")) return false;
    if (!nextTokenIs(b, "<included record param>", ASTERISK_TOKEN, AT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INCLUDED_RECORD_PARAM, "<included record param>");
    r = included_record_param_0(b, l + 1);
    r = r && consumeToken(b, ASTERISK_TOKEN);
    r = r && type_reference(b, l + 1);
    r = r && included_record_param_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean included_record_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_param_0")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean included_record_param_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_param_3")) return false;
    param_name(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // included_record_param (COMMA_TOKEN included_record_param)*
  public static boolean included_record_params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_params")) return false;
    if (!nextTokenIs(b, "<included record params>", ASTERISK_TOKEN, AT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INCLUDED_RECORD_PARAMS, "<included record params>");
    r = included_record_param(b, l + 1);
    r = r && included_record_params_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN included_record_param)*
  private static boolean included_record_params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_params_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!included_record_params_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "included_record_params_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN included_record_param
  private static boolean included_record_params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "included_record_params_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && included_record_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (RECORD_KEYWORD OPEN_BRACE_TOKEN Tokens CLOSE_BRACE_TOKEN)
  //    |(RECORD_KEYWORD OPEN_NESTED_BRACE_TOKEN field_descriptor* CLOSE_NESTED_BRACE_TOKEN)
  public static boolean inclusive_record_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusive_record_type_descriptor")) return false;
    if (!nextTokenIs(b, RECORD_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = inclusive_record_type_descriptor_0(b, l + 1);
    if (!r) r = inclusive_record_type_descriptor_1(b, l + 1);
    exit_section_(b, m, INCLUSIVE_RECORD_TYPE_DESCRIPTOR, r);
    return r;
  }

  // RECORD_KEYWORD OPEN_BRACE_TOKEN Tokens CLOSE_BRACE_TOKEN
  private static boolean inclusive_record_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusive_record_type_descriptor_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RECORD_KEYWORD, OPEN_BRACE_TOKEN);
    r = r && Tokens(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACE_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // RECORD_KEYWORD OPEN_NESTED_BRACE_TOKEN field_descriptor* CLOSE_NESTED_BRACE_TOKEN
  private static boolean inclusive_record_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusive_record_type_descriptor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RECORD_KEYWORD, OPEN_NESTED_BRACE_TOKEN);
    r = r && inclusive_record_type_descriptor_1_2(b, l + 1);
    r = r && consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // field_descriptor*
  private static boolean inclusive_record_type_descriptor_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inclusive_record_type_descriptor_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!field_descriptor(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "inclusive_record_type_descriptor_1_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // metadata [READONLY_KEYWORD] type_descriptor field_name [QUESTION_MARK_TOKEN | (EQUAL_TOKEN default_expression)] SEMICOLON_TOKEN
  public static boolean individual_field_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "individual_field_descriptor")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INDIVIDUAL_FIELD_DESCRIPTOR, "<individual field descriptor>");
    r = metadata(b, l + 1);
    r = r && individual_field_descriptor_1(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    p = r; // pin = 3
    r = r && report_error_(b, field_name(b, l + 1));
    r = p && report_error_(b, individual_field_descriptor_4(b, l + 1)) && r;
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [READONLY_KEYWORD]
  private static boolean individual_field_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "individual_field_descriptor_1")) return false;
    consumeToken(b, READONLY_KEYWORD);
    return true;
  }

  // [QUESTION_MARK_TOKEN | (EQUAL_TOKEN default_expression)]
  private static boolean individual_field_descriptor_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "individual_field_descriptor_4")) return false;
    individual_field_descriptor_4_0(b, l + 1);
    return true;
  }

  // QUESTION_MARK_TOKEN | (EQUAL_TOKEN default_expression)
  private static boolean individual_field_descriptor_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "individual_field_descriptor_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUESTION_MARK_TOKEN);
    if (!r) r = individual_field_descriptor_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EQUAL_TOKEN default_expression
  private static boolean individual_field_descriptor_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "individual_field_descriptor_4_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL_TOKEN);
    r = r && default_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // infer_param_list expr_function_body
  public static boolean infer_anonymous_function_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_anonymous_function_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INFER_ANONYMOUS_FUNCTION_EXPR, "<infer anonymous function expr>");
    r = infer_param_list(b, l + 1);
    r = r && expr_function_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier | OPEN_PAREN_TOKEN[identifier (COMMA_TOKEN identifier)*]CLOSE_PAREN_TOKEN
  public static boolean infer_param_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INFER_PARAM_LIST, "<infer param list>");
    r = identifier(b, l + 1);
    if (!r) r = infer_param_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OPEN_PAREN_TOKEN[identifier (COMMA_TOKEN identifier)*]CLOSE_PAREN_TOKEN
  private static boolean infer_param_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && infer_param_list_1_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [identifier (COMMA_TOKEN identifier)*]
  private static boolean infer_param_list_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list_1_1")) return false;
    infer_param_list_1_1_0(b, l + 1);
    return true;
  }

  // identifier (COMMA_TOKEN identifier)*
  private static boolean infer_param_list_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifier(b, l + 1);
    r = r && infer_param_list_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN identifier)*
  private static boolean infer_param_list_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list_1_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!infer_param_list_1_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "infer_param_list_1_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN identifier
  private static boolean infer_param_list_1_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "infer_param_list_1_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN [ inferable_array_length ] CLOSE_BRACKET_TOKEN
  public static boolean inferable_array_dimension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferable_array_dimension")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && inferable_array_dimension_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, INFERABLE_ARRAY_DIMENSION, r);
    return r;
  }

  // [ inferable_array_length ]
  private static boolean inferable_array_dimension_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferable_array_dimension_1")) return false;
    inferable_array_length(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // array_length | inferred_array_length
  public static boolean inferable_array_length(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferable_array_length")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INFERABLE_ARRAY_LENGTH, "<inferable array length>");
    r = array_length(b, l + 1);
    if (!r) r = inferred_array_length(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // type_descriptor | VAR_KEYWORD
  public static boolean inferable_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferable_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INFERABLE_TYPE_DESCRIPTOR, "<inferable type descriptor>");
    r = type_descriptor(b, l + 1, -1);
    if (!r) r = consumeToken(b, VAR_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ASTERISK_TOKEN
  public static boolean inferred_array_length(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferred_array_length")) return false;
    if (!nextTokenIs(b, ASTERISK_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASTERISK_TOKEN);
    exit_section_(b, m, INFERRED_ARRAY_LENGTH, r);
    return r;
  }

  /* ********************************************************** */
  // LT_TOKEN GT_TOKEN
  public static boolean inferred_typedesc_default(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inferred_typedesc_default")) return false;
    if (!nextTokenIs(b, LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT_TOKEN, GT_TOKEN);
    exit_section_(b, m, INFERRED_TYPEDESC_DEFAULT, r);
    return r;
  }

  /* ********************************************************** */
  // DECIMAL_INTEGER_LITERAL_TOKEN | HEX_INTEGER_LITERAL_TOKEN
  public static boolean int_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "int_literal")) return false;
    if (!nextTokenIs(b, "<int literal>", DECIMAL_INTEGER_LITERAL_TOKEN, HEX_INTEGER_LITERAL_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INT_LITERAL, "<int literal>");
    r = consumeToken(b, DECIMAL_INTEGER_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, HEX_INTEGER_LITERAL_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INT_KEYWORD
  public static boolean int_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "int_type_descriptor")) return false;
    if (!nextTokenIs(b, INT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INT_KEYWORD);
    exit_section_(b, m, INT_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // from_clause
  //    | where_clause
  //    | let_clause
  //    | join_clause
  //    | limit_clause
  //    | order_by_clause
  //    | group_by_clause
  public static boolean intermediate_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "intermediate_clause")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INTERMEDIATE_CLAUSE, "<intermediate clause>");
    r = from_clause(b, l + 1);
    if (!r) r = where_clause(b, l + 1);
    if (!r) r = let_clause(b, l + 1);
    if (!r) r = join_clause(b, l + 1);
    if (!r) r = limit_clause(b, l + 1);
    if (!r) r = order_by_clause(b, l + 1);
    if (!r) r = group_by_clause(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTERPOLATION_START_TOKEN expression INTERPOLATION_END_TOKEN
  public static boolean interpolation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation")) return false;
    if (!nextTokenIs(b, INTERPOLATION_START_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INTERPOLATION, null);
    r = consumeToken(b, INTERPOLATION_START_TOKEN);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && consumeToken(b, INTERPOLATION_END_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INVALID_TOKEN
  public static boolean invalid(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "invalid")) return false;
    if (!nextTokenIs(b, INVALID_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INVALID_TOKEN);
    exit_section_(b, m, INVALID, r);
    return r;
  }

  /* ********************************************************** */
  // ISOLATED_KEYWORD
  public static boolean isolated_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "isolated_qual")) return false;
    if (!nextTokenIs(b, ISOLATED_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ISOLATED_KEYWORD);
    exit_section_(b, m, ISOLATED_QUAL, r);
    return r;
  }

  /* ********************************************************** */
  // [OUTER_KEYWORD] JOIN_KEYWORD typed_binding_pattern IN_KEYWORD expression join_on_condition
  public static boolean join_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "join_clause")) return false;
    if (!nextTokenIs(b, "<join clause>", JOIN_KEYWORD, OUTER_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JOIN_CLAUSE, "<join clause>");
    r = join_clause_0(b, l + 1);
    r = r && consumeToken(b, JOIN_KEYWORD);
    r = r && typed_binding_pattern(b, l + 1);
    r = r && consumeToken(b, IN_KEYWORD);
    r = r && expression(b, l + 1, -1);
    r = r && join_on_condition(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [OUTER_KEYWORD]
  private static boolean join_clause_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "join_clause_0")) return false;
    consumeToken(b, OUTER_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // ON_KEYWORD expression EQUALS_KEYWORD expression
  public static boolean join_on_condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "join_on_condition")) return false;
    if (!nextTokenIs(b, ON_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ON_KEYWORD);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, EQUALS_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, JOIN_ON_CONDITION, r);
    return r;
  }

  /* ********************************************************** */
  // key_specifier | key_type_constraint
  public static boolean key_constraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_constraint")) return false;
    if (!nextTokenIs(b, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key_specifier(b, l + 1);
    if (!r) r = key_type_constraint(b, l + 1);
    exit_section_(b, m, KEY_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean key_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEY_EXPRESSION, "<key expression>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEY_KEYWORD OPEN_PAREN_TOKEN [ field_name (COMMA_TOKEN field_name)* ] CLOSE_PAREN_TOKEN
  public static boolean key_specifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_specifier")) return false;
    if (!nextTokenIs(b, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY_KEYWORD, OPEN_PAREN_TOKEN);
    r = r && key_specifier_2(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, KEY_SPECIFIER, r);
    return r;
  }

  // [ field_name (COMMA_TOKEN field_name)* ]
  private static boolean key_specifier_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_specifier_2")) return false;
    key_specifier_2_0(b, l + 1);
    return true;
  }

  // field_name (COMMA_TOKEN field_name)*
  private static boolean key_specifier_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_specifier_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = field_name(b, l + 1);
    r = r && key_specifier_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN field_name)*
  private static boolean key_specifier_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_specifier_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!key_specifier_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "key_specifier_2_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN field_name
  private static boolean key_specifier_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_specifier_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && field_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEY_KEYWORD type_parameter
  public static boolean key_type_constraint(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_type_constraint")) return false;
    if (!nextTokenIs(b, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_KEYWORD);
    r = r && type_parameter(b, l + 1);
    exit_section_(b, m, KEY_TYPE_CONSTRAINT, r);
    return r;
  }

  /* ********************************************************** */
  // PUBLIC_KEYWORD
  //     |PRIVATE_KEYWORD
  //     |REMOTE_KEYWORD
  //     |ABSTRACT_KEYWORD
  //     |CLIENT_KEYWORD
  //     |IMPORT_KEYWORD
  //     |FUNCTION_KEYWORD
  //     |CONST_KEYWORD
  //     |LISTENER_KEYWORD
  //     |SERVICE_KEYWORD
  //     |XMLNS_KEYWORD
  //     |ANNOTATION_KEYWORD
  //     |TYPE_KEYWORD
  //     |RECORD_KEYWORD
  //     |OBJECT_KEYWORD
  //     |AS_KEYWORD
  //     |ON_KEYWORD
  //     |RESOURCE_KEYWORD
  //     |FINAL_KEYWORD
  //     |SOURCE_KEYWORD
  //     |WORKER_KEYWORD
  //     |PARAMETER_KEYWORD
  //     |FIELD_KEYWORD
  //     |ISOLATED_KEYWORD
  //     |RETURNS_KEYWORD
  //     |RETURN_KEYWORD
  //     |EXTERNAL_KEYWORD
  //     |TRUE_KEYWORD
  //     |FALSE_KEYWORD
  //     |IF_KEYWORD
  //     |ELSE_KEYWORD
  //     |ELSEIF_KEYWORD
  //     |WHILE_KEYWORD
  //     |CHECK_KEYWORD
  //     |CHECKPANIC_KEYWORD
  //     |PANIC_KEYWORD
  //     |CONTINUE_KEYWORD
  //     |BREAK_KEYWORD
  //     |TYPEOF_KEYWORD
  //     |IS_KEYWORD
  //     |NULL_KEYWORD
  //     |LOCK_KEYWORD
  //     |FORK_KEYWORD
  //     |TRAP_KEYWORD
  //     |IN_KEYWORD
  //     |FOREACH_KEYWORD
  //     |TABLE_KEYWORD
  //     |KEY_KEYWORD
  //     |LET_KEYWORD
  //     |NEW_KEYWORD
  //     |FROM_KEYWORD
  //     |WHERE_KEYWORD
  //     |SELECT_KEYWORD
  //     |START_KEYWORD
  //     |FLUSH_KEYWORD
  //     |CONFIGURABLE_KEYWORD
  //     |WAIT_KEYWORD
  //     |DO_KEYWORD
  //     |TRANSACTION_KEYWORD
  //     |TRANSACTIONAL_KEYWORD
  //     |COMMIT_KEYWORD
  //     |ROLLBACK_KEYWORD
  //     |RETRY_KEYWORD
  //     |ENUM_KEYWORD
  //     |BASE16_KEYWORD
  //     |BASE64_KEYWORD
  //     |MATCH_KEYWORD
  //     |CONFLICT_KEYWORD
  //     |LIMIT_KEYWORD
  //     |JOIN_KEYWORD
  //     |OUTER_KEYWORD
  //     |EQUALS_KEYWORD
  //     |CLASS_KEYWORD
  //     |ORDER_KEYWORD
  //     |BY_KEYWORD
  //     |ASCENDING_KEYWORD
  //     |DESCENDING_KEYWORD
  //     |UNDERSCORE_KEYWORD
  //     |NOT_IS_KEYWORD
  static boolean keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyword")) return false;
    boolean r;
    r = consumeToken(b, PUBLIC_KEYWORD);
    if (!r) r = consumeToken(b, PRIVATE_KEYWORD);
    if (!r) r = consumeToken(b, REMOTE_KEYWORD);
    if (!r) r = consumeToken(b, ABSTRACT_KEYWORD);
    if (!r) r = consumeToken(b, CLIENT_KEYWORD);
    if (!r) r = consumeToken(b, IMPORT_KEYWORD);
    if (!r) r = consumeToken(b, FUNCTION_KEYWORD);
    if (!r) r = consumeToken(b, CONST_KEYWORD);
    if (!r) r = consumeToken(b, LISTENER_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = consumeToken(b, XMLNS_KEYWORD);
    if (!r) r = consumeToken(b, ANNOTATION_KEYWORD);
    if (!r) r = consumeToken(b, TYPE_KEYWORD);
    if (!r) r = consumeToken(b, RECORD_KEYWORD);
    if (!r) r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, AS_KEYWORD);
    if (!r) r = consumeToken(b, ON_KEYWORD);
    if (!r) r = consumeToken(b, RESOURCE_KEYWORD);
    if (!r) r = consumeToken(b, FINAL_KEYWORD);
    if (!r) r = consumeToken(b, SOURCE_KEYWORD);
    if (!r) r = consumeToken(b, WORKER_KEYWORD);
    if (!r) r = consumeToken(b, PARAMETER_KEYWORD);
    if (!r) r = consumeToken(b, FIELD_KEYWORD);
    if (!r) r = consumeToken(b, ISOLATED_KEYWORD);
    if (!r) r = consumeToken(b, RETURNS_KEYWORD);
    if (!r) r = consumeToken(b, RETURN_KEYWORD);
    if (!r) r = consumeToken(b, EXTERNAL_KEYWORD);
    if (!r) r = consumeToken(b, TRUE_KEYWORD);
    if (!r) r = consumeToken(b, FALSE_KEYWORD);
    if (!r) r = consumeToken(b, IF_KEYWORD);
    if (!r) r = consumeToken(b, ELSE_KEYWORD);
    if (!r) r = consumeToken(b, ELSEIF_KEYWORD);
    if (!r) r = consumeToken(b, WHILE_KEYWORD);
    if (!r) r = consumeToken(b, CHECK_KEYWORD);
    if (!r) r = consumeToken(b, CHECKPANIC_KEYWORD);
    if (!r) r = consumeToken(b, PANIC_KEYWORD);
    if (!r) r = consumeToken(b, CONTINUE_KEYWORD);
    if (!r) r = consumeToken(b, BREAK_KEYWORD);
    if (!r) r = consumeToken(b, TYPEOF_KEYWORD);
    if (!r) r = consumeToken(b, IS_KEYWORD);
    if (!r) r = consumeToken(b, NULL_KEYWORD);
    if (!r) r = consumeToken(b, LOCK_KEYWORD);
    if (!r) r = consumeToken(b, FORK_KEYWORD);
    if (!r) r = consumeToken(b, TRAP_KEYWORD);
    if (!r) r = consumeToken(b, IN_KEYWORD);
    if (!r) r = consumeToken(b, FOREACH_KEYWORD);
    if (!r) r = consumeToken(b, TABLE_KEYWORD);
    if (!r) r = consumeToken(b, KEY_KEYWORD);
    if (!r) r = consumeToken(b, LET_KEYWORD);
    if (!r) r = consumeToken(b, NEW_KEYWORD);
    if (!r) r = consumeToken(b, FROM_KEYWORD);
    if (!r) r = consumeToken(b, WHERE_KEYWORD);
    if (!r) r = consumeToken(b, SELECT_KEYWORD);
    if (!r) r = consumeToken(b, START_KEYWORD);
    if (!r) r = consumeToken(b, FLUSH_KEYWORD);
    if (!r) r = consumeToken(b, CONFIGURABLE_KEYWORD);
    if (!r) r = consumeToken(b, WAIT_KEYWORD);
    if (!r) r = consumeToken(b, DO_KEYWORD);
    if (!r) r = consumeToken(b, TRANSACTION_KEYWORD);
    if (!r) r = consumeToken(b, TRANSACTIONAL_KEYWORD);
    if (!r) r = consumeToken(b, COMMIT_KEYWORD);
    if (!r) r = consumeToken(b, ROLLBACK_KEYWORD);
    if (!r) r = consumeToken(b, RETRY_KEYWORD);
    if (!r) r = consumeToken(b, ENUM_KEYWORD);
    if (!r) r = consumeToken(b, BASE16_KEYWORD);
    if (!r) r = consumeToken(b, BASE64_KEYWORD);
    if (!r) r = consumeToken(b, MATCH_KEYWORD);
    if (!r) r = consumeToken(b, CONFLICT_KEYWORD);
    if (!r) r = consumeToken(b, LIMIT_KEYWORD);
    if (!r) r = consumeToken(b, JOIN_KEYWORD);
    if (!r) r = consumeToken(b, OUTER_KEYWORD);
    if (!r) r = consumeToken(b, EQUALS_KEYWORD);
    if (!r) r = consumeToken(b, CLASS_KEYWORD);
    if (!r) r = consumeToken(b, ORDER_KEYWORD);
    if (!r) r = consumeToken(b, BY_KEYWORD);
    if (!r) r = consumeToken(b, ASCENDING_KEYWORD);
    if (!r) r = consumeToken(b, DESCENDING_KEYWORD);
    if (!r) r = consumeToken(b, UNDERSCORE_KEYWORD);
    if (!r) r = consumeToken(b, NOT_IS_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // OPEN_PAREN_TOKEN const_expr CLOSE_PAREN_TOKEN
  public static boolean last_const_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "last_const_expr")) return false;
    if (!nextTokenIs(b, OPEN_PAREN_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && const_expr(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, LAST_CONST_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // LET_KEYWORD let_var_decl (COMMA_TOKEN let_var_decl)*
  public static boolean let_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_clause")) return false;
    if (!nextTokenIs(b, LET_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LET_KEYWORD);
    r = r && let_var_decl(b, l + 1);
    r = r && let_clause_2(b, l + 1);
    exit_section_(b, m, LET_CLAUSE, r);
    return r;
  }

  // (COMMA_TOKEN let_var_decl)*
  private static boolean let_clause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_clause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!let_clause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "let_clause_2", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN let_var_decl
  private static boolean let_clause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_clause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && let_var_decl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [annots] typed_binding_pattern EQUAL_TOKEN expression
  public static boolean let_var_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_var_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LET_VAR_DECL, "<let var decl>");
    r = let_var_decl_0(b, l + 1);
    r = r && typed_binding_pattern(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean let_var_decl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_var_decl_0")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LIMIT_KEYWORD expression
  public static boolean limit_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "limit_clause")) return false;
    if (!nextTokenIs(b, LIMIT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LIMIT_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, LIMIT_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN list_member_binding_patterns CLOSE_BRACKET_TOKEN
  public static boolean list_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_binding_pattern")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && list_member_binding_patterns(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, LIST_BINDING_PATTERN, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN [ list_member_list ] CLOSE_BRACKET_TOKEN
  public static boolean list_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_constructor_expr")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && list_constructor_expr_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, LIST_CONSTRUCTOR_EXPR, r);
    return r;
  }

  // [ list_member_list ]
  private static boolean list_constructor_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_constructor_expr_1")) return false;
    list_member_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // single_list_member | spread_list_member
  public static boolean list_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_MEMBER, "<list member>");
    r = single_list_member(b, l + 1);
    if (!r) r = spread_list_member(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // binding_pattern (COMMA_TOKEN binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  //    | [ rest_binding_pattern ]
  public static boolean list_member_binding_patterns(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_MEMBER_BINDING_PATTERNS, "<list member binding patterns>");
    r = list_member_binding_patterns_0(b, l + 1);
    if (!r) r = list_member_binding_patterns_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // binding_pattern (COMMA_TOKEN binding_pattern)* [COMMA_TOKEN rest_binding_pattern]
  private static boolean list_member_binding_patterns_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = binding_pattern(b, l + 1);
    r = r && list_member_binding_patterns_0_1(b, l + 1);
    r = r && list_member_binding_patterns_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN binding_pattern)*
  private static boolean list_member_binding_patterns_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_member_binding_patterns_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "list_member_binding_patterns_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN binding_pattern
  private static boolean list_member_binding_patterns_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_binding_pattern]
  private static boolean list_member_binding_patterns_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_0_2")) return false;
    list_member_binding_patterns_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_binding_pattern
  private static boolean list_member_binding_patterns_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_binding_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ rest_binding_pattern ]
  private static boolean list_member_binding_patterns_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_binding_patterns_1")) return false;
    rest_binding_pattern(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // list_member (COMMA_TOKEN list_member)*
  public static boolean list_member_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_MEMBER_LIST, "<list member list>");
    r = list_member(b, l + 1);
    r = r && list_member_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN list_member)*
  private static boolean list_member_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_member_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "list_member_list_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN list_member
  private static boolean list_member_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_member_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && list_member(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (metadata
  //    [PUBLIC_KEYWORD] LISTENER_KEYWORD listener_variable_name EQUAL_TOKEN expression SEMICOLON_TOKEN)
  //   | (metadata
  //    [PUBLIC_KEYWORD] LISTENER_KEYWORD type_descriptor listener_variable_name EQUAL_TOKEN expression SEMICOLON_TOKEN)
  public static boolean listener_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LISTENER_DECL, "<listener decl>");
    r = listener_decl_0(b, l + 1);
    if (!r) r = listener_decl_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // metadata
  //    [PUBLIC_KEYWORD] LISTENER_KEYWORD listener_variable_name EQUAL_TOKEN expression SEMICOLON_TOKEN
  private static boolean listener_decl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_decl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && listener_decl_0_1(b, l + 1);
    r = r && consumeToken(b, LISTENER_KEYWORD);
    r = r && listener_variable_name(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean listener_decl_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_decl_0_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // metadata
  //    [PUBLIC_KEYWORD] LISTENER_KEYWORD type_descriptor listener_variable_name EQUAL_TOKEN expression SEMICOLON_TOKEN
  private static boolean listener_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_decl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = metadata(b, l + 1);
    r = r && listener_decl_1_1(b, l + 1);
    r = r && consumeToken(b, LISTENER_KEYWORD);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && listener_variable_name(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean listener_decl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_decl_1_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER_TOKEN
  public static boolean listener_variable_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listener_variable_name")) return false;
    if (!nextTokenIs(b, IDENTIFIER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER_TOKEN);
    exit_section_(b, m, LISTENER_VARIABLE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER_TOKEN
  //     |STRING_LITERAL_TOKEN
  //     |DECIMAL_INTEGER_LITERAL_TOKEN
  //     |HEX_INTEGER_LITERAL_TOKEN
  //     |DECIMAL_FLOATING_POINT_LITERAL_TOKEN
  //     |HEX_FLOATING_POINT_LITERAL_TOKEN
  //     |XML_TEXT_CONTENT
  //     |TEMPLATE_STRING
  static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER_TOKEN);
    if (!r) r = consumeToken(b, STRING_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, DECIMAL_INTEGER_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, HEX_INTEGER_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, DECIMAL_FLOATING_POINT_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, HEX_FLOATING_POINT_LITERAL_TOKEN);
    if (!r) r = consumeToken(b, XML_TEXT_CONTENT);
    if (!r) r = consumeToken(b, TEMPLATE_STRING);
    return r;
  }

  /* ********************************************************** */
  // MAP_KEYWORD type_parameter
  public static boolean map_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "map_type_descriptor")) return false;
    if (!nextTokenIs(b, MAP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MAP_KEYWORD);
    r = r && type_parameter(b, l + 1);
    exit_section_(b, m, MAP_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // (OPEN_NESTED_BRACE_TOKEN|OPEN_BRACE_TOKEN) field_binding_patterns (CLOSE_NESTED_BRACE_TOKEN|CLOSE_BRACE_TOKEN)
  public static boolean mapping_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_binding_pattern")) return false;
    if (!nextTokenIs(b, "<mapping binding pattern>", OPEN_BRACE_TOKEN, OPEN_NESTED_BRACE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAPPING_BINDING_PATTERN, "<mapping binding pattern>");
    r = mapping_binding_pattern_0(b, l + 1);
    r = r && field_binding_patterns(b, l + 1);
    r = r && mapping_binding_pattern_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OPEN_NESTED_BRACE_TOKEN|OPEN_BRACE_TOKEN
  private static boolean mapping_binding_pattern_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_binding_pattern_0")) return false;
    boolean r;
    r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACE_TOKEN);
    return r;
  }

  // CLOSE_NESTED_BRACE_TOKEN|CLOSE_BRACE_TOKEN
  private static boolean mapping_binding_pattern_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_binding_pattern_2")) return false;
    boolean r;
    r = consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // (IGNORED_OPEN_BRACE_TOKEN | OPEN_NESTED_BRACE_TOKEN | OPEN_BRACE_TOKEN) [field (COMMA_TOKEN field)*] (IGNORED_CLOSE_BRACE_TOKEN | CLOSE_NESTED_BRACE_TOKEN | CLOSE_BRACE_TOKEN)
  public static boolean mapping_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAPPING_CONSTRUCTOR_EXPR, "<mapping constructor expr>");
    r = mapping_constructor_expr_0(b, l + 1);
    r = r && mapping_constructor_expr_1(b, l + 1);
    r = r && mapping_constructor_expr_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IGNORED_OPEN_BRACE_TOKEN | OPEN_NESTED_BRACE_TOKEN | OPEN_BRACE_TOKEN
  private static boolean mapping_constructor_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_0")) return false;
    boolean r;
    r = consumeToken(b, IGNORED_OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACE_TOKEN);
    return r;
  }

  // [field (COMMA_TOKEN field)*]
  private static boolean mapping_constructor_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_1")) return false;
    mapping_constructor_expr_1_0(b, l + 1);
    return true;
  }

  // field (COMMA_TOKEN field)*
  private static boolean mapping_constructor_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = field(b, l + 1);
    r = r && mapping_constructor_expr_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN field)*
  private static boolean mapping_constructor_expr_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!mapping_constructor_expr_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "mapping_constructor_expr_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN field
  private static boolean mapping_constructor_expr_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IGNORED_CLOSE_BRACE_TOKEN | CLOSE_NESTED_BRACE_TOKEN | CLOSE_BRACE_TOKEN
  private static boolean mapping_constructor_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_constructor_expr_2")) return false;
    boolean r;
    r = consumeToken(b, IGNORED_CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // [annots] type_descriptor
  public static boolean member_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MEMBER_TYPE_DESCRIPTOR, "<member type descriptor>");
    r = member_type_descriptor_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean member_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_type_descriptor_0")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // [documentationString][annots]
  public static boolean metadata(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadata")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METADATA, "<metadata>");
    r = metadata_0(b, l + 1);
    r = r && metadata_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [documentationString]
  private static boolean metadata_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadata_0")) return false;
    documentationString(b, l + 1);
    return true;
  }

  // [annots]
  private static boolean metadata_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadata_1")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // metadata [PUBLIC_KEYWORD] method_quals
  //    FUNCTION_KEYWORD method_name function_signature SEMICOLON_TOKEN
  public static boolean method_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DECL, "<method decl>");
    r = metadata(b, l + 1);
    r = r && method_decl_1(b, l + 1);
    r = r && method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && method_name(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean method_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_decl_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // metadata [object_visibility_qual] method_quals
  //    FUNCTION_KEYWORD method_name function_signature method_defn_body
  public static boolean method_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_defn")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DEFN, "<method defn>");
    r = metadata(b, l + 1);
    r = r && method_defn_1(b, l + 1);
    r = r && method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && method_name(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && method_defn_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [object_visibility_qual]
  private static boolean method_defn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_defn_1")) return false;
    object_visibility_qual(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // function_defn_body
  public static boolean method_defn_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_defn_body")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DEFN_BODY, "<method defn body>");
    r = function_defn_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier | special_method_name
  static boolean method_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_name")) return false;
    boolean r;
    r = identifier(b, l + 1);
    if (!r) r = special_method_name(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // function_quals
  public static boolean method_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, METHOD_QUALS, "<method quals>");
    r = function_quals(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // WHITESPACE_MINUTIAE
  //     |END_OF_LINE_MINUTIAE
  //     |COMMENT_MINUTIAE
  //     |INVALID_NODE_MINUTIAE
  public static boolean minutiae(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "minutiae")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MINUTIAE, "<minutiae>");
    r = consumeToken(b, WHITESPACE_MINUTIAE);
    if (!r) r = consumeToken(b, END_OF_LINE_MINUTIAE);
    if (!r) r = consumeToken(b, COMMENT_MINUTIAE);
    if (!r) r = consumeToken(b, INVALID_NODE_MINUTIAE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // other_decl
  public static boolean moduleDefinitions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "moduleDefinitions")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, OTHER_DECL, "<module definitions>");
    r = other_decl(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // metadata
  //    [PUBLIC_KEYWORD] class_type_quals CLASS_KEYWORD identifier OPEN_BRACE_TOKEN
  //       class_members
  //   CLOSE_BRACE_TOKEN [SEMICOLON_TOKEN]
  public static boolean module_class_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_class_defn")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_CLASS_DEFN, "<module class defn>");
    r = metadata(b, l + 1);
    r = r && module_class_defn_1(b, l + 1);
    r = r && class_type_quals(b, l + 1);
    r = r && consumeToken(b, CLASS_KEYWORD);
    p = r; // pin = 4
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && report_error_(b, consumeToken(b, OPEN_BRACE_TOKEN)) && r;
    r = p && report_error_(b, class_members(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, CLOSE_BRACE_TOKEN)) && r;
    r = p && module_class_defn_8(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [PUBLIC_KEYWORD]
  private static boolean module_class_defn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_class_defn_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [SEMICOLON_TOKEN]
  private static boolean module_class_defn_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_class_defn_8")) return false;
    consumeToken(b, SEMICOLON_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // metadata PUBLIC_KEYWORD? CONST_KEYWORD (type_descriptor identifier | identifier) EQUAL_TOKEN const_expr SEMICOLON_TOKEN
  public static boolean module_const_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_const_decl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_CONST_DECL, "<module const decl>");
    r = metadata(b, l + 1);
    r = r && module_const_decl_1(b, l + 1);
    r = r && consumeToken(b, CONST_KEYWORD);
    r = r && module_const_decl_3(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    p = r; // pin = 5
    r = r && report_error_(b, const_expr(b, l + 1));
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // PUBLIC_KEYWORD?
  private static boolean module_const_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_const_decl_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // type_descriptor identifier | identifier
  private static boolean module_const_decl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_const_decl_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_const_decl_3_0(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // type_descriptor identifier
  private static boolean module_const_decl_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_const_decl_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_descriptor(b, l + 1, -1);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // metadata
  //    [PUBLIC_KEYWORD] ENUM_KEYWORD identifier OPEN_BRACE_TOKEN Tokens CLOSE_BRACE_TOKEN [SEMICOLON_TOKEN]
  public static boolean module_enum_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_enum_decl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_ENUM_DECL, "<module enum decl>");
    r = metadata(b, l + 1);
    r = r && module_enum_decl_1(b, l + 1);
    r = r && consumeToken(b, ENUM_KEYWORD);
    p = r; // pin = 3
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && report_error_(b, consumeToken(b, OPEN_BRACE_TOKEN)) && r;
    r = p && report_error_(b, Tokens(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, CLOSE_BRACE_TOKEN)) && r;
    r = p && module_enum_decl_7(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [PUBLIC_KEYWORD]
  private static boolean module_enum_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_enum_decl_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [SEMICOLON_TOKEN]
  private static boolean module_enum_decl_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_enum_decl_7")) return false;
    consumeToken(b, SEMICOLON_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // metadata [PUBLIC_KEYWORD] [module_init_var_quals] typed_binding_pattern EQUAL_TOKEN module_var_init SEMICOLON_TOKEN
  public static boolean module_init_var_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_decl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_INIT_VAR_DECL, "<module init var decl>");
    r = metadata(b, l + 1);
    r = r && module_init_var_decl_1(b, l + 1);
    r = r && module_init_var_decl_2(b, l + 1);
    r = r && typed_binding_pattern(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    p = r; // pin = 5
    r = r && report_error_(b, module_var_init(b, l + 1));
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [PUBLIC_KEYWORD]
  private static boolean module_init_var_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_decl_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [module_init_var_quals]
  private static boolean module_init_var_decl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_decl_2")) return false;
    module_init_var_quals(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (FINAL_KEYWORD | isolated_qual)+ | CONFIGURABLE_KEYWORD
  public static boolean module_init_var_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_INIT_VAR_QUALS, "<module init var quals>");
    r = module_init_var_quals_0(b, l + 1);
    if (!r) r = consumeToken(b, CONFIGURABLE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (FINAL_KEYWORD | isolated_qual)+
  private static boolean module_init_var_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_quals_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_init_var_quals_0_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!module_init_var_quals_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "module_init_var_quals_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // FINAL_KEYWORD | isolated_qual
  private static boolean module_init_var_quals_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_init_var_quals_0_0")) return false;
    boolean r;
    r = consumeToken(b, FINAL_KEYWORD);
    if (!r) r = isolated_qual(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // import_identifier (DOT_TOKEN import_identifier)*
  public static boolean module_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_name")) return false;
    if (!nextTokenIs(b, "<module name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_NAME, "<module name>");
    r = import_identifier(b, l + 1);
    p = r; // pin = 1
    r = r && module_name_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (DOT_TOKEN import_identifier)*
  private static boolean module_name_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_name_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!module_name_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "module_name_1", c)) break;
    }
    return true;
  }

  // DOT_TOKEN import_identifier
  private static boolean module_name_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_name_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT_TOKEN);
    r = r && import_identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // metadata [PUBLIC_KEYWORD] [FINAL_KEYWORD] type_descriptor variable_name SEMICOLON_TOKEN
  public static boolean module_no_init_var_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_no_init_var_decl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_NO_INIT_VAR_DECL, "<module no init var decl>");
    r = metadata(b, l + 1);
    r = r && module_no_init_var_decl_1(b, l + 1);
    r = r && module_no_init_var_decl_2(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && variable_name(b, l + 1);
    p = r; // pin = 5
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [PUBLIC_KEYWORD]
  private static boolean module_no_init_var_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_no_init_var_decl_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  // [FINAL_KEYWORD]
  private static boolean module_no_init_var_decl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_no_init_var_decl_2")) return false;
    consumeToken(b, FINAL_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // import_decl* moduleDefinitions*
  public static boolean module_part(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_part")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_PART, "<module part>");
    r = module_part_0(b, l + 1);
    r = r && module_part_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // import_decl*
  private static boolean module_part_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_part_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!import_decl(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "module_part_0", c)) break;
    }
    return true;
  }

  // moduleDefinitions*
  private static boolean module_part_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_part_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!moduleDefinitions(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "module_part_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // predeclared_prefix | identifier
  static boolean module_prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_prefix")) return false;
    boolean r;
    r = predeclared_prefix(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // metadata
  //    [PUBLIC_KEYWORD] TYPE_KEYWORD identifier type_descriptor SEMICOLON_TOKEN
  public static boolean module_type_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_type_defn")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MODULE_TYPE_DEFN, "<module type defn>");
    r = metadata(b, l + 1);
    r = r && module_type_defn_1(b, l + 1);
    r = r && consumeToken(b, TYPE_KEYWORD);
    p = r; // pin = 3
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && report_error_(b, type_descriptor(b, l + 1, -1)) && r;
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [PUBLIC_KEYWORD]
  private static boolean module_type_defn_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_type_defn_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // module_init_var_decl | module_no_init_var_decl
  public static boolean module_var_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_var_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_VAR_DECL, "<module var decl>");
    r = module_init_var_decl(b, l + 1);
    if (!r) r = module_no_init_var_decl(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression | QUESTION_MARK_TOKEN
  public static boolean module_var_init(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_var_init")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODULE_VAR_INIT, "<module var init>");
    r = expression(b, l + 1, -1);
    if (!r) r = consumeToken(b, QUESTION_MARK_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // xmlns_decl
  public static boolean module_xmlns_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "module_xmlns_decl")) return false;
    if (!nextTokenIs(b, XMLNS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = xmlns_decl(b, l + 1);
    exit_section_(b, m, MODULE_XMLNS_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA_TOKEN expression)+
  public static boolean multi_key_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multi_key_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MULTI_KEY_EXPRESSION, "<multi key expression>");
    r = expression(b, l + 1, -1);
    r = r && multi_key_expression_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN expression)+
  private static boolean multi_key_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multi_key_expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multi_key_expression_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!multi_key_expression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "multi_key_expression_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA_TOKEN expression
  private static boolean multi_key_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multi_key_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // arg_name EQUAL_TOKEN expression
  public static boolean named_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "named_arg")) return false;
    if (!nextTokenIs(b, "<named arg>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAMED_ARG, "<named arg>");
    r = arg_name(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // arg_name EQUAL_TOKEN binding_pattern
  public static boolean named_arg_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "named_arg_binding_pattern")) return false;
    if (!nextTokenIs(b, "<named arg binding pattern>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAMED_ARG_BINDING_PATTERN, "<named arg binding pattern>");
    r = arg_name(b, l + 1);
    r = r && consumeToken(b, EQUAL_TOKEN);
    r = r && binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // named_arg (COMMA_TOKEN named_arg)*
  public static boolean named_args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "named_args")) return false;
    if (!nextTokenIs(b, "<named args>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NAMED_ARGS, "<named args>");
    r = named_arg(b, l + 1);
    r = r && named_args_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN named_arg)*
  private static boolean named_args_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "named_args_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!named_args_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "named_args_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN named_arg
  private static boolean named_args_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "named_args_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && named_arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ( OPEN_PAREN_TOKEN CLOSE_PAREN_TOKEN ) | NULL_KEYWORD
  public static boolean nil_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nil_literal")) return false;
    if (!nextTokenIs(b, "<nil literal>", NULL_KEYWORD, OPEN_PAREN_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NIL_LITERAL, "<nil literal>");
    r = nil_literal_0(b, l + 1);
    if (!r) r = consumeToken(b, NULL_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OPEN_PAREN_TOKEN CLOSE_PAREN_TOKEN
  private static boolean nil_literal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nil_literal_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPEN_PAREN_TOKEN, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // nil_literal
  public static boolean nil_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nil_type_descriptor")) return false;
    if (!nextTokenIs(b, "<nil type descriptor>", NULL_KEYWORD, OPEN_PAREN_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NIL_TYPE_DESCRIPTOR, "<nil type descriptor>");
    r = nil_literal(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // int_literal | floating_point_literal
  public static boolean numeric_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "numeric_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = int_literal(b, l + 1);
    if (!r) r = floating_point_literal(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPEN_NESTED_BRACE_TOKEN object_member* CLOSE_NESTED_BRACE_TOKEN
  public static boolean object_constructor_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_constructor_block")) return false;
    if (!nextTokenIs(b, OPEN_NESTED_BRACE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    r = r && object_constructor_block_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    exit_section_(b, m, OBJECT_CONSTRUCTOR_BLOCK, r);
    return r;
  }

  // object_member*
  private static boolean object_constructor_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_constructor_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_member(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_constructor_block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // metadata [object_visibility_qual] [FINAL_KEYWORD]
  //    type_descriptor field_name [EQUAL_TOKEN field_initializer] SEMICOLON_TOKEN
  public static boolean object_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_FIELD, "<object field>");
    r = metadata(b, l + 1);
    r = r && object_field_1(b, l + 1);
    r = r && object_field_2(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && field_name(b, l + 1);
    r = r && object_field_5(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [object_visibility_qual]
  private static boolean object_field_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_1")) return false;
    object_visibility_qual(b, l + 1);
    return true;
  }

  // [FINAL_KEYWORD]
  private static boolean object_field_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_2")) return false;
    consumeToken(b, FINAL_KEYWORD);
    return true;
  }

  // [EQUAL_TOKEN field_initializer]
  private static boolean object_field_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_5")) return false;
    object_field_5_0(b, l + 1);
    return true;
  }

  // EQUAL_TOKEN field_initializer
  private static boolean object_field_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL_TOKEN);
    r = r && field_initializer(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // metadata [PUBLIC_KEYWORD] type_descriptor field_name SEMICOLON_TOKEN
  public static boolean object_field_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_FIELD_DESCRIPTOR, "<object field descriptor>");
    r = metadata(b, l + 1);
    r = r && object_field_descriptor_1(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && field_name(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [PUBLIC_KEYWORD]
  private static boolean object_field_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_descriptor_1")) return false;
    consumeToken(b, PUBLIC_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // object_field | method_defn | remote_method_defn | resource_method_defn
  public static boolean object_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_MEMBER, "<object member>");
    r = object_field(b, l + 1);
    if (!r) r = method_defn(b, l + 1);
    if (!r) r = remote_method_defn(b, l + 1);
    if (!r) r = resource_method_defn(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // object_field_descriptor
  //    | method_decl
  //    | remote_method_decl
  //    | resource_method_decl
  //    | object_type_inclusion
  public static boolean object_member_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_member_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_MEMBER_DESCRIPTOR, "<object member descriptor>");
    r = object_field_descriptor(b, l + 1);
    if (!r) r = method_decl(b, l + 1);
    if (!r) r = remote_method_decl(b, l + 1);
    if (!r) r = resource_method_decl(b, l + 1);
    if (!r) r = object_type_inclusion(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CLIENT_KEYWORD | SERVICE_KEYWORD
  public static boolean object_network_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_network_qual")) return false;
    if (!nextTokenIs(b, "<object network qual>", CLIENT_KEYWORD, SERVICE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_NETWORK_QUAL, "<object network qual>");
    r = consumeToken(b, CLIENT_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // object_type_quals OBJECT_KEYWORD (IGNORED_OPEN_BRACE_TOKEN|OPEN_NESTED_BRACE_TOKEN | OPEN_BRACE_TOKEN)
  //       object_member_descriptor*
  //    (IGNORED_CLOSE_BRACE_TOKEN|CLOSE_NESTED_BRACE_TOKEN | CLOSE_BRACE_TOKEN)
  public static boolean object_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_TYPE_DESCRIPTOR, "<object type descriptor>");
    r = object_type_quals(b, l + 1);
    r = r && consumeToken(b, OBJECT_KEYWORD);
    r = r && object_type_descriptor_2(b, l + 1);
    r = r && object_type_descriptor_3(b, l + 1);
    r = r && object_type_descriptor_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IGNORED_OPEN_BRACE_TOKEN|OPEN_NESTED_BRACE_TOKEN | OPEN_BRACE_TOKEN
  private static boolean object_type_descriptor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_descriptor_2")) return false;
    boolean r;
    r = consumeToken(b, IGNORED_OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACE_TOKEN);
    return r;
  }

  // object_member_descriptor*
  private static boolean object_type_descriptor_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_descriptor_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_member_descriptor(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_type_descriptor_3", c)) break;
    }
    return true;
  }

  // IGNORED_CLOSE_BRACE_TOKEN|CLOSE_NESTED_BRACE_TOKEN | CLOSE_BRACE_TOKEN
  private static boolean object_type_descriptor_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_descriptor_4")) return false;
    boolean r;
    r = consumeToken(b, IGNORED_CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // ASTERISK_TOKEN type_reference SEMICOLON_TOKEN
  public static boolean object_type_inclusion(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_inclusion")) return false;
    if (!nextTokenIs(b, ASTERISK_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASTERISK_TOKEN);
    r = r && type_reference(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, OBJECT_TYPE_INCLUSION, r);
    return r;
  }

  /* ********************************************************** */
  // isolated_qual [object_network_qual]
  //    | [object_network_qual [isolated_qual]]
  public static boolean object_type_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_TYPE_QUALS, "<object type quals>");
    r = object_type_quals_0(b, l + 1);
    if (!r) r = object_type_quals_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // isolated_qual [object_network_qual]
  private static boolean object_type_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = isolated_qual(b, l + 1);
    r = r && object_type_quals_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [object_network_qual]
  private static boolean object_type_quals_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals_0_1")) return false;
    object_network_qual(b, l + 1);
    return true;
  }

  // [object_network_qual [isolated_qual]]
  private static boolean object_type_quals_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals_1")) return false;
    object_type_quals_1_0(b, l + 1);
    return true;
  }

  // object_network_qual [isolated_qual]
  private static boolean object_type_quals_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_network_qual(b, l + 1);
    r = r && object_type_quals_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean object_type_quals_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_type_quals_1_0_1")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PUBLIC_KEYWORD|PRIVATE_KEYWORD
  public static boolean object_visibility_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_visibility_qual")) return false;
    if (!nextTokenIs(b, "<object visibility qual>", PRIVATE_KEYWORD, PUBLIC_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_VISIBILITY_QUAL, "<object visibility qual>");
    r = consumeToken(b, PUBLIC_KEYWORD);
    if (!r) r = consumeToken(b, PRIVATE_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ON_KEYWORD CONFLICT_KEYWORD expression
  public static boolean on_conflict_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "on_conflict_clause")) return false;
    if (!nextTokenIs(b, ON_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ON_KEYWORD, CONFLICT_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, ON_CONFLICT_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // EQUAL_TOKEN
  //     |DOUBLE_EQUAL_TOKEN
  //     |TRIPPLE_EQUAL_TOKEN
  //     |PLUS_TOKEN
  //     |MINUS_TOKEN
  //     |SLASH_TOKEN
  //     |PERCENT_TOKEN
  //     |ASTERISK_TOKEN
  //     |LT_TOKEN
  //     |LT_EQUAL_TOKEN
  //     |GT_TOKEN
  //     |RIGHT_DOUBLE_ARROW_TOKEN
  //     |QUESTION_MARK_TOKEN
  //     |PIPE_TOKEN
  //     |GT_EQUAL_TOKEN
  //     |EXCLAMATION_MARK_TOKEN
  //     |NOT_EQUAL_TOKEN
  //     |NOT_DOUBLE_EQUAL_TOKEN
  //     |BITWISE_AND_TOKEN
  //     |BITWISE_XOR_TOKEN
  //     |LOGICAL_AND_TOKEN
  //     |LOGICAL_OR_TOKEN
  //     |NEGATION_TOKEN
  //     |RIGHT_ARROW_TOKEN
  //     |INTERPOLATION_START_TOKEN
  //     |INTERPOLATION_END_TOKEN
  //     |XML_PI_START_TOKEN
  //     |XML_PI_END_TOKEN
  //     |XML_COMMENT_START_TOKEN
  //     |XML_COMMENT_END_TOKEN
  //     |SYNC_SEND_TOKEN
  //     |LEFT_ARROW_TOKEN
  //     |DOUBLE_DOT_LT_TOKEN
  //     |DOUBLE_LT_TOKEN
  //     |ANNOT_CHAINING_TOKEN
  //     |OPTIONAL_CHAINING_TOKEN
  //     |ELVIS_TOKEN
  //     |DOT_LT_TOKEN
  //     |SLASH_LT_TOKEN
  //     |DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN
  //     |SLASH_ASTERISK_TOKEN
  //     |DOUBLE_GT_TOKEN
  //     |TRIPPLE_GT_TOKEN
  //     |XML_CDATA_START_TOKEN
  //     |XML_CDATA_END_TOKEN
  //     |BACK_SLASH_TOKEN
  //     |DOLLAR_TOKEN
  //     |ESCAPED_MINUS_TOKEN
  static boolean operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator")) return false;
    boolean r;
    r = consumeToken(b, EQUAL_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, TRIPPLE_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, PLUS_TOKEN);
    if (!r) r = consumeToken(b, MINUS_TOKEN);
    if (!r) r = consumeToken(b, SLASH_TOKEN);
    if (!r) r = consumeToken(b, PERCENT_TOKEN);
    if (!r) r = consumeToken(b, ASTERISK_TOKEN);
    if (!r) r = consumeToken(b, LT_TOKEN);
    if (!r) r = consumeToken(b, LT_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, GT_TOKEN);
    if (!r) r = consumeToken(b, RIGHT_DOUBLE_ARROW_TOKEN);
    if (!r) r = consumeToken(b, QUESTION_MARK_TOKEN);
    if (!r) r = consumeToken(b, PIPE_TOKEN);
    if (!r) r = consumeToken(b, GT_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, EXCLAMATION_MARK_TOKEN);
    if (!r) r = consumeToken(b, NOT_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, NOT_DOUBLE_EQUAL_TOKEN);
    if (!r) r = consumeToken(b, BITWISE_AND_TOKEN);
    if (!r) r = consumeToken(b, BITWISE_XOR_TOKEN);
    if (!r) r = consumeToken(b, LOGICAL_AND_TOKEN);
    if (!r) r = consumeToken(b, LOGICAL_OR_TOKEN);
    if (!r) r = consumeToken(b, NEGATION_TOKEN);
    if (!r) r = consumeToken(b, RIGHT_ARROW_TOKEN);
    if (!r) r = consumeToken(b, INTERPOLATION_START_TOKEN);
    if (!r) r = consumeToken(b, INTERPOLATION_END_TOKEN);
    if (!r) r = consumeToken(b, XML_PI_START_TOKEN);
    if (!r) r = consumeToken(b, XML_PI_END_TOKEN);
    if (!r) r = consumeToken(b, XML_COMMENT_START_TOKEN);
    if (!r) r = consumeToken(b, XML_COMMENT_END_TOKEN);
    if (!r) r = consumeToken(b, SYNC_SEND_TOKEN);
    if (!r) r = consumeToken(b, LEFT_ARROW_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_DOT_LT_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_LT_TOKEN);
    if (!r) r = consumeToken(b, ANNOT_CHAINING_TOKEN);
    if (!r) r = consumeToken(b, OPTIONAL_CHAINING_TOKEN);
    if (!r) r = consumeToken(b, ELVIS_TOKEN);
    if (!r) r = consumeToken(b, DOT_LT_TOKEN);
    if (!r) r = consumeToken(b, SLASH_LT_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN);
    if (!r) r = consumeToken(b, SLASH_ASTERISK_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_GT_TOKEN);
    if (!r) r = consumeToken(b, TRIPPLE_GT_TOKEN);
    if (!r) r = consumeToken(b, XML_CDATA_START_TOKEN);
    if (!r) r = consumeToken(b, XML_CDATA_END_TOKEN);
    if (!r) r = consumeToken(b, BACK_SLASH_TOKEN);
    if (!r) r = consumeToken(b, DOLLAR_TOKEN);
    if (!r) r = consumeToken(b, ESCAPED_MINUS_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // ORDER_KEYWORD BY_KEYWORD order_key (COMMA_TOKEN order_key)*
  public static boolean order_by_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_by_clause")) return false;
    if (!nextTokenIs(b, ORDER_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ORDER_KEYWORD, BY_KEYWORD);
    r = r && order_key(b, l + 1);
    r = r && order_by_clause_3(b, l + 1);
    exit_section_(b, m, ORDER_BY_CLAUSE, r);
    return r;
  }

  // (COMMA_TOKEN order_key)*
  private static boolean order_by_clause_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_by_clause_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!order_by_clause_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "order_by_clause_3", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN order_key
  private static boolean order_by_clause_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_by_clause_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && order_key(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ASCENDING_KEYWORD | DESCENDING_KEYWORD
  public static boolean order_direction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_direction")) return false;
    if (!nextTokenIs(b, "<order direction>", ASCENDING_KEYWORD, DESCENDING_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ORDER_DIRECTION, "<order direction>");
    r = consumeToken(b, ASCENDING_KEYWORD);
    if (!r) r = consumeToken(b, DESCENDING_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression [order_direction]
  public static boolean order_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_key")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ORDER_KEY, "<order key>");
    r = expression(b, l + 1, -1);
    r = r && order_key_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [order_direction]
  private static boolean order_key_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "order_key_1")) return false;
    order_direction(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // import_identifier
  public static boolean org_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "org_name")) return false;
    if (!nextTokenIs(b, "<org name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ORG_NAME, "<org name>");
    r = import_identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // EOF_TOKEN
  public static boolean other(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "other")) return false;
    if (!nextTokenIs(b, EOF_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOF_TOKEN);
    exit_section_(b, m, OTHER, r);
    return r;
  }

  /* ********************************************************** */
  // named_args | rest_arg | type
  public static boolean other_args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "other_args")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OTHER_ARGS, "<other args>");
    r = named_args(b, l + 1);
    if (!r) r = rest_arg(b, l + 1);
    if (!r) r = type(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // listener_decl
  //    | module_class_defn
  //    | service_decl
  //    | function_defn
  //    | module_const_decl
  //    | module_type_defn
  //    | module_var_decl
  //    | module_enum_decl
  //    | module_xmlns_decl
  //    | annotation_decl
  public static boolean other_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "other_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OTHER_DECL, "<other decl>");
    r = listener_decl(b, l + 1);
    if (!r) r = module_class_defn(b, l + 1);
    if (!r) r = service_decl(b, l + 1);
    if (!r) r = function_defn(b, l + 1);
    if (!r) r = module_const_decl(b, l + 1);
    if (!r) r = module_type_defn(b, l + 1);
    if (!r) r = module_var_decl(b, l + 1);
    if (!r) r = module_enum_decl(b, l + 1);
    if (!r) r = module_xmlns_decl(b, l + 1);
    if (!r) r = annotation_decl(b, l + 1);
    exit_section_(b, l, m, r, false, BallerinaParser::recoverModuleDecl);
    return r;
  }

  /* ********************************************************** */
  // (required_params [COMMA_TOKEN defaultable_params] [COMMA_TOKEN included_record_params] [COMMA_TOKEN rest_param])
  //    | (defaultable_params [COMMA_TOKEN included_record_params] [COMMA_TOKEN rest_param])
  //    | (included_record_params [COMMA_TOKEN rest_param])
  //    | [rest_param]
  public static boolean param_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM_LIST, "<param list>");
    r = param_list_0(b, l + 1);
    if (!r) r = param_list_1(b, l + 1);
    if (!r) r = param_list_2(b, l + 1);
    if (!r) r = param_list_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // required_params [COMMA_TOKEN defaultable_params] [COMMA_TOKEN included_record_params] [COMMA_TOKEN rest_param]
  private static boolean param_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = required_params(b, l + 1);
    r = r && param_list_0_1(b, l + 1);
    r = r && param_list_0_2(b, l + 1);
    r = r && param_list_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN defaultable_params]
  private static boolean param_list_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_1")) return false;
    param_list_0_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN defaultable_params
  private static boolean param_list_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && defaultable_params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN included_record_params]
  private static boolean param_list_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_2")) return false;
    param_list_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN included_record_params
  private static boolean param_list_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && included_record_params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_param]
  private static boolean param_list_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_3")) return false;
    param_list_0_3_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_param
  private static boolean param_list_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // defaultable_params [COMMA_TOKEN included_record_params] [COMMA_TOKEN rest_param]
  private static boolean param_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = defaultable_params(b, l + 1);
    r = r && param_list_1_1(b, l + 1);
    r = r && param_list_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN included_record_params]
  private static boolean param_list_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_1_1")) return false;
    param_list_1_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN included_record_params
  private static boolean param_list_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && included_record_params(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_param]
  private static boolean param_list_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_1_2")) return false;
    param_list_1_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_param
  private static boolean param_list_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // included_record_params [COMMA_TOKEN rest_param]
  private static boolean param_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = included_record_params(b, l + 1);
    r = r && param_list_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMA_TOKEN rest_param]
  private static boolean param_list_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_2_1")) return false;
    param_list_2_1_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN rest_param
  private static boolean param_list_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && rest_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [rest_param]
  private static boolean param_list_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_list_3")) return false;
    rest_param(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // identifier
  public static boolean param_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "param_name")) return false;
    if (!nextTokenIs(b, "<param name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM_NAME, "<param name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MARKDOWN_DOCUMENTATION_LINE_START documentationText?
  public static boolean parameterDescription(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDescription")) return false;
    if (!nextTokenIs(b, MARKDOWN_DOCUMENTATION_LINE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    r = r && parameterDescription_1(b, l + 1);
    exit_section_(b, m, PARAMETER_DESCRIPTION, r);
    return r;
  }

  // documentationText?
  private static boolean parameterDescription_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDescription_1")) return false;
    documentationText(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PARAMETER_DOCUMENTATION_START PARAMETER_NAME DESCRIPTION_SEPARATOR documentationText?
  public static boolean parameterDocumentation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDocumentation")) return false;
    if (!nextTokenIs(b, PARAMETER_DOCUMENTATION_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_DOCUMENTATION, null);
    r = consumeTokens(b, 3, PARAMETER_DOCUMENTATION_START, PARAMETER_NAME, DESCRIPTION_SEPARATOR);
    p = r; // pin = 3
    r = r && parameterDocumentation_3(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // documentationText?
  private static boolean parameterDocumentation_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDocumentation_3")) return false;
    documentationText(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // parameterDocumentation parameterDescription*
  public static boolean parameterDocumentationLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDocumentationLine")) return false;
    if (!nextTokenIs(b, PARAMETER_DOCUMENTATION_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameterDocumentation(b, l + 1);
    r = r && parameterDocumentationLine_1(b, l + 1);
    exit_section_(b, m, PARAMETER_DOCUMENTATION_LINE, r);
    return r;
  }

  // parameterDescription*
  private static boolean parameterDocumentationLine_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDocumentationLine_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterDescription(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterDocumentationLine_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // expression | type
  public static boolean positional_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, POSITIONAL_ARG, "<positional arg>");
    r = expression(b, l + 1, -1);
    if (!r) r = type(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (named_arg | positional_arg) (COMMA_TOKEN (named_arg | positional_arg))*
  public static boolean positional_args(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_args")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, POSITIONAL_ARGS, "<positional args>");
    r = positional_args_0(b, l + 1);
    r = r && positional_args_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // named_arg | positional_arg
  private static boolean positional_args_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_args_0")) return false;
    boolean r;
    r = named_arg(b, l + 1);
    if (!r) r = positional_arg(b, l + 1);
    return r;
  }

  // (COMMA_TOKEN (named_arg | positional_arg))*
  private static boolean positional_args_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_args_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!positional_args_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "positional_args_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN (named_arg | positional_arg)
  private static boolean positional_args_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_args_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && positional_args_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // named_arg | positional_arg
  private static boolean positional_args_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positional_args_1_0_1")) return false;
    boolean r;
    r = named_arg(b, l + 1);
    if (!r) r = positional_arg(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN_KEYWORD
  //    | DECIMAL_KEYWORD
  //    | ERROR_KEYWORD
  //    | FLOAT_KEYWORD
  //    | FUNCTION_KEYWORD
  //    | FUTURE_KEYWORD
  //    | INT_KEYWORD
  //    | MAP_KEYWORD
  //    | OBJECT_KEYWORD
  //    | STREAM_KEYWORD
  //    | STRING_KEYWORD
  //    | TABLE_KEYWORD
  //    | TRANSACTION_KEYWORD
  //    | TYPEDESC_KEYWORD
  //    | XML_KEYWORD
  public static boolean predeclared_prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "predeclared_prefix")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREDECLARED_PREFIX, "<predeclared prefix>");
    r = consumeToken(b, BOOLEAN_KEYWORD);
    if (!r) r = consumeToken(b, DECIMAL_KEYWORD);
    if (!r) r = consumeToken(b, ERROR_KEYWORD);
    if (!r) r = consumeToken(b, FLOAT_KEYWORD);
    if (!r) r = consumeToken(b, FUNCTION_KEYWORD);
    if (!r) r = consumeToken(b, FUTURE_KEYWORD);
    if (!r) r = consumeToken(b, INT_KEYWORD);
    if (!r) r = consumeToken(b, MAP_KEYWORD);
    if (!r) r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, STREAM_KEYWORD);
    if (!r) r = consumeToken(b, STRING_KEYWORD);
    if (!r) r = consumeToken(b, TABLE_KEYWORD);
    if (!r) r = consumeToken(b, TRANSACTION_KEYWORD);
    if (!r) r = consumeToken(b, TYPEDESC_KEYWORD);
    if (!r) r = consumeToken(b, XML_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // module_prefix NoSpaceColon identifier
  static boolean qualified_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualified_identifier")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = module_prefix(b, l + 1);
    r = r && NoSpaceColon(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // query_pipeline collect_clause
  public static boolean query_collect_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_collect_expr")) return false;
    if (!nextTokenIs(b, FROM_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = query_pipeline(b, l + 1);
    r = r && collect_clause(b, l + 1);
    exit_section_(b, m, QUERY_COLLECT_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // MAP_KEYWORD
  //   | TABLE_KEYWORD key_specifier
  //   | STREAM_KEYWORD
  public static boolean query_construct_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_construct_type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUERY_CONSTRUCT_TYPE, "<query construct type>");
    r = consumeToken(b, MAP_KEYWORD);
    if (!r) r = query_construct_type_1(b, l + 1);
    if (!r) r = consumeToken(b, STREAM_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TABLE_KEYWORD key_specifier
  private static boolean query_construct_type_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_construct_type_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TABLE_KEYWORD);
    r = r && key_specifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // from_clause intermediate_clause*
  public static boolean query_pipeline(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_pipeline")) return false;
    if (!nextTokenIs(b, FROM_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = from_clause(b, l + 1);
    r = r && query_pipeline_1(b, l + 1);
    exit_section_(b, m, QUERY_PIPELINE, r);
    return r;
  }

  // intermediate_clause*
  private static boolean query_pipeline_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_pipeline_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!intermediate_clause(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "query_pipeline_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // [query_construct_type] query_pipeline select_clause [on_conflict_clause]
  public static boolean query_select_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_select_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUERY_SELECT_EXPR, "<query select expr>");
    r = query_select_expr_0(b, l + 1);
    r = r && query_pipeline(b, l + 1);
    r = r && select_clause(b, l + 1);
    r = r && query_select_expr_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [query_construct_type]
  private static boolean query_select_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_select_expr_0")) return false;
    query_construct_type(b, l + 1);
    return true;
  }

  // [on_conflict_clause]
  private static boolean query_select_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_select_expr_3")) return false;
    on_conflict_clause(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // type_descriptor ELLIPSIS_TOKEN SEMICOLON_TOKEN
  public static boolean record_rest_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_rest_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RECORD_REST_DESCRIPTOR, "<record rest descriptor>");
    r = type_descriptor(b, l + 1, -1);
    r = r && consumeTokens(b, 0, ELLIPSIS_TOKEN, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // inclusive_record_type_descriptor | exclusive_record_type_descriptor
  public static boolean record_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_type_descriptor")) return false;
    if (!nextTokenIs(b, RECORD_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = inclusive_record_type_descriptor(b, l + 1);
    if (!r) r = exclusive_record_type_descriptor(b, l + 1);
    exit_section_(b, m, RECORD_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // ASTERISK_TOKEN type_reference SEMICOLON_TOKEN
  public static boolean record_type_inclusion(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_type_inclusion")) return false;
    if (!nextTokenIs(b, ASTERISK_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RECORD_TYPE_INCLUSION, null);
    r = consumeToken(b, ASTERISK_TOKEN);
    p = r; // pin = 1
    r = r && report_error_(b, type_reference(b, l + 1));
    r = p && consumeToken(b, SEMICOLON_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // !(CLOSE_BRACE_TOKEN)
  static boolean recoverClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverClass")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, CLOSE_BRACE_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(CLOSE_NESTED_BRACE_TOKEN)
  static boolean recoverIgnore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverIgnore")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(LISTENER_KEYWORD
  // |SERVICE_KEYWORD|TYPE_KEYWORD|PUBLIC_KEYWORD|PRIVATE_KEYWORD
  // |FUNCTION_KEYWORD|CONST_KEYWORD|CLASS_KEYWORD|ENUM_KEYWORD|XMLNS_KEYWORD
  // |ANNOTATION_KEYWORD|IMPORT_KEYWORD|ISOLATED_KEYWORD|TRANSACTIONAL_KEYWORD
  // |DISTINCT_KEYWORD|READONLY_KEYWORD|CLIENT_KEYWORD|FINAL_KEYWORD|CONFIGURABLE_KEYWORD
  // |BOOLEAN_KEYWORD|DECIMAL_KEYWORD|ERROR_KEYWORD|FLOAT_KEYWORD|FUTURE_KEYWORD|INT_KEYWORD|MAP_KEYWORD
  // |OBJECT_KEYWORD|STREAM_KEYWORD|STRING_KEYWORD|TABLE_KEYWORD|TRANSACTION_KEYWORD|TYPEDESC_KEYWORD
  // |XML_KEYWORD|NULL_KEYWORD|OPEN_PAREN_TOKEN|REMOTE_KEYWORD|ABSTRACT_KEYWORD|RECORD_KEYWORD|AS_KEYWORD|ON_KEYWORD|RESOURCE_KEYWORD|SOURCE_KEYWORD
  // |WORKER_KEYWORD|PARAMETER_KEYWORD|FIELD_KEYWORD|EXTERNAL_KEYWORD|TYPEOF_KEYWORD|LOCK_KEYWORD
  // |FORK_KEYWORD|TRAP_KEYWORD|KEY_KEYWORD|LET_KEYWORD|NEW_KEYWORD|FROM_KEYWORD
  // |WHERE_KEYWORD|SELECT_KEYWORD|START_KEYWORD|FLUSH_KEYWORD|WAIT_KEYWORD|COMMIT_KEYWORD
  // |ROLLBACK_KEYWORD|RETRY_KEYWORD|BASE16_KEYWORD|BASE64_KEYWORD|MATCH_KEYWORD|CONFLICT_KEYWORD
  // |LIMIT_KEYWORD|JOIN_KEYWORD|OUTER_KEYWORD|ORDER_KEYWORD|BY_KEYWORD|ASCENDING_KEYWORD
  // |DESCENDING_KEYWORD|UNDERSCORE_KEYWORD|AT_TOKEN|MARKDOWN_DOCUMENTATION_LINE_START
  // |BYTE_KEYWORD|JSON_KEYWORD|HANDLE_KEYWORD|ANY_KEYWORD|ANYDATA_KEYWORD|SEMICOLON_TOKEN
  // |NEVER_KEYWORD|VAR_KEYWORD|FAIL_KEYWORD|IDENTIFIER_TOKEN)
  static boolean recoverModuleDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverModuleDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverModuleDecl_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LISTENER_KEYWORD
  // |SERVICE_KEYWORD|TYPE_KEYWORD|PUBLIC_KEYWORD|PRIVATE_KEYWORD
  // |FUNCTION_KEYWORD|CONST_KEYWORD|CLASS_KEYWORD|ENUM_KEYWORD|XMLNS_KEYWORD
  // |ANNOTATION_KEYWORD|IMPORT_KEYWORD|ISOLATED_KEYWORD|TRANSACTIONAL_KEYWORD
  // |DISTINCT_KEYWORD|READONLY_KEYWORD|CLIENT_KEYWORD|FINAL_KEYWORD|CONFIGURABLE_KEYWORD
  // |BOOLEAN_KEYWORD|DECIMAL_KEYWORD|ERROR_KEYWORD|FLOAT_KEYWORD|FUTURE_KEYWORD|INT_KEYWORD|MAP_KEYWORD
  // |OBJECT_KEYWORD|STREAM_KEYWORD|STRING_KEYWORD|TABLE_KEYWORD|TRANSACTION_KEYWORD|TYPEDESC_KEYWORD
  // |XML_KEYWORD|NULL_KEYWORD|OPEN_PAREN_TOKEN|REMOTE_KEYWORD|ABSTRACT_KEYWORD|RECORD_KEYWORD|AS_KEYWORD|ON_KEYWORD|RESOURCE_KEYWORD|SOURCE_KEYWORD
  // |WORKER_KEYWORD|PARAMETER_KEYWORD|FIELD_KEYWORD|EXTERNAL_KEYWORD|TYPEOF_KEYWORD|LOCK_KEYWORD
  // |FORK_KEYWORD|TRAP_KEYWORD|KEY_KEYWORD|LET_KEYWORD|NEW_KEYWORD|FROM_KEYWORD
  // |WHERE_KEYWORD|SELECT_KEYWORD|START_KEYWORD|FLUSH_KEYWORD|WAIT_KEYWORD|COMMIT_KEYWORD
  // |ROLLBACK_KEYWORD|RETRY_KEYWORD|BASE16_KEYWORD|BASE64_KEYWORD|MATCH_KEYWORD|CONFLICT_KEYWORD
  // |LIMIT_KEYWORD|JOIN_KEYWORD|OUTER_KEYWORD|ORDER_KEYWORD|BY_KEYWORD|ASCENDING_KEYWORD
  // |DESCENDING_KEYWORD|UNDERSCORE_KEYWORD|AT_TOKEN|MARKDOWN_DOCUMENTATION_LINE_START
  // |BYTE_KEYWORD|JSON_KEYWORD|HANDLE_KEYWORD|ANY_KEYWORD|ANYDATA_KEYWORD|SEMICOLON_TOKEN
  // |NEVER_KEYWORD|VAR_KEYWORD|FAIL_KEYWORD|IDENTIFIER_TOKEN
  private static boolean recoverModuleDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverModuleDecl_0")) return false;
    boolean r;
    r = consumeToken(b, LISTENER_KEYWORD);
    if (!r) r = consumeToken(b, SERVICE_KEYWORD);
    if (!r) r = consumeToken(b, TYPE_KEYWORD);
    if (!r) r = consumeToken(b, PUBLIC_KEYWORD);
    if (!r) r = consumeToken(b, PRIVATE_KEYWORD);
    if (!r) r = consumeToken(b, FUNCTION_KEYWORD);
    if (!r) r = consumeToken(b, CONST_KEYWORD);
    if (!r) r = consumeToken(b, CLASS_KEYWORD);
    if (!r) r = consumeToken(b, ENUM_KEYWORD);
    if (!r) r = consumeToken(b, XMLNS_KEYWORD);
    if (!r) r = consumeToken(b, ANNOTATION_KEYWORD);
    if (!r) r = consumeToken(b, IMPORT_KEYWORD);
    if (!r) r = consumeToken(b, ISOLATED_KEYWORD);
    if (!r) r = consumeToken(b, TRANSACTIONAL_KEYWORD);
    if (!r) r = consumeToken(b, DISTINCT_KEYWORD);
    if (!r) r = consumeToken(b, READONLY_KEYWORD);
    if (!r) r = consumeToken(b, CLIENT_KEYWORD);
    if (!r) r = consumeToken(b, FINAL_KEYWORD);
    if (!r) r = consumeToken(b, CONFIGURABLE_KEYWORD);
    if (!r) r = consumeToken(b, BOOLEAN_KEYWORD);
    if (!r) r = consumeToken(b, DECIMAL_KEYWORD);
    if (!r) r = consumeToken(b, ERROR_KEYWORD);
    if (!r) r = consumeToken(b, FLOAT_KEYWORD);
    if (!r) r = consumeToken(b, FUTURE_KEYWORD);
    if (!r) r = consumeToken(b, INT_KEYWORD);
    if (!r) r = consumeToken(b, MAP_KEYWORD);
    if (!r) r = consumeToken(b, OBJECT_KEYWORD);
    if (!r) r = consumeToken(b, STREAM_KEYWORD);
    if (!r) r = consumeToken(b, STRING_KEYWORD);
    if (!r) r = consumeToken(b, TABLE_KEYWORD);
    if (!r) r = consumeToken(b, TRANSACTION_KEYWORD);
    if (!r) r = consumeToken(b, TYPEDESC_KEYWORD);
    if (!r) r = consumeToken(b, XML_KEYWORD);
    if (!r) r = consumeToken(b, NULL_KEYWORD);
    if (!r) r = consumeToken(b, OPEN_PAREN_TOKEN);
    if (!r) r = consumeToken(b, REMOTE_KEYWORD);
    if (!r) r = consumeToken(b, ABSTRACT_KEYWORD);
    if (!r) r = consumeToken(b, RECORD_KEYWORD);
    if (!r) r = consumeToken(b, AS_KEYWORD);
    if (!r) r = consumeToken(b, ON_KEYWORD);
    if (!r) r = consumeToken(b, RESOURCE_KEYWORD);
    if (!r) r = consumeToken(b, SOURCE_KEYWORD);
    if (!r) r = consumeToken(b, WORKER_KEYWORD);
    if (!r) r = consumeToken(b, PARAMETER_KEYWORD);
    if (!r) r = consumeToken(b, FIELD_KEYWORD);
    if (!r) r = consumeToken(b, EXTERNAL_KEYWORD);
    if (!r) r = consumeToken(b, TYPEOF_KEYWORD);
    if (!r) r = consumeToken(b, LOCK_KEYWORD);
    if (!r) r = consumeToken(b, FORK_KEYWORD);
    if (!r) r = consumeToken(b, TRAP_KEYWORD);
    if (!r) r = consumeToken(b, KEY_KEYWORD);
    if (!r) r = consumeToken(b, LET_KEYWORD);
    if (!r) r = consumeToken(b, NEW_KEYWORD);
    if (!r) r = consumeToken(b, FROM_KEYWORD);
    if (!r) r = consumeToken(b, WHERE_KEYWORD);
    if (!r) r = consumeToken(b, SELECT_KEYWORD);
    if (!r) r = consumeToken(b, START_KEYWORD);
    if (!r) r = consumeToken(b, FLUSH_KEYWORD);
    if (!r) r = consumeToken(b, WAIT_KEYWORD);
    if (!r) r = consumeToken(b, COMMIT_KEYWORD);
    if (!r) r = consumeToken(b, ROLLBACK_KEYWORD);
    if (!r) r = consumeToken(b, RETRY_KEYWORD);
    if (!r) r = consumeToken(b, BASE16_KEYWORD);
    if (!r) r = consumeToken(b, BASE64_KEYWORD);
    if (!r) r = consumeToken(b, MATCH_KEYWORD);
    if (!r) r = consumeToken(b, CONFLICT_KEYWORD);
    if (!r) r = consumeToken(b, LIMIT_KEYWORD);
    if (!r) r = consumeToken(b, JOIN_KEYWORD);
    if (!r) r = consumeToken(b, OUTER_KEYWORD);
    if (!r) r = consumeToken(b, ORDER_KEYWORD);
    if (!r) r = consumeToken(b, BY_KEYWORD);
    if (!r) r = consumeToken(b, ASCENDING_KEYWORD);
    if (!r) r = consumeToken(b, DESCENDING_KEYWORD);
    if (!r) r = consumeToken(b, UNDERSCORE_KEYWORD);
    if (!r) r = consumeToken(b, AT_TOKEN);
    if (!r) r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    if (!r) r = consumeToken(b, BYTE_KEYWORD);
    if (!r) r = consumeToken(b, JSON_KEYWORD);
    if (!r) r = consumeToken(b, HANDLE_KEYWORD);
    if (!r) r = consumeToken(b, ANY_KEYWORD);
    if (!r) r = consumeToken(b, ANYDATA_KEYWORD);
    if (!r) r = consumeToken(b, SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, NEVER_KEYWORD);
    if (!r) r = consumeToken(b, VAR_KEYWORD);
    if (!r) r = consumeToken(b, FAIL_KEYWORD);
    if (!r) r = consumeToken(b, IDENTIFIER_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // !(CLOSE_BRACE_PIPE_TOKEN)
  static boolean recoverPipe(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverPipe")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, CLOSE_BRACE_PIPE_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(CLOSE_BRACE_TOKEN)
  static boolean recoverService(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverService")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, CLOSE_BRACE_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(STRING_TEMPLATE_END_TOKEN|INTERPOLATION_START_TOKEN|TEMPLATE_STRING)
  static boolean recoverTemplate(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverTemplate")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !recoverTemplate_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STRING_TEMPLATE_END_TOKEN|INTERPOLATION_START_TOKEN|TEMPLATE_STRING
  private static boolean recoverTemplate_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recoverTemplate_0")) return false;
    boolean r;
    r = consumeToken(b, STRING_TEMPLATE_END_TOKEN);
    if (!r) r = consumeToken(b, INTERPOLATION_START_TOKEN);
    if (!r) r = consumeToken(b, TEMPLATE_STRING);
    return r;
  }

  /* ********************************************************** */
  // DOCTYPE | DOCSERVICE | DOCVARIABLE | DOCVAR | DOCANNOTATION | DOCMODULE | DOCFUNCTION | DOCPARAMETER | DOCCONST
  public static boolean referenceType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_TYPE, "<reference type>");
    r = consumeToken(b, DOCTYPE);
    if (!r) r = consumeToken(b, DOCSERVICE);
    if (!r) r = consumeToken(b, DOCVARIABLE);
    if (!r) r = consumeToken(b, DOCVAR);
    if (!r) r = consumeToken(b, DOCANNOTATION);
    if (!r) r = consumeToken(b, DOCMODULE);
    if (!r) r = consumeToken(b, DOCFUNCTION);
    if (!r) r = consumeToken(b, DOCPARAMETER);
    if (!r) r = consumeToken(b, DOCCONST);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // metadata remote_method_quals
  //    FUNCTION_KEYWORD remote_method_name function_signature SEMICOLON_TOKEN
  public static boolean remote_method_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REMOTE_METHOD_DECL, "<remote method decl>");
    r = metadata(b, l + 1);
    r = r && remote_method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && remote_method_name(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // metadata remote_method_quals
  //    FUNCTION_KEYWORD remote_method_name function_signature method_defn_body
  public static boolean remote_method_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_defn")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REMOTE_METHOD_DEFN, "<remote method defn>");
    r = metadata(b, l + 1);
    r = r && remote_method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && remote_method_name(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && method_defn_body(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier
  static boolean remote_method_name(PsiBuilder b, int l) {
    return identifier(b, l + 1);
  }

  /* ********************************************************** */
  // remote_qual function_quals
  //    | isolated_qual [transactional_qual] remote_qual
  //    | transactional_qual [isolated_qual] remote_qual
  //    | isolated_qual remote_qual transactional_qual
  //    | transactional_qual remote_qual isolated_qual
  public static boolean remote_method_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REMOTE_METHOD_QUALS, "<remote method quals>");
    r = remote_method_quals_0(b, l + 1);
    if (!r) r = remote_method_quals_1(b, l + 1);
    if (!r) r = remote_method_quals_2(b, l + 1);
    if (!r) r = remote_method_quals_3(b, l + 1);
    if (!r) r = remote_method_quals_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // remote_qual function_quals
  private static boolean remote_method_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = remote_qual(b, l + 1);
    r = r && function_quals(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // isolated_qual [transactional_qual] remote_qual
  private static boolean remote_method_quals_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = isolated_qual(b, l + 1);
    r = r && remote_method_quals_1_1(b, l + 1);
    r = r && remote_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [transactional_qual]
  private static boolean remote_method_quals_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_1_1")) return false;
    transactional_qual(b, l + 1);
    return true;
  }

  // transactional_qual [isolated_qual] remote_qual
  private static boolean remote_method_quals_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transactional_qual(b, l + 1);
    r = r && remote_method_quals_2_1(b, l + 1);
    r = r && remote_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean remote_method_quals_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_2_1")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  // isolated_qual remote_qual transactional_qual
  private static boolean remote_method_quals_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = isolated_qual(b, l + 1);
    r = r && remote_qual(b, l + 1);
    r = r && transactional_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // transactional_qual remote_qual isolated_qual
  private static boolean remote_method_quals_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_method_quals_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transactional_qual(b, l + 1);
    r = r && remote_qual(b, l + 1);
    r = r && isolated_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // REMOTE_KEYWORD
  public static boolean remote_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "remote_qual")) return false;
    if (!nextTokenIs(b, REMOTE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REMOTE_KEYWORD);
    exit_section_(b, m, REMOTE_QUAL, r);
    return r;
  }

  /* ********************************************************** */
  // [annots] type_descriptor [param_name]
  public static boolean required_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REQUIRED_PARAM, "<required param>");
    r = required_param_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && required_param_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean required_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_param_0")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean required_param_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_param_2")) return false;
    param_name(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (rest_param | defaultable_param | required_param) (COMMA_TOKEN (rest_param | defaultable_param | required_param))*
  public static boolean required_params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_params")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REQUIRED_PARAMS, "<required params>");
    r = required_params_0(b, l + 1);
    r = r && required_params_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // rest_param | defaultable_param | required_param
  private static boolean required_params_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_params_0")) return false;
    boolean r;
    r = rest_param(b, l + 1);
    if (!r) r = defaultable_param(b, l + 1);
    if (!r) r = required_param(b, l + 1);
    return r;
  }

  // (COMMA_TOKEN (rest_param | defaultable_param | required_param))*
  private static boolean required_params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_params_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!required_params_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "required_params_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN (rest_param | defaultable_param | required_param)
  private static boolean required_params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_params_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && required_params_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // rest_param | defaultable_param | required_param
  private static boolean required_params_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "required_params_1_0_1")) return false;
    boolean r;
    r = rest_param(b, l + 1);
    if (!r) r = defaultable_param(b, l + 1);
    if (!r) r = required_param(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // metadata resource_method_quals
  //    FUNCTION_KEYWORD resource_method_name resource_path function_signature SEMICOLON_TOKEN
  public static boolean resource_method_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_METHOD_DECL, "<resource method decl>");
    r = metadata(b, l + 1);
    r = r && resource_method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    r = r && resource_method_name(b, l + 1);
    r = r && resource_path(b, l + 1);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // metadata resource_method_quals
  //    FUNCTION_KEYWORD resource_method_name resource_path function_signature method_defn_body
  public static boolean resource_method_defn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_defn")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_METHOD_DEFN, "<resource method defn>");
    r = metadata(b, l + 1);
    r = r && resource_method_quals(b, l + 1);
    r = r && consumeToken(b, FUNCTION_KEYWORD);
    p = r; // pin = 3
    r = r && report_error_(b, resource_method_name(b, l + 1));
    r = p && report_error_(b, resource_path(b, l + 1)) && r;
    r = p && report_error_(b, function_signature(b, l + 1)) && r;
    r = p && method_defn_body(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // identifier
  public static boolean resource_method_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_name")) return false;
    if (!nextTokenIs(b, "<resource method name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_METHOD_NAME, "<resource method name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // resource_qual function_quals
  //    | isolated_qual [transactional_qual] resource_qual
  //    | transactional_qual [isolated_qual] resource_qual
  //    | isolated_qual resource_qual transactional_qual
  //    | transactional_qual resource_qual isolated_qual
  public static boolean resource_method_quals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_METHOD_QUALS, "<resource method quals>");
    r = resource_method_quals_0(b, l + 1);
    if (!r) r = resource_method_quals_1(b, l + 1);
    if (!r) r = resource_method_quals_2(b, l + 1);
    if (!r) r = resource_method_quals_3(b, l + 1);
    if (!r) r = resource_method_quals_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // resource_qual function_quals
  private static boolean resource_method_quals_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = resource_qual(b, l + 1);
    r = r && function_quals(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // isolated_qual [transactional_qual] resource_qual
  private static boolean resource_method_quals_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = isolated_qual(b, l + 1);
    r = r && resource_method_quals_1_1(b, l + 1);
    r = r && resource_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [transactional_qual]
  private static boolean resource_method_quals_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_1_1")) return false;
    transactional_qual(b, l + 1);
    return true;
  }

  // transactional_qual [isolated_qual] resource_qual
  private static boolean resource_method_quals_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transactional_qual(b, l + 1);
    r = r && resource_method_quals_2_1(b, l + 1);
    r = r && resource_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [isolated_qual]
  private static boolean resource_method_quals_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_2_1")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  // isolated_qual resource_qual transactional_qual
  private static boolean resource_method_quals_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = isolated_qual(b, l + 1);
    r = r && resource_qual(b, l + 1);
    r = r && transactional_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // transactional_qual resource_qual isolated_qual
  private static boolean resource_method_quals_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_method_quals_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = transactional_qual(b, l + 1);
    r = r && resource_qual(b, l + 1);
    r = r && isolated_qual(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // dot_resource_path
  //    | resource_path_segment (SLASH_TOKEN resource_path_segment)* [SLASH_TOKEN resource_path_rest_param]
  //    | resource_path_rest_param
  public static boolean resource_path(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_PATH, "<resource path>");
    r = dot_resource_path(b, l + 1);
    if (!r) r = resource_path_1(b, l + 1);
    if (!r) r = resource_path_rest_param(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // resource_path_segment (SLASH_TOKEN resource_path_segment)* [SLASH_TOKEN resource_path_rest_param]
  private static boolean resource_path_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = resource_path_segment(b, l + 1);
    r = r && resource_path_1_1(b, l + 1);
    r = r && resource_path_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SLASH_TOKEN resource_path_segment)*
  private static boolean resource_path_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!resource_path_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "resource_path_1_1", c)) break;
    }
    return true;
  }

  // SLASH_TOKEN resource_path_segment
  private static boolean resource_path_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_TOKEN);
    r = r && resource_path_segment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [SLASH_TOKEN resource_path_rest_param]
  private static boolean resource_path_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_1_2")) return false;
    resource_path_1_2_0(b, l + 1);
    return true;
  }

  // SLASH_TOKEN resource_path_rest_param
  private static boolean resource_path_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_TOKEN);
    r = r && resource_path_rest_param(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN [annots] type_descriptor ELLIPSIS_TOKEN [param_name] CLOSE_BRACKET_TOKEN
  public static boolean resource_path_rest_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_rest_param")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && resource_path_rest_param_1(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && consumeToken(b, ELLIPSIS_TOKEN);
    r = r && resource_path_rest_param_4(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, RESOURCE_PATH_REST_PARAM, r);
    return r;
  }

  // [annots]
  private static boolean resource_path_rest_param_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_rest_param_1")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean resource_path_rest_param_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_rest_param_4")) return false;
    param_name(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // resource_path_segment_name | resource_path_segment_param
  public static boolean resource_path_segment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_segment")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_PATH_SEGMENT, "<resource path segment>");
    r = resource_path_segment_name(b, l + 1);
    if (!r) r = resource_path_segment_param(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean resource_path_segment_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_segment_name")) return false;
    if (!nextTokenIs(b, "<resource path segment name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESOURCE_PATH_SEGMENT_NAME, "<resource path segment name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACKET_TOKEN[annots] type_descriptor [param_name] CLOSE_BRACKET_TOKEN
  public static boolean resource_path_segment_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_segment_param")) return false;
    if (!nextTokenIs(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && resource_path_segment_param_1(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && resource_path_segment_param_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, RESOURCE_PATH_SEGMENT_PARAM, r);
    return r;
  }

  // [annots]
  private static boolean resource_path_segment_param_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_segment_param_1")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean resource_path_segment_param_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_path_segment_param_3")) return false;
    param_name(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // RESOURCE_KEYWORD
  public static boolean resource_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "resource_qual")) return false;
    if (!nextTokenIs(b, RESOURCE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RESOURCE_KEYWORD);
    exit_section_(b, m, RESOURCE_QUAL, r);
    return r;
  }

  /* ********************************************************** */
  // spread | aggregated_variable_reference
  public static boolean rest_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rest_arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REST_ARG, "<rest arg>");
    r = spread(b, l + 1);
    if (!r) r = aggregated_variable_reference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ELLIPSIS_TOKEN variable_name
  public static boolean rest_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rest_binding_pattern")) return false;
    if (!nextTokenIs(b, ELLIPSIS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELLIPSIS_TOKEN);
    r = r && variable_name(b, l + 1);
    exit_section_(b, m, REST_BINDING_PATTERN, r);
    return r;
  }

  /* ********************************************************** */
  // [annots] type_descriptor ELLIPSIS_TOKEN [param_name]
  public static boolean rest_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rest_param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REST_PARAM, "<rest param>");
    r = rest_param_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && consumeToken(b, ELLIPSIS_TOKEN);
    r = r && rest_param_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean rest_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rest_param_0")) return false;
    annots(b, l + 1);
    return true;
  }

  // [param_name]
  private static boolean rest_param_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rest_param_3")) return false;
    param_name(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MARKDOWN_DOCUMENTATION_LINE_START documentationText?
  public static boolean returnParameterDescription(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterDescription")) return false;
    if (!nextTokenIs(b, MARKDOWN_DOCUMENTATION_LINE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MARKDOWN_DOCUMENTATION_LINE_START);
    r = r && returnParameterDescription_1(b, l + 1);
    exit_section_(b, m, RETURN_PARAMETER_DESCRIPTION, r);
    return r;
  }

  // documentationText?
  private static boolean returnParameterDescription_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterDescription_1")) return false;
    documentationText(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // RETURN_PARAMETER_DOCUMENTATION_START docParameterDescription
  public static boolean returnParameterDocumentation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterDocumentation")) return false;
    if (!nextTokenIs(b, RETURN_PARAMETER_DOCUMENTATION_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN_PARAMETER_DOCUMENTATION_START);
    r = r && docParameterDescription(b, l + 1);
    exit_section_(b, m, RETURN_PARAMETER_DOCUMENTATION, r);
    return r;
  }

  /* ********************************************************** */
  // returnParameterDocumentation returnParameterDescription*
  public static boolean returnParameterDocumentationLine(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterDocumentationLine")) return false;
    if (!nextTokenIs(b, RETURN_PARAMETER_DOCUMENTATION_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = returnParameterDocumentation(b, l + 1);
    r = r && returnParameterDocumentationLine_1(b, l + 1);
    exit_section_(b, m, RETURN_PARAMETER_DOCUMENTATION_LINE, r);
    return r;
  }

  // returnParameterDescription*
  private static boolean returnParameterDocumentationLine_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterDocumentationLine_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!returnParameterDescription(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "returnParameterDocumentationLine_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // [ RETURNS_KEYWORD [annots] type_descriptor ]
  public static boolean return_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_type_descriptor")) return false;
    Marker m = enter_section_(b, l, _NONE_, RETURN_TYPE_DESCRIPTOR, "<return type descriptor>");
    return_type_descriptor_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // RETURNS_KEYWORD [annots] type_descriptor
  private static boolean return_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_type_descriptor_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURNS_KEYWORD);
    r = r && return_type_descriptor_0_1(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annots]
  private static boolean return_type_descriptor_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_type_descriptor_0_1")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SLASH_TOKEN
  public static boolean root_resource_path(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_resource_path")) return false;
    if (!nextTokenIs(b, SLASH_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_TOKEN);
    exit_section_(b, m, ROOT_RESOURCE_PATH, r);
    return r;
  }

  /* ********************************************************** */
  // mapping_constructor_expr (COMMA_TOKEN mapping_constructor_expr)*
  public static boolean row_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "row_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROW_LIST, "<row list>");
    r = mapping_constructor_expr(b, l + 1);
    r = r && row_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA_TOKEN mapping_constructor_expr)*
  private static boolean row_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "row_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!row_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "row_list_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN mapping_constructor_expr
  private static boolean row_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "row_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && mapping_constructor_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // type_parameter
  public static boolean row_type_parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "row_type_parameter")) return false;
    if (!nextTokenIs(b, LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_parameter(b, l + 1);
    exit_section_(b, m, ROW_TYPE_PARAMETER, r);
    return r;
  }

  /* ********************************************************** */
  // SELECT_KEYWORD expression
  public static boolean select_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "select_clause")) return false;
    if (!nextTokenIs(b, SELECT_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SELECT_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, SELECT_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // IGNORED_OPEN_BRACE_TOKEN
  //     |IGNORED_CLOSE_BRACE_TOKEN
  //     |OPEN_PAREN_TOKEN
  //     |CLOSE_PAREN_TOKEN
  //     |OPEN_BRACKET_TOKEN
  //     |CLOSE_BRACKET_TOKEN
  //     |SEMICOLON_TOKEN|IGNORED_SEMICOLON_TOKEN
  //     |DOT_TOKEN
  //     |COLON_TOKEN
  //     |COMMA_TOKEN
  //     |ELLIPSIS_TOKEN
  //     |OPEN_BRACE_PIPE_TOKEN|OPEN_NESTED_BRACE_PIPE_TOKEN|IGNORED_OPEN_BRACE_PIPE_TOKEN
  //     |CLOSE_BRACE_PIPE_TOKEN|CLOSE_NESTED_BRACE_PIPE_TOKEN|IGNORED_CLOSE_BRACE_PIPE_TOKEN
  //     |AT_TOKEN
  //     |HASH_TOKEN
  //     |BACKTICK_TOKEN
  //     |DOUBLE_QUOTE_TOKEN
  //     |SINGLE_QUOTE_TOKEN
  //     |STRING_TEMPLATE_START_TOKEN
  //     |STRING_TEMPLATE_END_TOKEN
  //     |DOUBLE_BACKTICK_TOKEN
  //     |TRIPLE_BACKTICK_TOKEN
  static boolean separatorIgnore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "separatorIgnore")) return false;
    boolean r;
    r = consumeToken(b, IGNORED_OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_PAREN_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_PAREN_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, DOT_TOKEN);
    if (!r) r = consumeToken(b, COLON_TOKEN);
    if (!r) r = consumeToken(b, COMMA_TOKEN);
    if (!r) r = consumeToken(b, ELLIPSIS_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_OPEN_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, AT_TOKEN);
    if (!r) r = consumeToken(b, HASH_TOKEN);
    if (!r) r = consumeToken(b, BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, SINGLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_START_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_END_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // OPEN_NESTED_BRACE_TOKEN|IGNORED_OPEN_BRACE_TOKEN
  //     |CLOSE_NESTED_BRACE_TOKEN|IGNORED_CLOSE_BRACE_TOKEN
  //     |OPEN_PAREN_TOKEN
  //     |CLOSE_PAREN_TOKEN
  //     |OPEN_BRACKET_TOKEN
  //     |CLOSE_BRACKET_TOKEN
  //     |SEMICOLON_TOKEN|IGNORED_SEMICOLON_TOKEN
  //     |DOT_TOKEN
  //     |COLON_TOKEN
  //     |COMMA_TOKEN
  //     |ELLIPSIS_TOKEN
  //     |OPEN_BRACE_PIPE_TOKEN|OPEN_NESTED_BRACE_PIPE_TOKEN|IGNORED_OPEN_BRACE_PIPE_TOKEN
  //     |CLOSE_BRACE_PIPE_TOKEN|CLOSE_NESTED_BRACE_PIPE_TOKEN|IGNORED_CLOSE_BRACE_PIPE_TOKEN
  //     |AT_TOKEN
  //     |HASH_TOKEN
  //     |BACKTICK_TOKEN
  //     |DOUBLE_QUOTE_TOKEN
  //     |SINGLE_QUOTE_TOKEN
  //     |STRING_TEMPLATE_START_TOKEN
  //     |STRING_TEMPLATE_END_TOKEN
  //     |DOUBLE_BACKTICK_TOKEN
  //     |TRIPLE_BACKTICK_TOKEN
  static boolean separatorIn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "separatorIn")) return false;
    boolean r;
    r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_PAREN_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_PAREN_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, DOT_TOKEN);
    if (!r) r = consumeToken(b, COLON_TOKEN);
    if (!r) r = consumeToken(b, COMMA_TOKEN);
    if (!r) r = consumeToken(b, ELLIPSIS_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_OPEN_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, AT_TOKEN);
    if (!r) r = consumeToken(b, HASH_TOKEN);
    if (!r) r = consumeToken(b, BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, SINGLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_START_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_END_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // OPEN_BRACE_TOKEN|OPEN_NESTED_BRACE_TOKEN|IGNORED_OPEN_BRACE_TOKEN
  //            |CLOSE_BRACE_TOKEN|CLOSE_NESTED_BRACE_TOKEN|IGNORED_CLOSE_BRACE_TOKEN
  //            |OPEN_PAREN_TOKEN
  //            |CLOSE_PAREN_TOKEN
  //            |OPEN_BRACKET_TOKEN
  //            |CLOSE_BRACKET_TOKEN
  //            |SEMICOLON_TOKEN|IGNORED_SEMICOLON_TOKEN
  //            |DOT_TOKEN
  //            |COLON_TOKEN
  //            |COMMA_TOKEN
  //            |ELLIPSIS_TOKEN
  //            |OPEN_NESTED_BRACE_PIPE_TOKEN|IGNORED_OPEN_BRACE_PIPE_TOKEN
  //            |CLOSE_NESTED_BRACE_PIPE_TOKEN|IGNORED_CLOSE_BRACE_PIPE_TOKEN
  //            |AT_TOKEN
  //            |HASH_TOKEN
  //            |BACKTICK_TOKEN
  //            |DOUBLE_QUOTE_TOKEN
  //            |SINGLE_QUOTE_TOKEN
  //            |STRING_TEMPLATE_START_TOKEN
  //            |STRING_TEMPLATE_END_TOKEN
  //            |DOUBLE_BACKTICK_TOKEN
  //            |TRIPLE_BACKTICK_TOKEN
  static boolean separatorPipe(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "separatorPipe")) return false;
    boolean r;
    r = consumeToken(b, OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_OPEN_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_TOKEN);
    if (!r) r = consumeToken(b, OPEN_PAREN_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_PAREN_TOKEN);
    if (!r) r = consumeToken(b, OPEN_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_BRACKET_TOKEN);
    if (!r) r = consumeToken(b, SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_SEMICOLON_TOKEN);
    if (!r) r = consumeToken(b, DOT_TOKEN);
    if (!r) r = consumeToken(b, COLON_TOKEN);
    if (!r) r = consumeToken(b, COMMA_TOKEN);
    if (!r) r = consumeToken(b, ELLIPSIS_TOKEN);
    if (!r) r = consumeToken(b, OPEN_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_OPEN_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, CLOSE_NESTED_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, IGNORED_CLOSE_BRACE_PIPE_TOKEN);
    if (!r) r = consumeToken(b, AT_TOKEN);
    if (!r) r = consumeToken(b, HASH_TOKEN);
    if (!r) r = consumeToken(b, BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, SINGLE_QUOTE_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_START_TOKEN);
    if (!r) r = consumeToken(b, STRING_TEMPLATE_END_TOKEN);
    if (!r) r = consumeToken(b, DOUBLE_BACKTICK_TOKEN);
    if (!r) r = consumeToken(b, TRIPLE_BACKTICK_TOKEN);
    return r;
  }

  /* ********************************************************** */
  // metadata [isolated_qual]
  //    SERVICE_KEYWORD [type_descriptor] [attach_point] ON_KEYWORD expression_list OPEN_BRACE_TOKEN service_members CLOSE_BRACE_TOKEN [SEMICOLON_TOKEN]
  public static boolean service_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_decl")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_DECL, "<service decl>");
    r = metadata(b, l + 1);
    r = r && service_decl_1(b, l + 1);
    r = r && consumeToken(b, SERVICE_KEYWORD);
    p = r; // pin = 3
    r = r && report_error_(b, service_decl_3(b, l + 1));
    r = p && report_error_(b, service_decl_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, ON_KEYWORD)) && r;
    r = p && report_error_(b, expression_list(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, OPEN_BRACE_TOKEN)) && r;
    r = p && report_error_(b, service_members(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, CLOSE_BRACE_TOKEN)) && r;
    r = p && service_decl_10(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [isolated_qual]
  private static boolean service_decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_decl_1")) return false;
    isolated_qual(b, l + 1);
    return true;
  }

  // [type_descriptor]
  private static boolean service_decl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_decl_3")) return false;
    type_descriptor(b, l + 1, -1);
    return true;
  }

  // [attach_point]
  private static boolean service_decl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_decl_4")) return false;
    attach_point(b, l + 1);
    return true;
  }

  // [SEMICOLON_TOKEN]
  private static boolean service_decl_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_decl_10")) return false;
    consumeToken(b, SEMICOLON_TOKEN);
    return true;
  }

  /* ********************************************************** */
  // object_field
  //    | method_defn
  //    | remote_method_defn
  //    | resource_method_defn
  public static boolean service_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_MEMBER, "<service member>");
    r = object_field(b, l + 1);
    if (!r) r = method_defn(b, l + 1);
    if (!r) r = remote_method_defn(b, l + 1);
    if (!r) r = resource_method_defn(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // service_member*
  public static boolean service_members(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "service_members")) return false;
    Marker m = enter_section_(b, l, _NONE_, SERVICE_MEMBERS, "<service members>");
    while (true) {
      int c = current_position_(b);
      if (!service_member(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "service_members", c)) break;
    }
    exit_section_(b, l, m, true, false, BallerinaParser::recoverService);
    return true;
  }

  /* ********************************************************** */
  // capture_binding_pattern | wildcard_binding_pattern
  public static boolean simple_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_BINDING_PATTERN, "<simple binding pattern>");
    r = capture_binding_pattern(b, l + 1);
    if (!r) r = wildcard_binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // nil_literal
  //   | boolean_literal
  //   | [Sign] int_literal
  //   | [Sign] floating_point_literal
  //   | string_literal
  //   | constant_reference_expr
  public static boolean simple_const_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_const_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_CONST_EXPR, "<simple const expr>");
    r = nil_literal(b, l + 1);
    if (!r) r = boolean_literal(b, l + 1);
    if (!r) r = simple_const_expr_2(b, l + 1);
    if (!r) r = simple_const_expr_3(b, l + 1);
    if (!r) r = string_literal(b, l + 1);
    if (!r) r = constant_reference_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [Sign] int_literal
  private static boolean simple_const_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_const_expr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = simple_const_expr_2_0(b, l + 1);
    r = r && int_literal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [Sign]
  private static boolean simple_const_expr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_const_expr_2_0")) return false;
    Sign(b, l + 1);
    return true;
  }

  // [Sign] floating_point_literal
  private static boolean simple_const_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_const_expr_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = simple_const_expr_3_0(b, l + 1);
    r = r && floating_point_literal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [Sign]
  private static boolean simple_const_expr_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_const_expr_3_0")) return false;
    Sign(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // SINGLE_BACKTICK_MARKDOWN_START SINGLE_BACKTICK_CONTENT? SINGLE_BACKTICK_MARKDOWN_END
  public static boolean singleBacktickedBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "singleBacktickedBlock")) return false;
    if (!nextTokenIs(b, SINGLE_BACKTICK_MARKDOWN_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SINGLE_BACKTICKED_BLOCK, null);
    r = consumeToken(b, SINGLE_BACKTICK_MARKDOWN_START);
    p = r; // pin = 1
    r = r && report_error_(b, singleBacktickedBlock_1(b, l + 1));
    r = p && consumeToken(b, SINGLE_BACKTICK_MARKDOWN_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // SINGLE_BACKTICK_CONTENT?
  private static boolean singleBacktickedBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "singleBacktickedBlock_1")) return false;
    consumeToken(b, SINGLE_BACKTICK_CONTENT);
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean single_list_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "single_list_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SINGLE_LIST_MEMBER, "<single list member>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SOURCE_KEYWORD source_only_attach_point_ident
  public static boolean source_only_attach_point(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "source_only_attach_point")) return false;
    if (!nextTokenIs(b, SOURCE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SOURCE_KEYWORD);
    r = r && source_only_attach_point_ident(b, l + 1);
    exit_section_(b, m, SOURCE_ONLY_ATTACH_POINT, r);
    return r;
  }

  /* ********************************************************** */
  // ANNOTATION_KEYWORD
  //    | EXTERNAL_KEYWORD
  //    | VAR_KEYWORD
  //    | CONST_KEYWORD
  //    | LISTENER_KEYWORD
  //    | CLIENT_KEYWORD
  //    | WORKER_KEYWORD
  public static boolean source_only_attach_point_ident(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "source_only_attach_point_ident")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SOURCE_ONLY_ATTACH_POINT_IDENT, "<source only attach point ident>");
    r = consumeToken(b, ANNOTATION_KEYWORD);
    if (!r) r = consumeToken(b, EXTERNAL_KEYWORD);
    if (!r) r = consumeToken(b, VAR_KEYWORD);
    if (!r) r = consumeToken(b, CONST_KEYWORD);
    if (!r) r = consumeToken(b, LISTENER_KEYWORD);
    if (!r) r = consumeToken(b, CLIENT_KEYWORD);
    if (!r) r = consumeToken(b, WORKER_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MAP_KEYWORD | JOIN_KEYWORD | START_KEYWORD
  public static boolean special_method_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "special_method_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SPECIAL_METHOD_NAME, "<special method name>");
    r = consumeToken(b, MAP_KEYWORD);
    if (!r) r = consumeToken(b, JOIN_KEYWORD);
    if (!r) r = consumeToken(b, START_KEYWORD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [READONLY_KEYWORD] (field_name | string_literal) COLON_TOKEN value_expr
  //    | [READONLY_KEYWORD] variable_name
  public static boolean specific_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SPECIFIC_FIELD, "<specific field>");
    r = specific_field_0(b, l + 1);
    if (!r) r = specific_field_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [READONLY_KEYWORD] (field_name | string_literal) COLON_TOKEN value_expr
  private static boolean specific_field_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = specific_field_0_0(b, l + 1);
    r = r && specific_field_0_1(b, l + 1);
    r = r && consumeToken(b, COLON_TOKEN);
    r = r && value_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [READONLY_KEYWORD]
  private static boolean specific_field_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field_0_0")) return false;
    consumeToken(b, READONLY_KEYWORD);
    return true;
  }

  // field_name | string_literal
  private static boolean specific_field_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field_0_1")) return false;
    boolean r;
    r = field_name(b, l + 1);
    if (!r) r = string_literal(b, l + 1);
    return r;
  }

  // [READONLY_KEYWORD] variable_name
  private static boolean specific_field_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = specific_field_1_0(b, l + 1);
    r = r && variable_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [READONLY_KEYWORD]
  private static boolean specific_field_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "specific_field_1_0")) return false;
    consumeToken(b, READONLY_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // ELLIPSIS_TOKEN expression
  public static boolean spread(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "spread")) return false;
    if (!nextTokenIs(b, ELLIPSIS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELLIPSIS_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, SPREAD, r);
    return r;
  }

  /* ********************************************************** */
  // spread
  public static boolean spread_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "spread_field")) return false;
    if (!nextTokenIs(b, ELLIPSIS_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = spread(b, l + 1);
    exit_section_(b, m, SPREAD_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // spread | aggregated_variable_reference
  public static boolean spread_list_member(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "spread_list_member")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SPREAD_LIST_MEMBER, "<spread list member>");
    r = spread(b, l + 1);
    if (!r) r = aggregated_variable_reference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STREAM_KEYWORD [stream_type_parameters]
  public static boolean stream_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stream_type_descriptor")) return false;
    if (!nextTokenIs(b, STREAM_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STREAM_KEYWORD);
    r = r && stream_type_descriptor_1(b, l + 1);
    exit_section_(b, m, STREAM_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [stream_type_parameters]
  private static boolean stream_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stream_type_descriptor_1")) return false;
    stream_type_parameters(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LT_TOKEN type_descriptor [COMMA_TOKEN type_descriptor] GT_TOKEN
  public static boolean stream_type_parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stream_type_parameters")) return false;
    if (!nextTokenIs(b, LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LT_TOKEN);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && stream_type_parameters_2(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, STREAM_TYPE_PARAMETERS, r);
    return r;
  }

  // [COMMA_TOKEN type_descriptor]
  private static boolean stream_type_parameters_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stream_type_parameters_2")) return false;
    stream_type_parameters_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN type_descriptor
  private static boolean stream_type_parameters_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stream_type_parameters_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && type_descriptor(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING_LITERAL_TOKEN
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    if (!nextTokenIs(b, STRING_LITERAL_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL_TOKEN);
    exit_section_(b, m, STRING_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // [IDENTIFIER_TOKEN] STRING_TEMPLATE_START_TOKEN template_content* STRING_TEMPLATE_END_TOKEN
  public static boolean string_template_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_template_expr")) return false;
    if (!nextTokenIs(b, "<string template expr>", IDENTIFIER_TOKEN, STRING_TEMPLATE_START_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STRING_TEMPLATE_EXPR, "<string template expr>");
    r = string_template_expr_0(b, l + 1);
    r = r && consumeToken(b, STRING_TEMPLATE_START_TOKEN);
    p = r; // pin = 2
    r = r && report_error_(b, string_template_expr_2(b, l + 1));
    r = p && consumeToken(b, STRING_TEMPLATE_END_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [IDENTIFIER_TOKEN]
  private static boolean string_template_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_template_expr_0")) return false;
    consumeToken(b, IDENTIFIER_TOKEN);
    return true;
  }

  // template_content*
  private static boolean string_template_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_template_expr_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!template_content(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "string_template_expr_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRING_KEYWORD
  public static boolean string_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_type_descriptor")) return false;
    if (!nextTokenIs(b, STRING_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_KEYWORD);
    exit_section_(b, m, STRING_TYPE_DESCRIPTOR, r);
    return r;
  }

  /* ********************************************************** */
  // TABLE_KEYWORD [key_specifier] OPEN_BRACKET_TOKEN [row_list] CLOSE_BRACKET_TOKEN
  public static boolean table_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "table_constructor_expr")) return false;
    if (!nextTokenIs(b, TABLE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TABLE_KEYWORD);
    r = r && table_constructor_expr_1(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && table_constructor_expr_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, TABLE_CONSTRUCTOR_EXPR, r);
    return r;
  }

  // [key_specifier]
  private static boolean table_constructor_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "table_constructor_expr_1")) return false;
    key_specifier(b, l + 1);
    return true;
  }

  // [row_list]
  private static boolean table_constructor_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "table_constructor_expr_3")) return false;
    row_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // qualified_identifier
  public static boolean tagged_data_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagged_data_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TAGGED_DATA_TYPE_DESCRIPTOR, "<tagged data type descriptor>");
    r = qualified_identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TEMPLATE_STRING | interpolation
  public static boolean template_content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "template_content")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEMPLATE_CONTENT, "<template content>");
    r = consumeToken(b, TEMPLATE_STRING);
    if (!r) r = interpolation(b, l + 1);
    exit_section_(b, l, m, r, false, BallerinaParser::recoverTemplate);
    return r;
  }

  /* ********************************************************** */
  // simple_type_descriptor
  //                                | sequence_type_descriptor
  //                                 | mapping_type_descriptor
  //                                | array_type_descriptor | tuple_type_descriptor
  //                                     | table_type_descriptor
  //                                | behavioral_type_descriptor
  //                                | type_reference
  //                                     | singleton_type_descriptor
  //                                     | any_type_descriptor
  //                                     | never_type_descriptor
  //                                     | readonly_type_descriptor
  //                                     | distinct_type_descriptor
  //                                     | union_type_descriptor
  //                                     | intersection_type_descriptor
  //                                     | anydata_type_descriptor
  //                                     | json_type_descriptor
  //                                     | byte_type_descriptor
  //                                     | last_type_descriptor
  public static boolean ternary_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TERNARY_TYPE_DESCRIPTOR, "<ternary type descriptor>");
    r = simple_type_descriptor(b, l + 1);
    if (!r) r = sequence_type_descriptor(b, l + 1);
    if (!r) r = mapping_type_descriptor(b, l + 1);
    if (!r) r = type_descriptor(b, l + 1, 2);
    if (!r) r = tuple_type_descriptor(b, l + 1);
    if (!r) r = table_type_descriptor(b, l + 1);
    if (!r) r = behavioral_type_descriptor(b, l + 1);
    if (!r) r = type_reference(b, l + 1);
    if (!r) r = singleton_type_descriptor(b, l + 1);
    if (!r) r = any_type_descriptor(b, l + 1);
    if (!r) r = never_type_descriptor(b, l + 1);
    if (!r) r = readonly_type_descriptor(b, l + 1);
    if (!r) r = distinct_type_descriptor(b, l + 1);
    if (!r) r = type_descriptor(b, l + 1, 12);
    if (!r) r = type_descriptor(b, l + 1, 13);
    if (!r) r = anydata_type_descriptor(b, l + 1);
    if (!r) r = json_type_descriptor(b, l + 1);
    if (!r) r = byte_type_descriptor(b, l + 1);
    if (!r) r = last_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TRANSACTIONAL_KEYWORD
  public static boolean transactional_qual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transactional_qual")) return false;
    if (!nextTokenIs(b, TRANSACTIONAL_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TRANSACTIONAL_KEYWORD);
    exit_section_(b, m, TRANSACTIONAL_QUAL, r);
    return r;
  }

  /* ********************************************************** */
  // (tuple_rest_descriptor | member_type_descriptor) (COMMA_TOKEN (tuple_rest_descriptor | member_type_descriptor))* [COMMA_TOKEN tuple_rest_descriptor]
  //    | [ tuple_rest_descriptor ]
  public static boolean tuple_member_type_descriptors(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TUPLE_MEMBER_TYPE_DESCRIPTORS, "<tuple member type descriptors>");
    r = tuple_member_type_descriptors_0(b, l + 1);
    if (!r) r = tuple_member_type_descriptors_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (tuple_rest_descriptor | member_type_descriptor) (COMMA_TOKEN (tuple_rest_descriptor | member_type_descriptor))* [COMMA_TOKEN tuple_rest_descriptor]
  private static boolean tuple_member_type_descriptors_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tuple_member_type_descriptors_0_0(b, l + 1);
    r = r && tuple_member_type_descriptors_0_1(b, l + 1);
    r = r && tuple_member_type_descriptors_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // tuple_rest_descriptor | member_type_descriptor
  private static boolean tuple_member_type_descriptors_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_0")) return false;
    boolean r;
    r = tuple_rest_descriptor(b, l + 1);
    if (!r) r = member_type_descriptor(b, l + 1);
    return r;
  }

  // (COMMA_TOKEN (tuple_rest_descriptor | member_type_descriptor))*
  private static boolean tuple_member_type_descriptors_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!tuple_member_type_descriptors_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tuple_member_type_descriptors_0_1", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN (tuple_rest_descriptor | member_type_descriptor)
  private static boolean tuple_member_type_descriptors_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && tuple_member_type_descriptors_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // tuple_rest_descriptor | member_type_descriptor
  private static boolean tuple_member_type_descriptors_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_1_0_1")) return false;
    boolean r;
    r = tuple_rest_descriptor(b, l + 1);
    if (!r) r = member_type_descriptor(b, l + 1);
    return r;
  }

  // [COMMA_TOKEN tuple_rest_descriptor]
  private static boolean tuple_member_type_descriptors_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_2")) return false;
    tuple_member_type_descriptors_0_2_0(b, l + 1);
    return true;
  }

  // COMMA_TOKEN tuple_rest_descriptor
  private static boolean tuple_member_type_descriptors_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA_TOKEN);
    r = r && tuple_rest_descriptor(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ tuple_rest_descriptor ]
  private static boolean tuple_member_type_descriptors_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_member_type_descriptors_1")) return false;
    tuple_rest_descriptor(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // type_descriptor ELLIPSIS_TOKEN
  public static boolean tuple_rest_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_rest_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TUPLE_REST_DESCRIPTOR, "<tuple rest descriptor>");
    r = type_descriptor(b, l + 1, -1);
    r = r && consumeToken(b, ELLIPSIS_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INT_KEYWORD
  //     |BYTE_KEYWORD
  //     |FLOAT_KEYWORD
  //     |DECIMAL_KEYWORD
  //     |STRING_KEYWORD
  //     |BOOLEAN_KEYWORD
  //     |XML_KEYWORD
  //     |JSON_KEYWORD
  //     |HANDLE_KEYWORD
  //     |ANY_KEYWORD
  //     |ANYDATA_KEYWORD
  //     |NEVER_KEYWORD
  //     |VAR_KEYWORD
  //     |MAP_KEYWORD
  //     |FUTURE_KEYWORD
  //     |TYPEDESC_KEYWORD
  //     |ERROR_KEYWORD
  //     |STREAM_KEYWORD
  //     |READONLY_KEYWORD
  //     |DISTINCT_KEYWORD
  //     |FAIL_KEYWORD
  static boolean type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type")) return false;
    boolean r;
    r = consumeToken(b, INT_KEYWORD);
    if (!r) r = consumeToken(b, BYTE_KEYWORD);
    if (!r) r = consumeToken(b, FLOAT_KEYWORD);
    if (!r) r = consumeToken(b, DECIMAL_KEYWORD);
    if (!r) r = consumeToken(b, STRING_KEYWORD);
    if (!r) r = consumeToken(b, BOOLEAN_KEYWORD);
    if (!r) r = consumeToken(b, XML_KEYWORD);
    if (!r) r = consumeToken(b, JSON_KEYWORD);
    if (!r) r = consumeToken(b, HANDLE_KEYWORD);
    if (!r) r = consumeToken(b, ANY_KEYWORD);
    if (!r) r = consumeToken(b, ANYDATA_KEYWORD);
    if (!r) r = consumeToken(b, NEVER_KEYWORD);
    if (!r) r = consumeToken(b, VAR_KEYWORD);
    if (!r) r = consumeToken(b, MAP_KEYWORD);
    if (!r) r = consumeToken(b, FUTURE_KEYWORD);
    if (!r) r = consumeToken(b, TYPEDESC_KEYWORD);
    if (!r) r = consumeToken(b, ERROR_KEYWORD);
    if (!r) r = consumeToken(b, STREAM_KEYWORD);
    if (!r) r = consumeToken(b, READONLY_KEYWORD);
    if (!r) r = consumeToken(b, DISTINCT_KEYWORD);
    if (!r) r = consumeToken(b, FAIL_KEYWORD);
    return r;
  }

  /* ********************************************************** */
  // [annots] type_descriptor | annots
  public static boolean type_cast_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_param")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_CAST_PARAM, "<type cast param>");
    r = type_cast_param_0(b, l + 1);
    if (!r) r = annots(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots] type_descriptor
  private static boolean type_cast_param_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_param_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_cast_param_0_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [annots]
  private static boolean type_cast_param_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_param_0_0")) return false;
    annots(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LT_TOKEN type_descriptor GT_TOKEN
  public static boolean type_parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_parameter")) return false;
    if (!nextTokenIs(b, LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LT_TOKEN);
    r = r && type_descriptor(b, l + 1, -1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, TYPE_PARAMETER, r);
    return r;
  }

  /* ********************************************************** */
  // inferable_type_descriptor binding_pattern
  public static boolean typed_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typed_binding_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPED_BINDING_PATTERN, "<typed binding pattern>");
    r = inferable_type_descriptor(b, l + 1);
    r = r && binding_pattern(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TYPEDESC_KEYWORD [type_parameter]
  public static boolean typedesc_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedesc_type_descriptor")) return false;
    if (!nextTokenIs(b, TYPEDESC_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TYPEDESC_KEYWORD);
    r = r && typedesc_type_descriptor_1(b, l + 1);
    exit_section_(b, m, TYPEDESC_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [type_parameter]
  private static boolean typedesc_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typedesc_type_descriptor_1")) return false;
    type_parameter(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean value_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_EXPR, "<value expr>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean variable_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_name")) return false;
    if (!nextTokenIs(b, "<variable name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_NAME, "<variable name>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // qualified_identifier | identifier | xml_qualified_name
  public static boolean variable_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_REFERENCE, "<variable reference>");
    r = qualified_identifier(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    if (!r) r = xml_qualified_name(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // WHERE_KEYWORD expression
  public static boolean where_clause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "where_clause")) return false;
    if (!nextTokenIs(b, WHERE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHERE_KEYWORD);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, WHERE_CLAUSE, r);
    return r;
  }

  /* ********************************************************** */
  // UNDERSCORE_KEYWORD
  public static boolean wildcard_binding_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "wildcard_binding_pattern")) return false;
    if (!nextTokenIs(b, UNDERSCORE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, UNDERSCORE_KEYWORD);
    exit_section_(b, m, WILDCARD_BINDING_PATTERN, r);
    return r;
  }

  /* ********************************************************** */
  // SLASH_ASTERISK_TOKEN
  public static boolean xml_all_children_step(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_all_children_step")) return false;
    if (!nextTokenIs(b, SLASH_ASTERISK_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_ASTERISK_TOKEN);
    exit_section_(b, m, XML_ALL_CHILDREN_STEP, r);
    return r;
  }

  /* ********************************************************** */
  // ASTERISK_TOKEN
  //   | xml_namespace_prefix NoSpaceColon identifier
  //   | xml_namespace_prefix NoSpaceColon ASTERISK_TOKEN
  //   | identifier
  public static boolean xml_atomic_name_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_atomic_name_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_ATOMIC_NAME_PATTERN, "<xml atomic name pattern>");
    r = consumeToken(b, ASTERISK_TOKEN);
    if (!r) r = xml_atomic_name_pattern_1(b, l + 1);
    if (!r) r = xml_atomic_name_pattern_2(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // xml_namespace_prefix NoSpaceColon identifier
  private static boolean xml_atomic_name_pattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_atomic_name_pattern_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = xml_namespace_prefix(b, l + 1);
    r = r && NoSpaceColon(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // xml_namespace_prefix NoSpaceColon ASTERISK_TOKEN
  private static boolean xml_atomic_name_pattern_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_atomic_name_pattern_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = xml_namespace_prefix(b, l + 1);
    r = r && NoSpaceColon(b, l + 1);
    r = r && consumeToken(b, ASTERISK_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // xml_qualified_name | qualified_identifier | identifier
  public static boolean xml_attribute_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_attribute_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_ATTRIBUTE_NAME, "<xml attribute name>");
    r = xml_qualified_name(b, l + 1);
    if (!r) r = qualified_identifier(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SLASH_LT_TOKEN xml_name_pattern GT_TOKEN
  public static boolean xml_element_children_step(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_element_children_step")) return false;
    if (!nextTokenIs(b, SLASH_LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SLASH_LT_TOKEN);
    r = r && xml_name_pattern(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, XML_ELEMENT_CHILDREN_STEP, r);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN xml_name_pattern GT_TOKEN
  public static boolean xml_element_descendants_step(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_element_descendants_step")) return false;
    if (!nextTokenIs(b, DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOUBLE_SLASH_DOUBLE_ASTERISK_LT_TOKEN);
    r = r && xml_name_pattern(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, XML_ELEMENT_DESCENDANTS_STEP, r);
    return r;
  }

  /* ********************************************************** */
  // xml_atomic_name_pattern (PIPE_TOKEN xml_atomic_name_pattern)*
  public static boolean xml_name_pattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_name_pattern")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_NAME_PATTERN, "<xml name pattern>");
    r = xml_atomic_name_pattern(b, l + 1);
    r = r && xml_name_pattern_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (PIPE_TOKEN xml_atomic_name_pattern)*
  private static boolean xml_name_pattern_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_name_pattern_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!xml_name_pattern_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "xml_name_pattern_1", c)) break;
    }
    return true;
  }

  // PIPE_TOKEN xml_atomic_name_pattern
  private static boolean xml_name_pattern_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_name_pattern_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE_TOKEN);
    r = r && xml_atomic_name_pattern(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier
  public static boolean xml_namespace_prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_namespace_prefix")) return false;
    if (!nextTokenIs(b, "<xml namespace prefix>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_NAMESPACE_PREFIX, "<xml namespace prefix>");
    r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // simple_const_expr
  public static boolean xml_namespace_uri(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_namespace_uri")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_NAMESPACE_URI, "<xml namespace uri>");
    r = simple_const_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // xml_namespace_prefix NoSpaceColon identifier
  public static boolean xml_qualified_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_qualified_name")) return false;
    if (!nextTokenIs(b, "<xml qualified name>", IDENTIFIER_TOKEN, KEY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_QUALIFIED_NAME, "<xml qualified name>");
    r = xml_namespace_prefix(b, l + 1);
    r = r && NoSpaceColon(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT_LT_TOKEN xml_name_pattern GT_TOKEN
  //    | OPEN_BRACKET_TOKEN expression CLOSE_BRACKET_TOKEN
  //    | DOT_TOKEN method_name OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN
  public static boolean xml_step_extend(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_extend")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_STEP_EXTEND, "<xml step extend>");
    r = xml_step_extend_0(b, l + 1);
    if (!r) r = xml_step_extend_1(b, l + 1);
    if (!r) r = xml_step_extend_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOT_LT_TOKEN xml_name_pattern GT_TOKEN
  private static boolean xml_step_extend_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_extend_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT_LT_TOKEN);
    r = r && xml_name_pattern(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPEN_BRACKET_TOKEN expression CLOSE_BRACKET_TOKEN
  private static boolean xml_step_extend_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_extend_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_BRACKET_TOKEN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT_TOKEN method_name OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN
  private static boolean xml_step_extend_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_extend_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT_TOKEN);
    r = r && method_name(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && arg_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // xml_all_children_step
  //    | xml_element_children_step
  //    | xml_element_descendants_step
  public static boolean xml_step_start(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_start")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, XML_STEP_START, "<xml step start>");
    r = xml_all_children_step(b, l + 1);
    if (!r) r = xml_element_children_step(b, l + 1);
    if (!r) r = xml_element_descendants_step(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // XML_KEYWORD [type_parameter]
  public static boolean xml_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_type_descriptor")) return false;
    if (!nextTokenIs(b, XML_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XML_KEYWORD);
    r = r && xml_type_descriptor_1(b, l + 1);
    exit_section_(b, m, XML_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [type_parameter]
  private static boolean xml_type_descriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_type_descriptor_1")) return false;
    type_parameter(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // XMLNS_KEYWORD xml_namespace_uri [ AS_KEYWORD xml_namespace_prefix ] SEMICOLON_TOKEN
  public static boolean xmlns_decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xmlns_decl")) return false;
    if (!nextTokenIs(b, XMLNS_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XMLNS_KEYWORD);
    r = r && xml_namespace_uri(b, l + 1);
    r = r && xmlns_decl_2(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_TOKEN);
    exit_section_(b, m, XMLNS_DECL, r);
    return r;
  }

  // [ AS_KEYWORD xml_namespace_prefix ]
  private static boolean xmlns_decl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xmlns_decl_2")) return false;
    xmlns_decl_2_0(b, l + 1);
    return true;
  }

  // AS_KEYWORD xml_namespace_prefix
  private static boolean xmlns_decl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xmlns_decl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS_KEYWORD);
    r = r && xml_namespace_prefix(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: ATOM(anonymous_function_expr)
  // 1: ATOM(literals)
  // 2: ATOM(new_expr)
  // 3: POSTFIX(method_call_expr)
  // 4: POSTFIX(field_access_expr)
  // 5: ATOM(function_call_expr)
  // 6: ATOM(variable_reference_expr)
  // 7: ATOM(template_expr)
  // 8: ATOM(structural_constructor_expr)
  // 9: ATOM(object_constructor_expr)
  // 10: POSTFIX(optional_field_access_expr)
  // 11: POSTFIX(xml_required_attribute_access_expr) POSTFIX(xml_optional_attribute_access_expr)
  // 12: POSTFIX(annot_access_expr)
  // 13: POSTFIX(member_access_expr)
  // 14: ATOM(error_constructor_expr)
  // 15: PREFIX(let_expr)
  // 16: PREFIX(typeof_expr)
  // 17: PREFIX(unary_logical_expr)
  // 18: ATOM(unary_numeric_expr) BINARY(multiplicative_expr) BINARY(additive_expr) BINARY(shift_expr)
  //    BINARY(bitwise_and_expr) BINARY(bitwise_xor_expr) BINARY(bitwise_or_expr)
  // 19: BINARY(range_expr)
  // 20: POSTFIX(is_expr)
  // 21: BINARY(ternary_is_conditional_expr_1) BINARY(ternary_is_conditional_expr_2) BINARY(ternary_conditional_expr) BINARY(nil_conditional_expr)
  // 22: BINARY(relational_expr)
  // 23: BINARY(equality_expr)
  // 24: BINARY(logical_and_expr) BINARY(logical_or_expr)
  // 25: POSTFIX(xml_filter_expr) POSTFIX(xml_step_expr)
  // 26: ATOM(transactional_expr)
  // 27: PREFIX(type_cast_expr)
  // 28: PREFIX(checking_expr)
  // 29: PREFIX(trap_expr)
  // 30: ATOM(query_expr)
  // 31: PREFIX(last_expression)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = anonymous_function_expr(b, l + 1);
    if (!r) r = literals(b, l + 1);
    if (!r) r = new_expr(b, l + 1);
    if (!r) r = function_call_expr(b, l + 1);
    if (!r) r = variable_reference_expr(b, l + 1);
    if (!r) r = template_expr(b, l + 1);
    if (!r) r = structural_constructor_expr(b, l + 1);
    if (!r) r = object_constructor_expr(b, l + 1);
    if (!r) r = error_constructor_expr(b, l + 1);
    if (!r) r = let_expr(b, l + 1);
    if (!r) r = typeof_expr(b, l + 1);
    if (!r) r = unary_logical_expr(b, l + 1);
    if (!r) r = unary_numeric_expr(b, l + 1);
    if (!r) r = transactional_expr(b, l + 1);
    if (!r) r = type_cast_expr(b, l + 1);
    if (!r) r = checking_expr(b, l + 1);
    if (!r) r = trap_expr(b, l + 1);
    if (!r) r = query_expr(b, l + 1);
    if (!r) r = last_expression(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 3 && method_call_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, METHOD_CALL_EXPR, r, true, null);
      }
      else if (g < 4 && field_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, FIELD_ACCESS_EXPR, r, true, null);
      }
      else if (g < 10 && optional_field_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, OPTIONAL_FIELD_ACCESS_EXPR, r, true, null);
      }
      else if (g < 11 && xml_required_attribute_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, XML_REQUIRED_ATTRIBUTE_ACCESS_EXPR, r, true, null);
      }
      else if (g < 11 && xml_optional_attribute_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, XML_OPTIONAL_ATTRIBUTE_ACCESS_EXPR, r, true, null);
      }
      else if (g < 12 && annot_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, ANNOT_ACCESS_EXPR, r, true, null);
      }
      else if (g < 13 && member_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_ACCESS_EXPR, r, true, null);
      }
      else if (g < 18 && multiplicative_expr_0(b, l + 1)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, MULTIPLICATIVE_EXPR, r, true, null);
      }
      else if (g < 18 && additive_expr_0(b, l + 1)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, ADDITIVE_EXPR, r, true, null);
      }
      else if (g < 18 && shift_expr_0(b, l + 1)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, SHIFT_EXPR, r, true, null);
      }
      else if (g < 18 && consumeTokenSmart(b, BITWISE_AND_TOKEN)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, BITWISE_AND_EXPR, r, true, null);
      }
      else if (g < 18 && consumeTokenSmart(b, BITWISE_XOR_TOKEN)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, BITWISE_XOR_EXPR, r, true, null);
      }
      else if (g < 18 && consumeTokenSmart(b, PIPE_TOKEN)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, BITWISE_OR_EXPR, r, true, null);
      }
      else if (g < 19 && range_expr_0(b, l + 1)) {
        r = expression(b, l, 19);
        exit_section_(b, l, m, RANGE_EXPR, r, true, null);
      }
      else if (g < 20 && is_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, IS_EXPR, r, true, null);
      }
      else if (g < 21 && ternary_is_conditional_expr_1_0(b, l + 1)) {
        r = expression(b, l, 21);
        exit_section_(b, l, m, TERNARY_IS_CONDITIONAL_EXPR_1, r, true, null);
      }
      else if (g < 21 && ternary_is_conditional_expr_2_0(b, l + 1)) {
        r = report_error_(b, expression(b, l, 21));
        r = ternary_is_conditional_expr_2_1(b, l + 1) && r;
        exit_section_(b, l, m, TERNARY_IS_CONDITIONAL_EXPR_2, r, true, null);
      }
      else if (g < 21 && consumeTokenSmart(b, QUESTION_MARK_TOKEN)) {
        r = report_error_(b, expression(b, l, 21));
        r = ternary_conditional_expr_1(b, l + 1) && r;
        exit_section_(b, l, m, TERNARY_CONDITIONAL_EXPR, r, true, null);
      }
      else if (g < 21 && consumeTokenSmart(b, ELVIS_TOKEN)) {
        r = expression(b, l, 21);
        exit_section_(b, l, m, NIL_CONDITIONAL_EXPR, r, true, null);
      }
      else if (g < 22 && relational_expr_0(b, l + 1)) {
        r = expression(b, l, 22);
        exit_section_(b, l, m, RELATIONAL_EXPR, r, true, null);
      }
      else if (g < 23 && equality_expr_0(b, l + 1)) {
        r = expression(b, l, 23);
        exit_section_(b, l, m, EQUALITY_EXPR, r, true, null);
      }
      else if (g < 24 && consumeTokenSmart(b, LOGICAL_AND_TOKEN)) {
        r = expression(b, l, 24);
        exit_section_(b, l, m, LOGICAL_AND_EXPR, r, true, null);
      }
      else if (g < 24 && consumeTokenSmart(b, LOGICAL_OR_TOKEN)) {
        r = expression(b, l, 24);
        exit_section_(b, l, m, LOGICAL_OR_EXPR, r, true, null);
      }
      else if (g < 25 && xml_filter_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, XML_FILTER_EXPR, r, true, null);
      }
      else if (g < 25 && xml_step_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, XML_STEP_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // explicit_anonymous_function_expr | infer_anonymous_function_expr
  public static boolean anonymous_function_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymous_function_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANONYMOUS_FUNCTION_EXPR, "<anonymous function expr>");
    r = explicit_anonymous_function_expr(b, l + 1);
    if (!r) r = infer_anonymous_function_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // string_literal | nil_literal | boolean_literal | numeric_literal | byte_array_literal
  public static boolean literals(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literals")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERALS, "<literals>");
    r = string_literal(b, l + 1);
    if (!r) r = nil_literal(b, l + 1);
    if (!r) r = boolean_literal(b, l + 1);
    if (!r) r = numeric_literal(b, l + 1);
    if (!r) r = byte_array_literal(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // explicit_new_expr | implicit_new_expr
  public static boolean new_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "new_expr")) return false;
    if (!nextTokenIsSmart(b, NEW_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicit_new_expr(b, l + 1);
    if (!r) r = implicit_new_expr(b, l + 1);
    exit_section_(b, m, NEW_EXPR, r);
    return r;
  }

  // DOT_TOKEN method_name OPEN_PAREN_TOKEN arg_list? CLOSE_PAREN_TOKEN
  private static boolean method_call_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_call_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOT_TOKEN);
    r = r && method_name(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && method_call_expr_0_3(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // arg_list?
  private static boolean method_call_expr_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "method_call_expr_0_3")) return false;
    arg_list(b, l + 1);
    return true;
  }

  // DOT_TOKEN field_name
  private static boolean field_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOT_TOKEN);
    r = r && field_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // function_reference OPEN_PAREN_TOKEN arg_list CLOSE_PAREN_TOKEN
  public static boolean function_call_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL_EXPR, "<function call expr>");
    r = function_reference(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && arg_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // variable_reference
  public static boolean variable_reference_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_reference_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_REFERENCE_EXPR, "<variable reference expr>");
    r = variable_reference(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // string_template_expr
  public static boolean template_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "template_expr")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER_TOKEN, STRING_TEMPLATE_START_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEMPLATE_EXPR, "<template expr>");
    r = string_template_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // list_constructor_expr | table_constructor_expr | mapping_constructor_expr
  public static boolean structural_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structural_constructor_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRUCTURAL_CONSTRUCTOR_EXPR, "<structural constructor expr>");
    r = list_constructor_expr(b, l + 1);
    if (!r) r = table_constructor_expr(b, l + 1);
    if (!r) r = mapping_constructor_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots] object_type_quals OBJECT_KEYWORD [type_reference] object_constructor_block
  public static boolean object_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_constructor_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_CONSTRUCTOR_EXPR, "<object constructor expr>");
    r = object_constructor_expr_0(b, l + 1);
    r = r && object_type_quals(b, l + 1);
    r = r && consumeToken(b, OBJECT_KEYWORD);
    r = r && object_constructor_expr_3(b, l + 1);
    r = r && object_constructor_block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [annots]
  private static boolean object_constructor_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_constructor_expr_0")) return false;
    annots(b, l + 1);
    return true;
  }

  // [type_reference]
  private static boolean object_constructor_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_constructor_expr_3")) return false;
    type_reference(b, l + 1);
    return true;
  }

  // OPTIONAL_CHAINING_TOKEN field_name
  private static boolean optional_field_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optional_field_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OPTIONAL_CHAINING_TOKEN);
    r = r && field_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT_TOKEN xml_attribute_name
  private static boolean xml_required_attribute_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_required_attribute_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOT_TOKEN);
    r = r && xml_attribute_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPTIONAL_CHAINING_TOKEN xml_attribute_name
  private static boolean xml_optional_attribute_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_optional_attribute_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OPTIONAL_CHAINING_TOKEN);
    r = r && xml_attribute_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ANNOT_CHAINING_TOKEN annot_tag_reference
  private static boolean annot_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annot_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ANNOT_CHAINING_TOKEN);
    r = r && annot_tag_reference(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPEN_BRACKET_TOKEN (multi_key_expression | key_expression) CLOSE_BRACKET_TOKEN
  private static boolean member_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OPEN_BRACKET_TOKEN);
    r = r && member_access_expr_0_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // multi_key_expression | key_expression
  private static boolean member_access_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_access_expr_0_1")) return false;
    boolean r;
    r = multi_key_expression(b, l + 1);
    if (!r) r = key_expression(b, l + 1);
    return r;
  }

  // ERROR_KEYWORD [error_type_reference] OPEN_PAREN_TOKEN error_arg_list CLOSE_PAREN_TOKEN
  public static boolean error_constructor_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_constructor_expr")) return false;
    if (!nextTokenIsSmart(b, ERROR_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ERROR_KEYWORD);
    r = r && error_constructor_expr_1(b, l + 1);
    r = r && consumeToken(b, OPEN_PAREN_TOKEN);
    r = r && error_arg_list(b, l + 1);
    r = r && consumeToken(b, CLOSE_PAREN_TOKEN);
    exit_section_(b, m, ERROR_CONSTRUCTOR_EXPR, r);
    return r;
  }

  // [error_type_reference]
  private static boolean error_constructor_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "error_constructor_expr_1")) return false;
    error_type_reference(b, l + 1);
    return true;
  }

  public static boolean let_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_expr")) return false;
    if (!nextTokenIsSmart(b, LET_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = let_expr_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 15);
    exit_section_(b, l, m, LET_EXPR, r, p, null);
    return r || p;
  }

  // LET_KEYWORD let_var_decl (COMMA_TOKEN let_var_decl)* IN_KEYWORD
  private static boolean let_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LET_KEYWORD);
    r = r && let_var_decl(b, l + 1);
    r = r && let_expr_0_2(b, l + 1);
    r = r && consumeToken(b, IN_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA_TOKEN let_var_decl)*
  private static boolean let_expr_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_expr_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!let_expr_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "let_expr_0_2", c)) break;
    }
    return true;
  }

  // COMMA_TOKEN let_var_decl
  private static boolean let_expr_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "let_expr_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA_TOKEN);
    r = r && let_var_decl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean typeof_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeof_expr")) return false;
    if (!nextTokenIsSmart(b, TYPEOF_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, TYPEOF_KEYWORD);
    p = r;
    r = p && expression(b, l, 16);
    exit_section_(b, l, m, TYPEOF_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_logical_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_logical_expr")) return false;
    if (!nextTokenIsSmart(b, EXCLAMATION_MARK_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, EXCLAMATION_MARK_TOKEN);
    p = r;
    r = p && expression(b, l, 17);
    exit_section_(b, l, m, UNARY_LOGICAL_EXPR, r, p, null);
    return r || p;
  }

  // PLUS_TOKEN expression
  //    | MINUS_TOKEN expression
  //    | NEGATION_TOKEN expression
  public static boolean unary_numeric_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_numeric_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARY_NUMERIC_EXPR, "<unary numeric expr>");
    r = unary_numeric_expr_0(b, l + 1);
    if (!r) r = unary_numeric_expr_1(b, l + 1);
    if (!r) r = unary_numeric_expr_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PLUS_TOKEN expression
  private static boolean unary_numeric_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_numeric_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, PLUS_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MINUS_TOKEN expression
  private static boolean unary_numeric_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_numeric_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, MINUS_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEGATION_TOKEN expression
  private static boolean unary_numeric_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_numeric_expr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, NEGATION_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ASTERISK_TOKEN | SLASH_TOKEN | PERCENT_TOKEN
  private static boolean multiplicative_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicative_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, ASTERISK_TOKEN);
    if (!r) r = consumeTokenSmart(b, SLASH_TOKEN);
    if (!r) r = consumeTokenSmart(b, PERCENT_TOKEN);
    return r;
  }

  // PLUS_TOKEN | MINUS_TOKEN
  private static boolean additive_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additive_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, PLUS_TOKEN);
    if (!r) r = consumeTokenSmart(b, MINUS_TOKEN);
    return r;
  }

  // DOUBLE_LT_TOKEN|(GT_TOKEN)+
  private static boolean shift_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shift_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOUBLE_LT_TOKEN);
    if (!r) r = shift_expr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (GT_TOKEN)+
  private static boolean shift_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shift_expr_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, GT_TOKEN);
    while (r) {
      int c = current_position_(b);
      if (!consumeTokenSmart(b, GT_TOKEN)) break;
      if (!empty_element_parsed_guard_(b, "shift_expr_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ELLIPSIS_TOKEN|DOUBLE_DOT_LT_TOKEN
  private static boolean range_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "range_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, ELLIPSIS_TOKEN);
    if (!r) r = consumeTokenSmart(b, DOUBLE_DOT_LT_TOKEN);
    return r;
  }

  // ((EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD) type_descriptor
  private static boolean is_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "is_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = is_expr_0_0(b, l + 1);
    r = r && type_descriptor(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD
  private static boolean is_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "is_expr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = is_expr_0_0_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // EXCLAMATION_MARK_TOKEN IS_KEYWORD
  private static boolean is_expr_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "is_expr_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, EXCLAMATION_MARK_TOKEN, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD) ternary_type_descriptor QUESTION_MARK_TOKEN*
  private static boolean ternary_is_conditional_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ternary_is_conditional_expr_1_0_0(b, l + 1);
    r = r && ternary_type_descriptor(b, l + 1);
    r = r && ternary_is_conditional_expr_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD
  private static boolean ternary_is_conditional_expr_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ternary_is_conditional_expr_1_0_0_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // EXCLAMATION_MARK_TOKEN IS_KEYWORD
  private static boolean ternary_is_conditional_expr_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, EXCLAMATION_MARK_TOKEN, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK_TOKEN*
  private static boolean ternary_is_conditional_expr_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeTokenSmart(b, QUESTION_MARK_TOKEN)) break;
      if (!empty_element_parsed_guard_(b, "ternary_is_conditional_expr_1_0_2", c)) break;
    }
    return true;
  }

  // ((EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD) ternary_type_descriptor QUESTION_MARK_TOKEN*
  private static boolean ternary_is_conditional_expr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ternary_is_conditional_expr_2_0_0(b, l + 1);
    r = r && ternary_type_descriptor(b, l + 1);
    r = r && ternary_is_conditional_expr_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EXCLAMATION_MARK_TOKEN IS_KEYWORD) | IS_KEYWORD
  private static boolean ternary_is_conditional_expr_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ternary_is_conditional_expr_2_0_0_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // EXCLAMATION_MARK_TOKEN IS_KEYWORD
  private static boolean ternary_is_conditional_expr_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, EXCLAMATION_MARK_TOKEN, IS_KEYWORD);
    exit_section_(b, m, null, r);
    return r;
  }

  // QUESTION_MARK_TOKEN*
  private static boolean ternary_is_conditional_expr_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_2_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeTokenSmart(b, QUESTION_MARK_TOKEN)) break;
      if (!empty_element_parsed_guard_(b, "ternary_is_conditional_expr_2_0_2", c)) break;
    }
    return true;
  }

  // COLON_TOKEN expression
  private static boolean ternary_is_conditional_expr_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_is_conditional_expr_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COLON_TOKEN expression]
  private static boolean ternary_conditional_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_conditional_expr_1")) return false;
    ternary_conditional_expr_1_0(b, l + 1);
    return true;
  }

  // COLON_TOKEN expression
  private static boolean ternary_conditional_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternary_conditional_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON_TOKEN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LT_TOKEN|GT_TOKEN|LT_EQUAL_TOKEN|GT_EQUAL_TOKEN
  private static boolean relational_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relational_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, LT_TOKEN);
    if (!r) r = consumeTokenSmart(b, GT_TOKEN);
    if (!r) r = consumeTokenSmart(b, LT_EQUAL_TOKEN);
    if (!r) r = consumeTokenSmart(b, GT_EQUAL_TOKEN);
    return r;
  }

  // DOUBLE_EQUAL_TOKEN|NOT_EQUAL_TOKEN|TRIPPLE_EQUAL_TOKEN|NOT_DOUBLE_EQUAL_TOKEN
  private static boolean equality_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equality_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, DOUBLE_EQUAL_TOKEN);
    if (!r) r = consumeTokenSmart(b, NOT_EQUAL_TOKEN);
    if (!r) r = consumeTokenSmart(b, TRIPPLE_EQUAL_TOKEN);
    if (!r) r = consumeTokenSmart(b, NOT_DOUBLE_EQUAL_TOKEN);
    return r;
  }

  // DOT_LT_TOKEN xml_name_pattern GT_TOKEN
  private static boolean xml_filter_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_filter_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, DOT_LT_TOKEN);
    r = r && xml_name_pattern(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  // xml_step_start xml_step_extend*
  private static boolean xml_step_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = xml_step_start(b, l + 1);
    r = r && xml_step_expr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // xml_step_extend*
  private static boolean xml_step_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_step_expr_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!xml_step_extend(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "xml_step_expr_0_1", c)) break;
    }
    return true;
  }

  // TRANSACTIONAL_KEYWORD
  public static boolean transactional_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transactional_expr")) return false;
    if (!nextTokenIsSmart(b, TRANSACTIONAL_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, TRANSACTIONAL_KEYWORD);
    exit_section_(b, m, TRANSACTIONAL_EXPR, r);
    return r;
  }

  public static boolean type_cast_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_expr")) return false;
    if (!nextTokenIsSmart(b, LT_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = type_cast_expr_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 27);
    exit_section_(b, l, m, TYPE_CAST_EXPR, r, p, null);
    return r || p;
  }

  // LT_TOKEN type_cast_param GT_TOKEN
  private static boolean type_cast_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_cast_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LT_TOKEN);
    r = r && type_cast_param(b, l + 1);
    r = r && consumeToken(b, GT_TOKEN);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean checking_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "checking_expr")) return false;
    if (!nextTokenIsSmart(b, CHECKPANIC_KEYWORD, CHECK_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = checking_keyword(b, l + 1);
    p = r;
    r = p && expression(b, l, 28);
    exit_section_(b, l, m, CHECKING_EXPR, r, p, null);
    return r || p;
  }

  public static boolean trap_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "trap_expr")) return false;
    if (!nextTokenIsSmart(b, TRAP_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, TRAP_KEYWORD);
    p = r;
    r = p && expression(b, l, 29);
    exit_section_(b, l, m, TRAP_EXPR, r, p, null);
    return r || p;
  }

  // query_select_expr | query_collect_expr
  public static boolean query_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, QUERY_EXPR, "<query expr>");
    r = query_select_expr(b, l + 1);
    if (!r) r = query_collect_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean last_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "last_expression")) return false;
    if (!nextTokenIsSmart(b, OPEN_PAREN_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, OPEN_PAREN_TOKEN);
    p = r;
    r = p && expression(b, l, -1);
    r = p && report_error_(b, consumeToken(b, CLOSE_PAREN_TOKEN)) && r;
    exit_section_(b, l, m, LAST_EXPRESSION, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Expression root: type_descriptor
  // Operator priority table:
  // 0: ATOM(simple_type_descriptor)
  // 1: ATOM(sequence_type_descriptor)
  // 2: ATOM(mapping_type_descriptor)
  // 3: POSTFIX(array_type_descriptor)
  // 4: ATOM(tuple_type_descriptor)
  // 5: ATOM(table_type_descriptor)
  // 6: ATOM(behavioral_type_descriptor)
  // 7: ATOM(type_reference)
  // 8: ATOM(singleton_type_descriptor)
  // 9: ATOM(any_type_descriptor)
  // 10: ATOM(never_type_descriptor)
  // 11: ATOM(readonly_type_descriptor)
  // 12: PREFIX(distinct_type_descriptor)
  // 13: BINARY(union_type_descriptor)
  // 14: BINARY(intersection_type_descriptor)
  // 15: ATOM(anydata_type_descriptor)
  // 16: ATOM(json_type_descriptor)
  // 17: ATOM(byte_type_descriptor)
  // 18: ATOM(last_type_descriptor)
  // 19: POSTFIX(optional_type_descriptor)
  public static boolean type_descriptor(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "type_descriptor")) return false;
    addVariant(b, "<type descriptor>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<type descriptor>");
    r = simple_type_descriptor(b, l + 1);
    if (!r) r = sequence_type_descriptor(b, l + 1);
    if (!r) r = mapping_type_descriptor(b, l + 1);
    if (!r) r = tuple_type_descriptor(b, l + 1);
    if (!r) r = table_type_descriptor(b, l + 1);
    if (!r) r = behavioral_type_descriptor(b, l + 1);
    if (!r) r = type_reference(b, l + 1);
    if (!r) r = singleton_type_descriptor(b, l + 1);
    if (!r) r = any_type_descriptor(b, l + 1);
    if (!r) r = never_type_descriptor(b, l + 1);
    if (!r) r = readonly_type_descriptor(b, l + 1);
    if (!r) r = distinct_type_descriptor(b, l + 1);
    if (!r) r = anydata_type_descriptor(b, l + 1);
    if (!r) r = json_type_descriptor(b, l + 1);
    if (!r) r = byte_type_descriptor(b, l + 1);
    if (!r) r = last_type_descriptor(b, l + 1);
    p = r;
    r = r && type_descriptor_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean type_descriptor_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "type_descriptor_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 3 && array_type_descriptor_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, ARRAY_TYPE_DESCRIPTOR, r, true, null);
      }
      else if (g < 13 && consumeTokenSmart(b, PIPE_TOKEN)) {
        r = type_descriptor(b, l, 13);
        exit_section_(b, l, m, UNION_TYPE_DESCRIPTOR, r, true, null);
      }
      else if (g < 14 && consumeTokenSmart(b, BITWISE_AND_TOKEN)) {
        r = type_descriptor(b, l, 14);
        exit_section_(b, l, m, INTERSECTION_TYPE_DESCRIPTOR, r, true, null);
      }
      else if (g < 19 && consumeTokenSmart(b, QUESTION_MARK_TOKEN)) {
        r = true;
        exit_section_(b, l, m, OPTIONAL_TYPE_DESCRIPTOR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // tagged_data_type_descriptor
  //    | nil_type_descriptor
  //    | boolean_type_descriptor
  //    | int_type_descriptor
  //    | floating_point_type_descriptor
  public static boolean simple_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIMPLE_TYPE_DESCRIPTOR, "<simple type descriptor>");
    r = tagged_data_type_descriptor(b, l + 1);
    if (!r) r = nil_type_descriptor(b, l + 1);
    if (!r) r = boolean_type_descriptor(b, l + 1);
    if (!r) r = int_type_descriptor(b, l + 1);
    if (!r) r = floating_point_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // string_type_descriptor
  //    | xml_type_descriptor
  public static boolean sequence_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sequence_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, STRING_KEYWORD, XML_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SEQUENCE_TYPE_DESCRIPTOR, "<sequence type descriptor>");
    r = string_type_descriptor(b, l + 1);
    if (!r) r = xml_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // map_type_descriptor | record_type_descriptor
  public static boolean mapping_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapping_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, MAP_KEYWORD, RECORD_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MAPPING_TYPE_DESCRIPTOR, "<mapping type descriptor>");
    r = map_type_descriptor(b, l + 1);
    if (!r) r = record_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // inferable_array_dimension array_dimension*
  private static boolean array_type_descriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_type_descriptor_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = inferable_array_dimension(b, l + 1);
    r = r && array_type_descriptor_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // array_dimension*
  private static boolean array_type_descriptor_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_type_descriptor_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!array_dimension(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_type_descriptor_0_1", c)) break;
    }
    return true;
  }

  // OPEN_BRACKET_TOKEN tuple_member_type_descriptors CLOSE_BRACKET_TOKEN
  public static boolean tuple_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, OPEN_BRACKET_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OPEN_BRACKET_TOKEN);
    r = r && tuple_member_type_descriptors(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET_TOKEN);
    exit_section_(b, m, TUPLE_TYPE_DESCRIPTOR, r);
    return r;
  }

  // TABLE_KEYWORD row_type_parameter [key_constraint]
  public static boolean table_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "table_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, TABLE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, TABLE_KEYWORD);
    r = r && row_type_parameter(b, l + 1);
    r = r && table_type_descriptor_2(b, l + 1);
    exit_section_(b, m, TABLE_TYPE_DESCRIPTOR, r);
    return r;
  }

  // [key_constraint]
  private static boolean table_type_descriptor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "table_type_descriptor_2")) return false;
    key_constraint(b, l + 1);
    return true;
  }

  // error_type_descriptor
  //    | function_type_descriptor
  //    | object_type_descriptor
  //    | future_type_descriptor
  //    | typedesc_type_descriptor
  //    | handle_type_descriptor
  //    | stream_type_descriptor
  public static boolean behavioral_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "behavioral_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BEHAVIORAL_TYPE_DESCRIPTOR, "<behavioral type descriptor>");
    r = error_type_descriptor(b, l + 1);
    if (!r) r = function_type_descriptor(b, l + 1);
    if (!r) r = object_type_descriptor(b, l + 1);
    if (!r) r = future_type_descriptor(b, l + 1);
    if (!r) r = typedesc_type_descriptor(b, l + 1);
    if (!r) r = handle_type_descriptor(b, l + 1);
    if (!r) r = stream_type_descriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // qualified_identifier | identifier
  public static boolean type_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_reference")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_REFERENCE, "<type reference>");
    r = qualified_identifier(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // simple_const_expr
  public static boolean singleton_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "singleton_type_descriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SINGLETON_TYPE_DESCRIPTOR, "<singleton type descriptor>");
    r = simple_const_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ANY_KEYWORD
  public static boolean any_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "any_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, ANY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ANY_KEYWORD);
    exit_section_(b, m, ANY_TYPE_DESCRIPTOR, r);
    return r;
  }

  // NEVER_KEYWORD
  public static boolean never_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "never_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, NEVER_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, NEVER_KEYWORD);
    exit_section_(b, m, NEVER_TYPE_DESCRIPTOR, r);
    return r;
  }

  // READONLY_KEYWORD
  public static boolean readonly_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "readonly_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, READONLY_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, READONLY_KEYWORD);
    exit_section_(b, m, READONLY_TYPE_DESCRIPTOR, r);
    return r;
  }

  public static boolean distinct_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "distinct_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, DISTINCT_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, DISTINCT_KEYWORD);
    p = r;
    r = p && type_descriptor(b, l, 12);
    exit_section_(b, l, m, DISTINCT_TYPE_DESCRIPTOR, r, p, null);
    return r || p;
  }

  // ANYDATA_KEYWORD
  public static boolean anydata_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anydata_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, ANYDATA_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ANYDATA_KEYWORD);
    exit_section_(b, m, ANYDATA_TYPE_DESCRIPTOR, r);
    return r;
  }

  // JSON_KEYWORD
  public static boolean json_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "json_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, JSON_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, JSON_KEYWORD);
    exit_section_(b, m, JSON_TYPE_DESCRIPTOR, r);
    return r;
  }

  // BYTE_KEYWORD
  public static boolean byte_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "byte_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, BYTE_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, BYTE_KEYWORD);
    exit_section_(b, m, BYTE_TYPE_DESCRIPTOR, r);
    return r;
  }

  // OPEN_PAREN_TOKEN type_descriptor CLOSE_PAREN_TOKEN
  public static boolean last_type_descriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "last_type_descriptor")) return false;
    if (!nextTokenIsSmart(b, OPEN_PAREN_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LAST_TYPE_DESCRIPTOR, null);
    r = consumeTokenSmart(b, OPEN_PAREN_TOKEN);
    p = r; // pin = 1
    r = r && report_error_(b, type_descriptor(b, l + 1, -1));
    r = p && consumeToken(b, CLOSE_PAREN_TOKEN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
