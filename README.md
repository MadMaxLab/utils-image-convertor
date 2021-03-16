# utils-image-convertor
My utils program to convert HEIC files to .jpg for my blog

The program doesn't realize HEIC conversion itself instead, it calls ImageMagic API.
I've had to do so because I did not find any FOSS java library to work with HEIC files.
Please leave me a message if you know any FOSS java lib to work with HEIC files.

# What converter doing
It scans the given folder and searches for HEIC files.
For each found file it resizes it to 1280 pixels wight.
Convert and save each file as a jpg file.

# Before start
Before using the program you have to install Image Magic using the link: https://imagemagick.org/script/download.php

# How to start the program in IDE
* Find where is the bin folder of your installed  Image Magic.
* In the "app.properties" file in the project root directory set IMAGE_MAGIC_PATH property to your bin directory.
* Set a path to your image directory as a CLI argument to the program.

# How to start the program as a JAR file
* Using your favorite IDE build JAR file
* Fill app.properties file with the correct path to your installed Image Magic.
* Copy Jar file and app.properties file to any folder you want.
  Make sure that the Jar file and app.properties file at the same folder.
* Launch in cmd: java -jar <path to your jar>ImageConverter.jar <path to your images folder>.