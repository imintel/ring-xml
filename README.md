# ring-xml

Ring middleware functions for working with XML. 

## Installation
Add the following to your project.clj: 

	[imintel/ring-xml "0.0.1"]

## Usage
The exported functionality of the library consists of only two simple functions.  

`wrap-xml-request` checks the content-type of an incoming request body and parses it if it is XML.  

`wrap-xml-response` converts outgoing collections in the standard `{:tag nil :attrs {} :content}` format to XML. 

## License 
Copyright © 2013 IMIntel

Distributed under the MIT license. 