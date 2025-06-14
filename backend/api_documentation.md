# **API-Dokumentation**

## **1. UserController**

**Pfad:** `/api/users`

| HTTP-Methode | Endpunkt        | Beschreibung                                                                 | Parameter                                          |
|--------------|-----------------|------------------------------------------------------------------------------|----------------------------------------------------|
| `POST`       | `/register`     | Erstellt einen neuen Benutzer                                                | User-Objekt im Request-Body                        |
| `GET`        | `/`             | Gibt alle Benutzer zurück                                                    | Auth-Token als Cookie                              |
| `GET`        | `/{id}`         | Gibt einen Benutzer anhand seiner ID zurück                                  | `id` als Pfadvariable                              |
| `PUT`        | `/{id}`         | Aktualisiert einen bestehenden Benutzer                                      | `id` als Pfadvariable, User-Objekt im Request-Body |
| `DELETE`     | `/{id}`         | Löscht einen Benutzer                                                        | `id` als Pfadvariable                              |
| `GET`        | `/verify`       | Verifiziert die E-Mail-Adresse eines Benutzers                               | `token` als Request-Parameter                      |
| `POST`       | `/verify-token` | Überprüft die Gültigkeit des Auth-Tokens                                     | Auth-Token als Cookie in der Anfrage               |
| `POST`       | `/login`        | Authentifiziert einen Benutzer und erstellt ein Session-Cookie mit JWT-Token | `username` und `password` als Request-Parameter    |
| `POST`       | `/logout`       | Beendet die Benutzersitzung und löscht das Session-Cookie                    | Keine                                              |
| `GET`        | `/usernames`    | Gibt alle Benutzernamen zurück                                               | Keine                                              |

## **2. UserDataController**

**Pfad:** `/api/userdata`

| HTTP-Methode | Endpunkt | Beschreibung                              |
|--------------|----------|-------------------------------------------|
| `GET`        | `/`      | Gibt alle Benutzerdaten zurück            |
| `GET`        | `/{id}`  | Gibt Benutzerdaten anhand einer ID zurück |
| `POST`       | `/`      | Erstellt neue Benutzerdaten               |
| `DELETE`     | `/{id}`  | Löscht Benutzerdaten anhand ihrer ID      |

## **3. LikeSkillController**

**Pfad:** `/api/like-skills`

| HTTP-Methode | Endpunkt           | Beschreibung                                                      |
|--------------|--------------------|-------------------------------------------------------------------|
| `POST`       | `/like`            | Liked einen Skill (benötigt `userId` und `skillId`)               |
| `DELETE`     | `/unlike`          | Entfernt den Like eines Skills                                    |
| `GET`        | `/user/{userId}`   | Gibt die Likes eines spezifischen Benutzers zurück                |
| `GET`        | `/skill/{skillId}` | Gibt Like-Informationen eines Skills zurück (Anzahl und User-IDs) |
| `GET`        | `/check`           | Prüft, ob ein Benutzer einen Skill geliked hat                    |

## **4. SkillController**

**Pfad:** `/api/skills`

| HTTP-Methode | Endpunkt | Beschreibung                             |
|--------------|----------|------------------------------------------|
| `POST`       | `/`      | Erstellt einen neuen Skill               |
| `GET`        | `/`      | Gibt alle Skills zurück                  |
| `GET`        | `/{id}`  | Gibt einen Skill anhand seiner ID zurück |
| `PUT`        | `/{id}`  | Aktualisiert einen bestehenden Skill     |
| `DELETE`     | `/{id}`  | Löscht einen Skill                       |

## **5. FollowerController**

**Pfad:** `/api/followers`

| HTTP-Methode | Endpunkt              | Beschreibung                                                               |
|--------------|-----------------------|----------------------------------------------------------------------------|
| `POST`       | `/`                   | Erstellt eine neue Follower-Beziehung                                      |
| `DELETE`     | `/`                   | Entfernt eine Follower-Beziehung (benötigt `followerId` und `followingId`) |
| `GET`        | `/{userId}/followers` | Gibt die Liste der Follower eines Benutzers zurück                         |
| `GET`        | `/{userId}/following` | Gibt die Liste der Benutzer zurück, denen ein Benutzer folgt               |

## **6. MessageController**

**Pfad:** `/api/messages`

| HTTP-Methode | Endpunkt                   | Beschreibung                                                                  |
|--------------|----------------------------|-------------------------------------------------------------------------------|
| `POST`       | `/{fromUserId}/{toUserId}` | Erstellt eine neue Nachricht mit Absender- und Empfänger-ID als Pfadparameter |
| `GET`        | `/`                        | Gibt alle Nachrichten zurück                                                  |
| `GET`        | `/{id}`                    | Gibt eine Nachricht anhand ihrer ID zurück                                    |
| `GET`        | `/all/{userId}`            | Gibt alle gesendeten und empfangenen Nachrichten eines Benutzers zurück       |
| `DELETE`     | `/{id}`                    | Löscht eine Nachricht anhand ihrer ID                                         |

## **Detaillierte Beschreibung der Benutzer-API**

### Authentifizierung

Die meisten Endpunkte im `UserController` erfordern eine Authentifizierung mittels JWT-Token. Dieses Token wird nach
erfolgreicher Anmeldung als Cookie (`auth-token`) gesetzt und sollte bei nachfolgenden Anfragen automatisch mitgesendet
werden.

#### Token-Eigenschaften

- **Cookie-Name:** auth-token
- **Signatur-Algorithmus:** HMAC SHA-256 (HS256)
- **Cookie-Eigenschaften:** HttpOnly, Secure (in Produktionsumgebung)

### Antwort-Formate

#### Erfolgreiche Antworten

Bei erfolgreichen Anfragen gibt die API typischerweise Folgendes zurück:

- **Statuscode:** 200 OK oder 201 Created (bei Erstellung neuer Ressourcen)
- **Inhaltstyp:** application/json

#### Fehlerantworten

Bei Fehlern gibt die API typischerweise Folgendes zurück:

- **Statuscode:** 400 Bad Request, 401 Unauthorized, 404 Not Found oder 500 Internal Server Error
- **Inhaltstyp:** application/json mit einer Nachricht, die den Fehler beschreibt

### Cross-Origin Resource Sharing (CORS)

Alle Endpunkte im `UserController` sind mit `@CrossOrigin` annotiert, was CORS-Anfragen von anderen Domains ermöglicht.