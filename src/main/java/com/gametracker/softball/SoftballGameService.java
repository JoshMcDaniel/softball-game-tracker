package com.gametracker.softball;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SoftballGameService {

    public String createSoftballGame(SoftballGameCreate gameData) throws ExecutionException, InterruptedException {
        SoftballGame softballGame = new SoftballGame(gameData);
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = (ApiFuture<WriteResult>) dbFireStore
                .collection("activeSoftballGames").document(softballGame.getDocumentId()).set(softballGame);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public SoftballGame getSoftballGameById(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference collectionReference = dbFireStore.collection("activeSoftballGames").document(documentId);
        ApiFuture<DocumentSnapshot> future = collectionReference.get();
        DocumentSnapshot document = future.get();
        return document.toObject(SoftballGame.class);
    }

    public List<SoftballGame> getAllActiveSoftballGames() throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFireStore.collection("activeSoftballGames");
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        List<SoftballGame> allGames = new ArrayList<>();

        for (QueryDocumentSnapshot document : future.get().getDocuments()) {
            SoftballGame game = document.toObject(SoftballGame.class);
            allGames.add(game);
        }

        return allGames;
    }

    public String updateSoftballGame(SoftballGame softballGame) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = (ApiFuture<WriteResult>) dbFireStore
                .collection("activeSoftballGames").document(softballGame.getDocumentId()).set(softballGame);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteSoftballGame(String documentId) {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        dbFireStore.collection("activeSoftballGames").document(documentId).delete();
        return "Successfully deleted document " + documentId;
    }
}
