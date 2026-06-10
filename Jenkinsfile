pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    environment {
        DEPLOY_DIR = 'C:\\deploy'
        APP_NAME   = 'employee-management-app.jar'
        APP_PORT   = '8085'
    }

    stages {

        stage('Clone Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/bhagwan-raut/employee-management-app.git'
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Stop Old App') {
            steps {
                bat '''
                    for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":8085"') do (
                        taskkill /PID %%a /F 2>nul
                    )
                    exit 0
                '''
            }
        }

        stage('Deploy JAR') {
            steps {
                bat 'if not exist %DEPLOY_DIR% mkdir %DEPLOY_DIR%'
                bat 'copy /Y target\\*.jar %DEPLOY_DIR%\\%APP_NAME%'
            }
        }

        stage('Start App') {
            steps {
                bat 'start /B java -jar %DEPLOY_DIR%\\%APP_NAME% > %DEPLOY_DIR%\\app.log 2>&1'
                echo 'App started at http://localhost:8085'
            }
        }
    }

    post {
        success {
            echo '🎉 Build and Deploy Successful!'
            echo 'Access at: http://localhost:8085/api/employees'
        }
        failure {
            echo '❌ Build Failed! Check Console Output.'
        }
    }
}