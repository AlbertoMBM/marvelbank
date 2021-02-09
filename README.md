# marvelbank

MarvelCharacters:

Architecture:
This project has been built with a clean architecture with MVVM patter design, with 3 different
modules, which connection is: app --> domain <-- data.

Since the domain layer does not see the rest of the layers, it communicates with data layer through
interfaces of the repository pattern, and with the Either object towards the view layer.

Dependency injection:
I have used koin as a dependency injector, having a Koin module for each layer in the project.

To load the images, I have used the coil library.

The recyclerView of the Marvel characters, load the first 20 elements from the web service. When the
user scrolls down to the bottom, there is another call to the web service to load the next 20
elements.

Navigation is done through the navigation component.

There is only one use case in the app, and the dataSource calls the web service in one way
or another (to collect all the characters or the detail of a specific one), through the parameters
that are passed in the flow.

There is an interceptor which modify the request to add the parameters of the necessary keys to
the API.

To manage the JSON of the responses of the calls to the API I have used Moshi.