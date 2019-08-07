

# MAVEN 

### 一、相关信息

#### 1. [maven 官网]([http://maven.apache.org](http://maven.apache.org/))

>**Maven** is a powerful build tool for Java software projects. Actually, you can build software projects using other languages too, but Maven is developed in Java, and is thus historically used more for Java projects.
>
>typical features of build tool :
>
>- Generating source code (if auto-generated code is used in the project).
>- Generating documentation from the source code.
>- Compiling source code.
>- Packaging compiled code into JAR files or ZIP files.
>- Installing the packaged code on a server, in a repository or somewhere else.

#### 2. maven 安装

* 设置java路径，JAVA_HOME,CLASSPATH,Path
* 设置M2_HOME(maven所在目录路径)
* 将%M2_HOME%\bin放入环境变量Path
* echo %PATH%，刷新环境变量
* 运行 mvn -version

#### 3.maven结构

Maven以POM文件 ( Project Object Model, 项目对象模型 ) 概念为中心。 POM文件是项目资源的XML形式表示，如源代码，测试代码，依赖项（使用的外部JAR）等.POM包含对所有这些资源的引用。 POM文件应位于其所属项目的根目录中。

![](pic/mvn_pic/maven_concept.PNG)

POM文件
执行Maven命令时，为Maven提供一个POM文件来执行命令。然后，Maven将对POM中描述的资源执行命令。

生命周期，阶段和目标
Maven中的构建过程分为构建生命周期，构建阶段和构建目标。构建生命周期由一系列构建阶段组成，每个构建阶段由一系列目标组成。当您运行Maven时，您将命令传递给Maven。此命令是构建生命周期，阶段或目标的名称。如果请求执行生命周期，则执行该生命周期中的所有构建阶段。如果请求执行构建阶段，则也会执行此构建阶段之前定义的所有构建阶段。

依赖关系和存储库
Maven执行的第一个目标之一是检查项目所需的依赖项。依赖项是项目使用的外部JAR文件（Java库）。如果在本地Maven存储库中找不到依赖项，Maven会从中央Maven存储库下载它们并将它们放在本地存储库中。本地存储库只是计算机硬盘上的一个目录。如果您愿意，可以指定本地存储库的位置。您还可以指定用于下载依赖项的远程存储库。所有这些将在本教程后面更详细地解释。

构建插件
构建插件用于将额外目标插入构建阶段。如果您需要为标准Maven构建阶段和目标未涵盖的项目执行一组操作，则可以向POM文件添加插件。 Maven有一些你可以使用的标准插件，如果需要，你也可以在Java中实现自己的插件。

构建配置文件
如果需要以不同方式构建项目，则使用构建配置文件。例如，您可能需要为本地计算机构建项目，以进行开发和测试。您可能需要构建它以在生产环境中进行部署。这两个版本可能不同。要启用不同的构建，您可以向POM文件添加不同的构建配置文件。执行Maven时，您可以确定要使用的构建配置文件。

#### POM文件作用

Maven的POM文件描述了项目的资源，包括源码、测试代码的位置，以及有哪些外部依赖。

POM文件描述的是构建Maven仓库的内容，而不是如何构建，如何构建仓库是Maven命令的事。

每个项目都有POM文件，POM.xml ，必须放在项目根目录。如果项目分成多个子项目，一般来说会有一个POM文件作在父项目，每个子项目都有一个POM文件，这种结构允许父项目与子项目分离。

POM文件最小组成：

><project xmlns="http://maven.apache.org/POM/4.0.0"
>xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
>       http://maven.apache.org/xsd/maven-4.0.0.xsd">    
>
>​    <modelVersion>4.0.0</modelVersion>  
>
>​    <groupId>com.jenkov</groupId>
>​    <artifactId>java-web-crawler</artifactId>
>​    <version>1.0.0</version>
></project> 

**modelVersion**:POM模型版本

**groupId**:组织或者项目的唯一ID，不一定要使用java项目包名的形式(以.分割)。

**artifactId**:包含了需要构建仓库的java项目名称

**versionId**:项目版本号

`groupId`, `artifactId` and `version` 这三个元素将会在体现在maven项目打包结果的路径上，路径和包名

> ```
> MAVEN_REPO/com/jenkov/java-web-crawler/1.0.0/java-web-crawler-1.0.0.jar
> ```

每个POM文件都是继承自一个父文件，如果没有特别指明，就继承自 基础POM文件

![](pic/mvn_pic/POM_inheritance.PNG)

> ```
> <project xmlns="http://maven.apache.org/POM/4.0.0"
>          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
>                       http://maven.apache.org/xsd/maven-4.0.0.xsd">
>     <modelVersion>4.0.0</modelVersion>
>     
>         <parent>
>         <groupId>org.codehaus.mojo</groupId>
>         <artifactId>my-parent</artifactId>
>         <version>2.0</version>
>         <relativePath>../my-parent</relativePath>
>         </parent>
>     
> 
>     <artifactId>my-project</artifactId>
>     ...
> </project>
> ```

**子POM文件也可以适当覆盖父文件的，只需要指定新的配置就行**

查看POM文件整体结构 *effective POM* ：

> ```
> mvn help:effective-pom
> ```

#### Maven 配置文件

Maven有两个配置文件，通过这些配置文件可以跨过所有的POM文件配置Maven相关设置，包括：

> * 本地仓库的位置
> * 活动构建配置文件

这类配置文件叫做settings.xml ，位置：

> - The Maven installation directory: `$M2_HOME/conf/settings.xml`
> - The user's home directory: `${user.home}/.m2/settings.xml`

#### 运行maven

当maven配置好，根目录有POM文件买就可以运行maven命令，可以在mvn命令后面跟 **build life cycle**，**prase**，**goal**

运行build prase

> mvn install
>
> 或
>
> mvn clean install

运行 maven goal

> 
> mvn dependency:copy-dependencies
>

#### maven 目录架构

标准目录架构

| First Header | Second Header |
| ------------ | ------------- |
| src/main/java | Application/Library sources|
| src/main/resources	|Application/Library resources|
| src/main/filters	|Resource filter files|
| src/main/webapp|	Web application sources|
| src/test/java	|Test sources|
| src/test/resources|	Test resources|
| src/test/filters|	Test resource filter files|
| src/it|	Integration Tests (primarily for plugins)|
| src/assembly	|Assembly descriptors|
| src/site	|Site|
| LICENSE.txt	|Project's license|
| NOTICE.txt	|Notices and attributions required by libraries that the project depends on|
| README.txt	|Project's readme|
最重要的部分
``` 
- src
  - main
    - java
    - resources
    - webapp
  - test
    - java
    - resources

- target
```

#### 项目依赖

除非您的项目很小，否则您的项目可能需要***外部 Java API***或***框架***，这些Java API或框架打包在他们自己的JAR文件中。 编译项目代码时，类路径上需要这些JAR文件。

使用这些外部JAR文件的正确版本使项目保持最新可能是一项全面的任务。 每个外部JAR可能还需要其他外部JAR文件等。递归下载所有这些外部依赖项（JAR文件）并确保下载正确的版本是麻烦的。 特别是当您的项目变得越来越大，并且您获得越来越多的外部依赖项。

幸运的是，Maven内置了依赖管理。 您在POM文件中指定项目所依赖的外部库，以及哪个版本，然后Maven为您下载它们并将它们放在您的本地Maven存储库中。 如果这些外部库中的任何一个需要其他库，那么这些其他库也会下载到您的本地Maven存储库中。

POM配置项目依赖：

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
   http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.jenkov.crawler</groupId>
    <artifactId>java-web-crawler</artifactId>
    <version>1.0.0</version>
    
      <dependencies>

        <dependency>
          <groupId>org.jsoup</groupId>
          <artifactId>jsoup</artifactId>
          <version>1.7.1</version>
        </dependency>

        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.8.1</version>
          <scope>test</scope>
        </dependency>

      </dependencies>
    

    <build>
    </build>

</project>
```

当Maven执行此POM文件时，将从中央Maven存储库下载这两个依赖项并将其放入本地Maven存储库。 如果已在本地存储库中找到依赖项，则Maven将不会下载它们。 只有缺少依赖项才会将它们下载到本地存储库中。

有时，中央Maven存储库中没有给定的依赖项。 然后，您可以自己下载依赖项并将其放入本地Maven存储库。 请记住将其放入与groupId，artifactId和version匹配的子目录结构中。 用/替换所有点.并将groupId，artifactId和version与/也分开。 然后你有你的子目录结构。就像：

```
MAVEN_REPOSITORY_ROOT/junit/junit/4.8.1
MAVEN_REPOSITORY_ROOT/org/jsoup/jsoup/1.7.1
```

索引非maven项目仓中库依赖

```
<dependency>
  <groupId>mydependency</groupId>
  <artifactId>mydependency</artifactId>
  <scope>system</scope>
  <version>1.0</version>
  <systemPath>${basedir}\war\WEB-INF\lib\mydependency.jar</systemPath>
</dependency>
```

快照依赖 Snapshot Dependencies

快照依赖是指某些依赖还处于开发当中，您可以通过所依赖项目的快照版本，而不是不断更新版本号以获取最新版本。即使匹配的快照版本已经位于本地存储库中，快照版本也会始终下载到本地存储库中以进行每次构建。 始终下载快照依赖项可确保您在本地存储库中始终拥有每个版本的最新版本。

```
<dependency>
    <groupId>com.jenkov</groupId>
    <artifactId>java-web-crawler</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```



#### Maven 仓库

Maven存储库是具有额外元数据的打包JAR文件的目录。 元数据是描述每个打包的JAR文件所属项目的POM文件，包括每个打包的JAR具有的外部依赖项。 正是这个元数据使Maven能够递归地下载依赖关系的依赖关系，直到整个依赖关系树被下载并放入本地存储库。

Maven仓库类型：

* 本地仓库
* 中心仓库
* 远程仓库

Maven 以上述仓库顺序索引依赖

![](pic\mvn_pic\RepoType.PNG)

**本地存储库**是开发人员计算机上的目录。 该存储库将包含Maven下载的所有依赖项。 相同的Maven存储库通常用于几个不同的项目。 因此Maven只需要下载一次依赖项，即使多个项目依赖于它们（例如Junit）。

您还可以使用mvn install命令在本地存储库中构建和安装您自己的项目。 这样，您的其他项目可以将您自己项目的打包JAR文件用作外部依赖项，方法是将它们指定为Maven POM文件中的外部依赖项。

默认情况下，Maven将您的本地存储库放在本地计算机上的用户主目录中。 但是，您可以通过在Maven设置文件中设置目录来更改本地存储库的位置。 您的Maven设置文件也位于user-home / .m2目录中，名为settings.xml。 以下是为本地存储库指定其他位置的方法：

```
<settings>
    <localRepository>
        d:\data\java\products\maven\repository
    </localRepository>
</settings>
```

**中央存储库**
中央Maven存储库是Maven社区提供的存储库。默认情况下，Maven在此中央存储库中查找所需的任何依赖项，但在本地存储库中找不到。然后，Maven将这些依赖项下载到本地存储库中。您无需特殊配置即可访问中央存储库。

**远程存储库**
远程存储库是Web服务器上的存储库，Maven可以从中下载依赖项，就像中央存储库一样。远程存储库可以位于Internet上的任何位置，也可以位于本地网络内。

远程存储库通常用于托管组织内部的项目，这些项目由多个项目共享。例如，可以在多个内部项目中使用公共安全项目。外部世界不应该访问此安全项目，因此不应将其托管在公共的Maven中央存储库中。相反，它可以托管在内部远程存储库中。

在远程存储库中找到的依赖项也会被Maven下载并放入本地存储库。

您可以在POM文件中配置远程存储库。将以下XML元素放在<dependencies>元素后面：

```
<repositories>
   <repository>
       <id>jenkov.code</id>
       <url>http://maven.jenkov.com/maven2/lib</url>
   </repository>
</repositories>
```

#### Maven 构建生命周期、构建阶段、构建目标

当Maven构建软件项目时，它遵循构建生命周期。 构建生命周期分为构建阶段，构建阶段分为构建目标。 Maven构建生命周期，构建阶段和目标在Maven构建阶段简介中有更详细的描述，但在这里我将为您提供一个快速概述。

**Maven 构建生命周期**

Maven有三个默认的内置构建声明周期：

* default
* clean
* site

这些构建生命周期中的每一个都负责构建软件项目的不同方面。 因此，这些构建生命周期中的每一个都彼此独立地执行。 您可以让Maven执行多个构建生命周期，但它们将按顺序执行，彼此分开执行，就像您执行了两个单独的Maven命令一样。

dufault构建生命周期处理与编译和打包项目相关的所有内容。 clean构建生命周期处理与从输出目录中删除临时文件相关的所有内容，包括生成的源文件，编译的类，以前的JAR文件等。site构建生命周期处理与生成项目文档相关的所有内容。 实际上，站点可以生成一个包含项目文档的完整网站。

**构建阶段**

每个构建生命周期分为一系列构建阶段，构建阶段再次细分为目标。因此，整个构建过程是一系列构建生命周期，构建阶段和目标。

你可以执行整个构建生命周期（如clean或site），构建阶段（如安装，这是default构建生命周期的一部分），或构建目标（dependency:copy-dependencies）。注意：你无法直接执行default构建生命周期，必须在default构建生命周期内指定构建阶段或目标。

执行构建阶段时，将执行此标准阶段序列中构建阶段之前的所有构建阶段。因此，执行安装构建阶段实际上意味着在安装阶段之前执行所有构建阶段，然后执行安装阶段。

default构建生命周期是最有趣的因为是这个阶段构建代码。由于无法直接执行默认生命周期，因此需要执行default构建生命周期的构建阶段或目标。default狗就按生命周期具有大量的构建阶段和目标，常用的如下：

| Build Phase | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| `validate`  | Validates that the project is correct and all necessary information is available. This also makes sure the dependencies are downloaded. |
| `compile`   | Compiles the source code of the project.                     |
| `test`      | Runs the tests against the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed. |
| `package`   | Packs the compiled code in its distributable format, such as a JAR. |
| `install`   | Install the package into the local repository, for use as a dependency in other projects locally. |
| `deploy`    | Copies the final package to the remote repository for sharing with other developers and projects. |

#### Maven构建配置

Maven构建配置文件使您可以使用不同的配置构建项目。 您可以只使用不同的构建配置指定配置文件，而不是创建两个单独的POM文件，并在需要时使用此构建配置文件构建项目。

Maven构建配置文件在配置文件元素内的POM文件中指定。 每个构建配置文件都嵌套在配置文件元素中。 这是一个例子：

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
   http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.jenkov.crawler</groupId>
  <artifactId>java-web-crawler</artifactId>
  <version>1.0.0</version>

  <profiles>
      <profile>
          <id>test</id>
          <activation>...</activation>
          <build>...</build>
          <modules>...</modules>
          <repositories>...</repositories>
          <pluginRepositories>...</pluginRepositories>
          <dependencies>...</dependencies>
          <reporting>...</reporting>
          <dependencyManagement>...</dependencyManagement>
          <distributionManagement>...</distributionManagement>
      </profile>
  </profiles>

</project>
```

构建配置文件描述在该构建配置文件下执行时应对POM文件进行的更改。 这可能是将应用程序配置文件更改为使用等。概要文件元素内的元素将覆盖POM中具有相同名称的元素的值。

在profile元素中，您可以看到 *activation* 元素，此元素描述触发此构建配置文件的条件。另一种选择被执行的配置文件方式是在.xml文件中。 在那里，可以设置活动配置文件。 另一种方法命令行是添加-P profile -name。

**Maven Plugins**

Maven插件使您可以将自己的操作添加到构建过程中。 您可以通过创建一个扩展特殊Maven类的简单Java类，然后为该项目创建一个POM来实现。 该插件应位于自己的项目中。

----

**使用参数下载源码包与doc包**：
-DdownloadSources=true 下载源代码jar
-DdownloadJavadocs=true 下载javadoc包
mvn dependency:sources -DdownloadSources=true -DdownloadJavadocs=true

--亲测可用，下载完成之后jar的属性中的source和doc都自动关联上了(jar包属性上设置路径)。
mvn dependency:sources -DdownloadSources=true -DdownloadJavadocs=true

===========跳过测试==============
clean install -Dmaven.test.skip=true
or
clean install -DskipTests=true

![](pic\mvn_pic\repoEx.jpg)