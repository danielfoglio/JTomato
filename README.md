JTomato
=======

Rotten Tomatoes API Java Client 

-Put your API key in /res/tomatoes.properties

-Tests are grouped in two categories: (1) tests that use the network and actully use the API, and (2) local tests that rely on a mock-up of the API.

-This is a beta-version, please report to me any bug: tambug@gmail.com

-For Android developers:

1.Export the JTomato project as a jar package

2.Add in your libs folder of your Android project: the jar of JTomato and the gson-2.2.3.jar ( http://tinyurl.com/brchw84 )

3.Put the INTERNET permission in your manifest

4.Use JTomato asynchrounously w.r.t. to the UI thread (e.g., asynctask).


