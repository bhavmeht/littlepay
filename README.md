# Billing Customer on trip details
Billing software is to allow LittlePay to bill customers for their trasits between stops.

Before boarding a bus at a bus stop, passengers tap their credit card (identified by the PAN, or Primary Account Number)
on a card reader. This is called a tap on. When the passenger gets off the bus, they tap their card again.
This is called a tap off. The amount to charge the passenger for the trip is determined by the stops where the passenger tapped on and tapped off. 

The amount the passenger will be charged for the trip will be determined as follows:
- Trips between Stop 1 and Stop 2 cost $3.25 
- Trips between Stop 2 and Stop 3 cost $5.50 
- Trips between Stop 1 and Stop 3 cost $7.30

Note that the above prices apply for travel in either direction (e.g. a passenger can tap on at stop 1 and tap off at stop 2,
or they can tap on at stop 2 and can tap off at stop 1. In either case, they would be charged $3.25).
### Completed Trips
If a passenger taps on at one stop and taps off at another stop, this is called a complete trip. The amount the passenger will be charged for the trip will be determined based on the table above. For example, if a passenger taps on at stop 3 and taps off at stop 1, they will be charged $7.30.
### Incomplete trips
If a passenger taps on at one stop but forgets to tap off at another stop, this is called an incomplete trip. The passenger will be charged the maximum amount for a trip from that stop to any other stop they could have travelled to. For example, if a passenger taps on at Stop 2, but does not tap off, they could potentially have travelled to either stop 1 ($3.25) or stop 3 ($5.50), so they will be charged the higher value of $5.50.
### Cancelled trips
If a passenger taps on and then taps off again at the same bus stop, this is called a cancelled trip. The passenger will not be charged for the trip.

- **taps.csv** file that contain user transaction details are kept under: ```./src/main/resources``` folder
- **trips.csv** output file which contains user charges will be created under ```resource``` folder. This file will be recreated on every run, by wiping previous results.


# Assumptions for this project:
- Input CSV is well-formed. It will not have invalid or missing data.
- Duration for trip is not time bounded. User can tap ON today get off stop and Tap off tomorrow in 2nd transit.

# Pending items / TODO:
- Include loggers for logging in project.
- Extend test coverage/scenarios.
- CSV Writer utility is not outputting content as expected. Requires beautification of output file.
- Include more functional way of coding.

[Build Project]
----
$ gradlew build

----

[Run Test]
----
$ gradle test

----

== Customize the library JAR

You will often want the name of the JAR file to include the library _version_.
This is achieved by setting a top-level `version` property in the build script:

.build.gradle
[source.multi-language-sample,groovy]
----
version = '1.0.0'
----

Now run the `jar` task:

[Build Jar]
----
$ ./gradlew jar

----