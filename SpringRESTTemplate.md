# Spring Boot Microservices Communication (RestTemplate or Webclient)


MyApp is a Spring Boot application consisting of two services, Service A and Service B. Both services use `RestTemplate` to communicate with each other. Service B fetches data from Service A using a REST endpoint.

## Project Structure

![RestTemplate](https://github.com/user-attachments/assets/5497a79f-a429-4c4b-87a9-389f269f4e9c)

## Components

### MyAppApplication
This is the main class to bootstrap the Spring Boot application.

### Configuration
- **RestClientConfig.java**: Configures a `RestTemplate` bean with a custom interceptor.

### Service A
- **ServiceAController.java**: Exposes a REST endpoint `/api/serviceA/data` that returns a simple string response.
- **ServiceAService.java**: Contains business logic for Service A.

### Service B
- **ServiceBController.java**: Uses `RestTemplate` to call Service A's endpoint `/api/serviceA/data`.
- **ServiceBService.java**: Contains business logic for Service B.

### Interceptors
- **RestTemplateHeaderModifierInterceptor.java**: Adds a custom header (`"Foo": "bar"`) to the HTTP response.

## How to Run

1. **Build the project:**
    ```sh
    mvn clean install
    ```

2. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

3. **Access Service A:**
    - Endpoint: `http://localhost:8080/api/serviceA/data`
    - This will return the response: `Hello from Service A!`

4. **Access Service B:**
    - Endpoint: `http://localhost:8080/api/serviceB/fetch-from-serviceA`
    - This will fetch the response from Service A and return: `Response from Service A: Hello from Service A!`

## Configuration

### application.properties
Configure application-specific properties here.

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Test

## Notes
- Ensure that both Service A and Service B are running on the specified ports. Adjust the URLs in the controllers if needed.
- For production use, consider adding error handling, logging, and security configurations.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
