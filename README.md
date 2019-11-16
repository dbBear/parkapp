# CS177 ParkApp (name tbd)
Welcome to ParkApp. Please find information about how to load and update the project below :)

## Running Locally
#### Install
If you'd like to run the project locally, you'll need:
+ Java 11

    [If you're on a unix system, I recommend using SDKMAN.](https://sdkman.io/)
    Currently, I'm using the Amazon 11.0.5. [You can download it directly here.](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
+ Maven

    [Maven is a build/package management tool that makes life simple through complexity.](https://maven.apache.org/install.html) 
    I'm on Maven 3.6.2 (latest as of Nov 16, 2019)
    
#### Running

Once both are installed, bring up a terminal, navigate to the top level directory of ParkApp and run:

    `mvn package && java -jar target/parkapp-[version]-jar
    
[version] is found in the top level pom.xml file with the tag version. Currently, this is `0.0.1-SNAPSHOT`.

Assuming all the tests are valid and it compiles, you'll be able to access the site on http://localhost:8080/

>If you'd like to change the default port, go to `parkapp/src/main/resource/application-default.properties` and
> change `server.port` to any valid local port


## Git
#### Installing
[First, make sure you have git installed locally.](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
After that, make sure you've set up [you're identity.](https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup#_your_identity)

#### Workflow
We're going to use the 'Centralized Repository' workflow. [Atlassian as a good description of how it works.](https://www.atlassian.com/git/tutorials/comparing-workflows)

We'll keep two working branches, `master` and `production`. **Make sure all work is on the `production` branch!**
If you've cloned the `master` branch, just run `git checkout -t origin/production` and you'll be all set :)

Please make your commits nice and small. Also, make sure you include a message in your commit!

#### .gitignore
I've included a .gitignore file in the commit that's pretty extensive. If you need to add more so we're not getting
 you're IDE files, **PLEASE INCLUDE THEM!**

## Front End Design
#### Directories
There are two directories currently dealing with front end work
+ `src/main/resources/static` holds all the static stuff (css,js,images, etc)
+ `src/main/resources/templates` holds all your html stuff



#### Displaying
You don't need the server running to work on the design. As long as you make all you links referential, everything
 will be 'A Okay.' Just load up a page from the system directory and you'll be set!



## Documentation
+ [This Git Repository](https://github.com/dbBear/parkapp)
