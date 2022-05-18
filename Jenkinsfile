pipeline {
    agent any

    tools {
        maven "3.8.4"
    }

    stages {
        stage('Build') {
            steps {
                git branch: 'main', credentialsId: '8c71ff9f-7bdd-4629-8382-24213a2ecab3', url: 'https://github.com/Kebikov95/Test-api-with-selenide-and-jenkins-docker-images.git'
            }
        }
        stage('Test') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean test"
            }
        }
        stage('Archive files') {
            steps {
                archiveArtifacts artifacts: "target/screenshots/**", followSymlinks: true
            }
        }
        stage('Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}
