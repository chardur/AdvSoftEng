## Front-end Server setup

The Front-end server is an Amazon Linux AMI micro-ec2 instance. The instance can be setup using [this guide](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/install-LAMP.html)

The following modifications are needed for Angular routing:

1. Create a .htaccess file in the /var/www/html/ directory with the following code:
 `<IfModule mod_rewrite.c>
  RewriteEngine On
  RewriteBase /
  RewriteRule ^index\.html$ - [L]
  RewriteCond %{REQUEST_FILENAME} !-f
  RewriteCond %{REQUEST_FILENAME} !-d
  RewriteRule . /index.html [L]
</IfModule>`

2. Modify the httpd.conf file in the /etc/httpd/conf/ directory with the following:

  Change:
  `<Directory "/var/www">
      AllowOverride None`

  To:
  `<Directory "/var/www">
      AllowOverride All`


  Also change:
  `<Directory "/var/www/html">
    AllowOverride None`

  To:
  `<Directory "/var/www/html">
    AllowOverride All`


## Database Server setup

The database server is an Amazon RDS micro server running MySQL 5.7.23.
The server was setup using the amazon launch wizard with the default settings.

The following modifications are needed to allow incoming connections for the Development team and Back-end server:

1. Click `Edit` on the inbound security group rules page.
2. Click `Add Rule`
3. Select the following rules: `Type: MYSQL/Aurora` , `Protocol: TCP` , `Port Range: 3306` , `Source: Custom 0.0.0.0/0`
4. Click `Save`

## Back-end Server Setup

The back-end server is hosted in AWS using Elastic Beanstalk. The process for setup is quite straight forward.

The following links were used as a reference when setting up the back-end server:
* https://medium.com/@ryanzhou7/running-spring-boot-on-amazon-web-services-for-free-f3b0aeec809
* https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/
* https://medium.com/@autumn.bom/deploying-spring-boot-jar-application-on-beanstalk-java-se-platform-45d8d04608ae

Essentially, all that is needed is the built JAR (see [Back-end](/BackEnd/README.md) for details on building) and
an AWS account. Navigate to Elastic Beanstalk and click on `Create New Application`. Follow the wizard and enter
the required information. As part of the server setup there will be a place to upload the built JAR file.

After AWS provisions and launches the application, there is no further customization or configuration of the environment
needed.
