## WebService API Documentation

### Framework
For this project we're using the Spring framework for our backend. It comes with a ton 
of feature packed libraries that include a tomcat webserver or a security library that we use
for oAuth authentification.

[Spring Framework](https://spring.io/) --- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
___
## API Documentation

### API Call URLs
> https://localhost/api/

> https://walkwithme.com/api/

### API layout
> /api/{api_key}/{version}/{module}/{function}{parameters}
___
### User API (_/api/{key}/v1/users_)

#### Creating a new user
```html
POST /newUser?username={username}&password={password}
```
```text
"done"
```
#### Deleting a user
```html
POST /deleteUser?id={id}
```
```text
"done"
```
#### Getting all users
```html
POST /getUsers
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
POST /findByID?id={id}
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
POST /findByUsername?username={username}
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
POST /findByEmail?email={email}
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
POST /changeEmail?id={id}&email={new_email}
```
```text
"done"
```
#### Changing user password
```html
POST /changePassword?id={id}&password={new_password}
```
```text
"done"
```
### Relation API

### Report API