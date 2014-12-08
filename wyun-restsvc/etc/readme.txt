============================================
I. To prepare for a production release:
============================================
1. modify application.properties for production release;
   modify build.gradle: version =  'x.x.x' for a new version
2. In command line: gradle clean build
3. copy the build/libs/war-file to production server.
4. cd ~/wlsp
   ln -sf ../WYunRest-x.x.x.war WYunRest.war


=============================================
II. For dev.
=============================================
1. check out project from github
2. In eclipse, Project -> clean
3. Run gradle task, eclipseClasspath 


==============================================
III. For set up the service for Spring Boot
     service wyun_svc start to start server
     service wyun_svc stop to stop server
==============================================

1. copy the war file to /home/wlsp/wlsp on the server
2. copy the wyun_svc.sh to /home/wlsp/wlsp on the server
   wyun_svc.sh should be an excutable file, if not, "chmod +x wyun_svc.sh"
3.  cd /etc/init.d
    sudo ln -s /home/wlsp/wlsp/wyun_svc.sh wyun_svc
    sudo update-rc.d wyun_svc defaults
4. reboot. after reboot make sure wyun_svc is running by 'ps -ef | grep java'. If you see
   the service is running. It means the servic is set up correctly.
5. check /home/wlsp/wlsp/logs folder by tail -f spring.log
6.  mysql -uroot -p0ffs4t? wlsp

