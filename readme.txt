LMS
The Library Management System

Setting up this workspace on your local machine:

DISCLAIMER: I am writing most of this from memory, so these steps may or may not be accurate or complete. I did not feel like uninstalling and re-installing everything to properly check, nor do I have a spare computer to test the procedure on.
- Brandon

1. Install the JDK
Windows:
Download and install the JDK from here:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Navigate to Start > Computer > System Properties > Advanced System Properties > Environment Variables

Select Path from the user variables list and press edit.
DO NOT DELETE THE CONTENTS OF THIS FIELD

Append the path to your Java install's bin folder to the end of the contents of this field.
This is usually something like:
C:\Program Files (x86)\Java\jdk1.8.0_31\bin for 32bit installs
C:\Program Files\Java\jdk1.8.0_31\bin       for 64bit installs

Linux:
Open terminal and type: "sudo apt-get install openjdk-7-jdk"
Accept any confirmations.

Mac:
Download and install linux from here*: http://xubuntu.org/getxubuntu/

2. Install Eclipse**.
Download from here: https://www.eclipse.org/downloads/
Choose the IDE for Java Developers appropriate for your OS.
Unzip the file to where you want Eclipse to reside.

3. Install Git and/or a Git Client.
Windows:
Download and install Git from here: http://git-scm.com/download/win
Choose the default options.

Linux:
Open terminal and type: sudo apt-get install git
Accept any confirmations.

4. Clone the Github repository
Windows:
Navigate to where you want to clone your repository to.
Right click and select Git Bash.
Type: "git clone https://github.com/bcroden/LMS.git"

Linux:
Open terminal.
Navigate to where you want to clone your repository to.
Type: "git clone https://github.com/bcroden/LMS.git"

5. Open Eclipse
Select "path-to-where-you-cloned-your-repository-to/LMS" for the workspace on startup.
Alternatively select File > Switch Workspace > other and choose "path-to-where-you-cloned-your-repository-to/LMS"

6. Create 2 New Java Projects
Name the 1st one "Database Manager" and press Finish
Name the 2nd one "Workstation" and press Finish
There appears to be a slight bug in the current version of Eclipse where the first project you create does not display its Git information, but the second one does. This bug is purely visual. If this happens to you, just delete the Database Manager project and remake it, this will not affect the contents of the Database Manager folder on your machine.

7. Run Gradle to generate Eclipse files
Windows:
Navigate to the Database Manager Folder.
Right click and select Git Bash or shift right click and select open command window here.
Type: "gradlew cleanEclipse eclipse"

Navigate to the Workstation Folder.
Right click and select Git Bash or shift right click and select open command window here.
Type: "gradlew cleanEclipse eclipse"

Linux:
Open terminal.
Navigate to the Database Manager Folder.
Type: "./gradlew cleanEclipse eclipse"
Navigate to the Workstation Folder.
Type: "./gradlew cleanEclipse eclipse"

Setting up this workspace on Cloud9:

1. Log in to Cloud9 with your Github account.
Authorize application on Github.

2. Create a new workspace
Select: Clone from URL
For the source URL enter: https://github.com/bcroden/LMS.git
Choose Open or Private, your preference
Scroll down and select Custom
Press Create

3. Select lms from My Projects List and press Start Editing

*  other distro's are availabe.
** other IDE's are available.
