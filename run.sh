#!/bin/bash

echo "Hi $USER"

echo "Quickly make sure apt-get is up to date"
sudo apt-get update

echo "Installing apache2"
sudo apt-get install apache2

echo "Installing php5"
sudo apt-get install php5

echo "Installing php5-mysql"
sudo apt-get install php5-mysql

echo "About to install mysql-server"
echo "You should be asked to provide a password for the user: root"
echo "Choose 'LMS' for the password"
echo "Remember, LMS"
echo "LMS"
sleep(3)

sudo apt-get install mysql-server


echo "Everything should have been properly installed"
echo "Starting the website is more complex and I will complete part of it"
echo "Creating LMS config file at /etc/apache2/sites-available/default"
sudo cp /etc/apache2/sites-available/default /etc/apache2/sites-available/lms.com.conf
echo "Creating directories /var/www/lms.com"
sudo mkdir /var/www/lms.com
echo "Creting sub-directories /lms.com/public_html and some logs"
sudo mkdir /var/www/lms.com/public_html
sudo mkdir /var/www/lms.com/backup
sudo mkdir /var/www/lms.com/log

echo "To get the website to work you'll need to move the website files"
echo "from LMS to the public_html directory, also there is a document"
echo "path in /etc/apache2/sites-available/lms.com.conf that needs changing"
echo "in the line DocumentRoot, change the remainder to "
echo "/var/www/lms.com/public_html"

echo "Last thing to do to start the website is to type thes commands"
echo "sudo a2ensite lms.com.conf"
echo "sudo service apache2 restart"

echo "Almost done with the script, now with everything installed"
echo "We're going to run a java program to build the SQL tables and database"






















