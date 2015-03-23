#!/bin/bash

echo "Hi $USER"

echo "Quickly make sure apt-get is up to date"
sudo apt-get update

echo "Installing apache2"
read -p "Press [enter] to continue"
sudo apt-get install apache2


echo "About to install mysql-server"
echo "You should be asked to provide a password for the user: root"
echo "Choose 'LMS' for the password"
echo "Remember, LMS"
echo "LMS"
read -p "Press [enter] to confirm you will make the password LMS"

sudo apt-get install mysql-server


echo "Now installing php5-mysql connector"
read -p "Press enter to continue"
sudo apt-get install php5-mysql


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
read -p "Press [enter] when you're ready"
echo "Last thing to do to start the website is to type thes commands"
echo "sudo a2ensite lms.com.conf"
echo "sudo service apache2 restart"

echo "Almost done with the script, now with everything installed"
echo "We're going to run a java program to build the SQL tables and database"
echo "There is a file called BuildDB.java in the com.team1.db package"
echo "Simply run this file and the database will be constructed like"
echo "the current version that is being used.  This file can change as desired"
read -p "Press [enter] to end bash script"






















