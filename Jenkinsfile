pipeline {
    agent any

    tools {
        maven 'Maven 3.x' 

    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit & Integration Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('UI Tests') {
            steps {
                // Ensure ChromeDriver is installed and available
                sh 'mvn test -Dtest=SeleniumTest'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
