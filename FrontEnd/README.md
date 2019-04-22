# Shmoozed Front-end

> This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.8.

## Deployment Procedure

Ideally, this would be automated through a DevOps / Delivery Tool such as Jenkins, etc. but due to
time constraints these manual build and deploy instructions should be followed when a new version
of the application is ready to be released into Production.

1. From the `\FrontEnd` directory run `ng build`. This will create a `\FrontEnd\dist` folder with the needed files.
2. Type `node sftp` then wait for a few minutes while the files are transferred to the server. Once complete you will see a `success!` message. 
3. In your web browser go to [http://www.shmoozed.com](http://www.shmoozed.com) to check for functionality. You may need to hold shift while clicking refresh for the changes to reflect.

## Resources
When adding a module, put it here.
  - [Angular 6](https://angular.io/) Application builder
  - [TypeScript](https://www.typescriptlang.org/) Programming Language
  - [Bootstrap 4](http://getbootstrap.com/) Styles, Mobile
  - [Angular Materials](https://material.angular.io/) - icons, menus, text assets.
  - [TensorFlow.js](https://js.tensorflow.org/) - training and deploying ML models in the browser and on Node.js

## Code Structure
**tslint.json** - gives a list of rules governing typescript

**package.json** - is an automatically generated list of modules used by the app

**src/styles.css** - contains the template css files - should be overwritten not modified.

**src/app/app.component.css** - Write global css for the app. Overwrite **styles.css** behavior here as well

**src/app/(name)/(name).component.css** - Write component specific css. Overwrite other behavior here as well

**src/app/share/template** - contains the templated dashboard main page 

**src/app/(name)/(name).component.html** - contains the body of each component

Each component contains a .ts file for typescript, including **app.component**. 

**assets/** should contain **assets/img, assets/vid** etc. Additional **CSS** files can be put in **src/assets/css/**

 - **If anyone has a different set up in mind, feel free to change and document.**

## Development server

Angular requires Node.js version 8.x or 10.x. To check your version, run node -v in a terminal/console window. You can download Node.js [here.](https://nodejs.org/en/)

Clone the repo on your local machine. In a terminal/console window, navigate to the `/FrontEnd` folder. Type `npm install` to install the project dependencies.

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Note: You may may need to run `npm install` periodically (from the `/FrontEnd` directory) as modules are added to the project. If `ng serve` fails, try this first.

Note 2: On Linux, you may also need to followup the `npm install` with a `npm install -g @angular/cli` before the `npm build` will work properly.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

Note that the results are displayed in the browser window that is launched for testing. After analyzing the results, use a `Ctrl + C` to finsh the executive of the tests.

If you do not want the tests to leave the window open with the results showing, add a `--watch=false` at the end of the command. Doing `ng test --watch=false` is important for running in the CI/CD pipeline.

### Run Headless (No Browser Window)

On Headless machines (such as Travis CI) there is no GUI available for a browser to run in. As such, you need to run 
`ng test --watch=false --progress=false --browsers=ChromeHeadlessCI` instead.

`browsers=ChromeHeadlessCI` in configured in `karma.conf.js`.


## Running end-to-end tests

After performing an `npm install`, do the following instructions (based loosly on the instructions at https://www.protractortest.org/#/).

1. `npm install -g protractor`
2. `webdriver-manager update --versions.chrome 2.46`
   * The additional params are needed because webdriver-manager defaults to installing the latest version of webdriver which Chrome 74 (at the time of this writing). However, the latest stable version on Linux is Chrome 73... We have to pull down an older version because of that.
   * Why `2.46`? See https://sites.google.com/a/chromium.org/chromedriver/downloads and https://stackoverflow.com/questions/41133391/which-chromedriver-version-is-compatible-with-which-chrome-browser-version which says `2.46` supports Chrome 73.

Run `protractor conf.js` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Front-end Server setup

* [Front-end server setup](/Docs/Hosting.md#Front-end)
