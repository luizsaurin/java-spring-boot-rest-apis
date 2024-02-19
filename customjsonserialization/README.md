<h1 align="center"><strong>Custom JSON Serialization</strong></h1>

<p>This project is an example of a custom json serialization implementation. The objective is to configure a way to customize the fields in the request response json, changing their order, name and visibility.</p>

<p>Two implementations were used:</p>

<h3><b>1 - Annotations on the response object</b></h3>

<p>This method consists of using @Json annotations from the <i>com.fasterxml.jackson.annotation</i> package in the fields of the User class, which is the object returned in the request response.</p>

<p>In the example below, the following settings were made:</p>

<ol>
	<li>Fields order</li>
	<li>Fields names, from default Camel Case to Snake Case</li>
	<li>Hidden password field</li>
</ol>

<div align="center">
	<img src="resources/img/user1.png">
</div>

&nbsp;

<p>This is the json resulting from this configuration:</p>

<div align="center">
	<img src="resources/img/json1.png">
</div>

&nbsp;

<h3><b>2 - Using a Data Transfer Object (DTO)</b></h3>

<p>This method consists of using an object that represents the same structure as the User class, but being able to customize how and which data will be displayed. This DTO object will be sent in the response, allowing you to keep the User model class unchanged.</p>

<p><i>In this example I am using Records from Java 17. However, it is possible to do the same thing with conventional Java classes.</i></p>

<p>The configuration applied in this example is the same as that applied in the previous example.</p>

<div align="center">
	<img src="resources/img/user2.png">
</div>

&nbsp;

<div align="center">
	<img src="resources/img/user2dto.png">
</div>
<p>This is the json resulting from this configuration:</p>

<div align="center">
	<img src="resources/img/json2.png">
</div>

&nbsp;

<p>It was possible to obtain the same response json using both configurations.</p>