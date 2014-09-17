1. Ubuntu 12.04
2. Opencv 2.4.9, check out the source and build.
	sudo apt-get install build-essential checkinstall cmake pkg-config yasm
    sudo apt-get install ant
    
    http://docs.opencv.org/doc/tutorials/introduction/desktop_java/java_dev_intro.html
    mkdir build
    cd build
    
    export JAVA_HOME=/usr/lib/jvm/java-6-oracle
    cmake -DBUILD_SHARED_LIBS=OFF ..
    
    if java module cannot be built, need check if JAVA jdk is installed in complete form. 
    
    sudo apt-get install openjdk-6-jdk
    
    Now start the build:

    make -j8
    
3.  inside Opencv/build/lib
4. copy the libopencv_java249.so to /usr/lib
5. install jboss 7.1.0 (a separate note)
6. copy the face-detection.war to the standalone/deployment/ folder.
7. sudo service jboss start
8. create folder /opt/id-images/
   This folder will be the image repository for the images uploaded by user. And processed image will also be saved in this folder.
9. copy the lbpcascade_frontalface.xml to /opt/id-images. It is the configuration file for face detection.