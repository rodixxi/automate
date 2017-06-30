Install OpenCV 3.1.0 in Ubuntu 15.04/14.04
------------------------------------------

As OpenCV is platform dependant it has to be build on each platform. 15.04 and
14.04 are the ones we have tried this steps. If something goes wrong check
TROUBLESHOOTING section at the end of t his file.


1- Install dependencies:
------------------------
sudo apt-get install build-essential cmake git libgtk2.0-dev pkg-config
sudo apt-get install libavcodec-dev libavformat-dev libswscale-dev python-dev
sudo apt-get install python-numpy libtbb2 libtbb-dev libjpeg-dev libpng-dev
sudo apt-get install libtiff-dev libjasper-dev libdc1394-22-dev doxygen
sudo apt-get install checkinstall

note: checkinstall, is used to make it easier for us to uninstall (IF we need)
the application compiled from source


2-Reset python interpreter
--------------------------
killall python


3- Download OpenCV 3.1.0 sources and unzip it
---------------------------------------------
url -> http://opencv.org/downloads.html


4- We create a folder for compiling
-----------------------------------
cd /path/to/your/opencv/
mkdir build
cd build/


5- Set JAVA_HOME if it's not set up already
-------------------------------------------
export JAVA_HOME=/path/to/your/jdk


6- Compile configuration
------------------------
The command is between [ and ] just to show where it starts and ends, but
DO NOT paste them

cmake -G "Unix Makefiles" -D CMAKE_BUILD_TYPE=Release -D CMAKE_INSTALL_PREFIX=/usr/local -D WITH_FFMPEG=OFF -D BUILD_SHARED_LIBS=OFF -D BUILD_EXAMPLES=OFF -D BUILD_TESTS=OFF -D BUILD_PERF_TESTS=OFF ..

Troubleshoot warning: T1

7- Compile
----------
sudo make
sudo checkinstall

If all goes well in the folder "/usr/local/share/OpenCV/java"
you should see 2 files: libopencv_java310.so and  opencv-310.jar


8- Install local .jar into maven
--------------------------------
mvn install:install-file -Dfile=/usr/local/share/OpenCV/java/opencv-310.jar -DgroupId=org.opencv -DartifactId=opencv-java -Dversion=3.1.0 -Dpackaging=jar


9- Add native library to system variable
----------------------------------------
sudo nano ~/.bashrc

** at the end of the file add the following line **
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/share/OpenCV/java


#####################
## TROUBLESHOOTING ##
#####################

T1 - Ubuntu 14.04 - Error with hashes
-------------------------------------
Download the following file:
https://raw.githubusercontent.com/Itseez/opencv_3rdparty/81a676001ca8075ada498583e4166079e5744668/ippicv/ippicv_linux_20151201.tgz
Paste at:
/path/to/your/opencv/3rdparty/ippicv/downloads/linux-808b791a6eac9ed78d32a7666804320e

Run cmake again.
