SoftUni BlackJack console game. Team Project

Follow the steps in this tutorial in order to sucessfully run the application:
http://www.tech-recipes.com/rx/30018/how-to-compile-java-programs-through-windows-cmd/

First things first, you need to download the JDK from Oracle to have the latest version of Java.

2.Follow the instructions to install the JDK. It is important to make note of the path of the JDK install.

3.Create a central directory to hold all your Java files. For example, I created a folder at C:\javaand placed my .java files and projects within this folder.

4.Click the Windows Start icon and search for System and hit enter. Select Advanced system settings on the left and then select Environment Variables.

5.Under System variables scroll down to the variable Path.

6.Hit Edit at the beginning of the Variable value. We need to place the path of the Java compiler into Windows’s path. The compiler should be located in the JDK bin folder. For example, from the install I recorded that my bin folder was at the following location:C:\Program Files (x86)\Java\jdk1.7.0_06\bin; but your location may be different. Be sure to include a semi-colon at the end of the string you just added. Hit OK and close this.
If this is done incorrectly, you will get the following error when attempting to compile:
‘javac’ is not recognized as an internal or external command, operable program or batch file
7.Next, we need to open a terminal window or CMD shell. Click the Windows icon and search for “CMD” then hit enter. Change to the directory of your personal java files. For example, I would input cd \java since that is the personal java folder I created above. Now our current directory within the CMD shell is c:\java.

8.Type in javac JavaFileName.java where the “JavaFileName.java” is the name of the java file you want to compile. This file can be created with any text editor or IDE and contains your actual code. After hitting the enter key, you should see a new blank line in the CMD with nothing in it Now check within your personal Java folder. If the compile was successful, you should see a new .class file.

9.To actually run the interpreter and see output type in java YourFileName without the .java extension and your program should execute. My example simply outputs some text.



=======
