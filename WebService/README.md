# WebService Module
___
### Module Info
![](https://img.shields.io/badge/Java%20JDK-17-orange?style=for-the-badge)
![](https://img.shields.io/badge/Build%20&%20Deployment-Maven-purple?style=for-the-badge)

![](https://img.shields.io/badge/Package-org.acmseproject.WebService-green?style=for-the-badge)
___
### Master
![](https://img.shields.io/github/last-commit/Kushurando/Software-Engineering-Walk-with-me?style=for-the-badge)
### Dev
![](https://img.shields.io/github/last-commit/Kushurando/Software-Engineering-Walk-with-me/dev?style=for-the-badge)
___
### Statistics
![](https://img.shields.io/tokei/lines/github/Kushurando/Software-Engineering-Walk-with-me?style=for-the-badge)
![](https://img.shields.io/github/repo-size/Kushurando/Software-Engineering-Walk-with-me?style=for-the-badge)

![](https://img.shields.io/github/issues-raw/Kushurando/Software-Engineering-Walk-with-me?style=for-the-badge)
![](https://img.shields.io/github/issues-pr-raw/Kushurando/Software-Engineering-Walk-with-me?style=for-the-badge)
___
# API Documentation


### HTTPS {GET}
> __/api/v1__
>
> > Return format: JSON
___
##### User
> __/user/getUsers__
> > Returns all users in the DB (password excluded)
>
> __/user/getById?id={%id%}__
> > Returns the user with the given integer ID
>
> __/user/getByUsername?username={%username%}__
> > Returns the user with the given username
> 
> __/user/getByEmail?email={%email%}__
> > Returns the user with the given email
___
### API Example
> HTTP Request
> >GET http://localhost:8080/api/v1/user/getUsers
>
> Response
> >[
{
"id": 1,
"username": "admin",
"password": "#zubat74#",
"email": "admin@testUser.de",
"create_time": "2021-11-04T21:02:01"
},
> 
> >{
"id": 2,
"username": "testUser2",
"password": "testPW1234",
"email": "user@testUser.com",
"create_time": "2021-11-04T21:39:54"
}
]