pipeline {
    agent any

    environment {
        DOCKER_REPO = "zev2t/final-project-be"
        IMAGE_TAG   = "${BUILD_NUMBER}"
        CONTAINER   = "final-project-be"
        APP_PORT    = "8080"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ChinhToTruong/farm_management'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'final-project',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin

                    docker build -f docker/Dockerfile -t $DOCKER_REPO:$IMAGE_TAG .
                    docker tag $DOCKER_REPO:$IMAGE_TAG $DOCKER_REPO:latest

                    docker push $DOCKER_REPO:$IMAGE_TAG
                    docker push $DOCKER_REPO:latest
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker stop $CONTAINER || true
                docker rm $CONTAINER || true

                docker run -d \
                  --restart unless-stopped \
                  -p ${APP_PORT}:8080 \
                  --name $CONTAINER \
                  $DOCKER_REPO:latest
                '''
            }
        }
    }
}
