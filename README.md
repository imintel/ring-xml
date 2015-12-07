# ring-xml

[Ring](https://github.com/ring-clojure) middleware functions for working with [XML](https://en.wikipedia.org/wiki/XML). 

Inspired by and modeled after the [ring-json](https://github.com/ring-clojure/ring-json) middleware. 

## Installation
Add the following to your project.clj: 

	[imintel/ring-xml "0.0.3"]

## Usage
The exported functionality of the library consists of only two simple functions. 

`wrap-xml-request` checks the content-type of an incoming request and if it is XML then parses the body. Returns *400 Bad Request* if the XML is invalid.

`wrap-xml-response` converts outgoing collections in the standard `{:tag nil :attrs {} :content}` format to XML. 

## License 
Copyright © 2013 IMIntel

Distributed under the MIT license. 