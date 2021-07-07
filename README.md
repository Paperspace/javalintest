# javalintest

## Prereqs:
- Apache Maven 3.8.1
- Java 8

## Build
```
mvn assembly:single
```

## Run
```
java -cp target/javalintest-1.0-jar-with-dependencies.jar Main
```

## Test
Open a link to http://localhost:7000/


## Project layout generation from scratch
This project was originally created via the maven-archetype-quickstart project layout 
via this command:
```
mvn archetype:generate -DgroupId=com.paperspace.javalintest -DartifactId=javalintest -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Then the class name, maven goals, and project tree were reorganized as follows
1. Move src/main/java/com/paperspace/javalintest/App.java to src/main/java/Main.java
2. Edit src/main/java/Main.java
<ol>
a. Remove "package com.paperspace.javalintest;" from src/main/java/com/paperspace/javalintest/App.java

b. Change "public class App" to "public class Main"

c. Add additional import, and additional logic to main method:
</ol>

```
+import io.javalin.Javalin;

 public class Main
 {
     public static void main( String[] args )
     {
         System.out.println( "Hello World!" );
+        Javalin app = Javalin.create().start(7000);
+        app.get("/", ctx -> ctx.result("Hello World"));  
```

3. Remove unused paths/tests:  rm -rf src/main/java/com
4. Edit pom.xml
  a. Make the following changes:
```
--- a/pom.xml
+++ b/pom.xml
@@ -6,16 +6,14 @@
 
   <groupId>com.paperspace.javalintest</groupId>
   <artifactId>javalintest</artifactId>
   <groupId>com.paperspace.javalintest</groupId>
   <artifactId>javalintest</artifactId>
-  <version>1.0-SNAPSHOT</version>
+  <version>1.0</version>
 
   <name>javalintest</name>
-  <!-- FIXME change it to the project's website -->
-  <url>http://www.example.com</url>
 
   <properties>
     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
-    <maven.compiler.source>1.7</maven.compiler.source>
-    <maven.compiler.target>1.7</maven.compiler.target>
+    <maven.compiler.source>1.8</maven.compiler.source>
+    <maven.compiler.target>1.8</maven.compiler.target>
   </properties>
 
   <dependencies>
@@ -25,11 +23,49 @@
       <version>4.11</version>
       <scope>test</scope>
     </dependency>
+    <dependency>
+      <groupId>io.javalin</groupId>
+      <artifactId>javalin</artifactId>
+      <version>3.13.9</version>
+    </dependency>
+    <dependency>
+      <groupId>org.slf4j</groupId>
+      <artifactId>slf4j-api</artifactId>
+      <version>1.8.0-beta4</version>
+    </dependency>
+    <dependency>
+      <groupId>org.slf4j</groupId>
+      <artifactId>slf4j-simple</artifactId>
+      <version>1.8.0-beta4</version>
+    </dependency>
   </dependencies>
 
   <build>
     <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
       <plugins>
+        <plugin>
+          <artifactId>maven-assembly-plugin</artifactId>
+          <executions>
+            <execution>
+              <id>make-assembly</id> <!-- this is used for inheritance merges -->
+              <phase>package</phase> <!-- bind to the packaging phase -->
+              <goals>
+                <goal>single</goal>
+              </goals>
+            </execution>
+          </executions>
+          <configuration>
+            <descriptorRefs>
+              <!-- This tells Maven to include all dependencies -->
+              <descriptorRef>jar-with-dependencies</descriptorRef>
+            </descriptorRefs>
+            <archive>
+              <manifest>
+                <mainClass>Main</mainClass>
+              </manifest>
+            </archive>
+          </configuration>
+        </plugin>
         <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
         <plugin>
           <artifactId>maven-clean-plugin</artifactId>
```

This will change the maven goals to allow the build of an assembly that has all the depedencies included in the same jar file.

To build the assembly run:
```
mvn assembly:single
```

To run the application execute:
```
java -cp target/javalintest-1.0-jar-with-dependencies.jar Main
```