# Environment
  1. Java >= 1.8.0_261

  2. Maven >= Apache Maven 3.6.1

# Framework Introduction
1. This tiny framework is based on rest assured and testng
2. Data driven framework


# Project structure
![alt text](https://github.com/kettlescott/TestAPIProject/blob/master/Automation.jpg)

# Update configuration.xml
Configuration is under ./configuration.xml
```
<?xml version="1.0" encoding="utf-8"?>
<configuration>
    <appSettings>
        <add key="API_KEY" value="325f3901-e6c1-47ca-b858-5cb09cd57895" />
    </appSettings>
</configuration>
```

# Update Testsuite.xml
Configuration is under ./configuration.xml
```
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="TestSuite">
    <test name="HappyPath" >
        <classes>
            <class name="com.scott.api.test.EndToEndPositiveTest">
            </class>
        </classes>
    </test>
    <test name="Negative Test" >
        <classes>
            <class name="com.scott.api.test.NegativeLocationTest">
            </class>
            <class name="com.scott.api.test.NegativeServiceTest">
            </class>
            <class name="com.scott.api.test.NegativeCalculateCostTest">
            </class>
        </classes>
    </test>

</suite>
```


# Test
1. Happy Path is under EndToEndPositiveTest.java

2. Negative test is under NegativeCalculateCostTest.java ,NegativeLocationTest.java ,NegativeServiceTest.java


# Test Data
Test Data is under /src/test/resources

# Coding Style
1.The java project follows google'java coding format, for detail please refer to [Google Java Format!](https://github.com/google/google-java-format)

2.Run mvn git-code-format:format-code -Dgcf.globPattern=**/* to check and format java code

# Build and Test
1.mvn clean install -DskipTests

2.Run ```mvn test -Dsurefire.suiteXmlFiles=".\Testsuite.xml" -DconfigFilePath=".\configuration.xml"```

3.test report is under .\target\surefire-reports


