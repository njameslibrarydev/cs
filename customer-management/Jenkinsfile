pipeline {
    agent any    
    tools {
        maven 'maven'
        jdk 'jdk8'
    }
	stages {
        stage('Checkout') {
            steps {
            	checkout scm
        	}
        }

        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
        
        stage('Deploy Docker image') {
            
            steps {
                sh '''
					docker login -u "<Username>" -p "<password>"
                    docker build --no-cache -t customermanagement .
                    docker tag customermanagement:latest njamesapidev/customermanagement:latest 
                    docker push njamesapidev/customermanagement:latest
               		'''
            }
        }     

    }

}