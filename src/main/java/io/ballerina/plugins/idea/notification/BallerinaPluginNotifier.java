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

package io.ballerina.plugins.idea.notification;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;

/**
 * Provides notifications for Ballerina plugin.
 *
 * @since 2.0.0
 */
public class BallerinaPluginNotifier {

    public static void notifyBallerinaNotDetected(Project project) {
        Notification notification = NotificationGroupManager
                        .getInstance().getNotificationGroup("Ballerina Plugin Notifications")
                        .createNotification("Unable to detect Ballerina in your environment.",
                                NotificationType.ERROR)
                        .setTitle("Unable to detect Ballerina in your environment.")
                        .setContent(
                                "If you just installed Ballerina, you may need " +
                                        "to restart the IDE. If not, please install Ballerina.")
                        .addAction(NotificationAction.createSimple("Download Ballerina Swan Lake", () -> {
                            BrowserUtil.browse("https://ballerina.io/downloads/");
                        }));

        notification.notify(project);
    }

    public static void notifyToUseSwanLake(Project project) {
        Notification notification = NotificationGroupManager.getInstance()
                .getNotificationGroup("Ballerina Plugin Notifications")
                .createNotification("Use Ballerina Swan Lake.", NotificationType.INFORMATION)
                .setTitle("Invalid Ballerina version detected.")
                .setContent("This plugin only supports Ballerina Swan Lake versions." +
                        " Please install Ballerina Swan Lake.")
                .addAction(NotificationAction.createSimple("Download Ballerina Swan Lake", () -> {
                    BrowserUtil.browse("https://ballerina.io/downloads/");
                }));
        notification.notify(project);
    }

    public static void notifyRestartIde(Project project) {
        Notification notification = NotificationGroupManager.getInstance()
                .getNotificationGroup("Ballerina Plugin Notifications")
                .createNotification("Restart the IDE to apply the changes.", NotificationType.INFORMATION)
                .setTitle("Restart the IDE to apply the changes.")
                .setContent("Please restart the IDE to apply the changes.")
                .addAction(NotificationAction.createSimple("Restart", () -> {
                    // Restart all opened IDEs
                    ApplicationManager.getApplication().restart();
                }));
        notification.notify(project);
    }

    public static void customNotification(Project project, NotificationType type, String title, String msg) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("Ballerina Plugin Notifications")
                .createNotification("", type).setTitle(title).setContent(msg)
                .notify(project);
    }
}
