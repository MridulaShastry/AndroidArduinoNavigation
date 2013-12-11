AndroidArduinoNavigation
========================
This project is intended to display real time turn-by-turn instructions to the bicycle rider on an arduino interfaced
lcd panel. These instructions are obtained by an android application running on an android phone.
The MainActivity obtains the destination from the rider.Based on the current location obtained from gps,it calculates the 
route to the destination and outputs only the step instruction ie the next navigation instruction to the rider.
The current location of the rider is obtained by using gps in the GPSTracker class.
StepRoute class constructs a url using google maps by obtaining the current location of the user and the destination.By using this,
we obtain the route from source to destination every minute in the form of http response from the server.The route is obtained
in the json format. The obtained json is parsed to get only the first step instruction representing the next navigation instruction to the rider.
This application is particularly written for bicycle routes
Further details of this project will be available in a blog link which will be published shortly


