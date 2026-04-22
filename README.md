The design of my JAX-RS API is based on RESTful architecture style. I use different resources by exposing them via URI addresses. The resources are created via creating classes for specific resources that represent entities in an application (sensors, rooms, etc.) and annotated using the @Path annotation.

Such HTTP methods as GET, POST, PUT and DELETE are used to create functions that work with data. Incoming parameters are specified by using @PathParam annotation and @QueryParam annotation to define specific and optional resources correspondingly.

Specific data types in my JAX-RS API can be specified by using @Consumes and @Produces annotations. The clients should send and receive information in certain formats which can be defined by using these annotations. JSON is set as default in my application. Response objects are constructed by using Responses class.

To conclude, the JAX-RS API is designed clearly and functionally.


# STEP BY STEP INSTRUCTIONS FOR HOW TO BUILD PROJECT AND LAUNCH SERVER 

1. First Downlaod the Project and Extact the contents of the zipped file into a foldler

2. Open the project in NetBeans

3. To launch the server head over to the services tab next to where the prjoect has opened up and right click on servers to add a server.

4. Choose Apache Tomcat or TomEE as the Server

5. Locate the apache-tomcat-9.0.100 folder within the Project and set it as the Server Location

6. Set an appropriate username and password and click finish to set up the server

7. Go back to the Services tab and locate the newly Created Apache Tomacat Server right click it and press start

8. Enter your previously made username and password to launch the server. Once the server is running a green play symbol should appear next to the Apache Tomcat Server Icon.

9. Once the server is up and running, go back over to the projects tab, right click the project and press clean and build to compile the project and ensure its ready to be run on the server

10. Finally Right click the project and press run - now you're ready to send Requests!!!

# Sample Curl Requests:

1: Post Room: 
 
 curl.exe --% -X POST http://localhost:8080/w1908599SmartCampusCW/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"LIB-307\",\"name\":\"Library Room 307\",\"capacity\":50}"
 
 curl.exe --% -X POST http://localhost:8080/w1908599SmartCampusCW/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"ROOM-101\",\"name\":\"Study Room 101\",\"capacity\":55}"
 
 Output: Room Created

2: Get rooms: 

 curl.exe --% -X GET http://localhost:8080/w1908599SmartCampusCW/api/v1/rooms

 Output :[{"id":"LIB-307","name":"Library Room 307","capacity":50,"sensorIds":[]},{"id":"ROOM-101","name":"Study Room 101","capacity":55,"sensorIds":[]}]

3: Get specific room: 

 curl.exe --% -X GET http://localhost:8080/w1908599SmartCampusCW/api/v1/rooms/LIB-307
 
 Output: {"id":"LIB-307","name":"Library Room 307","capacity":50,"sensorIds":[]}

4: Post Sensor: 

 curl.exe --% -X POST http://localhost:8080/w1908599SmartCampusCW/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"TEMP-002\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":21.5,\"roomId\":\"ROOM-101\"}"
 
 Output: Sensor Created 

5: Return Sensors:

 curl.exe --% -X GET http://localhost:8080/w1908599SmartCampusCW/api/v1/sensors
 
 Output: [{"id":"TEMP-002","type":"Temperature","status":"ACTIVE","currentValue":21.5,"roomId":"ROOM-101"}]


# W1908599 Client-Server Architectures - Coursework (2025/26) REPORT

Q1 Project & Application Configuration (5 Marks):
In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

ANSWER: JAX-RS uses a per request lifecycle for resources which means unless they’ve been specifically marked with the ‘@Singleton’ annotation, a new instance of a resource class is instantiated for every request. This helps to prevent race conditions as it prevents state sharing in resources. (every request has its own instance variables) Because of this it’s important that shared data structures are kept separate from resource classes as if they aren’t, any shared data structures stored within the resource itself would be recreated for every request, leading to data loss. To tackle this problem we create a model layer to store shared in-memory data structures which runs over the lifetime of the application ensuring that data isn’t lost in-between requests and we ensure the methods which interact with these objects are atomic to prevent race conditions. – where two sources try to update/access the same data at the same time. This is especially relevant when data is shared and mutable like in this project as it can lead to data loss. (for example if two request run together at the same time to increment a value one update can be lost) 
{singletons resources can be used to prevent rebuilding heavy resources and for paths which hold non mutable read only data}

Q2 The ”Discovery” Endpoint (5 Marks):
Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

ANSWER: HATEOAS (Hypermedia As The Engine Of Application State) means that API responses include links to related actions/resources, so the client can dynamically navigate the API. The reason this is considered a hallmark of the RESTful design is because, it allows client developers to actively navigate the API through the use of dynamic discovery without the need of consulting documentation to do so. This allows for more efficient client side development as the client relies less on the server developers to gain an understanding of the API (decoupling). 



Q3 Room Resource Implementation (10 Marks):
When returning a list of rooms, what are the implications of returning only
IDs versus returning the full room objects? Consider network bandwidth and client side
processing.

ANSWER: When returning a list of rooms more network bandwidth is used due to the larger size of the response however in terms of client side processing there will be less work needed to be done overall as the client receives all the necessary information needed from the initial request. In comparison if a list of strings were returned whilst there may be less bandwidth used for the requests the client may need to make extra request if more information about the rooms is required which will increase overall network traffic.



Q4 Room Deletion & Safety Logic (10 Marks):
Is the DELETE operation idempotent in your implementation? Provide a detailed
justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

ANSWER: An operation is defined as idempotent when performing that same operation multiple times has the same effect as performing it once. Delete operations are idempotent because they result in the same resource state. Once a room is deleted in my implementation the room object get removed from the database and further attempts to delete will not affect the database or resource state. Even though the responses may differ between the deletions requests (200 Upon successful deletion and 404 when room cant be found anymore) since the resource state remains the same after every request my implementation is idempotent.


Q5: Sensor Resource & Integrity (10 Marks):
We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on
the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?


ANSWER: When a POST method is annotated with a @Consumes annotation it means that the method will only take request bodies whose content-type header match the type declared. If the request body format doesn’t match the content header then JAX-RS rejects the request and sends a 415 Unsupported Media Type response.




Q6 Filtered Retrieval & Search (10 Marks):
You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why
is the query parameter approach generally considered superior for filtering and searching collections?

ANSWER: The query parameter is generally superior for filtering and searching collections because it’s easier to combine multiple search filters within a single method. In comparison this approach /api/v1/sensors/type/CO2 makes the filter appear as a part of the resource hierarchy which is incorrect from a RESTful design perspective as filtering does NOT represent a new resource. This approach requires a lot more code to implement the filtering of a collection and query parameters are a better use in this context as they allow for the filters to be applied as filtering criteria in the URL without creating extra paths.

Q7 The Sub-Resource Locator Pattern (10 Marks): 
Discuss the architectural benefits of the Sub-Resource Locator pattern. How
does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

ANSWER: One of the main benefits of the Sub-Resource Locator pattern is that it promotes separation of concerns allowing for improved readability and more modular code which is key especially when dealing with large and complex APIs. With this Sub-Resource Locator pattern above each resource oversees its own entities. The parent handles Sensor objects whilst the child handles the related sensor readings, by using this architectural pattern to delegate sensor logic to a different resource, we improve scalability as we are able to add more sub resources at will instead of expanding on a massive hard to understand controller class furthermore, the separation of responsibility improves testability, as sub resource classes can be tested independently to the main resource.


Q8 Dependency Validation (422 Unprocessable Entity) (10 Marks): 
Why is HTTP 422 often considered more semantically accurate than a standard
404 when the issue is a missing reference inside a valid JSON payload?

ANSWER: Here HTTP 422 is often considered more semantically accurate than a standard
404 as the request itself is valid but the data inside the request is invalid leading to failure. HTTP 422 Unprocessable Entity depicts this error more accurately than a standard 404 Not Found. 

  

Q9 The Global Safety Net (500) (5 Marks):
From a cybersecurity standpoint, explain the risks associated with exposing
internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

ANSWER: The internal java stack trace is designed for developers to find errors within the application. When exposed, information such as files & project structure as well as methods and class names can be used by attackers to map out the API (reconnaissance) allowing them to plan more precise attacks. Additional information which could be leaked include the implemented framworks, eg JAX-RS, Tomcat. Again, the attacker could then use this information to search for known weaknesses within such technologies.







Q10 API Request & Response Logging Filters (5 Marks):
Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?

ANSWER: Its beneficial to use JAX-RS filters for cross-cutting concerns like logging as, it consolidates cross-cutting behaviour, reducing code duplication across resources this promotes separation of concerns and allows for better code structure.


