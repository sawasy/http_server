node("master"){
    properties([pipelineTriggers([pollSCM('H/10 * * * *')])])
    stage("checkout"){
        sshagent(credentials: ["private-ssh-key"]){
            git url: "git@github.com:ratanovvv/http_server.git", branch: "main", credentialsId: "private-ssh-key"
        }
    }
    stage("artifact"){
        withDockerRegistry(registry: [credentialsId: 'dockerhub']){
            sh """docker build -t ratanovvv/http_server:${env.BUILD_NUMBER} .
            docker push ratanovvv/http_server:${env.BUILD_NUMBER}
            docker rmi ratanovvv/http_server:${env.BUILD_NUMBER}
            """
        }
    }
    stage("deploy"){
        withEnv(["KUBECONFIG=/etc/kubernetes/admin.conf"]){
            sh """sed -i "s|TAG_NAME|${env.BUILD_NUMBER}|g" *.yaml
            """
            findFiles(glob: "**/*.yaml").each{
                sh "kubectl apply -f ${it}"
            }
        }
    }
    stage("healthcheck"){
        timeout(60) {
            waitUntil {
                def r = sh script: 'curl -sL http://127.0.0.1:30000/healthcheck', returnStdout: true
                 return (r == "OK");
            }
        }
    }
}
