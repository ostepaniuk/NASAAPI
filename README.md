# README #

A pilot test framework to retrieve and compare images from NASA API.

### Requirements ###

* Make sure to have the latest Java version installed. (Java 1.8 or above)
* Intellij IDEA

### Setup ###

* Clone the project to your local machine (git clone https://github.com/ostepaniuk/NASAAPI.git)
* Open project in the compatible IDE
* Import required dependencies unless it has been done automatically by the IDE (dependencies are located in pom.xml file in the root of the project)

### Usage ###

#### Run Tests
* Run all tests from Test > Java folder

#### Change data
* To run tests with different data by new time (Mars and/or Earth time), go to CompareImagesdataTest.java, search for line 24 or 26, change constants according to required format to retrieve different data: `SOLS` for mars time, `DATE` for earth time
* Tu run the CompareNumberOfPhotosTakenByCamerasTest test on different `SOLS` date and `CAMERA_PHOTOS_LIMIT` to change the limit of photos taken by each camera

### Clariications ###

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

Should you have any questions or seek further clarifications, please feel free to contact at stepaniukalex@gmail.com