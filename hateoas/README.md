<h1 align="center"><strong>HATEOAS</strong></h1>


Explaining what HATEOAS is in a simple way, it is the API's ability to inform the user of additional functionalities related to the responded content of the request.

&nbsp;

### **Example**

Let's assume that the user makes a request to consult a person's data, and receives the following response:

```
{
  "id": 1,
  "name": "John Doe",
}
```

In this example, HATEOAS is not being used. The API returns a simple JSON containing the ID and name of the person queried.

In the next example, the API is using HATEOAS, and along with the person's data, it returns a "links" field.

```
{
  "id": 1,
  "name": "John Doe",
  "links": [
	{
	  "rel": "self",
	  "href": "http://localhost/users/1"
	},
	{
	  "rel": "allUsers",
	  "href": "http://localhost/users"
  ]
}
```

The first link is "self", which describes the URL of the request itself.

```
{
  "rel": "self",
  "href": "http://localhost/users/1"
}
```

The second is the "allUsers" link, which describes the URL of another endpoint that allows the user to query information about all people registered in the API.

```
{
  "rel": "allUsers",
  "href": "http://localhost/users"
}
```

&nbsp;

### **Implementation**



There are different ways to implement HATEOAS in your API. The method I chose was to implement it in the Data Transfer Object (DTO) used in responding to requests.

First it is necessary to extend the **RepresentationModel** class from the *org.springframework.hateoas* package.

<div align="center">
	<img src="resources/img/dto-extends.png">
</div>

Then add the code with the "links" field and the desired tags inside the DTO class constructor.

<div align="center">
	<img src="resources/img/link-fields.png">
</div>

With these settings, each user will have the "self" and "allUsers" link in the response json.

<div align="center">
	<img src="resources/img/user1.png">
</div>

&nbsp;

## **Dependencies**

- Java JDK 17
- Maven 3.9.3+