pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                echo '🔄 Cloning Repository...'
                checkout scm
            }
        }
        stage('Install Dependencies') {
            steps {
                echo '📦 Installing Maven Dependencies...'
                // بش نحطو PowerShell بدل sh
                powershell 'mvn clean install'
            }
        }
        stage('Run Cucumber Tests') {
            steps {
                echo '🚀 Running Tests...'
                powershell 'mvn test'
            }
        }
        stage('Generate Reports') {
            steps {
                echo '📊 Generating Reports...'
                powershell 'mvn site'
            }
        }
    }
    post {
        success {
            echo '✅ Pipeline completed successfully.'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}
