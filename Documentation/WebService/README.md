## WebService API Documentation

### Framework

[Spring Framework](https://spring.io/) --- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
___
## API Documentation

### API Call URLs
> https://localhost:8080

> https://185.194.217.213:8080

### API layout
> /api/{version}/{module}/{function}?{api_key}{parameters}
___
### User API (_/api/v1/user_)

#### Login by username
```html
GET /loginByUsername?key={api_key}&username={username}&password={password}
```
```json
{
  "id": 2,
  "username": "frankie",
  "password": "itsmeagain41",
  "email": "test@hotmail.com",
  "create_time": null
}
```

#### Login by email
```html
GET /loginByEmail?key={api_key}&email={email}&password={password}
```
```json
{
  "id": 2,
  "username": "frankie",
  "password": "itsmeagain41",
  "email": "test@hotmail.com",
  "create_time": null
}
```

#### Creating a new user
```html
POST /newUser?key={api_key}&username={username}&password={password}
```
```text
```
#### Deleting a user
```html
POST /deleteUser?key={api_key}&id={uid}
```
```text
```
#### Getting all users
```html
GET /getUsers?key={api_key}
```
```json
{
  "id": 2,
  "username": "frankie",
  "password": "itsmeagain41",
  "email": "test@hotmail.com",
  "create_time": null
}
{
  "id": 5,
  "username": "dubsky",
  "password": "testPW",
  "email": "test@gmail.com",
  "create_time": null
}
```
#### Getting a random user
```html
GET /getMatch?key={api_key}&first={uid}
```
```json
{
  "id": 2,
  "username": "alesia",
  "password": "pw74adadaca",
  "email": null,
  "create_time": "2021-12-01T23:00:23"
}
```
#### Find user by ID
```html
GET /findByID?key={api_key}&id={uid}
```
```json
{
  "id": 5,
  "username": "dubsky",
  "password": "testPW",
  "email": "test@gmail.com",
  "create_time": null
}
```
#### Find user by username
```html
GET /findByUsername?key={api_key}&username={username}
```
```json
{
  "id": 5,
  "username": "dubsky",
  "password": "testPW",
  "email": "test@gmail.com",
  "create_time": null
}
```
#### Find user by email
```html
GET /findByEmail?key={api_key}&email={email}
```
```json
{
  "id": 5,
  "username": "dubsky",
  "password": "testPW",
  "email": "test@gmail.com",
  "create_time": null
}
```
#### Changing user email
```html
POST /changeEmail?key={api_key}&id={uid}&email={new_email}
```
```text
```
#### Changing user password
```html
POST /changePassword?key={api_key}&id={uid}&password={new_password}
```
```text
```
### Relation API

#### Getting all relationships
```html
GET /getRelations?key={api_key}
```
```json
{
  "first": 1,
  "second": 4,
  "liked": 1,
  "blocked": 0
}
{
  "first": 2,
  "second": 7,
  "liked": 1,
  "blocked": 1
}
```

#### Getting a relationship
```html
GET /getRelation?key={api_key}&id={uid}&id2={uid2}
```
```json
{
  "first": 1,
  "second": 4,
  "liked": 1,
  "blocked": 0
}
```

#### Getting mutual likes
```html
GET /getMatches?key={api_key}&id={uid}
```
```json
{
  "relationid": 2,
  "first": 1,
  "second": 4,
  "liked": 1,
  "blocked": 0
},
{
"relationid": 4,
"first": 1,
"second": 6,
"liked": 1,
"blocked": 0
}
```

#### Creating a relationship
```html
POST /addRelation?key={api_key}&id={uid}&id2={uid2}
```
```text
```

#### Changing a like relation
```html
POST /changeLike?key={api_key}&id={uid}&id2={uid2}&like={0,1,2}
```
```text
```

#### Removing a like
```html
POST /removeLike?key={api_key}&id={uid}&id2={uid2}
```
```text
```

#### Adding a block
```html
POST /addBlock?key={api_key}&id={uid}&id2={uid2}
```
```text
```
#### Removing a block
```html
POST /removeBlock?key={api_key}&id={uid}&id2={uid2}
```
```text
```

### Info API

#### Getting all available user information
```html
GET /getUsers?key={api_key}
```
```json
{
  "id": 1,
  "userid": 1,
  "username": "dubsky",
  "bio": "I am root",
  "gender": null,
  "race": null,
  "friendly": 0,
  "height": 0,
  "weight": 0,
  "avatar": "{\"image\": \"5\"}",
  "images": null
}
{
  "id": 2,
  "userid": 7,
  "username": "anotheruser",
  "bio": "I am a cat",
  "gender": "male",
  "race": "russian-blue",
  "friendly": 1,
  "height": 23,
  "weight": 3,
  "avatar": "{\"image\": \"8\"}",
  "images": null
}
```
#### Getting specific user information
```html
GET /getUser?key={api_key}&id={uid}
```
```json
{
  "id": 1,
  "userid": 1,
  "username": "dubsky",
  "bio": "I am root",
  "gender": null,
  "race": null,
  "friendly": 0,
  "height": 0,
  "weight": 0,
  "avatar": "{\"image\": \"5\"}",
  "images": null
}
```

#### Get the Top10 users
```html
GET /topUsers?key={api_key}
```
```json
[
  {
    "id": 1,
    "userid": 1,
    "username": "dubsky",
    "bio": "I am root",
    "gender": null,
    "race": null,
    "friendly": 0,
    "height": 0,
    "weight": 0,
    "avatar": "{\"image\": \"5\"}",
    "images": "{\"image\": \"8\"}",
    "ranking": 1
  },
  {
    "id": 2,
    "userid": 2,
    "username": "alesia",
    "bio": null,
    "gender": null,
    "race": null,
    "friendly": 0,
    "height": 0,
    "weight": 0,
    "avatar": "{\"image\": \"9\"}",
    "images": null,
    "ranking": 2
  }
]
```

#### Getting user ranking
```html
GET /getRank?key={api_key}&id={uid}
```
```java
x: int // -1 when null or error
```

#### Getting a user preference
```html
GET /getPref?key={api_key}&id={uid}
```
```json
{"cat": 1, "dog": 1}
```

#### Changing user bio
```html
POST /changeBio?key={api_key}&id={uid}&newBio={String}
```
```text
```

#### Changing user gender
```html
POST /changeGender?key={api_key}&id={uid}&newGender={String}
```
```text
```

#### Changing user race
```html
POST /changeRace?key={api_key}&id={uid}&newRace={String}
```
```text
```

#### Changing user friendly status
```html
POST /changeFriendly?key={api_key}&id={uid}&newFriendly={0,1}
```
```text
```

#### Changing user height
```html
POST /changeHeight?key={api_key}&id={uid}&newHeight={int}
```
```text
```

#### Changing user weight
```html
POST /changeWeight?key={api_key}&id={uid}&newWeight={int}
```
```text
```

#### Changing user avatar
```html
POST /changeAvatar?key={api_key}&id={uid}&image={image_id}
```
```text
```

#### Change user ranking
```html
POST /changeAvatar?key={api_key}&id={uid}&newrank={newrank}
```
```text
```

#### Changing a users preference
```html
POST /changePref?key={api_key}&id={uid}&cat={0,1}&dog={0,1}
```
```text
```


