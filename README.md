# TComplexOfficeRental Project

This project is a web application for managing office rentals at the TComplex building. Below are the details of the commands available in the `Makefile` to facilitate the development, testing, and deployment of the project.

Link debug: http://localhost:8080/TComplexOfficeRental-1.0-SNAPSHOT/

## Available Commands

### `make clean`
- **Description**: Removes the compiled classes and other build artifacts.
- **Usage**: This command is useful to clean the workspace before starting a new build, ensuring that no stale files are used.
- **Command**:
```bash
make clean
```

### `make build`
- **Description**: Compiles the project and packages it into a WAR (Web Application Archive) file.
- **Usage**: This is a critical step before deploying the application. It ensures that the code is compiled without errors and is packaged correctly.
- **Command**:
```bash
make build
```

### `make deploy`
- **Description**: Deploys the WAR file to the Tomcat server.
- **Usage**: After building the project, use this command to deploy the application to the Tomcat server for testing or production.
- **Command**:
```bash
make deploy
```

### `make run`
- **Description**: Starts the Tomcat server.
- **Usage**: Use this command to start the server and run the web application locally. Ensure that the Tomcat server is configured correctly.
- **Command**:
```bash
make run
```

### `make stop`
- **Description**: Stops the Tomcat server.
- **Usage**: Use this command to stop the Tomcat server when it is no longer needed or before restarting it.
- **Command**:
```bash
make stop
```

### `make db-setup`
- **Description**: Sets up the database by running the SQL script located in the `db/setup.sql` file.
- **Usage**: Use this command to initialize or reset the database. This is essential for setting up the environment with the required schema and data.
- **Command**:
```bash
make db-setup
```

## Usage Instructions

1. **Initialize the Project**:
	- Run `make db-setup` to set up the database.
	- Ensure the Tomcat server is configured and running.

2. **Build and Deploy**:
	- Run `make build` to compile and package the application.
	- Use `make deploy` to deploy the application to Tomcat.

3. **Running the Application**:
	- Start the server using `make run`.
	- Access the application through the browser.

4. **Cleaning the Workspace**:
	- Use `make clean` before rebuilding to ensure a fresh start.

5. **Stopping the Server**:
	- Run `make stop` to stop the Tomcat server.


