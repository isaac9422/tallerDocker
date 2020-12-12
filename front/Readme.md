For build the image execute
"docker build -t angular-cli:10.2.0 -f docker.file ."

For run the container executes
"docker run --rm --name angular-cli -p 4200:4200 -it --mount type=bind,source=C:\Users\IPALACIOS\Documents\Docker\tallerDocker\front,target=/home/angularProject isaac9422/angular-cli:10.2.0 /bin/sh"

In the container executes
"ng new app" for generate a new angular project
"ng serve --host 0.0.0.0" to run and to establish connection with browser host