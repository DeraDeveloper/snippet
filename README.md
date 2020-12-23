# snippet manager

- This application accepts a post request and saves it in memory with an API response that consists of the saved request and a url to access the request.
- As an additional feature, a third API updates the likes on a snippet.

### Tools

- This application uses [Spring Boot Web Starter](https://spring.io/guides/gs/spring-boot/)
- This Language of implementation is Java 1.8
- This application uses Maven for building and dependency management

### Application Structure

- Source files are located under `/src/main/java/com/storage/snippet/`
- For simplicity, The host base url is set in `application.properties` you can always change this to your desired host.

### Error Handling

- Custom error classes are located under `/src/main/java/com/storage/snippet/exceptions` 
- Errors are handled using a Controller Advice. Spring maps errors to thir specific error handling methods in the `ControllerAdvice.java` class

### Running the app

The application can be found at `https://repl.it/@ChideraNwoke/snippet` simply Press the "Run" button(this has been configured with the the terminal command `mvn spring-boot:run`

-- OR -- 

Usually, a browser window will appear above the terminal, showing you the output. You may copy the URL and open it in a new browser tab to change the URL, e.g. adding `/snippets` to the end of the URL to create a snippet or `/snippets/{name}`to retrieve a snippet ({name} is the name of the snippet that was created)

### Endpoints

The project has three endpoints all defined in the `SnippetController`:

- `POST /snippets` - a POST endpoint that takes in a JSON object mapped to a `SnippetCreationRequest` object
- `GET /snippets/{name}` - a GET endpoint that takes a snippet `name` String parameter and returns a JSON `SnippetCreationResponse` object
- `POST /snippets/{name}/like` - a POST endpoint that takes a snippet `name` String parameter, increments the like on the `snippet` object, and returns a JSON `SnippetCreationResponse` object

### Production Concerns

- Validations and null checks were carried out in `SnippetManagementServiceImpl`
- To keep things simple, errors are printed on the terminal
