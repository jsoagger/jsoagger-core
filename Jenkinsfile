pipeline {
    agent any
    
    tools{
		maven 'maven 3'
		jdk 'java 11'
	}

    stages {
    
    	stage ("initialize") {
			steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
					'''
				}
		}
    
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean verify'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}