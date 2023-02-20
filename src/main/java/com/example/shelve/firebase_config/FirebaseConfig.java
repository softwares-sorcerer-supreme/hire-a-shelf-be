package com.example.shelve.firebase_config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp config() {
        String serviceAccountKeyPath = System.getenv("SERVICE_ACCOUNT_KEY_PATH");
        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath)))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        for (FirebaseApp app : firebaseApps) {
            if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                firebaseApp = app;
                break;
            }
        }
        if (firebaseApp == null) {
            firebaseApp = FirebaseApp.initializeApp(options);
        }
        return firebaseApp;
    }

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }


}
