For run the container executes
"docker run --name java8 -p 8080:8080 -it --mount type=bind,source=C:\Users\IPALACIOS\Documents\Docker\tallerDocker\back\taller,target=/home/javaProject openjdk:8-jdk-alpine /bin/sh"

For run the deployment container executes
"docker run -d -p 8443:8443 --name javaapp --restart always javaapptest"