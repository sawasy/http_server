# http_server
A dead simple Ruby web server.
Serves on port 80.
/healthcheck path returns "OK"
All other paths return "Well, hello there!"

`$ ruby webserver.rb`


## infraestructure with terraform

Note: As this is should be tunned to minikube, dont have a shared backend in the cloud
Note2: This only defines the proyect and the namespace, as this proyect dont use other infra resources. Deployment is configured using helm

`$ terraform init`
`$ terraform apply`