# **API-Dokumentation**

## **1. UserController**

**Pfad:** `/api/users`

| HTTP-Methode | Endpunkt     | Beschreibung                                                                 |
|--------------|--------------|------------------------------------------------------------------------------|
| `POST`       | `/register`  | Erstellt einen neuen Benutzer                                                |
| `GET`        | `/`          | Gibt alle Benutzer zurück                                                    |
| `GET`        | `/{id}`      | Gibt einen Benutzer anhand seiner ID zurück                                  |
| `PUT`        | `/{id}`      | Aktualisiert einen bestehenden Benutzer                                      |
| `DELETE`     | `/{id}`      | Löscht einen Benutzer                                                        |
| `POST`       | `/login`     | Authentifiziert einen Benutzer und erstellt ein Session-Cookie mit JWT-Token |
| `POST`       | `/verify`    | Überprüft die Gültigkeit des Session-Cookies (auth-token)                    |
| `POST`       | `/logout`    | Beendet die Benutzersitzung und löscht das Session-Cookie                    |
| `GET`        | `/usernames` | Gibt alle Benutzernamen zurück                                               |

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

| HTTP-Methode | Endpunkt        | Beschreibung                                                            |
|--------------|-----------------|-------------------------------------------------------------------------|
| `POST`       | `/`             | Erstellt eine neue Nachricht                                            |
| `GET`        | `/`             | Gibt alle Nachrichten zurück                                            |
| `GET`        | `/{id}`         | Gibt eine Nachricht anhand ihrer ID zurück                              |
| `GET`        | `/all/{userId}` | Gibt alle gesendeten und empfangenen Nachrichten eines Benutzers zurück |
| `DELETE`     | `/{id}`         | Löscht eine Nachricht anhand ihrer ID                                   |