# Continuing Education Validation System

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](http://forthebadge.com/images/badges/powered-by-electricity.svg)](http://forthebadge.com)

## General Information

This is a software that will perform the validation of continuing education activity statements for members of a professional order.

## Getting Started

You will need an integrated development environment like **IntelliJ IDEA**.

## Installation

The steps to install your program are :

1. Clone the repository.
2. Go to the `projet` folder.
3. Go to pom.xml, right-click and add as `mavenproject`.
4. Open the `PrincipalProg` file on your IDE.
5. Right-click on the `lib` directory and `add` as `library`
6. If there is a problem, refresh the `maven` folder.

## Starting

To launch your project:

1. Go to the `src` directory.

2. Start the `PrincipalPRog` class.

 ### In case of a problem

If you receive an error message that the `inputfile` folder does not exist:

1. Right-click on `inputFile.json`.

2. Click on `copy path/reference`.

3. Copy the absolute path of the file to be executed.

4. Put the absolute path of the file in the `Principalprog` class on line **7**.

## Using Jacoco

To generate a report on the tests of this project, you will use the **Jacoco plugin** as follows:

1. Click on the `Maven` menu.
2. In the `Lifecyle` folder, select `Clean` and `test`.
3. Click on `Run Maven Build`.
4. Go to `target/site/Jacoco/index.html`.
5. Click on the browser you want to use to view the report.

## Built with

The programs/software/resources used to develop this project are:

* [IntelliJ](https://www.jetbrains.com/fr-fr/idea/) - Integrated Development Environment.
* [Jacoco](https://www.eclemma.org/jacoco/) - A Java code coverage library.
* [Maven](https://maven.apache.org/) - A tool for managing and automating the production of Java software projects.
* [typora](https://typora.io/) - Markdown text editor (For creating .md files).

# Important Note on Code Conventions

Please be aware that in the source code for this program, variable names, function names, and comments are primarily written in French. We understand that this might pose some challenges for users who are not familiar with the French language. However, We have ensured that the user documentation, such as this README, is written in English for broader accessibility.

## Auteurs
Les auteurs du projet :
* **Besma Jabri** 
* **Othmane Azzouzi**
