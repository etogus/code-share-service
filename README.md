# Code Sharing Service

## Description
A web-based code sharing service that allows users to share code snippets with optional time and view restrictions.

## Features
- Share code snippets with optional time and view restrictions
- View shared code snippets
- API endpoints for programmatic access
- Web interface for user interaction
- Syntax highlighting for shared code
- View latest unrestricted code snippets

## Technologies Used
- Java 11
- Spring Boot 2.3.3
- Spring Data JPA
- H2 Database
- FreeMarker Template Engine
- HTML/CSS/JavaScript

## Setup and Installation
1. Clone the repository
2. Navigate to the project directory
3. Build the project using Gradle
4. Run the application
5. Access the application at `http://localhost:8889`

## API Endpoints
- GET `/api/code/{uuid}` - Get a specific code snippet
- POST `/api/code/new` - Add a new code snippet
- GET `/api/code/latest` - Get the latest code snippets

## Web Endpoints
- GET `/code/{uuid}` - View a specific code snippet
- GET `/code/new` - Page to add a new code snippet
- GET `/code/latest` - View the latest code snippets

## Usage
1. To share a code snippet, go to `/code/new`, enter your code, and set optional time and view restrictions.
2. To view a shared snippet, go to `/code/{uuid}` where `{uuid}` is the unique identifier for the snippet.
3. To see the latest shared snippets, go to `/code/latest`.

## Future Improvements Ideas
- User authentication and personal code snippet libraries
- Commenting system for shared code snippets
