pipeline {
    agent any

    tools {
        maven 'Maven 3.x' 
        jdk 'Java 17' 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Unit & Integration Tests') {
            steps {
                bat 'mvn test -Dtest=!SeleniumTest'
            }
        }

        stage('UI Tests') {
            steps {
                // Ensure ChromeDriver is installed and available
                bat 'mvn test -Dtest=SeleniumTest'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
