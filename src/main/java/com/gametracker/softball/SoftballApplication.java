package com.gametracker.softball;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class SoftballApplication {

	public static void main(String[] args) throws IOException {

		String serviceAccountKeyPath = System.getenv("SERVICE_ACCOUNT_KEY");

		if (serviceAccountKeyPath == null || serviceAccountKeyPath.isEmpty()) {
			throw new IllegalArgumentException("SERVICE_ACCOUNT_KEY environment variable is not set");
		}

		FileInputStream serviceAccount = new FileInputStream(serviceAccountKeyPath);

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(SoftballApplication.class, args);
	}

}
