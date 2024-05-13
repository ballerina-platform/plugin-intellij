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

package io.ballerina.plugins.idea.editor;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import io.ballerina.plugins.idea.psi.BallerinaDocumentationString;
import io.ballerina.plugins.idea.psi.BallerinaFile;
import io.ballerina.plugins.idea.psi.BallerinaImportDecl;
import io.ballerina.plugins.idea.psi.BallerinaTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * Folding builder for Ballerina language.
 *
 * @since 2.0.0
 */
public class BallerinaFoldingBuilder extends CustomFoldingBuilder implements DumbAware {

    private static final String DEFAULT_PLACEHOLDER = "...";
    private static final String COMMENT_PLACEHOLDER = "// ...";
    private static final String DOCUMENTATION_PLACEHOLDER = "# ...";
    private static final String BRACE_PLACEHOLDER = "{...}";
    private static final String BRACE_PIPE_PLACEHOLDER = "{|...|}";

    @Override
    protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> list, @NotNull PsiElement psiElement,
                                            @NotNull Document document, boolean b) {
        if (!(psiElement instanceof BallerinaFile)) {
            return;
        }
        list.addAll(createFoldingRegions(psiElement, BallerinaTypes.OPEN_BRACE_TOKEN,
                BallerinaTypes.CLOSE_BRACE_TOKEN, BRACE_PLACEHOLDER));
        list.addAll(createFoldingRegions(psiElement, BallerinaTypes.OPEN_NESTED_BRACE_TOKEN,
                BallerinaTypes.CLOSE_NESTED_BRACE_TOKEN, BRACE_PLACEHOLDER));
        list.addAll(createFoldingRegions(psiElement, BallerinaTypes.IGNORED_OPEN_BRACE_TOKEN,
                BallerinaTypes.IGNORED_CLOSE_BRACE_TOKEN, BRACE_PLACEHOLDER));
        list.addAll(createFoldingRegions(psiElement, BallerinaTypes.OPEN_BRACE_PIPE_TOKEN,
                BallerinaTypes.CLOSE_BRACE_PIPE_TOKEN, BRACE_PIPE_PLACEHOLDER));
        list.addAll(createFoldingRegions(psiElement, BallerinaTypes.OPEN_NESTED_BRACE_PIPE_TOKEN,
                BallerinaTypes.CLOSE_NESTED_BRACE_PIPE_TOKEN, BRACE_PIPE_PLACEHOLDER));
        list.addAll(createImportFoldingRegions(psiElement));
        list.addAll(createDocumentationFoldingRegions(psiElement));
        list.addAll(createMultiCommentFoldingRegions(psiElement));
    }

    @Override
    protected String getLanguagePlaceholderText(@NotNull ASTNode astNode, @NotNull TextRange textRange) {
        return DEFAULT_PLACEHOLDER;
    }

    @Override
    protected boolean isRegionCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }

    private List<FoldingDescriptor> createFoldingRegions(PsiElement psiElement, IElementType open, IElementType close,
                                      String placeholder) {
        List<FoldingDescriptor> foldingDescriptors = new ArrayList<>();
        List<PsiElement> leaves = new ArrayList<>();
        Stack<PsiElement> stack = new Stack<>();
        stack.push(psiElement);

        while (!stack.isEmpty()) {
            PsiElement element = stack.pop();
            if (element.getFirstChild() == null &&
                    (element.getNode().getElementType() == open || element.getNode().getElementType() == close)) {
                leaves.add(0, element);
            } else {
                PsiElement child = element.getFirstChild();
                while (child != null) {
                    stack.push(child);
                    child = child.getNextSibling();
                }
            }
        }
        Stack<PsiElement> openBraces = new Stack<>();
        for (PsiElement leaf : leaves) {
            if (leaf.getNode().getElementType() == open) {
                openBraces.push(leaf);
            } else if (leaf.getNode().getElementType() == close && !openBraces.isEmpty()) {
                PsiElement openBrace = openBraces.pop();
                int startOffset = openBrace.getTextRange().getStartOffset();
                int endOffset = leaf.getTextRange().getEndOffset();
                if (endOffset > startOffset) {
                    foldingDescriptors.add(new FoldingDescriptor(openBrace.getNode(),
                            new TextRange(startOffset, endOffset), null,
                            placeholder));
                }
            }
        }
        return foldingDescriptors;
    }

    private List<FoldingDescriptor> createImportFoldingRegions(PsiElement psiElement) {
        List<FoldingDescriptor> foldingDescriptors = new ArrayList<>();
        Collection<BallerinaImportDecl> importDeclarationNodes =
                PsiTreeUtil.findChildrenOfType(psiElement, BallerinaImportDecl.class);
        if (!importDeclarationNodes.isEmpty()) {
            BallerinaImportDecl[] importDecls = importDeclarationNodes.toArray(new BallerinaImportDecl[0]);
            BallerinaImportDecl firstImport = importDecls[0];
            BallerinaImportDecl lastImport = importDecls[importDeclarationNodes.size() - 1];
            int importOffset = 7;
            int startOffset = firstImport.getTextRange().getStartOffset() + importOffset;
            int lastOffset = lastImport.getTextRange().getEndOffset();
            if (startOffset < lastOffset) {
                foldingDescriptors.add(new FoldingDescriptor(firstImport, startOffset,
                        lastOffset, null, DEFAULT_PLACEHOLDER));
            }
        }
        return foldingDescriptors;
    }

    private List<FoldingDescriptor> createDocumentationFoldingRegions(@NotNull PsiElement root) {
        List<FoldingDescriptor> foldingDescriptors = new ArrayList<>();
        Collection<BallerinaDocumentationString> docStrings =
                PsiTreeUtil.findChildrenOfType(root, BallerinaDocumentationString.class);
        for (BallerinaDocumentationString docString : docStrings) {
            if (docString != null) {
                int startOffset = docString.getTextRange().getStartOffset();
                int endOffset = docString.getTextRange().getEndOffset();
                foldingDescriptors.add(new FoldingDescriptor(docString, startOffset, endOffset,
                        null, DOCUMENTATION_PLACEHOLDER));
            }
        }
        return foldingDescriptors;
    }

    private List<FoldingDescriptor> createMultiCommentFoldingRegions(@NotNull PsiElement root) {
        List<FoldingDescriptor> foldingDescriptors = new ArrayList<>();
        Collection<PsiComment> comments = PsiTreeUtil.findChildrenOfType(root, PsiComment.class);

        for (PsiComment comment : comments) {
            PsiElement prevSibling = getPreviousElement(comment);
            if (prevSibling instanceof PsiComment) {
                continue;
            }
            PsiElement lastElement = getNextElement(comment);
            if (!(lastElement instanceof PsiComment)) {
                continue;
            }
            PsiElement nextSibling = getNextElement(lastElement);
            while (nextSibling instanceof PsiComment) {
                lastElement = nextSibling;
                nextSibling = getNextElement(lastElement);
            }
            int startOffset = comment.getTextRange().getStartOffset();
            int endOffset = lastElement.getTextRange().getEndOffset();

            foldingDescriptors.add(new FoldingDescriptor(comment, startOffset, endOffset,
                    null, COMMENT_PLACEHOLDER));
        }
        return foldingDescriptors;
    }

    private PsiElement getPreviousElement(PsiElement element) {
        PsiElement prev = element.getPrevSibling();
        while (prev instanceof PsiWhiteSpace) {
            prev = prev.getPrevSibling();
        }
        return prev;
    }

    private PsiElement getNextElement(PsiElement element) {
        PsiElement next = element.getNextSibling();
        while (next instanceof PsiWhiteSpace) {
            next = next.getNextSibling();
        }
        return next;
    }
}
