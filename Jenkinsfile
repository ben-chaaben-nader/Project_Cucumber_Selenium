pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo '🔄 Cloning Repository...'
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],
                          userRemoteConfigs: [[url: 'https://github.com/ben-chaaben-nader/Project_Cucumber_Selenium.git']]
                ])
            }
        }

        stage('Install Dependencies') {
            steps {
                echo '📦 Installing Maven Dependencies...'
                sh 'mvn clean install'
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                echo '✅ Running Cucumber Tests...'
                sh 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                echo '📊 Generating Cucumber Reports...'
                sh 'mvn surefire-report:report'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline executed successfully!'
            archiveArtifacts artifacts: '**/target/*.html', allowEmptyArchive: true
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}
