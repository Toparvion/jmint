[![Maven Central](https://maven-badges.herokuapp.com/maven-central/tech.toparvion/jmint/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/tech.toparvion/jmint)

# Overview

## What is jMint?
*jMint* is a tool for modifying methods of a running Java application without changing its source code. jMint key features are:
+ modification is expressed in ordinary Java source code - *no byte code knowledge is required*;
+ modification can be both extending and altering in relation to target method body;
+ both application and third-party (library) code can be modified.

:information_source: In a nutshell jMint is a wrapper around [Javassist](http://jboss-javassist.github.io/javassist/) byte code manipulation library. The latter is used to compile source code and modify byte code whilst jMint itself exposes developer-friendly interface and preprocesses the input data.  
:warning: jMint is not a hacking tool and therefore doesn't contain any facilities to break the protection of classes (if any). It also knows nothing about legal aspects so that before deploying a modified application or library please make sure you do not violate its license.

## What is it for?
Typical use cases of *jMint* include (but not restricted to) testing stage when some custom behavior should not (or even can not) be included into the source code. For example:
- to reproduce complicated test cases by injecting side effects right into the running app;
- to include simple stubs/mocks into the app at runtime;
- to prevent undesired scenario;
- to add some logging to where it wasn't provided initially;
- to fix a bug in a library without having its source code, etc
- _(welcome to add yours one:wink:)_ 

## How does it work?
jMint operates as Java agent - special kind of application that is launched by JVM and is able to modify byte code of classes being loaded by JVM.
* First, **jMint** loads and parses the modifying behavior in the form of **_droplet_** - Java source file describing the target of modification (class, method, etc) and the modifying code itself _(see next section)_.
* Then, at the time of target class loading the modifying code gets compiled against the class and **jMint** passes it to JVM alongside with the original byte code just like if it was loaded in usual way.

## What is droplet?
**Droplet** *(in the sense of injection)* is an ordinary Java source code file that is used by **jMint** to find out 2 things:

1. The target of modification - a place in the application where the modification must be applied;
2. The modifying code itself.

Droplets look like all other Java type definitions (classes, interfaces, enums) but in fact they are not. Their classes' and methods' signatures do not define anything new, instead they just specify the target of modification.  
:bulb: _**Example.**_ If you have class `com.example.coolapp.Saver` with method `private void save(BusinessEntity entity)` and you want to prepend it with dumping of argument to standard output then your droplet may look like (*Saver.java*):
```java
package com.example.coolapp;
import com.example.coolapp.model.BusinessEntity;
class Saver {
  /** @cutpoint BEFORE */
  void save(BusinessEntity entity) {
    System.out.println("Entity to save: " + entity.toString());
  }
}
```
This droplet doesn't define a new method `save`. Instead it instructs jMint to find method `com.example.coolapp.Saver#save(com.example.coolapp.model.BusinessEntity)` during class loading and to inject the specified code right before the body of the method.

***
The place of insertion relative to target method body is called **cutpoint** and is specified via custom javadoc tag `@cutpoint` which can take one of 3 values:
- `BEFORE` to inject the code right before the target method body.  
_Generally used for manipulating method arguments (including their change) and for preventing execution of the body (possibly depending on arguments values);_
- `INSTEAD` to completely replace the target method body with a new one.  
_Generally used to stub the whole method or to modify just a piece of code buried inside the body._
- `AFTER` to append some code to the end of the target method body.  
*Generally used to manipulate the result value (including its change). When writing modifying code against `AFTER` cutpoint there is synthetic variable `$_` available which holds current result value. If the result type is `void`, then the type of `$_` is `Object` and the value of `$_` is `null`. There is also `$r` variable which represents the result type (return type) of the method. It is intended to be used as the cast type in a cast expression.*  
When used without parameters, `AFTER` cutpoint behaves like ordinary code appended to the end of target method body. This means that in case of exception such code won't be executed. If you want it to be executed anyway (like `finally` block) just add `asFinally` parameter right after the cutpoint declaration:

```java
    /**
     * @cutpoint AFTER AS_FINALLY
     */
    public void actionPerformed(ActionEvent event) {
```
Note that the parameter may be specified in various forms: `asFinally`, `AS_FINALLY`, `as-finally` or even `as finally`.  
:bulb: There is also auxiliary `IGNORE` cutpoint which is applied by default to all methods with no explicit cutpoint tag. This cutpoint may be applied explicitly to the methods left in the droplet in order to maintain its semantic correctness.  
:construction: *Dedicated `CATCH` cutpoint  for certain exceptions is planned to be implemented in one of upcoming releases. Please feel free to send feedback (via email or issues) if you'd like it to be released sooner.*

For more info on droplets see Usage section.

## Download
Current version: [jmint-1.4-beta.jar](https://github.com/Toparvion/jmint/releases/download/v1.4-beta/jmint-1.4-beta.jar).  
:information_source: Description of current version is available on the [Latest Release page](https://github.com/Toparvion/jmint/releases/latest).

# Usage
Usage of **jMint** includes two steps:

1. Create droplet(s);
2. Attach **jMint** to target application for applying created droplet(s).

## Step 1: Create droplet
There are 2 general approaches to create a droplet: *from source code of target class* and *from scratch*. You are free to choose any of them.  Here are some hints that may help:  
- The first approach is handy when you have source code of a class being modified and you want to apply its slightly changed version at runtime. The **advantage** of this approach is that you don't have to manually write droplet class, you may just copy the existing one and modify the target method(s) only. The **flaw** is that your droplet gets some excess source code (which may be confusing in case of large class _(and may provoke bugs in jMint:wink:)_).
- The second approach is preferable when you don't want to copy the whole original source code and/or want to keep the droplet as simple as possible. The **pros and cons** of this approach are opposite to the first one: it makes you write some code manually but on the other hand it allows you to fill the droplet with the code you really need, without surpluses.

#### Approach 1. Creating droplet from target class

1. Make a copy of the target class's source code file with `Droplet` or `_Droplet` suffix (in any case) in the same package, for example `com.example.coolapp.TheAppDroplet` or `com.example.coolapp.TheApp_droplet`.  
:information_source: _If your target class name already ends with `Droplet` just add it again. Only the last one will be omitted._  
The suffix is needed to distinguish the original class from droplet. It will be omitted by jMint during parsing the droplet. The target type must not necessarily be a class, it can be an enum or an interface (but not annotation) as well as can be inner type on any level.  
:bulb: _In order to prevent accidental committing of droplet to your Version Control System (VCS) add droplets suffix into the list of exclusions. For instance in Git it can be achieved by adding the following line into `.gitignore` file:_
` *Droplet.java`
2. Find the target method(s) in the copied class and make sure it have javadoc description _(a block comment starting with forward slash and double asterisk (`/**`) located just before the method definition)_. If not, add one.
3. Add custom javadoc tag `@cutpoint` with one of values: `BEFORE`, `INSTEAD`, `AFTER`.
In the simplest case the whole javadoc definition may look like:  
`/** @cutpoint INSTEAD */`
4. In the body of target method write (or change) the code you want to be injected according to selected cutpoint.  
:warning: _Please remember about some limitations of modifying code (see corresponding section below)._
All other class members will be ignored by jMint.  
5. Repeat steps 2-4 for all the target methods of this class and then save the droplet anywhere you want.  
:bulb: *Note that in case of creating several droplets for the same purpose you can put them all into single ZIP or JAR archive and then use just like ordinary (separate) droplet.*

**Example.** Here's a sample droplet created from copy of its target class (_FooDroplet.java_):
```java
package com.example.coolapp;

import com.example.coolapp.model.*;
import java.lang.util.*;
import com.example.coolapp.util.Monitored;

@Monitored
public class FooDroplet extends FooBase implements Fooable {
  private static final Logger log = LoggerFactory.getLogger(Foo.class);
  /* other fields left from original class */

  /**
   * Performs fooing with given entity.
   *
   * @param entity an entity to fooify
   * @cutpoint BEFORE
   */
  @Override
  public void fooify(BusinessEntity entity) throws Exception {
    System.out.println("Entity to fooify: " + entity.toString());
  }

  /* other constructors and methods left from original class */
}
```
As you can see there is plenty of code that doesn't concerns the droplet. Compare it with code from the next approach.
***
#### Approach 2. Creating a droplet from scratch
1. Create a text file with name `<TargetClassSimpleName>Droplet.java` in any directory.  
:information_source: _This name format is just a kind of best practice; you may give the file any name._
2. Inside the file specify the package of target class just like if you'd create it.
3. Specify the target class (or enum, or interface) just like if you'd create it.  
_Access modifiers as well as `extends`/`implements` clauses and annotations make no sense to droplets and thus may be omitted. Arbitrary combinations of inner types are supported by jMint._
4. Define the target method just like if you'd create it.  
_Access modifiers as well as `throws` clause and annotations make no sense to droplets and thus may be omitted._
5. Prepend the target method with javadoc description.  
_It's a good practice to write detailed description of modifying method here but the droplet itself requires only one custom javadoc tag - `@cutpoint` followed by one of values: `BEFORE`, `INSTEAD`, `AFTER`._
6. Write the body of the method according to selected cutpoint.  
:warning: _Please remember about some limitations of modifying code (see corresponding section below)._
7. Repeat steps 4-6 for all the methods you'd like to modify and then save the droplet.  
:bulb: *Note that in case of creating several droplets for the same purpose you can put them all into single ZIP or JAR archive and then use just like ordinary (separate) droplet.*

**Example.** Here's a sample droplet created from scratch (_FooDroplet.java_):
```java
package com.example.coolapp;

import com.example.coolapp.model.BusinessEntity;

class FooDroplet {
  /** @cutpoint BEFORE */
  void fooify(BusinessEntity entity) {
    System.out.println("Entity to fooify: " + entity.toString());
  }
}
```
Writing such droplet might took some time but it is free of redundancy inherent to the first approach.
***

## Step 2: Attach jMint to the app
Because jMint ships as Java agent, it is attached to JVM through its startup argument `-javaagent`. The absolute or relative path to jMint jar is specified after colon (`:`) following the argument name. Then, followed by equal sign goes a list of droplets paths separated by semicolon (`;`). As a whole the command line may look like:
```bash
java -javaagent:path/to/jmint.jar=a/long/way/to/droplets/FirstDroplet.java;a/long/way/to/droplets/SecondDroplet.java com.example.coolapp.Main
```
:bulb: _To shorten the record you may introduce a couple of variables in the launch script to hold the prefix paths:_
```bash
JMINT_PATH=path/to/jmint.jar
DROPLETS_HOME=a/long/way/to/droplets
java -javaagent:$JMINT_PATH=$DROPLETS_HOME/FirstDroplet.java;$DROPLETS_HOME/SecondDroplet.java com.example.coolapp.Main
```
or 
```bash
JMINT=path/to/jmint.jar
DROPLETS=a/long/way/to/droplets/FirstDroplet.java
DROPLETS=$DROPLETS;a/long/way/to/droplets/SecondDroplet.java
java -javaagent:$JMINT=$DROPLETS com.example.coolapp.Main

```
Started with such arguments JVM will launch jMint and let it modify byte code of classes being loaded.  
:warning: *Note that being unable to load an agent JVM will not start at all.*  
:information_source: *`javaagent` is not singleton option for JVM. You may add as many agents as you want declaring them as separate  `javaagent` arguments on the JVM launch command.*  
To ensure that your target methods have been modified correctly look for messages from class `tech.toparvion.jmint.DropletsInjector` in the log (see _Logging_ section).

# Limitations
Unfortunately, source code of droplets' methods (the modifying code) can not be as rich and diverse as usual one.
The modifying code must not contain:
- anonymous classes (including lambdas);
- array initializers, a comma-separated list of expressions enclosed by braces { and } unless the array dimension is one;
- labeled continue and break statements;
- references to generic type parameters (like T in method `T <T> nvl(T t)`);
- references to static methods or variables imported on demand (i.e. like `java.lang.Double.*`; it should be replaced with separate single imports);
- implicit boxing/unboxing of primitive types (it must be replaced with explicit ones);
- invoking methods with listing-like varargs passing (it must be replaced with explicit array);
- _there are likely some other limitations; if you find one please share it with fellow users via Github issues._

The reasons explanation is beyond this document; you may see [Limitations](https://jboss-javassist.github.io/javassist/tutorial/tutorial2.html#limit) chapter of Javassist Tutorial for more info.

# Logging
jMint emits some messages about its work to the log using [SLF4J](http://slf4j.org/) logging facade. Therefore the actual logging implementation depends on SLF4J binding present on the application classpath. If there is no binding, jMint emits the only message to standard error output and stays silent till the end:
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
```
Here's some sample messages emitted by jMint when `slf4j-simple` binding is present on the classpath:
```
... (at the start of JVM) ...
[main] INFO tech.toparvion.jmint.JMintAgent - jMint started (version: 1.1).
...
[main] INFO tech.toparvion.jmint.JMintAgent - Droplets loading took: 1167 ms
... (later, at runtime) ...
[main] INFO tech.toparvion.jmint.DropletsInjector - Method 'sampleapp.standalone.painter.Painter.buildContent()' has been modified at AFTER.
...
[main] INFO tech.toparvion.jmint.DropletsInjector - Method 'sampleapp.standalone.painter.Painter#main' is skipped due to IGNORE.
```

# Under the hood
jMint is built upon three great tools: Java Byte Code Instrumentation API, Javassist byte code manipulating library and ANTLR4 language recognition tool. The latter (created by professor of genius Terence Parr (@parrt)) is used by jMint during startup to parse droplets and extract all the required information from them. Then with the help of Instrumentation API jMint registers itself as an interceptor for all the class loadings happening in JVM. When loading of some target class is detected jMint transforms its byte code by means of Javassist library (created by incredibly talented Shigeru Chiba (@chibash)) and returns it back to JVM.

# License
jMint is distributed under MIT License (see [LICENSE.txt](https://github.com/Toparvion/jmint/blob/master/LICENSE.txt)).

# Support & feedback
jMint is being developed by single person (@Toparvion) in free time as a helpful tool for day-to-day tasks. It is not finished yet so that new features (alongside with bug fixes) are expected in the foreseeable future. The priorities in choosing features to implement (and bugs to fix) depend heavily on the feedback going from the tool's users. You're welcome to post [issues](https://github.com/Toparvion/jmint/issues) or contact the author directly: `toparvion[at]gmx[dot]com`. Testing assistance is extremely appreciated:wink:
