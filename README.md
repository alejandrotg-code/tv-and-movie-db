# 🎬 TV & Movie DB

A movie and TV show recommendation platform with personalized suggestions based on user ratings and favorites.

## ✨ Features

- 🔍 Search movies and TV shows via TMDB API
- ⭐ Rate and review content
- ❤️ Save favorites
- 🤖 Personalized recommendations based on your taste
- 🔐 JWT Authentication & Authorization

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- TMDB API

## 📋 API Endpoints

### Users
| Method | URL | Description |
|--------|-----|-------------|
| GET | /users | Get all users |
| GET | /users/{id} | Get user by ID |
| POST | /users | Create account |
| PUT | /users/{id} | Update account |
| PUT | /users/{id}/deactivate | Deactivate account |
| DELETE | /users/{id} | Delete account |

### Content
| Method | URL | Description |
|--------|-----|-------------|
| GET | /content/search?q= | Search via TMDB |
| GET | /content/trending | Get trending content |
| GET | /content/{id} | Get content details |

### Favorites
| Method | URL | Description |
|--------|-----|-------------|
| GET | /favorites/user/{id} | Get user favorites |
| POST | /favorites | Add favorite |
| DELETE | /favorites/{id} | Remove favorite |

### Ratings
| Method | URL | Description |
|--------|-----|-------------|
| GET | /ratings/user/{id} | Get user ratings |
| GET | /ratings/content/{id} | Get content ratings |
| POST | /ratings | Add rating |
| PUT | /ratings/{id} | Edit rating |
| DELETE | /ratings/{id} | Delete rating |

## 🚀 Setup

1. Clone the repository
2. Create `application-local.properties` in `src/main/resources`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tv_and_movie_db
spring.datasource.username=your_username
spring.datasource.password=your_password
tmdb.api.key=your_tmdb_api_key
```
3. Run with `local` profile in IntelliJ
4. API available at `http://localhost:8080`

## 👤 Author

Alejandro — DAM Developer | AI & Big Data Student

## 🚧 Status

Under active development