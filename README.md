# JavaSeleniumBDD

Created a Maven Project 

# DEPENDENCIES
--io.cucumber

--junit

--org.seleniumhq.selenium

--io.cucumber

--org.apache.maven.plugins   this is for JAVA 11

--org.seleniumhq.selenium

--net.masterthought      this is for Cucumber Reporting

# Download a Framework

Maven https://github.com/nurullahsahin44/JavaSeleniumBDD

# Writing a new Test

The cucumber features goes in the features library and should have the ".feature" extension.

You can start out by looking at features/Login.feature. You can extend this feature or make your own features using some of the predefined steps that comes with selenium-cucumber.

# Running Test

mvn test -Dcucumber.options="--help"

mvn test -Dcucumber.options="path of feature"

mvn test -Dcucumber.options="-t @tags"    "@test" is ready
