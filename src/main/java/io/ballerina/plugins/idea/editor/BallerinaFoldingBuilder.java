/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.ballerina.plugins.idea.editor;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.folding.NamedFoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import io.ballerina.plugins.idea.psi.BallerinaAnnotationAttachment;
import io.ballerina.plugins.idea.psi.BallerinaBlockFunctionBody;
import io.ballerina.plugins.idea.psi.BallerinaDocumentationString;
import io.ballerina.plugins.idea.psi.BallerinaExclusiveRecordTypeDescriptor;
import io.ballerina.plugins.idea.psi.BallerinaFieldDescriptor;
import io.ballerina.plugins.idea.psi.BallerinaFile;
import io.ballerina.plugins.idea.psi.BallerinaFunctionDefinition;
import io.ballerina.plugins.idea.psi.BallerinaFunctionDefinitionBody;
import io.ballerina.plugins.idea.psi.BallerinaImportDeclaration;
import io.ballerina.plugins.idea.psi.BallerinaInclusiveRecordTypeDescriptor;
import io.ballerina.plugins.idea.psi.BallerinaMethodDefinition;
import io.ballerina.plugins.idea.psi.BallerinaObjectBody;
import io.ballerina.plugins.idea.psi.BallerinaObjectMethod;
import io.ballerina.plugins.idea.psi.BallerinaObjectTypeName;
import io.ballerina.plugins.idea.psi.BallerinaOrgName;
import io.ballerina.plugins.idea.psi.BallerinaRecordLiteral;
import io.ballerina.plugins.idea.psi.BallerinaServiceBody;
import io.ballerina.plugins.idea.psi.BallerinaServiceConstructorExpression;
import io.ballerina.plugins.idea.psi.BallerinaServiceDefinition;
import io.ballerina.plugins.idea.psi.BallerinaWorkerBody;
import io.ballerina.plugins.idea.psi.BallerinaWorkerDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * Provides support to fold code snippets in Ballerina.
 */
public class BallerinaFoldingBuilder extends CustomFoldingBuilder implements DumbAware {

    @Override
    protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root,
                                            @NotNull Document document, boolean quick) {
        if (!(root instanceof BallerinaFile)) {
            return;
        }
        buildImportFoldingRegion(descriptors, root);
        buildObjectFoldingRegions(descriptors, root);
        buildRecordFoldingRegions(descriptors, root);
        buildFunctionFoldRegions(descriptors, root);
        buildServiceFoldRegions(descriptors, root);
        buildWorkerFoldingRegions(descriptors, root);
        buildDocumentationFoldingRegions(descriptors, root);
        buildAnnotationFoldingRegions(descriptors, root);
        buildMultiCommentFoldingRegions(descriptors, root);
    }

    private void buildImportFoldingRegion(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        Collection<BallerinaImportDeclaration> importDeclarationNodes = PsiTreeUtil.findChildrenOfType(root,
                BallerinaImportDeclaration.class);
        if (!importDeclarationNodes.isEmpty()) {
            BallerinaImportDeclaration[] importDeclarationNodesArray = importDeclarationNodes
                    .toArray(new BallerinaImportDeclaration[0]);
            BallerinaImportDeclaration firstImport = importDeclarationNodesArray[0];
            BallerinaImportDeclaration lastImport = importDeclarationNodesArray[importDeclarationNodes.size() - 1];

            BallerinaOrgName firstOrgName = PsiTreeUtil.findChildOfType(firstImport, BallerinaOrgName.class);
            if (firstOrgName == null || firstOrgName.getText().isEmpty()) {
                return;
            }
            int startOffset = firstOrgName.getTextRange().getStartOffset();
            int endOffset = lastImport.getTextRange().getEndOffset();
            descriptors.add(new NamedFoldingDescriptor(firstImport, startOffset, endOffset, null, "..."));
        }
    }

    private void buildObjectFoldingRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        Collection<BallerinaObjectTypeName> objectDefinitions = PsiTreeUtil.findChildrenOfType(root,
                BallerinaObjectTypeName.class);
        for (BallerinaObjectTypeName objectDefinition : objectDefinitions) {
            // Get the object body. This is used to calculate the start offset.
            BallerinaObjectBody objectBody = PsiTreeUtil.getChildOfType(objectDefinition, BallerinaObjectBody.class);
            if (objectBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, objectDefinition, objectBody, true);
        }
    }

    private void buildRecordFoldingRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {

        // Add folding regions for inclusive records.
        Collection<BallerinaInclusiveRecordTypeDescriptor> inclusiveDefinitions = PsiTreeUtil.findChildrenOfType(root,
                BallerinaInclusiveRecordTypeDescriptor.class);
        for (BallerinaInclusiveRecordTypeDescriptor recordDefinition : inclusiveDefinitions) {
            BallerinaFieldDescriptor firstField = PsiTreeUtil.getChildOfType(recordDefinition,
                    BallerinaFieldDescriptor.class);
            // Retrieves the index of the left left brace of the record.
            if (firstField != null) {
                PsiElement leftBrace = getPreviousElement(firstField);
                int startOffset = leftBrace.getTextRange().getStartOffset();
                int endOffset = recordDefinition.getTextRange().getEndOffset();
                // Add the new folding descriptor.
                descriptors.add(new NamedFoldingDescriptor(recordDefinition, startOffset, endOffset, null, "{...}"));
            }
        }

        // Add folding regions for exclusive records.
        Collection<BallerinaExclusiveRecordTypeDescriptor> exclusiveDefinitions = PsiTreeUtil.findChildrenOfType(root,
                BallerinaExclusiveRecordTypeDescriptor.class);
        for (BallerinaExclusiveRecordTypeDescriptor recordDefinition : exclusiveDefinitions) {
            BallerinaFieldDescriptor firstField = PsiTreeUtil.getChildOfType(recordDefinition,
                    BallerinaFieldDescriptor.class);
            // Retrieves the index of the left left brace of the record.
            if (firstField != null) {
                PsiElement leftBrace = getPreviousElement(firstField);
                int startOffset = leftBrace.getTextRange().getStartOffset();
                int endOffset = recordDefinition.getTextRange().getEndOffset();
                // Add the new folding descriptor.
                descriptors.add(new NamedFoldingDescriptor(recordDefinition, startOffset, endOffset, null, "{...}"));
            }
        }
    }

    private void buildFunctionFoldRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        // Get all function nodes.
        Collection<BallerinaFunctionDefinition> functionNodes = PsiTreeUtil.findChildrenOfType(root,
                BallerinaFunctionDefinition.class);
        for (BallerinaFunctionDefinition functionNode : functionNodes) {
            // Get the function body. This is used to calculate the start offset.
            BallerinaFunctionDefinitionBody functionBodyNode = PsiTreeUtil.getChildOfType(functionNode,
                    BallerinaFunctionDefinitionBody.class);
            if (functionBodyNode == null) {
                continue;
            }
            BallerinaBlockFunctionBody functionBlockNode = PsiTreeUtil.getChildOfType(functionBodyNode,
                    BallerinaBlockFunctionBody.class);
            if (functionBlockNode == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, functionNode, functionBlockNode, false);
        }

        // Get all object method nodes.
        Collection<BallerinaObjectMethod> objectFunctions = PsiTreeUtil.findChildrenOfType(root,
                BallerinaObjectMethod.class);
        for (BallerinaObjectMethod objectMethod : objectFunctions) {
            // Get the object method body. This is used to calculate the start offset.
            BallerinaMethodDefinition methodDefinition = PsiTreeUtil.getChildOfType(objectMethod,
                    BallerinaMethodDefinition.class);
            if (methodDefinition == null) {
                continue;
            }
            BallerinaFunctionDefinitionBody functionDefBody = PsiTreeUtil.getChildOfType(methodDefinition,
                    BallerinaFunctionDefinitionBody.class);
            if (functionDefBody == null) {
                continue;
            }
            BallerinaBlockFunctionBody blockFunctionBody = PsiTreeUtil.getChildOfType(functionDefBody,
                    BallerinaBlockFunctionBody.class);
            if (blockFunctionBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, objectMethod, blockFunctionBody, false);
        }
    }

    private void buildServiceFoldRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        // Get all service nodes.
        Collection<BallerinaServiceDefinition> serviceNodes = PsiTreeUtil.findChildrenOfType(root,
                BallerinaServiceDefinition.class);
        for (BallerinaServiceDefinition serviceNode : serviceNodes) {
            // Get the service body. This is used to calculate the start offset.
            BallerinaServiceBody serviceBody = PsiTreeUtil.getChildOfType(serviceNode, BallerinaServiceBody.class);
            if (serviceBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, serviceNode, serviceBody, false);
        }

        // Get all service variable nodes.
        Collection<BallerinaServiceConstructorExpression> serviceVariableNodes = PsiTreeUtil.findChildrenOfType(root,
                BallerinaServiceConstructorExpression.class);
        for (BallerinaServiceConstructorExpression serviceNode : serviceVariableNodes) {
            // Get the service body. This is used to calculate the start offset.
            BallerinaServiceBody serviceBody = PsiTreeUtil.getChildOfType(serviceNode, BallerinaServiceBody.class);
            if (serviceBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, serviceNode, serviceBody, false);
        }
    }

    private void buildWorkerFoldingRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        Collection<BallerinaWorkerDefinition> workerDefinitions = PsiTreeUtil.findChildrenOfType(root,
                BallerinaWorkerDefinition.class);
        for (BallerinaWorkerDefinition workerDefinition : workerDefinitions) {
            // Get the worker body. This is used to calculate the start offset.
            BallerinaWorkerBody workerBody = PsiTreeUtil.getChildOfType(workerDefinition, BallerinaWorkerBody.class);
            if (workerBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, workerDefinition, workerBody, false);
        }
    }

    private void buildDocumentationFoldingRegions(@NotNull List<FoldingDescriptor> descriptors,
                                                  @NotNull PsiElement root) {
        // Get all documentation nodes.
        Collection<BallerinaDocumentationString> docStrings = PsiTreeUtil.findChildrenOfType(root,
                BallerinaDocumentationString.class);
        for (BallerinaDocumentationString docString : docStrings) {
            if (docString != null) {
                // Calculate the start and end offsets.
                int startOffset = docString.getTextRange().getStartOffset();
                int endOffset = docString.getTextRange().getEndOffset();
                // Add the new folding descriptor.
                descriptors.add(new NamedFoldingDescriptor(docString, startOffset, endOffset, null, "# ..."));
            }
        }
    }

    private void buildAnnotationFoldingRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root) {
        Collection<BallerinaAnnotationAttachment> annotations = PsiTreeUtil.findChildrenOfType(root,
                BallerinaAnnotationAttachment.class);
        for (BallerinaAnnotationAttachment annotation : annotations) {
            // Get the annotation body. This is used to calculate the start offset.
            BallerinaRecordLiteral annotationBody = PsiTreeUtil.getChildOfType(annotation,
                    BallerinaRecordLiteral.class);
            if (annotationBody == null) {
                continue;
            }
            // Add folding descriptor.
            addFoldingDescriptor(descriptors, annotation, annotationBody, false);
        }
    }

    private void buildMultiCommentFoldingRegions(@NotNull List<FoldingDescriptor> descriptors,
                                                 @NotNull PsiElement root) {

        Collection<PsiComment> comments = PsiTreeUtil.findChildrenOfType(root, PsiComment.class);

        for (PsiComment comment : comments) {
            PsiElement prevSibling = getPreviousElement(comment);
            // Prevents adding sub folding regions inside the comment blocks.
            if (prevSibling instanceof PsiComment) {
                continue;
            }
            PsiElement lastElement = getNextElement(comment);
            // Prevents folding single line comments.
            if (!(lastElement instanceof PsiComment)) {
                continue;
            }
            PsiElement nextSibling = getNextElement(lastElement);
            while (nextSibling instanceof PsiComment) {
                lastElement = nextSibling;
                nextSibling = getNextElement(lastElement);
            }
            // Calculates the region of the multiline comment.
            int startOffset = comment.getTextRange().getStartOffset();
            int endOffset = lastElement.getTextRange().getEndOffset();

            // Add the new folding descriptor.
            descriptors.add(new NamedFoldingDescriptor(comment, startOffset, endOffset, null, "// ..."));
        }
    }

    private void addFoldingDescriptor(@NotNull List<FoldingDescriptor> descriptors, PsiElement node,
                                      PsiElement bodyNode, boolean includePrevious) {

        PsiElement startNode = bodyNode;
        if (includePrevious) {
            PsiElement prevSibling = bodyNode.getPrevSibling();
            // Sometimes the body node might start with a comment node.
            while ((prevSibling instanceof PsiComment || prevSibling instanceof PsiWhiteSpace)) {
                prevSibling = prevSibling.getPrevSibling();
            }
            startNode = prevSibling;
        }

        if (startNode != null) {
            // Calculate the start and end offsets.
            int startOffset = startNode.getTextRange().getStartOffset();
            int endOffset = node.getTextRange().getEndOffset();
            // Add the new folding descriptor.
            descriptors.add(new NamedFoldingDescriptor(node, startOffset, endOffset, null, "{...}"));
        }
    }

    @Override
    protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
        return "...";
    }

    @Override
    protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
        return false;
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
