============================================
To prepare for a production deployment:
============================================
1. modify application.properties for production release;
2. In command line: gradle clean build
3. copy the build/libs/war-file to production server.


=============================================
For dev.
=============================================
1. check out project from github
2. In eclipse, Project -> clean
3. Run gradle task, eclipseClasspath 