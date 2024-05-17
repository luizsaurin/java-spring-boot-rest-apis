<h1 align="center"><strong>Bundles</strong></h1>

This is an example of a Java Spring Boot API configured to respond to HTTP requests with messages in different languages. Some points of attention will be covered below.

&nbsp;

## **Motivations**

It is possible to say that the world of programming is almost completely in the English language. Documentation, educational materials, online courses, generally almost everything in English. Programming is no different, it is common to program in English so that the codes are understood by almost anyone in the world. And this is even more important in large companies, where there is a high probability that teams will have members from different countries.

However, situations may arise in which your API needs to be able to respond to messages in different languages, in addition to standard English. Fortunately, Spring offers native solutions to deal with this type of situation. This project exemplifies just that. In the case of projects where there is a separation between backend and frontend, it would perhaps be recommended that frontend applications be responsible for this. But in this case, this is a project that fits as a backend application and is capable of responding to HTTP requests with messages in different languages.

&nbsp;

## **Existing implementations**

Spring already has this implemented in some scenarios. An example is the MethodArgumentNotValidException error messages. Error messages will be sent in different languages if this exception is handled by a @RestControllerAdvice, and the originating HTTP request has the 'Accept-Language' header. In the case of this project, the following scenarios were implemented:

- Custom messages in the HTTP response sent by MainController
- Custom messages for the CustomErrorException

&nbsp;

## **Messages properties**

Custom messages were created in .properties files within the *src/main/resources/messages* directory. You can create as many translations as you want.

It is important to follow the pattern exemplified in this project. When creating a message file, follow the following naming pattern:

``messages_language_REGION.properties``

Example:

``messages_en_US.properties``

It is even possible to create files in .yml, but in the .properties format it is easier to find the occurrences of messages throughout the project.

&nbsp;

## **Accept-Language Header**

To use the translated message configuration, it is necessary to inform the 'Accept-Language' header in the HTTP request. If not informed, the API will send the message in standard English.

&nbsp;

## **BCP 47 Language Tags**

The 'Accept-Language' header accepts any value, and this can cause problems with the API functioning. To avoid this, it is recommended to use the standard convention known as the **IETF BCP 47** (Best Current Practice) which is commonly used for locale identifiers. BCP 47 is a standard for language tags and includes language, script, and region subtags. The BCP 47 language tag format is as follows:

- **Language subtag:** This is a 2-3 letter code representing the language, such as en for English or pt for Portuguese.
- **Script subtag** (optional): A 4-letter code representing the script, such as Latn for Latin script.
- **Region subtag:** A 2-letter code representing the country or region, such as US for the United States or BR for Brazil.

Examples of BCP 47 Tags:

- **en-US:** English as used in the United States
- **en-GB:** English as used in the United Kingdom
- **pt-BR:** Portuguese as used in Brazil
- **pt-PT:** Portuguese as used in Portugal
- **zh-CN:** Chinese as used in Mainland China (Simplified Chinese)
- **zh-TW:** Chinese as used in Taiwan (Traditional Chinese)

&nbsp;

## **How to run**

1. Run the Spring Boot project
1. Use the postman collection on the *resources/postman* folder to test the API

&nbsp;

## **Dependencies**

- Java JDK 17
- Maven 3.9.3+
