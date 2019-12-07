# CS177 ParkApp (name tbd)
Welcome to ParkApp. Please find information about how to load and update the project below :)

This github project is private so only those with share privileges can see it.

# Bootstrapped Username: Password
+ **admin** - admin@admin.com : admin
+ **park 1 official** - official.park1@email.com : password
+ **park 1 ranger** - ranger.park1@email.com : password
+ **park 2 official** - official.park2@email.com : password
+ **park2 ranger** - ranger.park2@email.com : password
+ **submitter 1** - submitter.1@email.com : password
+ **submitter 2** - submitter.2@email.com : password
+ **anonymous submitter** - anonymous@parkapp.com : password


#
# Running Locally
### Install
If you'd like to run the project locally, you'll need:
+ Java 11

    [If you're on a unix system, I recommend using SDKMAN.](https://sdkman.io/)
    Currently, I'm using the Amazon 11.0.5. [You can download it directly here.](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
+ Maven(not required)

    [Maven is a build/package management tool that makes life simple through complexity.](https://maven.apache.org/install.html) 
    I'm on Maven 3.6.2 (latest as of Nov 16, 2019)
    
    Alternatively, you don't have to install Maven. Use the Maven Wrapper as detailed in the _'Didn't Install Maven'_
     section under _'Running'_.
    
### Running 
####Sans IDE

If you've installed maven, run:

    `mvn spring-boot:run`
   
Or, if maven isn't installed, run:

    `./mvnw clean install && ./mvnw spring-boot:run`
    
`[version]` is found in the top level pom.xml file with the tag version. Currently, this is `0.0.1-SNAPSHOT`.

Assuming all the tests are valid and it compiles, you'll be able to access the site on http://localhost:8080/

>If you'd like to change the default port, go to `parkapp/src/main/resource/application-default.properties` and
> change `server.port` to any valid local port

---
#### With an IDE
##### Intellij

_Step 1 (if not in a project)_

From the Welcome menu, select `Import Project`, navigate and select the parent folder (should be `parkapp`), 
click okay and the, on the 'Import Project' window, make sure to select `Import project from external model` and select `Maven`.

_Step 1 (while viewing a project)_

Select `File | NEW >  Project From Existing Sources` 

_Step 2_

navigate and select the parent folder (should be `parkapp`),
click okay and then, on the 'Import Project' window, make sure to select `Import project from external model` and select `Maven`.

---

##### Eclipse
[Here's a stack overflow post with the answer](https://stackoverflow.com/questions/2061094/importing-maven-project-into-eclipse)


### SQL
Currently, `0.0.2-SNAPSHOT` is using an in-memory database. This section will get updated once we link an SQL server.

---

### SMTP Server
[To verify that the email service is working, I'm using FakeSMTP](http://nilhcem.com/FakeSMTP/). 

Once installed, just navigate to the unzipped directory and run:

    `java -jar fakeSMTP.jar -s -p 6000`
    
If you get an error that port 6000 is not available, change the -p argument to and available port **and change the
 `spring.mail.port` property in the application-default.properties file the same port**



#
# Git
### Installing
[First, make sure you have git installed locally.](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
After that, make sure you've set up [you're identity.](https://git-scm.com/book/en/v2/Getting-Started-First-Time-Git-Setup#_your_identity)

### Workflow
We're going to use the 'Centralized Repository' workflow. [Atlassian as a good description of how it works.](https://www.atlassian.com/git/tutorials/comparing-workflows)

We'll keep two working branches, `master` and `production`. **Make sure all work is on the `production` branch!**
If you've cloned the `master` branch, just run `git checkout -t origin/production` and you'll be all set :)

Once you're on production, DON'T WORK DIRECTLY IN THIS BRANCH! Instead, create a new branch with a name describing
 the work you'll be doing, like:
 
 `git checkout -b add-new-feature`
 
 The `checkout -b` will create a new branch called 'add-new-feature' and automatically check out that branch for you
 . Confirm this by running `git branch` and you see output like this:
 
    * add-new-feature
    master
    production
    
The asterisk highlights which branch you're currently on. Now, do any work you need to do to make your new feature
 and make all your commits to this branch.
 
 
 Your commits should be nice and small - think of them like 'saves' that you can undo. So, you make a new file and
  write some code it works (but still needs some added pizaz), commit! You finished adding that pizez, commit!
  
 
One you have finished everything for your new feature and you've made your last commit, it's time to merge.

Check out the production branch again with `git checkout production` and THE FIRST THING YOU SHOULD RUN IS 
`git pull` (this will update production if someone else has updated it while you were working). Now, you can merge your
'add-new-feature' branch.

Run `git merge add-new-feature` and your branch will dump into production. If there are no conflicts, everything will
 be A-Okay. [If there are issues, you have to resolve the conflicts.](https://www.atlassian.com/git/tutorials/using-branches/merge-conflicts)
 Then, run `git push` to push the new change on the 'production' branch to git hub so we can see everything.
 
Lastly, you can delete you 'add-new-feature' branch as all those commits you made are part of production. Just run
 `git branch -d add-new-feature` and you're all done!


>##### .gitignore
>I've included a .gitignore file in the commit that's pretty extensive. If you need to add more so we're not getting
 you're IDE files, **PLEASE INCLUDE THEM!**



#
# Front End Design
### Directories
There are two directories currently dealing with front end work
+ `src/main/resources/static` holds all the static stuff (css,js,images, etc)
+ `src/main/resources/templates` holds all your html stuff

There are two directories in `static` and `templates`, both called `backEndStuff`. These are temp files for making
 sure the backend is displaying properly until we get the proper front end wired in. Please don't make changes to
  these files.

### Displaying
You don't need the server running to work on the design. As long as you make all you links referential, everything
 will be 'A Okay.' Just load up a page from the system directory and you'll be set!



#
# Documentation
+ [Git Repository](https://github.com/dbBear/parkapp)
+ [Slack Channel](https://cs177parkapp.slack.com)
+ [Google Drive](https://drive.google.com/drive/folders/1wA6F9TVSKuT24bAsBu-aqEe4TRkmf_Bn?usp=sharing)
+ [Trello Board](https://trello.com/cs177parkteam)

### Contacts
+ Daniel Blum [drumblum@gmail.com](mailto:drumblum@gmail.com)
+ Kenny Kong [kennyw899@gmail.com](mailto:kennyw899@gmail.com)
+ Jason Rosenberg [comparc@icloud.com](mailto:comparc@icloud.com)
+ Anastasiia Chalova [achalova@mail.ccsf.edu](mailto:achalova@mail.ccsf.edu)
+ Maryna Ponomarenko [mponoma1@mail.ccsf.edu](mailto:mponoma1@mail.ccsf.edu)


