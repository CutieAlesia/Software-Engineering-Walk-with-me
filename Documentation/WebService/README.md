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
### User API (_/api/v1/users_)

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
POST /deleteUser?key={api_key}&id={id}
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
#### Find user by ID
```html
GET /findByID?key={api_key}&id={id}
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
POST /changeEmail?key={api_key}&id={id}&email={new_email}
```
```text
```
#### Changing user password
```html
POST /changePassword?key={api_key}&id={id}&password={new_password}
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
GET /getRelation?key={api_key}&id={id}&id2={id2}
```
```json
{
  "first": 1,
  "second": 4,
  "liked": 1,
  "blocked": 0
}
```

#### Creating a relationship
```html
POST /addRelation?key={api_key}&id={id}&id2={id2}
```
```text
```

#### Adding a like
```html
POST /addLike?key={api_key}&id={id}&id2={id2}
```
```text
```

#### Removing a like
```html
POST /removeLike?key={api_key}&id={id}&id2={id2}
```
```text
```

#### Adding a block
```html
POST /addBlock?key={api_key}&id={id}&id2={id2}
```
```text
```
#### Removing a block
```html
GET /removeBlock?key={api_key}&id={id}&id2={id2}
```
```text
```

### Info API

#### Getting all available user information
```html
GET /getRelations?key={api_key}
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
GET /getRelation?key={api_key}&id={id}
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

#### Changing user avatar
```html
POST /changeAvatar?key={api_key}&id={id}&image={image_id}
```
```text
```