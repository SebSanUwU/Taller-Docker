FROM openjdk:17

WORKDIR /usrapp/bin

EXPOSE 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.AppLbRoundRobinApplication"]