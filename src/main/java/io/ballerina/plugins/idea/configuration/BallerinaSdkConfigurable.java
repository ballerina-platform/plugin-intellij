/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
 */

package io.ballerina.plugins.idea.configuration;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.DependencyScope;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBCardLayout;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.Alarm;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.AsyncProcessIcon;
import com.intellij.util.ui.UIUtil;
import io.ballerina.plugins.idea.sdk.BallerinaSdkService;
import io.ballerina.plugins.idea.sdk.BallerinaSdkUtils;
import io.ballerina.plugins.idea.sdk.BallerinaSmallIDEsSdkService;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

/**
 * Responsible for Ballerina SDK configuration window in small IDEs.
 */
public class BallerinaSdkConfigurable implements SearchableConfigurable, Configurable.NoScroll {

    private static final String VERSION_GETTING = "VERSION_GETTING_CARD";
    private static final String VERSION_RESULT = "VERSION_RESULT_CARD";

    @NotNull
    private final Project myProject;
    @NotNull
    private final Disposable myDisposable = Disposer.newDisposable();
    @NotNull
    private final Alarm myAlarm = new Alarm(Alarm.ThreadToUse.POOLED_THREAD, myDisposable);
    private JPanel myComponent;
    private TextFieldWithBrowseButton mySdkPathField;
    private JPanel myVersionPanel;
    private JBLabel myVersionLabel;
    private Color myDefaultLabelColor;

    public BallerinaSdkConfigurable(@NotNull Project project, boolean dialogMode) {
        myProject = project;
        if (dialogMode) {
            myComponent.setPreferredSize(new Dimension(400, -1));
        }
        FileChooserDescriptor chooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
                .withTitle("Select GOROOT");
        mySdkPathField.addBrowseFolderListener(myProject, new MyBrowseFolderListener(chooserDescriptor));
        listenForPathUpdate();
        Disposer.register(myDisposable, mySdkPathField);
    }

    @Override
    public void apply() throws ConfigurationException {
        ApplicationManager.getApplication().runWriteAction(() -> {
            if (myProject.isDefault() || myProject.isDisposed()) {
                return;
            }

            LibraryTable table = LibraryTablesRegistrar.getInstance().getLibraryTable(myProject);
            Library get = table.getLibraryByName(BallerinaSmallIDEsSdkService.LIBRARY_NAME);
            Library lib = get != null ? get : table.createLibrary(BallerinaSmallIDEsSdkService.LIBRARY_NAME);

            Library.ModifiableModel libraryModel = lib.getModifiableModel();
            String libUrl = ArrayUtil.getFirstElement(lib.getUrls(OrderRootType.CLASSES));
            if (libUrl != null) {
                libraryModel.removeRoot(libUrl, OrderRootType.CLASSES);
            }

            String sdkPath = BallerinaSdkUtils.adjustSdkPath(mySdkPathField.getText());
            String versionString = BallerinaSdkUtils.retrieveBallerinaVersion(sdkPath);
            boolean toRemove = StringUtil.isEmpty(sdkPath) || versionString == null;

            if (!toRemove) {
                for (VirtualFile file : BallerinaSdkUtils.getSdkDirectoriesToAttach(sdkPath, versionString)) {
                    libraryModel.addRoot(file, OrderRootType.CLASSES);
                }
            }
            libraryModel.commit();

            if (toRemove) {
                updateModules(myProject, lib, true);
                table.removeLibrary(lib);
            }

            table.getModifiableModel().commit();

            if (!toRemove) {
                updateModules(myProject, lib, false);
            }
            BallerinaSdkService.getInstance(myProject).incModificationCount();
        });
    }

    @Override
    public void reset() {
        mySdkPathField.setText(StringUtil.notNullize(BallerinaSdkService.getInstance(myProject).getSdkHomePath(null)));
    }

    @Override
    public boolean isModified() {
        String currentPath = StringUtil.notNullize(BallerinaSdkService.getInstance(myProject).getSdkHomePath(null));
        return !BallerinaSdkUtils.adjustSdkPath(mySdkPathField.getText()).equals(currentPath);
    }

    @NotNull
    @Override
    public String getId() {
        return "ballerina.sdk";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Ballerina SDK";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return myComponent;
    }

    private static void updateModules(@NotNull Project project, @NotNull Library lib, boolean remove) {
        Module[] modules = ModuleManager.getInstance(project).getModules();
        for (Module module : modules) {
            ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
            if (!remove) {
                if (model.findLibraryOrderEntry(lib) == null) {
                    LibraryOrderEntry entry = model.addLibraryEntry(lib);
                    entry.setScope(DependencyScope.PROVIDED);
                }
            } else {
                LibraryOrderEntry entry = model.findLibraryOrderEntry(lib);
                if (entry != null) {
                    model.removeOrderEntry(entry);
                }
            }
            model.commit();
        }
    }

    private void asyncUpdateSdkVersion(@NotNull String sdkPath) {
        ApplicationManager.getApplication().assertIsDispatchThread();
        ((CardLayout) myVersionPanel.getLayout()).show(myVersionPanel, VERSION_GETTING);

        if (!myAlarm.isDisposed()) {
            myAlarm.cancelAllRequests();
            myAlarm.addRequest(() -> {
                String version = BallerinaSdkUtils.retrieveBallerinaVersion(BallerinaSdkUtils.adjustSdkPath(sdkPath));
                ApplicationManager.getApplication().invokeLater(() -> {
                    if (!Disposer.isDisposed(myDisposable)) {
                        setVersion(version);
                    }
                }, ModalityState.any());
            }, 100);
        }
    }

    private void setVersion(@Nullable String version) {
        if (version == null) {
            myVersionLabel.setText("N/A");
            myVersionLabel.setForeground(JBColor.RED);
        } else {
            myVersionLabel.setText(version);
            myVersionLabel.setForeground(myDefaultLabelColor);
        }
        ((CardLayout) myVersionPanel.getLayout()).show(myVersionPanel, VERSION_RESULT);
    }

    private void createUIComponents() {
        myVersionLabel = new JBLabel();
        myDefaultLabelColor = myVersionLabel.getForeground();

        myVersionPanel = new JPanel(new JBCardLayout());
        JPanel gettingVersionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        AsyncProcessIcon gettingVersionIcon = new AsyncProcessIcon("Getting Ballerina version");
        gettingVersionPanel.add(gettingVersionIcon);
        gettingVersionPanel.add(new JLabel("Getting..."));
        myVersionPanel.add(gettingVersionPanel, VERSION_GETTING);
        myVersionPanel.add(myVersionLabel, VERSION_RESULT);

        setVersion(null);
    }

    @Override
    public void disposeUIResources() {
        UIUtil.dispose(myVersionLabel);
        UIUtil.dispose(myVersionPanel);
        UIUtil.dispose(myComponent);
        myVersionLabel = null;
        myVersionPanel = null;
        myDefaultLabelColor = null;
        Disposer.dispose(myDisposable);
    }

    private class MyBrowseFolderListener extends ComponentWithBrowseButton.BrowseFolderActionListener<JTextField> {
        public MyBrowseFolderListener(@NotNull FileChooserDescriptor descriptor) {
            super("Select Ballerina SDK Path", "", mySdkPathField, myProject, descriptor,
                    TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT);
        }

        @Nullable
        @Override
        protected VirtualFile getInitialFile() {
            return ObjectUtils.chooseNotNull(super.getInitialFile(), BallerinaSdkUtils.suggestSdkDirectory());
        }
    }

    private void listenForPathUpdate() {
        JTextField textField = mySdkPathField.getTextField();
        Ref<String> prevPathRef = Ref.create(StringUtil.notNullize(textField.getText()));
        textField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(DocumentEvent e) {
                String sdkPath = StringUtil.notNullize(textField.getText());
                String prevPath = prevPathRef.get();
                if (!prevPath.equals(sdkPath)) {
                    asyncUpdateSdkVersion(sdkPath);
                    prevPathRef.set(sdkPath);
                }
            }
        });
    }
}
