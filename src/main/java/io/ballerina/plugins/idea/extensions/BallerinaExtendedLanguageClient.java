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

package io.ballerina.plugins.idea.extensions;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import io.ballerina.plugins.idea.preloading.BallerinaLSConfigSettings;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.jetbrains.annotations.NotNull;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.ClientContext;
import org.wso2.lsp4intellij.client.DefaultLanguageClient;
import org.wso2.lsp4intellij.client.ServerWrapperBaseClientContext;
import org.wso2.lsp4intellij.client.languageserver.ServerStatus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ballerina Language Client for ballerina specific operations.
 *
 * @since 2.0.0
 */
public class BallerinaExtendedLanguageClient extends DefaultLanguageClient {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean statusTriggered = false;
    private final Project project;

    public BallerinaExtendedLanguageClient(@NotNull ClientContext context) {
        super(context);
        startStatusListener();
        this.project = context.getProject();
    }

    private void startStatusListener() {
        scheduler.scheduleAtFixedRate(() -> {
            ServerStatus status = ((ServerWrapperBaseClientContext) getContext()).getWrapper().getStatus();
            if (status == ServerStatus.INITIALIZED && !statusTriggered) {
                triggerAction();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void triggerAction() {
        IntellijLanguageClient.didChangeConfiguration(
                new DidChangeConfigurationParams(BallerinaLSConfigSettings.getInstance().getState()),
                Objects.requireNonNull(getContext().getProject()));
        statusTriggered = true;
        scheduler.shutdown();
    }

    @Override
    public void logMessage(MessageParams messageParams) {
        super.logMessage(messageParams);
        String message = messageParams.getMessage();
        MessageType msgType = messageParams.getType();
        NotificationGroup notificationGroup =
                NotificationGroupManager.getInstance().getNotificationGroup("LSPProgressNotification");
        NotificationType notificationType;
        if (msgType == MessageType.Error) {
            notificationType = NotificationType.ERROR;
        } else if (msgType == MessageType.Warning) {
            notificationType = NotificationType.WARNING;
        } else {
            notificationType = NotificationType.INFORMATION;
        }
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        String title = String.format("[bal extension] [%s - %s] ", msgType.name(), formattedTime);

        Pattern urlPattern = Pattern.compile("(https?://\\S+\\b)|(file://\\S+\\b)", Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = urlPattern.matcher(message);
        StringBuilder sb = new StringBuilder();
        while (urlMatcher.find()) {
            String url = urlMatcher.group();
            urlMatcher.appendReplacement(sb, "<a href=\"" + url + "\">" + url + "</a>");
        }
        urlMatcher.appendTail(sb);
        message = sb.toString();

        notificationGroup.createNotification(message, notificationType).setTitle(title).notify(project);
    }
}
