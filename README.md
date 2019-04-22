<h1 align="center"><a href="http://shmoozed.com"><img src="/FrontEnd/src/assets/images/logoSmallDark.png" alt="Markdownify" width="50"></a>Shmoozed</h1>

<h4 align="center">A web application where Sellers and Buyers <em>BOTH</em> win!</h4>

<p align="center">
  <a href="https://angular.io/"><img src="https://img.shields.io/badge/Front--end-Angular-blue.svg"></a>
  <a href="https://spring.io/"><img src="https://img.shields.io/badge/Back--end-Java%20%26%20Spring%20Boot-brightgreen.svg"></a>
  <a href="https://www.mysql.com/"><img src="https://img.shields.io/badge/Database-MySQL-orange.svg"></a>
  <a href="https://aws.amazon.com/"><img src="https://img.shields.io/badge/Hosting--Platform-AWS-yellow.svg"></a>
</p>

[Shmoozed](http://www.shmoozed.com) is a web application designed to allow Sellers to better understand the market price for their items. This is done by trending price data for items over time. In addition, potential Buyers can provide their ideal price (how good of a deal or steal they want) and negotiate with sellers of those items, eventually coming to an agreement on a price.

Shmoozed was developed as a project at Weber State University as part of CS 3750 Software Eng II and CS 4450 Advanced Software Eng.

<p align="center">
  <img src="/Docs/screenshots/Shmoozed_2018-11-23_01.png" alt="Shmoozed Application Screenshot"
       width="683" height="384">
</p>

## Architecture

Shmoozed is made up of a Front-end application written using Angular, a Back-end Java API powered by Spring & Spring Boot, backed by a MySQL Database, and all hosted on Amazon Web Services. The Back-end
calls to various online retailers APIs.

![](/Docs/high-level-arch.png)

### Detailed diagrams & documentation:
* [Front-end](/FrontEnd/README.md)
* [Back-end](/BackEnd/README.md)
* [Database](/Database/README.md)
* [Hosting](/Docs/Hosting.md)

## Project Setup

Project setup is dependant on which architectural piece, front-end or back-end, you are working on:

* [Front-end project setup](/FrontEnd/README.md#development-setup)
* [Back-end project setup](/BackEnd/README.md#development-setup)

## Other Documentation

* [Pull Request / Code Review Procedure](/Docs/Contributing.md)

## Contributors

* Anthony Perez
* Charles Durfee
* Christopher Nash
* Daniel Kiser
* Eric Christensen
* James Larson
* Jonathan Pedregon
* Michael Gray
