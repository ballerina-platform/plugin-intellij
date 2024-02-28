package org.intellij.sdk.language;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class BallerinaTypedHandler extends TypedHandlerDelegate {

    @Override
    public @NotNull Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        if (c == '`' || c == '"') { // Check if the typed character is a backtick or double quote
            insertCharAndMoveCaret(editor, c);
        }

        return super.charTyped(c, project, editor, file);
    }

    private void insertCharAndMoveCaret(Editor editor, char c) {
        Document document = editor.getDocument();
        int offset = editor.getCaretModel().getOffset();

        // Insert the character automatically for closing
        document.insertString(offset, String.valueOf(c));

        // Move the caret to the position between the two characters
        editor.getCaretModel().moveToOffset(offset);
    }

}

