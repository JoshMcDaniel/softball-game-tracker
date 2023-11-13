## Build Docker Image

```bash
DOCKER_BUILDKIT=1 docker build -t softball-game-tracker --secret id=serviceAccountKey_json,src=/etc/secrets/serviceAccountKey.json .
```

_Note_

Firebase service account file must be stored locally at `/etc/secrets/serviceAccountKey.json`.

## Run Docker Container

```bash
docker run -e SERVICE_ACCOUNT_KEY=/app/serviceAccountKey.json -v /etc/secrets/serviceAccountKey.json:/app/serviceAccountKey.json -p 8080:8080 softball-game-tracker
```
