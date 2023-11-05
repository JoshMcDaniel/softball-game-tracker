package com.gametracker.softball;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class SoftballApplication {

	public static void main(String[] args) throws IOException {

		// Load serviceAccountKey.json as a resource stream
		ClassLoader classLoader = SoftballApplication.class.getClassLoader();
		InputStream serviceAccountStream = classLoader.getResourceAsStream("serviceAccountKey.json");

		if (serviceAccountStream == null) {
			// Handle the case when the resource is not found
			throw new RuntimeException("serviceAccountKey.json not found in the classpath");
		}

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(SoftballApplication.class, args);
	}

}
