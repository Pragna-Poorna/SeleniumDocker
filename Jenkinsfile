pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
               bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
               script {
                        app = docker.build('poorna89/poornadocker')
                       }
            }
        }
        stage('Push Image') {
            steps {
               script {
                      // registry url is blank for dockerhub
               docker.withRegistry('', 'docker-credentials') {
                              app.push("latest")
                                                             }
                      }
            }
                }
        // Additional stages
    }
}