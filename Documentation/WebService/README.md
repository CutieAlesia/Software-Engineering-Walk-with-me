## WebService API Documentation

### Framework

[Spring Framework](https://spring.io/) --- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
___
## API Documentation

### API Call URLs
> https://localhost:8080/api/

> https://185.194.217.213:8080/api/

### API layout
> /api/{version}/{module}/{function}?{api_key}{parameters}
___
### User API (_/api/v1/users_)

#### Creating a new user
```html
POST /newUser?{api_key}&username={username}&password={password}
```
```text
```
#### Deleting a user
```html
POST /deleteUser?{api_key}&id={id}
```
```text
```
#### Getting all users
```html
POST /getUsers?{api_key}
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
POST /findByID?{api_key}&id={id}
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
POST /findByUsername?{api_key}&username={username}
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
POST /findByEmail?{api_key}&email={email}
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
POST /changeEmail?{api_key}&id={id}&email={new_email}
```
```text
```
#### Changing user password
```html
POST /changePassword?{api_key}&id={id}&password={new_password}
```
```text
```
### Relation API

#### Getting a relationship
```html
GET /getRelation?{api_key}&id={id}&id2={id2}
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
GET /createRelation?{api_key}&id={id}&id2={id2}
```
```text
```

#### Adding a like
```html
POST /addLike?{api_key}&id={id}&id2={id2}
```
```text
```

#### Removing a like
```html
POST /removeLike?{api_key}&id={id}&id2={id2}
```
```text
```

#### Adding a block
```html
POST /addBlock?{api_key}&id={id}&id2={id2}
```
```text
```
#### Removing a block
```html
GET /removeBlock?{api_key}&id={id}&id2={id2}
```
```text
```

### Report API