# **API-Dokumentation**

## **1. UserController**

**Pfad:** `/api/users`

| HTTP-Methode | Endpunkt     | Beschreibung                                |
|--------------|--------------|---------------------------------------------|
| `POST`       | `/register`  | Erstellt einen neuen Benutzer               |
| `GET`        | `/`          | Gibt alle Benutzer zurück                   |
| `GET`        | `/{id}`      | Gibt einen Benutzer anhand seiner ID zurück |
| `PUT`        | `/{id}`      | Aktualisiert einen bestehenden Benutzer     |
| `DELETE`     | `/{id}`      | Löscht einen Benutzer                       |
| `POST`       | `/verify`    | Überprüft die Anmeldedaten eines Benutzers  |
| `GET`        | `/usernames` | Gibt alle Benutzernamen zurück              |

---

## **2. UserDataController**

**Pfad:** `/api/userdata`

| HTTP-Methode | Endpunkt | Beschreibung                              |
|--------------|----------|-------------------------------------------|
| `GET`        | `/`      | Gibt alle Benutzerdaten zurück            |
| `GET`        | `/{id}`  | Gibt Benutzerdaten anhand einer ID zurück |
| `POST`       | `/`      | Erstellt neue Benutzerdaten               |
| `DELETE`     | `/{id}`  | Löscht Benutzerdaten anhand ihrer ID      |

---

## **3. LikeSkillController**

**Pfad:** `/api/like-skills`

| HTTP-Methode | Endpunkt           | Beschreibung                                        |
|--------------|--------------------|-----------------------------------------------------|
| `POST`       | `/like`            | Liked einen Skill (benötigt `userId` und `skillId`) |
| `DELETE`     | `/unlike`          | Entfernt den Like eines Skills                      |
| `GET`        | `/user/{userId}`   | Gibt die Likes eines spezifischen Benutzers zurück  |
| `GET`        | `/skill/{skillId}` | Gibt die Likes eines spezifischen Skills zurück     |

---

## **4. SkillController**

**Pfad:** `/api/skills`

| HTTP-Methode | Endpunkt | Beschreibung                             |
|--------------|----------|------------------------------------------|
| `POST`       | `/`      | Erstellt einen neuen Skill               |
| `GET`        | `/`      | Gibt alle Skills zurück                  |
| `GET`        | `/{id}`  | Gibt einen Skill anhand seiner ID zurück |
| `PUT`        | `/{id}`  | Aktualisiert einen bestehenden Skill     |
| `DELETE`     | `/{id}`  | Löscht einen Skill                       |

---

## **5. FollowerController**

**Pfad:** `/api/followers`

| HTTP-Methode | Endpunkt              | Beschreibung                                                               |
|--------------|-----------------------|----------------------------------------------------------------------------|
| `POST`       | `/`                   | Erstellt eine neue Follower-Beziehung                                      |
| `DELETE`     | `/`                   | Entfernt eine Follower-Beziehung (benötigt `followerId` und `followingId`) |
| `GET`        | `/{userId}/followers` | Gibt die Liste der Follower eines Benutzers zurück                         |
| `GET`        | `/{userId}/following` | Gibt die Liste der Benutzer zurück, denen ein Benutzer folgt               |

---

## **6. MessageController**

**Pfad:** `/api/messages`

| HTTP-Methode | Endpunkt                 | Beschreibung                                                                                                       |
|--------------|--------------------------|--------------------------------------------------------------------------------------------------------------------|
| `POST`       | `/`                      | Erstellt eine neue Nachricht zwischen zwei Benutzern (benötigt `senderId`, `recipientId`, `content`)               |
| `GET`        | `/{messageId}`           | Gibt eine Nachricht anhand ihrer ID zurück                                                                         |
| `GET`        | `/conversation/{userId}` | Gibt alle Nachrichten zurück, die zwischen dem aktuellen Benutzer und einem gegebenen Benutzer ausgetauscht wurden |
| `DELETE`     | `/{messageId}`           | Löscht eine Nachricht anhand ihrer ID                                                                              |
