package com.gametracker.softball;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SoftballGameService {
    public String createSoftballGame(SoftballGame softballGame) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = (ApiFuture<WriteResult>) dbFireStore.collection("activeSoftballGames").document(softballGame.getDocumentId()).set(softballGame);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public SoftballGame getSoftballGame(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection("activeSoftballGames").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        return document.toObject(SoftballGame.class);
    }

    public String updateSoftballGame(SoftballGame softballGame) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = (ApiFuture<WriteResult>) dbFireStore.collection("activeSoftballGames").document(softballGame.getDocumentId()).set(softballGame);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteSoftballGame(String documentId) {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        dbFireStore.collection("activeSoftballGames").document(documentId).delete();
        return "Successfully deleted document " + documentId;
    }
}
