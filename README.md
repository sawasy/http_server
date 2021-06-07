# http_server
A dead simple Ruby web server.
Serves on port 80.
/healthcheck path returns "OK"
All other paths return "Well, hello there!"

`$ ruby webserver.rb`

# CI/CD
- install `jenkins`,`docker`,`minikube` on the same server
- install jenkins plugins: `Pipeline Utility Steps`,`Docker Pipeline`
- create credentials for checkout from github and for pushing images to dockerhub
- create jenkins pipeline job from main.groovy
- run job, it will create scm polling trigger and http-server deployment in kubernetes
- I edited `http_server.rb`, because you can't bind on 80 port as non-root user
