pipeline {
    agent any
    stages {
        stage ('Build Tasks Api') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployTasksApi -Dsonar.host.url=http://localhost:9000 -Dsonar.login=0efbef98ab4afa1f21d66454d14fed37d3010c14 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/RootController.java,**/TasksApiApplication.java,**/ValidacaoHandler.java,**/WebConfiguration.java,**/model/Task.java,**validation/Error.java"
                }
            }
        }
		/*
		 * Desabled because is not working with the jenkins version 2.204.4
        stage ('Quality Gate') {
            steps {
                sleep(5)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
		*/
        stage ('Deploy Api') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-api', war: 'target/tasks-api.war'
            }
        }		
    }
}


