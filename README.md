# gwt-boot-sample-basic

Basic HelloWorld example for GWT

# Structure of the Maven Project

![Maven Project Structure](gwt-boot-sample-maven-structure.png?raw=true "Maven Project Structure")

# Starting GWT in SuperDev Mode

The application _[gwt-boot-sample-basic](https://github.com/lofidewanto/gwt-boot-sample-basic)_ 
uses integrated Jetty server from GWT to deliver the HTML host file. 
This can be done with other Servlet apps as well. This webapp also uses [GWT Boot modules](https://github.com/gwtboot/gwt-boot-modules).

## Step 1 - Run GWT DevMode to automatically compile the code

First generate the GWT Module Descriptor and then run the GWT Dev Mode 
in SuperDev mode to be able to compile the Java code to JavaScript code 
on reload in the web browser. In Maven you can run following command:

```java
mvn gwt:generate-module gwt:devmode
```

You can just generate the module once and after that just run:

```java
mvn gwt:devmode
```

![GWT Development Mode](gwt-boot-sample-development-mode.png?raw=true "GWT Development Mode")

## Step 2 - Run the App in your Browser

Now you can copy&paste the "Copy to Clipboard" result of the GWT Development Mode UI above. Run it on:

```java
http://localhost:8888/basic
```

Just reload your web app and GWT SuperDev mode will transpile your
Java code to JavaScript on the fly. That's it, now you can develop 
your web app with GWT incrementally and fast! 

## Step 3 - Debug the App in your Browser

You can debug the Java code on the browser with the help of source maps. In this example we use Google Chrome.

![GWT Debug Chrome](gwt-boot-sample-debugging.png?raw=true "GWT Debug Chrome")

Enjoy! 
