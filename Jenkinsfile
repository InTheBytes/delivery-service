pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'Java JDK'
        dockerTool 'Docker'
    }
    stages {
        stage('Clean and Test target') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Test and Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Code Analysis: Sonarqube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Await Quality Gateway') {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    docker.build('deliveryservice')
                }
            }
        }
        stage('Push ECR') {
            steps {
                script {
                    docker.withRegistry('https://241465518750.dkr.ecr.us-east-2.amazonaws.com', 'ecr:us-east-2:aws-ecr-creds') {
                        docker.image('deliveryservice').push("${env.BUILD_NUMBER}")
                        docker.image('deliveryservice').push('latest')
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying cloudformation..'
                sh "aws cloudformation deploy --stack-name StackLunchDeliveryService --template-file ./ecs.yaml --parameter-overrides ApplicationName=DeliveryService ApplicationEnvironment=dev ECRRepositoryUri=241465518750.dkr.ecr.us-east-2.amazonaws.com/deliveryservice:latest --capabilities CAPABILITY_IAM CAPABILITY_NAMED_IAM --region us-east-2"
        	}
        }
    }
    post {
        always {
            sh 'mvn clean'
        }
    }
}