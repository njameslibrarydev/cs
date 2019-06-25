node {
    withMaven(maven:'maven') {

        stage('Checkout') {
            checkout scm
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
            when { buildingTag() }
            steps {
                sh '''
					docker login -u "njamesapidev" -p "Test1234@"
                    docker build --no-cache -t customermanagement .
                    docker tag customermanagement:latest njamesapidev/customermanagement:${TAG_NAME}
                    docker push njamesapidev/customermanagement:${TAG_NAME}
					docker rmi $(docker images -f “dangling=true” -q)
               		'''
            }
        }     

    }

}