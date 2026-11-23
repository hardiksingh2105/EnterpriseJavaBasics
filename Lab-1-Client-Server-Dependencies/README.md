# Lab 1 — Client-Server Dependencies and Environment Setup

## Objective

Prepare a Windows 11 Java Enterprise development environment with IntelliJ IDEA, JDK 17 and Maven, and identify the additional client-server files distributed by the instructor.

## 1. Install IntelliJ IDEA

1. Download IntelliJ IDEA from the official JetBrains website: <https://www.jetbrains.com/idea/download/>.
2. Install the edition available to you.
3. Launch IntelliJ IDEA and complete its first-run setup.
4. Keep the default project and plugin settings unless your instructor specifies otherwise.

## 2. Install JDK 17

1. Download a JDK 17 distribution, such as Eclipse Temurin 17: <https://adoptium.net/temurin/releases/?version=17>.
2. Install the Windows x64 JDK package.
3. Note the installation directory. A typical path is similar to `C:\Program Files\Eclipse Adoptium\jdk-17...`; use the actual installed path on your computer.
4. Open a new PowerShell window and check:

   ```powershell
   java -version
   javac -version
   ```

Both commands should report version 17 when the JDK 17 installation is selected.

## 3. Configure Windows environment variables

1. Open **Start → View advanced system settings → Environment Variables**.
2. Under **System variables** or **User variables**, create `JAVA_HOME` and set its value to the JDK 17 installation directory (the directory containing `bin`).
3. Select `Path`, choose **Edit**, and add `%JAVA_HOME%\bin`.
4. Move the JDK 17 entry above older Java entries if the computer has several JDKs installed.
5. Open a new terminal and verify:

   ```powershell
   echo $env:JAVA_HOME
   java -version
   javac -version
   ```

The shell must be restarted after changing environment variables.

## 4. Configure IntelliJ IDEA to use JDK 17

1. Open IntelliJ IDEA.
2. Open **File → Project Structure → Project**.
3. Set **SDK** to the installed JDK 17. Use **Add SDK → JDK** if it is not listed.
4. Set **Language level** to 17 (or use the SDK default).
5. For each Maven project, check **Settings → Build, Execution, Deployment → Build Tools → Maven → Runner** and select the project JDK 17.
6. Reload the Maven project after changing the SDK.

## 5. Prepare Maven

IntelliJ IDEA can use its bundled Maven installation. Alternatively, install Apache Maven 3.6.3 or later from <https://maven.apache.org/download.cgi>.

If installing Maven manually:

1. Extract the Maven distribution to a stable directory.
2. Optionally create `MAVEN_HOME` pointing to the extracted directory.
3. Add `%MAVEN_HOME%\bin` to `Path`.
4. Open a new PowerShell window and run:

   ```powershell
   mvn -version
   ```

Confirm that Maven is using the intended Java installation.

## 6. Instructor-provided client-server dependency files

The original laboratory instruction requires downloading two additional client-server dependency files. Their exact filenames, formats, source and download URLs were not included in the supplied specification.

**Obtain the two files and their installation/import instructions from the course instructor.** Do not guess their filenames or download them from an unrelated source. Once identified, keep them in the location instructed by the course and record the actual names in your lab submission notes.

## 7. Environment verification checklist

- [ ] IntelliJ IDEA launches successfully.
- [ ] JDK 17 is installed.
- [ ] `JAVA_HOME` points to the JDK 17 installation.
- [ ] `java -version` and `javac -version` report JDK 17.
- [ ] IntelliJ project SDK and Maven runner use JDK 17.
- [ ] Maven is available and `mvn -version` succeeds.
- [ ] The two instructor-provided client-server dependency files have been identified and configured as directed by the instructor.

## Result

The workstation is prepared for the Java/Spring Boot experiments. The two additional client-server files remain instructor-dependent until their real filenames and instructions are supplied.
